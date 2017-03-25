package com.qianjg.event;
/**
 * 上报地理位置事件
 * @author Administrator
 *
 */
public class LocationEvent extends BaseEvent {
	//地理位置纬度
	private String Latitude;
	//地理位置经度
	private String Longitude;
	//地理位置精读
	private String Precision;
	/**
	 * @return the latitude
	 */
	public String getLatitude() {
		return Latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public String getLongitude() {
		return Longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return Precision;
	}
	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		Precision = precision;
	}
	
	
}
