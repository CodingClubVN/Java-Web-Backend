package com.se.codingclub.entity;

import javax.persistence.Table;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@Column(name = "total")
	private double total;
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "modified_date")
	private Date modifiedDate;

	@OneToMany(mappedBy = "order")
	private List<PaymentDetail> paymentDetails;

	@OneToMany(mappedBy = "order")
	private List<OrderDetail> orderDetails;
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

}
