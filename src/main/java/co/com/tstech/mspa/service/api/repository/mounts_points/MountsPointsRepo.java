package co.com.tstech.mspa.service.api.repository.mounts_points;

import co.com.tstech.mspa.service.model.entity.DmaMountPoints;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MountsPointsRepo extends JpaRepository<DmaMountPoints, Integer> {

    DmaMountPoints findByMpoCode(String code);

}
