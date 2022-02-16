package co.com.tstech.mspa.service.api.controller;

import co.com.tstech.mspa.service.api.service.services_types.SpaServicesTypesServices;
import co.com.tstech.mspa.service.model.dto.ResponseDTO;
import co.com.tstech.mspa.service.model.dto.ServicesTypeRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/services/")
@RequiredArgsConstructor
public class ServicesResources {

    private final SpaServicesTypesServices servicesTypesServices;

    @GetMapping(value = "/types/")
    public ResponseEntity<ResponseDTO> getServicesTypes() {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .reason("get services types success")
                        .data(Map.of("get_serv_types", servicesTypesServices.getByStatus("A")))
                        .build());
    }

    @PostMapping(value = "/types/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> saveType(@RequestBody ServicesTypeRequestDTO request) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.CREATED.value())
                        .status(HttpStatus.CREATED)
                        .reason("create services type success")
                        .data(Map.of("save_serv_types", servicesTypesServices.create(request)))
                        .build());
    }

    @PostMapping(value = "/types/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResponseDTO> updateType(@RequestBody ServicesTypeRequestDTO request) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .reason("update services type success")
                        .data(Map.of("update_serv_types", servicesTypesServices.update(request)))
                        .build());
    }

    @DeleteMapping(value = "/types/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteType(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(
                ResponseDTO.builder()
                        .timeStamp(LocalDateTime.now())
                        .statusCode(HttpStatus.OK.value())
                        .status(HttpStatus.OK)
                        .reason("delete services type success")
                        .data(Map.of("delete_serv_types", servicesTypesServices.delete(id)))
                        .build());
    }

}
