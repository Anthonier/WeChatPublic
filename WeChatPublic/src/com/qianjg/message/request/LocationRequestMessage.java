package com.qianjg.message.request;

/**
 * 请求消息之地理位置消息
 * 
 * @author Administrator
 *
 */
public class LocationRequestMessage extends BaseRequestMessage {

	// 地理位置纬度
	private String Location_X;
	// 地理位置精度
	private String Location_Y;
	// 地图缩放大小
	private String Scale;
	// 地理位置信息
	private String Label;

	/**
	 * @return the location_X
	 */
	public String getLocation_X() {
		return Location_X;
	}

	/**
	 * @param location_X
	 *            the location_X to set
	 */
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}

	/**
	 * @return the location_Y
	 */
	public String getLocation_Y() {
		return Location_Y;
	}

	/**
	 * @param location_Y
	 *            the location_Y to set
	 */
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}

	/**
	 * @return the scale
	 */
	public String getScale() {
		return Scale;
	}

	/**
	 * @param scale
	 *            the scale to set
	 */
	public void setScale(String scale) {
		Scale = scale;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return Label;
	}

	/**
	 * @param label
	 *            the label to set
	 */
	public void setLabel(String label) {
		Label = label;
	}

}
