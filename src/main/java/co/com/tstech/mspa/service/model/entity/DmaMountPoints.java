package co.com.tstech.mspa.service.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "dma_mount_points")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DmaMountPoints {

    @Id
    @Column(name = "mpo_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mpoId;

    @Column(name = "mpo_code", length = 20)
    private String mpoCode;

    @Column(name = "mpo_path", length = 500)
    private String mpoPath;

    @Column(name = "mpo_max_size")
    private BigInteger mpoMaxSize;

    @Column(name = "mpo_status", length = 1)
    private String mpoStatus;

}
