package com.pavikumbhar.springmvc.store.model;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "purchaseitem")
public class PurchaseItem {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

	@Version
    @Column(name = "version")
	
    private Integer version;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Calendar orderDate;

    @ManyToOne(fetch=FetchType.EAGER)
    @JsonIgnore
    private Product product;

    @NotNull
    private int quantity;

    @ManyToOne
    @JsonIgnore
    private Purchase purchase;

	public Long getId() {
        return this.id;
    }

	public void setId(Long id) {
        this.id = id;
    }

	@JsonIgnore
	public Integer getVersion() {
        return this.version;
    }

	public void setVersion(Integer version) {
        this.version = version;
    }
	public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

	public Calendar getOrderDate() {
        return this.orderDate;
    }

	public void setOrderDate(Calendar orderDate) {
        this.orderDate = orderDate;
    }
	
	@JsonIgnore
	public Product getProduct() {
        return this.product;
    }

	public void setProduct(Product product) {
        this.product = product;
    }

	public int getQuantity() {
        return this.quantity;
    }

	public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
	@JsonIgnore
	public Purchase getPurchase() {
        return this.purchase;
    }

	public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }
}
