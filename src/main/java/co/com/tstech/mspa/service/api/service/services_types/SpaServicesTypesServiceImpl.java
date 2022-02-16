package co.com.tstech.mspa.service.api.service.services_types;

import co.com.tstech.mspa.service.api.repository.services_types.ServerTypesRepo;
import co.com.tstech.mspa.service.api.service.archives.ArchivesServices;
import co.com.tstech.mspa.service.model.dto.ServicesTypeRequestDTO;
import co.com.tstech.mspa.service.model.dto.ServicesTypeResponseDTO;
import co.com.tstech.mspa.service.model.entity.SpaServicesTypes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class SpaServicesTypesServiceImpl implements SpaServicesTypesServices {

    private final ServerTypesRepo serverTypesRepo;
    private final ArchivesServices archivesServices;

    @Override
    public Collection<ServicesTypeResponseDTO> getByStatus(String status) {
        Collection<SpaServicesTypes> servicesTypes = serverTypesRepo.findByStyStatus(status);
        return servicesTypes
                .stream()
                .map(it -> new ServicesTypeResponseDTO(it.getStyId(), it.getStyName(), archivesServices.getFileUri(it.getArcId())))
                .collect(Collectors.toList());
    }

    @Override
    public SpaServicesTypes create(ServicesTypeRequestDTO request) {
        SpaServicesTypes serviceType = getNewType(request);
        return serverTypesRepo.save(serviceType);
    }

    @Override
    public SpaServicesTypes update(ServicesTypeRequestDTO request) {
        SpaServicesTypes serviceType = serverTypesRepo.findById(request.getId()).get();
        serviceType.setArcId(archivesServices.createImageFromBase64(request.getImgBase64()));
        serviceType.setStyCode(request.getCode());
        serviceType.setStyName(request.getName());
        return serverTypesRepo.save(serviceType);
    }

    @Override
    public boolean delete(Integer id) {
        serverTypesRepo.deleteById(id);
        return Boolean.TRUE;
    }

    private SpaServicesTypes getNewType(ServicesTypeRequestDTO request) {
        SpaServicesTypes serviceType = new SpaServicesTypes();
        serviceType.setArcId(archivesServices.createImageFromBase64(request.getImgBase64()));
        serviceType.setStyCode(request.getCode());
        serviceType.setStyName(request.getName());
        serviceType.setStyStatus("A");
        return serviceType;
    }

}
