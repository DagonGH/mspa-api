package co.com.tstech.mspa.service.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileInfoDTO {

    private byte[] bytes;
    private String name;
    private String extension;
    private long size;
    private String mimeType;

}
