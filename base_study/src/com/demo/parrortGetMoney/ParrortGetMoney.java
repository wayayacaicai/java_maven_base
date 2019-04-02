package com.demo.parrortGetMoney;

public class ParrortGetMoney {

	public static void main(String[] args) {
		Money m1 = new Money();
		m1.set(100, true, true);
		Parrort p1 = new Parrort();
		p1.set(1, "金刚鹦鹉", 2, "黄色");

		Money money1 = p1.ParrortGetMoney(m1);
		System.out.println(money1);

		Money m2 = new Money();
		m2.set(10, false, true);
		Parrort p2 = new Parrort();
		p2.set(2, "大头鹦鹉", 5, "白色");
		Money money2 = p2.ParrortGetMoney(m2);
		System.out.println(money2);
	}

}
