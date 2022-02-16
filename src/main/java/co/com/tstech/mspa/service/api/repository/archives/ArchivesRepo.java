package co.com.tstech.mspa.service.api.repository.archives;

import co.com.tstech.mspa.service.model.entity.DmaArchives;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArchivesRepo extends JpaRepository<DmaArchives, Integer> {

    @EntityGraph(value = "archive.mount_point")
    DmaArchives findByArcHash(String hash);

}
