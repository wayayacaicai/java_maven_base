package com.testng1;

import org.testng.annotations.Test;

import com.base.licai.Invester;

/**
 * @Desc:investor正向用例 
 * @author:zpp 
 * @time:2019年3月22日 下午10:58:22
 */
public class Invest_Forward_Test extends Member_Test {
	// 充值成功100
	@Test
	public void f1() {
		m.recharge(100);
	}

	// 充值成功1000
	@Test
	public void f2() {
		m.recharge(1000);
	}

}
