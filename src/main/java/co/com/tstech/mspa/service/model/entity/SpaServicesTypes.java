package co.com.tstech.mspa.service.model.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "spa_services_types")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@NamedEntityGraph(
        name = "service_type.archive",
        attributeNodes = @NamedAttributeNode(value = "arcId", subgraph = "mpoId"),
        subgraphs = @NamedSubgraph(name = "mpoId", attributeNodes = @NamedAttributeNode("mpoId")))
public class SpaServicesTypes {

    @Id
    @Column(name = "sty_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer styId;

    @JoinColumn(name = "arc_id", referencedColumnName = "arc_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmaArchives arcId;

    @Column(name = "sty_code", length = 10)
    private String styCode;

    @Column(name = "sty_name", length = 50)
    private String styName;

    @Column(name = "sty_status", length = 1)
    private String styStatus;

}
