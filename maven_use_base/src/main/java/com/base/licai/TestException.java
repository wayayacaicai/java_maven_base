package com.base.licai;

public class TestException {

	public static void main(String[] args) throws LoanException {
		Loan loan = new Loan();
		loan.setLoanRate(10);
		System.out.println(loan.getLoanRate());
		Loan loan1 = new Loan();
		loan1.setLoanRate(24.1);
		System.out.println(loan1.getLoanRate());
	}

}
