package bdd.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(generator = "address_id_generator")
    @SequenceGenerator(
            name = "address_id_generator",
            sequenceName = "address_id_seq",
            initialValue = 1,
            allocationSize = 1
    )
    @Column(name = "id")
    private Integer id;

    @Column(name = "number")
    private Integer number;

    @Column(name = "street", length = 500)
    private String street;

    @Column(name = "zipcode", length = 50)
    private String zipcode;
    @ManyToOne
    @JoinColumn(name ="merchant_id", nullable = false, foreignKey = @ForeignKey(name = "address_merchant_id_fkey"))
    private Merchant merchant;

}
