package model;

import lombok.Data;

import java.io.Serializable;

@Data
public class Address implements Serializable {

    private static final long serialVersionUID = -4344440530213008705L;

    private Integer id;
    private Integer number;
    private String street;
    private String zipcode;
    private Merchant merchant;

}
