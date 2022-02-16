package co.com.tstech.mspa.service.api.service.archives;

import co.com.tstech.mspa.service.api.repository.archives.ArchivesRepo;
import co.com.tstech.mspa.service.api.repository.mounts_points.MountsPointsRepo;
import co.com.tstech.mspa.service.model.dto.FileInfoDTO;
import co.com.tstech.mspa.service.model.entity.DmaArchives;
import co.com.tstech.mspa.service.model.entity.DmaMountPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Calendar;

@Service
@Transactional
@RequiredArgsConstructor
public class ArchivesServicesImpl implements ArchivesServices {

    private final String FILE_SEPARATOR = System.getProperty("file.separator");

    private final ArchivesRepo archivesRepo;
    private final MountsPointsRepo mountsPointsRepo;

    @Override
    public DmaArchives create(FileInfoDTO fileInfoDTO) {
        DmaArchives archive = getArchive(fileInfoDTO);
        createWriteFile(archive, fileInfoDTO.getBytes());
        return archivesRepo.save(archive);
    }

    @Override
    public DmaArchives createImageFromBase64(String base64) {
        FileInfoDTO fileInfoDTO = new FileInfoDTO();
        byte[] file = Base64.getDecoder().decode(base64);
        fileInfoDTO.setBytes(file);
        fileInfoDTO.setName("image");
        fileInfoDTO.setExtension("png");
        fileInfoDTO.setMimeType("image/png");
        fileInfoDTO.setSize(fileInfoDTO.getSize());
        return create(fileInfoDTO);
    }

    @Override
    public String getFileUri(DmaArchives archive) {
        String path = "/api/static/files/image/" + archive.getArcHash();
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(path).toUriString();
    }

    @Override
    public String getPathByHash(String hash) {
        DmaArchives archive = archivesRepo.findByArcHash(hash);
        return archive.getMpoId().getMpoPath() + FILE_SEPARATOR + archive.getArcInternalPath() + archive.getArcHash() + "." + archive.getArcExtension();
    }

    private DmaArchives getArchive(FileInfoDTO fileInfoDTO) {
        DmaArchives archives = new DmaArchives();
        archives.setMpoId(getMainMountPoint());
        archives.setArcName(fileInfoDTO.getName());
        archives.setArcExtension(fileInfoDTO.getExtension());
        archives.setArcHash(getArchiveHash());
        archives.setArcMimeType(fileInfoDTO.getMimeType());
        archives.setArcDateUploaded(LocalDateTime.now());
        archives.setArcSize(BigInteger.valueOf(fileInfoDTO.getSize()));
        archives.setArcInternalPath(getInternalPath());
        return archives;
    }

    private DmaMountPoints getMainMountPoint() {
        return mountsPointsRepo.findByMpoCode("main");
    }

    private String getArchiveHash() {
        SecureRandom secureRandom = new SecureRandom();
        return new BigInteger(124, secureRandom).toString(10);
    }

    private String getInternalPath() {
        Calendar calendar = Calendar.getInstance();
        return (calendar.get(Calendar.YEAR)) + FILE_SEPARATOR +
                (calendar.get(Calendar.MONTH) + 1) + FILE_SEPARATOR +
                (calendar.get(Calendar.DATE)) + FILE_SEPARATOR +
                (calendar.get(Calendar.HOUR_OF_DAY)) + FILE_SEPARATOR;
    }

    private void createWriteFile(DmaArchives archive, byte[] content) {
        try {
            String pathName = archive.getMpoId().getMpoPath() + FILE_SEPARATOR + archive.getArcInternalPath();
            File containerFolder = new File(pathName);
            if (!containerFolder.exists()) {
                boolean b = containerFolder.mkdirs();
            }
            String fileName = pathName + FILE_SEPARATOR + archive.getArcHash() + "." + archive.getArcExtension();
            File destinationFile = new File(fileName);
            Files.write(Paths.get(destinationFile.getAbsolutePath()), content);
        } catch (IOException ie) {
            ie.printStackTrace();
        }
    }

}
