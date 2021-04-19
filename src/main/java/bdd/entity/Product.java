package bdd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(generator = "product_id_generator")
    @SequenceGenerator(
            name = "product_id_generator",
            sequenceName = "product_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;
    @Column(name = "create_date")
    private Date createDate;
    @Column(name = "label", length = 500)
    private String label;
    @Column(name = "unit_price")
    private Integer unitPrice;
    @Column(name = "currency", length = 50)
    private String currency;
    @Column(name = "weight")
    private Integer weight;
    @Column(name = "height")
    private Integer height;
    @OneToMany(mappedBy="product")
    private Set<MerchantProduct> merchantProducts = new HashSet<>();
}
