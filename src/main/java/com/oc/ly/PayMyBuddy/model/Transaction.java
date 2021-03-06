package com.oc.ly.PayMyBuddy.model;

import com.oc.ly.PayMyBuddy.exceptions.DataNotFoundException;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name= "id_transaction", nullable = false)
	private int idTransaction;
	
	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = false)
	private User payer;
	
	@ManyToOne
	@JoinColumn(name= "beneficiary_id", nullable = false)
	private User beneficiary;
	
	@Column(name= "date", nullable = false)
	private LocalDateTime creationDate = LocalDateTime.now();
	
	@Column(name= "amount", nullable = false)
	private double amount;
	
	@Column(name= "description", length = 200,nullable = false)
	private String description;

	@Column(name= "fee")
	private double fee;

	
	//--------------------------------------------------------------------------------------------
	
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	//--------------------------------------------------------------------------------------------
	public int getIdTransaction() {
		return idTransaction;
	}

	public void setIdTransaction(int idTransaction) {
		this.idTransaction = idTransaction;
	}

	public User getPayer() {
		return payer;
	}

	public void setPayer(User payer) {
		this.payer = payer;
	}

	public User getBeneficiary() {
		return beneficiary;
	}

	public void setBeneficiary(User beneficiary) {
		this.beneficiary = beneficiary;
	}

	public LocalDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDateTime createDate) {
		this.creationDate = createDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFee() {
		return fee;
	}

	public void setFee(double fee) {
		this.fee = fee;
	}

	
}
