package com.qianjg.pojoface;
/**
 * Face Model
 * @author Administrator
 *
 */
public class Face implements Comparable<Face>{
	//被检测出的每一张人脸都在Face++系统中的标识符
	private String faceId;
	//年龄估计值
	private int ageValue;
	//年龄估计值的正负区间
	private int ageRange;
	//性别：Male/Female
	private String genderValue;
	//性别分析的可信度
	private double genderConfidence;
	//人种：Asian/White/Black
	private String raceValue;
	//人种分析的可信度
	private double raceConfidence;
	//微笑程度
	private double smilingValue;
	//人脸框的中心点坐标
	private double centerX;
	private double centerY;
	
	
	
	
	/**
	 * @return the faceId
	 */
	public String getFaceId() {
		return faceId;
	}




	/**
	 * @param faceId the faceId to set
	 */
	public void setFaceId(String faceId) {
		this.faceId = faceId;
	}




	/**
	 * @return the ageValue
	 */
	public int getAgeValue() {
		return ageValue;
	}




	/**
	 * @param ageValue the ageValue to set
	 */
	public void setAgeValue(int ageValue) {
		this.ageValue = ageValue;
	}




	/**
	 * @return the ageRange
	 */
	public int getAgeRange() {
		return ageRange;
	}




	/**
	 * @param ageRange the ageRange to set
	 */
	public void setAgeRange(int ageRange) {
		this.ageRange = ageRange;
	}




	/**
	 * @return the genderValue
	 */
	public String getGenderValue() {
		return genderValue;
	}




	/**
	 * @param genderValue the genderValue to set
	 */
	public void setGenderValue(String genderValue) {
		this.genderValue = genderValue;
	}




	/**
	 * @return the genderConfidence
	 */
	public double getGenderConfidence() {
		return genderConfidence;
	}




	/**
	 * @param genderConfidence the genderConfidence to set
	 */
	public void setGenderConfidence(double genderConfidence) {
		this.genderConfidence = genderConfidence;
	}




	/**
	 * @return the raceValue
	 */
	public String getRaceValue() {
		return raceValue;
	}




	/**
	 * @param raceValue the raceValue to set
	 */
	public void setRaceValue(String raceValue) {
		this.raceValue = raceValue;
	}




	/**
	 * @return the raceConfidence
	 */
	public double getRaceConfidence() {
		return raceConfidence;
	}




	/**
	 * @param raceConfidence the raceConfidence to set
	 */
	public void setRaceConfidence(double raceConfidence) {
		this.raceConfidence = raceConfidence;
	}




	/**
	 * @return the smilingValue
	 */
	public double getSmilingValue() {
		return smilingValue;
	}




	/**
	 * @param smilingValue the smilingValue to set
	 */
	public void setSmilingValue(double smilingValue) {
		this.smilingValue = smilingValue;
	}




	/**
	 * @return the centerX
	 */
	public double getCenterX() {
		return centerX;
	}




	/**
	 * @param centerX the centerX to set
	 */
	public void setCenterX(double centerX) {
		this.centerX = centerX;
	}




	/**
	 * @return the centerY
	 */
	public double getCenterY() {
		return centerY;
	}




	/**
	 * @param centerY the centerY to set
	 */
	public void setCenterY(double centerY) {
		this.centerY = centerY;
	}

	//根据人脸中心坐标从左至右排序
	@Override
	public int compareTo(Face face) {
		// TODO Auto-generated method stub
		int result=0;
		if(this.getCenterX()>face.getCenterX())
			result=1;
		else 
			result=-1;
		return result;
	}
	
}
