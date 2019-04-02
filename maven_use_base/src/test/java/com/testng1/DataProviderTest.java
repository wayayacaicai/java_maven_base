package com.testng1;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.base.licai.Invester;
import com.base.licai.Member;

public class DataProviderTest {
	
	Member m = new Invester();
	
	@Test(dataProvider = "datas")
	public void test_case(double amount,String tempAmount){
		//取出预期的金额
		String[] split = tempAmount.split(",");
		
		double before = m.getLeaveAmount();
		m.recharge(amount);
		//实际
		double actual = m.getLeaveAmount();
		//预期
		double expected = before + Double.parseDouble(split[1]);
		Assert.assertEquals(actual, expected);
//		Assert.assertTrue(actual==expected);
	}
	

	@DataProvider
	public Object[][] datas() {
		return new Object[][] { 
			new Object[] { -1, "1,0" }, 
			new Object[] { 99, "2,0" },
			new Object[] { 0, "3,0" },
			new Object[] { 100,"4,100" },
			new Object[] { 101, "4,101" },
			new Object[] { 1000, "5,1001" },
			};
	}
}
