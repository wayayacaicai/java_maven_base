package com.xml.reflect.xmlUtil;

/**
 * @Desc:二层属性类
 * @author:zpp
 * @time:2019年3月22日 下午10:51:58
 */
public class Locator {
	private String by;
	private String value;
	private String desc;

	public Locator() {
		super();
	}

	public String getBy() {
		return by;
	}

	public void setBy(String by) {
		this.by = by;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "Locator [by=" + by + ", value=" + value + ", desc=" + desc + "]";
	}

}
