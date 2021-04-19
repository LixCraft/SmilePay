package model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Product implements Serializable {
    private static final long serialVersionUID = -3302280386880265560L;

    private Integer id;
    private Date createDate;
    private String label;
    private Integer unitPrice;
    private String currency;
    private Integer weight;
    private Integer height;
    
}
