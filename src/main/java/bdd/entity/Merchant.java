package bdd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "merchant")
public class Merchant {

    @Id
    @GeneratedValue(generator = "merchant_id_generator")
    @SequenceGenerator(
            name = "merchant_id_generator",
            sequenceName = "merchant_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "name", nullable = false, length = 250)
    private String name;
    @Column(name = "lastname", nullable = false, length = 250)
    private String lastName;
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;
    @OneToMany(mappedBy="merchant", cascade = CascadeType.ALL)
    private Set<Address> addresses = new HashSet<>();
    @OneToMany(mappedBy="merchant", cascade = CascadeType.ALL)
    private Set<MerchantProduct> merchantProducts = new HashSet<>();

}
