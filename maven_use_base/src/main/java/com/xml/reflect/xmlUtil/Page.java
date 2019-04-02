package com.xml.reflect.xmlUtil;

/**
 * @Desc:一层属性类
 * @author:zpp
 * @time:2019年3月22日 下午10:52:15
 */
public class Page {
	private String name;
	private String value;

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "Page [name=" + name + ", value=" + value + "]";
	}

}
