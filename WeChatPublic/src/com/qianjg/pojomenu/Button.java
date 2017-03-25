package com.qianjg.pojomenu;

/**
 * 按钮的基类 所有一级菜单、二级菜单都共有一个相同的属性，那就是 name。
 * 
 * @author Administrator
 *
 */
public class Button {
	private String name;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
