package com.qianjg.test;

import com.qianjg.faceservice.FaceService;

public class testFace {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String picUrl="http://www.faceplusplus.com.cn/wp-content/themes/faceplusplus/assets/img/demo/1.jpg?v=4";
		System.out.println(FaceService.detect(picUrl));	
	}

}
