package com.baiyang.oms.rest.wsdl.xml.common;

import java.security.PrivateKey;
import java.security.PublicKey;

public class PfxInfo{
	private PrivateKey privateKey = null;
	private PublicKey publicKey = null;
	private String alias = null;//别名，证书id
	public PrivateKey getPrivateKey() {
		return privateKey;
	}
	public void setPrivateKey(PrivateKey privateKey) {
		this.privateKey = privateKey;
	}
	public PublicKey getPublicKey() {
		return publicKey;
	}
	public void setPublicKey(PublicKey publicKey) {
		this.publicKey = publicKey;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	@Override
	public String toString() {
		return "PfxInfo [alias=" + alias + "]";
	}
	
	
}
