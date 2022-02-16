package co.com.tstech.mspa.service.api.controller;

import co.com.tstech.mspa.service.api.service.archives.ArchivesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping(value = "/api/static/files/")
@RequiredArgsConstructor
public class FilesResources {

    private final ArchivesServices archivesServices;

    @GetMapping(path = "/image/{fileHash}", produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("fileHash") String fileHash) throws IOException {
        return Files.readAllBytes(Paths.get(archivesServices.getPathByHash(fileHash)));
    }

}
