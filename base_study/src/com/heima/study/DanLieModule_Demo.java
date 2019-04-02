package com.heima.study;

public class DanLieModule_Demo {

	public static void main(String[] args) {
		// SingleModule sm1 = SingleModule.getInstance();
		// SingleModule sm2 = SingleModule.getInstance();
		// System.out.println(sm1 == sm2);

		SingleModule sm1 = SingleModule.sm;
		SingleModule sm2 = SingleModule.sm;
		System.out.println(sm1 == sm2);
	}

}

class SingleModule {
	private SingleModule() {

	}

	public static final SingleModule sm = new SingleModule();
	// ����ʽ
	// private static SingleModule sm = new SingleModule();
	//
	// public static SingleModule getInstance(){
	// return sm;
	// }

	// //����ʽ
	// private static SingleModule sm;
	// public static SingleModule getInstance(){
	// if(sm == null){
	// sm = new SingleModule();
	// }
	// return sm;
	// }
}
