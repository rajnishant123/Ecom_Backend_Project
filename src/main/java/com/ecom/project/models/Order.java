package com.ecom.project.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="e_com_Order")
public class Order {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int orderId;
	
	private String orderStatus;
	
	private Date orderCretaedAt;

	private String paymentStatus;
	
	private double orderAmount;
	
	private Date orderDeliveredDate;
	
	private String billingAddress;
	
	@OneToOne
	private User user;
	
	@OneToMany(mappedBy="order",cascade = CascadeType.ALL)
	private Set<OrderItem> items= new HashSet<>();
	
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Date getOrderCretaedAt() {
		return orderCretaedAt;
	}

	public void setOrderCretaedAt(Date orderCretaedAt) {
		this.orderCretaedAt = orderCretaedAt;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public Date getOrderDeliveredDate() {
		return orderDeliveredDate;
	}

	public void setOrderDeliveredDate(Date orderDeliveredDate) {
		this.orderDeliveredDate = orderDeliveredDate;
	}

	public String getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
	}

	
	
}
