package com.testng1;

import org.testng.annotations.Test;

import javax.sound.midi.VoiceStatus;

import org.testng.annotations.DataProvider;

public class NoNormalMethod {
	
	@Test(dependsOnMethods={"f3"})
	public void f1(){
		System.out.println("NoNormalMethod.f1()");
	}
	@Test(enabled=false)
	public void f2(){
		System.out.println("NoNormalMethod.f2()");
	}
	@Test
	public void f3(){
		System.out.println("NoNormalMethod.f3()");
	}
	
	
	@Test(timeOut=2000)
	public void f4() throws InterruptedException{
		Thread.sleep(1000);
		System.out.println("NoNormalMethod.f4()");
	}
	
	@Test(expectedExceptions={ArithmeticException.class})
	public void f5() {
		int a=3,b=0;
		int c = a/b;
		
	}
	
	@Test(priority=2)
	public void f6(){
		System.out.println("NoNormalMethod.f6()");
	}
	
	
	@Test(priority=1)
	public void f7(){
		System.out.println("NoNormalMethod.f7()");
	}

}
