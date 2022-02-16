package co.com.tstech.mspa.service.api.repository.services_types;

import co.com.tstech.mspa.service.model.entity.SpaServicesTypes;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface ServerTypesRepo extends JpaRepository<SpaServicesTypes, Integer> {

    @EntityGraph(value = "service_type.archive")
    Collection<SpaServicesTypes> findByStyStatus(String status);

}
