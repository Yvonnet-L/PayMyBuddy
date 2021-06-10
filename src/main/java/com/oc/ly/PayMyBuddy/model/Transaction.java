package com.oc.ly.PayMyBuddy.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name="transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name= "id_transaction", nullable = false)
	private int idTransaction;
	
	@ManyToOne
	@JoinColumn(name = "payer", nullable = false)
	private User payer;
	
	@ManyToOne
	@JoinColumn(name= "beneficiary", nullable = false)
	private User beneficiary;
	
	@Column(name= "date", nullable = false)
	private LocalDate creationDate = LocalDate.now();
	
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


	public Transaction(int idTransaction, User payer, User beneficiary, LocalDate creationDate, double amount,
			String description) {
		super();
		this.idTransaction = idTransaction;
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.creationDate = creationDate;
		this.amount = amount;
		this.description = description;
	}



	public Transaction(User payer, User beneficiary, LocalDate createDate, double amount,
			String description, double fee ) {
		super();
		this.payer = payer;
		this.beneficiary = beneficiary;
		this.creationDate = createDate;
		this.amount = amount;
		this.description = description;
		this.fee = fee;
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




	public LocalDate getCreationDate() {
		return creationDate;
	}




	public void setCreationDate(LocalDate createDate) {
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




	@Override
	public String toString() {
		return "Transaction [idTransaction=" + idTransaction + ", payer=" + payer + ", beneficiary=" + beneficiary
				+ ", createDate=" + creationDate + ", amount=" + amount + ", description=" + description + "]";
	}
	
	
}
