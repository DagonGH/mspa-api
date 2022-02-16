package co.com.tstech.mspa.service.model.entity;

import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "spa_services")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpaServices {

    @Id
    @Column(name = "ser_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer serId;

    @JoinColumn(name = "sty_id", referencedColumnName = "sty_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SpaServicesTypes styId;

    @JoinColumn(name = "arc_id", referencedColumnName = "arc_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DmaArchives arcId;

    @Column(name = "ser_code", length = 10)
    private String serCode;

    @Column(name = "ser_name", length = 50)
    private String serName;

    @Column(name = "ser_description", length = 1000)
    private String serDescription;

    @Column(name = "ser_value", length = 1000)
    private BigInteger serValue;

    @Column(name = "ser_status", length = 1)
    private String serStatus;

}
