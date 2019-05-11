package com.testng1;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;

/**
 * @Desc:testng基础用法 
 * @author:zpp 
 * @time:2019年3月22日 下午10:57:00
 */
public class TestBase {
  @Test
  public void f1() {
	  System.out.println("Invest_Forward_Test.f1()");
  }
  
  @Test
  public void f2() {
	  System.out.println("Invest_Forward_Test.f2()");
  }
  @BeforeMethod
  public void beforeMethod() {
	  System.out.println("Invest_Forward_Test.beforeMethod()");
  }

  @AfterMethod
  public void afterMethod() {
	  System.out.println("Invest_Forward_Test.afterMethod()");
  }

  @BeforeClass
  public void beforeClass() {
	  System.out.println("Invest_Forward_Test.beforeClass()");
  }

  @AfterClass
  public void afterClass() {
	  System.out.println("Invest_Forward_Test.afterClass()");
  }

  @BeforeTest
  public void beforeTest() {
	  System.out.println("Invest_Forward_Test.beforeTest()");
  }

  @AfterTest
  public void afterTest() {
	  System.out.println("Invest_Forward_Test.afterTest()");
  }

  @BeforeSuite
  public void beforeSuite() {
	  System.out.println("Invest_Forward_Test.beforeSuite()");
  }

  @AfterSuite
  public void afterSuite() {
	  System.out.println("Invest_Forward_Test.afterSuite()");
  }
}
