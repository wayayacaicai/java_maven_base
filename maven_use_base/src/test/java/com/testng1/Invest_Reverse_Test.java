package com.testng1;

import org.junit.BeforeClass;
import org.testng.annotations.Test;

import com.base.licai.Invester;
import com.base.licai.Member;

/**
 * @Desc:investor反向用例 
 * @author:zpp 
 * @time:2019年3月22日 下午10:57:56
 */
public class Invest_Reverse_Test extends Member_Test {

	// 充值0
	@Test
	public void f3() {
		m.recharge(0);
	}

	// 充值99
	@Test
	public void f4() {
		m.recharge(99);
	}
}
