package co.com.tstech.mspa.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "spa_packages_services")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpaPackagesServices {

    @Id
    @Column(name = "pse_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pse_id;

    @JoinColumn(name = "pac_id", referencedColumnName = "pac_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SpaPackages pacId;

    @JoinColumn(name = "ser_id", referencedColumnName = "ser_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private SpaServices serId;

}
