package com.base.licai;

//投资者
public class Invester extends Member {
	public void invest(double amount) {
		// System.out.println("用户投资");
		// 1.投资金额大于等于100 2.必须是100的整数倍
		// 3.余额需要减去投资金额 4.投资金额必须小于等于余额
		if (amount < 100) {
			System.out.println("投资失败：投资金额不能小于100！");
		} else if (amount % 100 != 0) {
			System.out.println("投资失败：投资金额必须是100的整数倍!");
		} else if (this.getLeaveAmount() < amount) {
			System.out.println("投资失败：投资金额必须小于所剩余额!");
		} else {
			this.setLeaveAmount(this.getLeaveAmount() - amount);
		}
	}

	// 充值
	public boolean recharge(double amount) {
		// if (amount < 100) {
		// System.out.println("1:充值失败：单笔最低充值100元！");
		// return false;
		// } else {
		// System.out.println("1：会员充值成功!充值了" +amount+"元，你可以去投资了");
		// this.setLeaveAmount(this.getLeaveAmount()+amount);
		// return true;
		// }

		boolean result = super.recharge(amount);
		if (result) {
			System.out.println("2：投资人充值成功!充值了" + amount + "元，你可以去投资了");
		}
		return result;
	}

	@Override
	public void logout() {
		System.out.println("投资人退出了系统");
	}
}
