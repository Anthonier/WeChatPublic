package com.qianjg.util;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
/**
 * 证书信任管理器（适用于https请求	）
 * 证书管理器可以信任指定的证书，也意味着信任所有的证书，不管是否是权威机构颁发
 * @author Administrator
 *
 */
public class MyX509TrustManager implements javax.net.ssl.X509TrustManager {

	@Override
	public void checkClientTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkServerTrusted(X509Certificate[] chain, String authType)
			throws CertificateException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public X509Certificate[] getAcceptedIssuers() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
