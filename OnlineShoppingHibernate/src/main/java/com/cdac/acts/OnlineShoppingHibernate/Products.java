package com.cdac.acts.OnlineShoppingHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="products")
public class Products {
    @EmbeddedId
    private Products productId;
    
    @Column(name="productName")
    private String productName;
    
    @Column(name="productDescription")
    private String productDescription;
    
    @Column(name="productImageUrl")
    private String productImageUrl;
    
    @Column(name="productPrice")
    private float productPrice;


	public Products() {
	}
	
	public Products(Products productId, String productName, String productDescription, String productImageUrl, float productPrice) {
		this.productId = productId;
	}
}
	

