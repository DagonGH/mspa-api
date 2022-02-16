package co.com.tstech.mspa.service.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Table(name = "dma_archives")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(name = "archive.mount_point", attributeNodes = @NamedAttributeNode("mpoId"))
public class DmaArchives {

    @Id
    @Column(name = "arc_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer arcId;

    @JoinColumn(name = "mpo_id", referencedColumnName = "mpo_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private DmaMountPoints mpoId;

    @Column(name = "arc_name", length = 100)
    private String arcName;

    @Column(name = "arc_extension", length = 10)
    private String arcExtension;

    @Column(name = "arc_hash", length = 500)
    private String arcHash;

    @Column(name = "arc_mime_type", length = 100)
    private String arcMimeType;

    @Column(name = "arc_size")
    private BigInteger arcSize;

    @Column(name = "arc_internal_path", length = 500)
    private String arcInternalPath;

    @Column(name = "arc_date_uploaded")
    private LocalDateTime arcDateUploaded;

}
