package model;

import java.io.Serializable;

public class Adress implements Serializable {

    private static final long serialVersionUID = -4344440530213008705L;

    private Integer id;
    private Integer number;
    private String street;
    private String zipcode;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
    
    
    
}
