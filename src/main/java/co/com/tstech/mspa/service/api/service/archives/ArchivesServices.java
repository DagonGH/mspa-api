package co.com.tstech.mspa.service.api.service.archives;

import co.com.tstech.mspa.service.model.dto.FileInfoDTO;
import co.com.tstech.mspa.service.model.entity.DmaArchives;

public interface ArchivesServices {

    DmaArchives create(FileInfoDTO fileInfoDTO);

    DmaArchives createImageFromBase64(String base64);

    String getFileUri(DmaArchives archive);

    String getPathByHash(String hash);

}
