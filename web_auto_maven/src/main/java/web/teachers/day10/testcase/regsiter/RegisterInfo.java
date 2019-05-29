package web.teachers.day10.testcase.regsiter;

import web.teachers.day10.pojo.ExcelSheetObject;

/**
 * 注册测试用例相关数据
 * @author happy
 * @date 2019年5月23日
 * @desc 
 * @email
 */
public class RegisterInfo extends ExcelSheetObject {

	//	手机号(MobilePhone)	
	private String mobilePhone;
	//	密码(Pwd)	
	private String pwd;
	//	确认密码(ConfirmPwd)	
	private String confirmPwd;
	//	验证码(Verifycode)	
	private String verifycode;
	//	期望结果(ExpectedResult)
	private String expectedResult;

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getConfirmPwd() {
		return confirmPwd;
	}

	public void setConfirmPwd(String confirmPwd) {
		this.confirmPwd = confirmPwd;
	}

	public String getVerifycode() {
		return verifycode;
	}

	public void setVerifycode(String verifycode) {
		this.verifycode = verifycode;
	}

	public String getExpectedResult() {
		return expectedResult;
	}

	public void setExpectedResult(String expectedResult) {
		this.expectedResult = expectedResult;
	}

	@Override
	public String toString() {
		return "RegisterInfo [mobilePhone=" + mobilePhone + ", pwd=" + pwd + ", confirmPwd=" + confirmPwd
				+ ", verifycode=" + verifycode + ", expectedResult=" + expectedResult + ", getRowNo()=" + getRowNo()
				+ "]";
	}

}
