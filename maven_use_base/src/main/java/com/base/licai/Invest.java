package com.base.licai;

import java.sql.Date;

/*
 * 投资记录类
 * */
public class Invest {
	// 投资id
	private int id;
	// 投资人id
	private int memberId;
	// 项目id
	private int loanId;
	// 投资金额
	private int amount;
	// 投资日期
	private Date investDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMemberId() {
		return memberId;
	}

	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}

	public int getLoanId() {
		return loanId;
	}

	public void setLoanId(int loanId) {
		this.loanId = loanId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getInvestDate() {
		return investDate;
	}

	public void setInvestDate(Date investDate) {
		this.investDate = investDate;
	}

}
