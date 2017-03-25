package com.qianjg.pojomenu;

/**
 * 普通按钮（子按钮） 不含子菜单
 * 
 * @author Administrator
 *         子菜单项的封装。这里对子菜单是这样定义的：没有子菜单的菜单项，有可能是二级菜单项，也有可能是不含二级菜单的一级菜单
 *         。这类子菜单项一定会包含三个属性：type、name 和 key
 */
public class CommonButton extends Button {
	private String type;
	private String key;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

}
