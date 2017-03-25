package com.qianjg.pojomenu;
/**
 * 整个 菜单对象的封装，菜单对象包含多个菜单项（最多只能有 3 个），这些菜单项即可以是子菜单项（不含二级菜单的一级菜单），也可以是父菜单项（包含二级菜单的菜单项）
 * @author Administrator
 *
 */
public class Menu {
	private Button[] button;

	/**
	 * @return the button
	 */
	public Button[] getButton() {
		return button;
	}

	/**
	 * @param button the button to set
	 */
	public void setButton(Button[] button) {
		this.button = button;
	}
	
	
}
