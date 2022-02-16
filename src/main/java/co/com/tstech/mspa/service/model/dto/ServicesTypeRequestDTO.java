package co.com.tstech.mspa.service.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ServicesTypeRequestDTO {

    private Integer id;
    private String name;
    private String code;
    private String imgBase64;

}
