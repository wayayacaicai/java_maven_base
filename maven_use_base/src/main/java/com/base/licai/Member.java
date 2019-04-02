package com.base.licai;

/*行为：（方法）:注册、登录、注销、借款、投资
1：充值
单笔最低充值100元 最高充值限额以个人银行限额为准
充值隐藏的逻辑--如果充值成功：用户的余额要加上充值的金额*/
public abstract class Member {
	private int id;

	private String phone;

	private String password;

	private double leaveAmount;

	private String idCard;

	public Member() {
		super();
	}

	public Member(int id, String phone, String password, double leaveAmount, String idCard) {
		super();
		this.id = id;
		this.phone = phone;
		this.password = password;
		this.leaveAmount = leaveAmount;
		this.idCard = idCard;
	}

	public abstract void logout();

	public boolean register(String phone, String password) {
		return true;
	}

	public boolean login(String phone, String password) {
		if (phone.length() == 11 && password.length() == 6) {
			System.out.println("登录成功");
			return true;
		} else {
			System.out.println("账号信息错误！！");
			return false;
		}
	}

	public boolean withdraw(double amount) {
		if (amount < 100) {
			System.out.println("提现失败：最低提现金额100元！");
		} else if (amount > 500000) {
			System.out.println("提现失败：单笔提现金额最高50万元！");
		} else if (amount > this.leaveAmount) {
			System.out.println("提现失败：余额不足！");
		} else {
			// 可用余额
			this.leaveAmount -= amount;
			System.out.println("提现成功");
			return true;
		}
		return false;
	}

	public boolean recharge(double amount) {
		if (amount < 100) {
			System.out.println("1:充值失败：单笔最低充值100元！");
			return false;
		} else {
			System.out.println("1：会员充值成功!充值了" + amount + "元");
			this.leaveAmount += amount;
			return true;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		if (phone.length() == 11) {
			this.phone = phone;
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getLeaveAmount() {
		return leaveAmount;
	}

	public void setLeaveAmount(double leaveAmount) {
		this.leaveAmount = leaveAmount;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
}
