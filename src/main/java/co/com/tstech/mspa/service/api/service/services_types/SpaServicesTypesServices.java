package co.com.tstech.mspa.service.api.service.services_types;

import co.com.tstech.mspa.service.model.dto.ServicesTypeRequestDTO;
import co.com.tstech.mspa.service.model.dto.ServicesTypeResponseDTO;
import co.com.tstech.mspa.service.model.entity.SpaServicesTypes;

import java.util.Collection;

public interface SpaServicesTypesServices {

    Collection<ServicesTypeResponseDTO> getByStatus(String status);

    SpaServicesTypes create(ServicesTypeRequestDTO request);

    SpaServicesTypes update(ServicesTypeRequestDTO request);

    boolean delete(Integer id);

}
