package model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Merchant implements Serializable {
    private static final long serialVersionUID = -2114552085295185168L;

    private Integer id;
    private Date createDate;
    private String name;
    private String lastName;
    private Date birthdate;
}
