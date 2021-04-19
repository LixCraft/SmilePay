package model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class MerchantProduct implements Serializable {

    private static final long serialVersionUID = 5965602386219145944L;

    private Integer id;
    private String affiliationId;
    private Merchant merchant;
    private Product product;
    private Date affiliationDate;
}
