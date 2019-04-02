package com.base.licai;

import org.apache.log4j.Logger;

/*
 * 借款记录类
 * */
public class Loan {
	private static Logger myLogger = Logger.getLogger(TestException.class);

	private int id;

	private String title;

	private int loanTerm; // 借款周期：15 6

	private int loanDateType;// 借款周期的类型1：天标 2：月标

	private double loanRate;// 年化收益

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	public int getLoanDateType() {
		return loanDateType;
	}

	public void setLoanDateType(int loanDateType) {
		this.loanDateType = loanDateType;
	}

	public double getLoanRate() {
		return loanRate;
	}

	public void setLoanRate(double loanRate) throws LoanException {
		if (loanRate > 24) {
			myLogger.error("汇率不合法异常");
			throw new LoanException("汇率不合法");
		}
		this.loanRate = loanRate;
	}

}
