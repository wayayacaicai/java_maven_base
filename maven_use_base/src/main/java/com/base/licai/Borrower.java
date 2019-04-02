package com.base.licai;

/*
 * 借款人*/
public class Borrower extends Member {

	// 扩展新的属性
	// 详细的地址
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// 借款（自己扩展）
	public void borrow(double amount) {
		System.out.println("用户借款");
	}

	// 充值
	public boolean recharge(double amount) {
		// if (amount < 100) {
		// System.out.println("1:充值失败：单笔最低充值100元！");
		// return false;
		// } else {
		// System.out.println("1：会员充值成功!充值了" +amount+"元，你可以还钱了");
		// this.setLeaveAmount(this.getLeaveAmount()+amount);
		// return true;
		// }
		// }

		boolean result = super.recharge(amount);
		if (result) {
			System.out.println("3：借款人充值成功!充值了" + amount + "元，你可以借款了");
		}
		return result;
	}

	@Override
	public void logout() {
		System.out.println("借款人退出了系统");
	}
}
