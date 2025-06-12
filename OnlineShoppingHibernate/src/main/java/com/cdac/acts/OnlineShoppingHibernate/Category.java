package com.cdac.acts.OnlineShoppingHibernate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedNativeQueries;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

	@Entity
	@Table(name="category")
	@NamedQueries({
		@NamedQuery(name = "getAllCategories",
				query = "Select OBJECT(ocat) from Category ocat"),
		@NamedQuery(name = "getCategoryById",
		query = "select OBJECT(ocat) from Category ocat where ocat.categoryId=:cid")
	})
	
	@NamedNativeQueries({
		@NamedNativeQuery(name="sqlAllCategories", 
				query = "select * from category")
	})
public class Category {
	@Id
	@Column(name="categoryId")
	@GeneratedValue
	private int categoryId;
	
	@Column(name = "categoryName")
	private String categoryName;
	
	@Column(name = "categoryDescription")
	private String categoryImageUrl;
	
	@Column(name = "categoryDescription")
	private Object categoryDescription;
	
	public Category() {}

	public Category(int categoryId, String categoryName, String categoryImageUrl, Object categoryDescription) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryImageUrl = categoryImageUrl;
		this.categoryDescription = categoryDescription;
		
	}

	@Override
	public String toString() {
		return "Category [categoryId=" + categoryId + ", categoryName=" + categoryName + ", categoryImageUrl="
				+ categoryImageUrl + ", categoryDescription=" + categoryDescription + "]";
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryImageUrl() {
		return categoryImageUrl;
	}

	public void setCategoryImageUrl(String categoryImageUrl) {
		this.categoryImageUrl = categoryImageUrl;
	}

	public Object getCategoryDescription() {
		return categoryDescription;
	}

	public void setCategoryDescription(Object categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
	
}
