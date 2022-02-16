package co.com.tstech.mspa.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "spa_packages")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpaPackages {

    @Id
    @Column(name = "pac_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pacId;

    @Column(name = "pac_name", length = 50)
    private String pacName;

    @Column(name = "pac_value")
    private BigInteger pacValue;

    @Column(name = "pac_status", length = 1)
    private String pacStatus;

}
