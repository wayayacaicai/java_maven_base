package web.base09.pojo;

/**
 * 
 * @author happy
 * @date 2019年5月25日
 * @desc 定位器
 * @email
 */
public class Locator {

	/**
	 * 元素的名称
	 */
	private String name;
	/**
	 * 元素的定位方式
	 */
	private String by;
	/**
	 * 元素定位的值
	 */
	private String value;
	/**
	 * 延时时间
	 */
	private String delay;
	/**
	 * 是否要点击
	 */
	private String clickable;
	/**
	 * 是否要显示
	 */
	private String displayed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getDelay() {
		return delay;
	}

	public void setDelay(String delay) {
		this.delay = delay;
	}

	public String getClickable() {
		return clickable;
	}

	public void setClickable(String clickable) {
		this.clickable = clickable;
	}

	public String getDisplayed() {
		return displayed;
	}

	public void setDisplayed(String displayed) {
		this.displayed = displayed;
	}

	@Override
	public String toString() {
		return "Locator [name=" + name + ", by=" + by + ", value=" + value + ", delay=" + delay + ", clickable="
				+ clickable + ", displayed=" + displayed + "]";
	}

	public Locator(String name, String by, String value, String delay, String clickable, String displayed) {
		super();
		this.name = name;
		this.by = by;
		this.value = value;
		this.delay = delay;
		this.clickable = clickable;
		this.displayed = displayed;
	}
	
	

}
