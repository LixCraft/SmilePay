package bdd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "merchant_product",
        uniqueConstraints = @UniqueConstraint(columnNames = "affiliation_id", name = "merchant_product_affiliation_id_key"))
public class MerchantProduct {

    /**
     * Format id is concatenation of "[merchant_id]_[product_id]"
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "merchant_product_id_generator")
    @SequenceGenerator(
            name = "merchant_product_id_generator",
            sequenceName = "merchant_product_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    private Integer id;
    @Column(name = "affiliation_id", nullable = false, length = 100)
    private String affiliationId;
    @ManyToOne
    @JoinColumn(name = "merchant_id", nullable = false, foreignKey = @ForeignKey(name = "merchant_product_merchant_id_fkey"))
    private Merchant merchant;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, foreignKey = @ForeignKey(name = "merchant_product_product_id_fkey"))
    private Product product;
    @Column(name = "affiliation_date")
    private Date affiliationDate;

}
