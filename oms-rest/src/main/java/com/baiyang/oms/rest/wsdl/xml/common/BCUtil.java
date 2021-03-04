package com.baiyang.oms.rest.wsdl.xml.common;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import java.io.FileInputStream;
import java.security.*;
import java.security.cert.Certificate;
import java.util.Enumeration;


public class BCUtil {
	private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(BCUtil.class);
	static {
		Security.addProvider(new BouncyCastleProvider());
	}
	private static final String KEY_ALGORITHM = "RSA";

	public static byte[] digestMD5(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(data);
		// System.out.println("md.getDigestLength():["+md.getDigestLength()+"]");
		return md.digest();
	}
	public static byte[] digestSHA256(byte[] data) throws Exception {
		MessageDigest md = MessageDigest.getInstance("SHA-256");
		md.update(data);
		// System.out.println("md.getDigestLength():["+md.getDigestLength()+"]");
		return md.digest();
	}

	/**
	 * 私钥解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param privateKey
	 *            私钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
		// 对数据解密
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥解密
	 * 
	 * @param data
	 *            待解密数据
	 * @param publicKey
	 *            公钥
	 * @return byte[] 解密数据
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
		// 对数据解密
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 私钥加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param publicKey
	 *            公钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, PrivateKey privateKey) throws Exception {
		// 对数据加密
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * 公钥加密
	 * 
	 * @param data
	 *            待加密数据
	 * @param publicKey
	 *            公钥
	 * @return byte[] 加密数据
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, PublicKey publicKey) throws Exception {
		// 对数据加密
		Cipher cipher = Cipher.getInstance(KEY_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * 
	 * @param privateKey
	 *            私钥
	 * @param data
	 *            待签名数据
	 * @throws Exception
	 */
	public static byte[] sign(PrivateKey privateKey, byte[] data) throws Exception {

		// 实例化Signature
		Signature signature = Signature.getInstance("SHA256withRSA", "BC");
		// 初始化Signature
		signature.initSign(privateKey);
		// 更新
		signature.update(data);
		// 获得签名，即字节数组sign
		byte[] sign = signature.sign();
		return sign;
	}

	/**
	 * 
	 * @param publicKey
	 *            公钥对象
	 * @param data
	 *            待验证数据
	 * @param sign
	 *            私钥得到的签名
	 * @return
	 * @throws Exception
	 */
	public static boolean signVerify(PublicKey publicKey, byte[] data, byte[] sign) throws Exception {
		// Security.addProvider(new BouncyCastleProvider());
		// 实例化Signature
		Signature signature = Signature.getInstance("SHA256withRSA");
		// 初始化Signature
		signature.initVerify(publicKey);
		// 更新
		signature.update(data);
		// 验证
		boolean status = signature.verify(sign);
		return status;
	}

	public static boolean flagShowCertificateInfo = false;

	public static PfxInfo getPfxInfo(String strPfxPath, String strPassword) {
		try {
			PfxInfo certificateInfo = new PfxInfo();
			// 实例化KeyStore对象
			KeyStore ks = KeyStore.getInstance("PKCS12");
			FileInputStream fis = new FileInputStream(strPfxPath);
			// If the keystore password is empty(""), then we have to set
			// to null, otherwise it won't work!!!
			char[] nPassword = null;
			if ((strPassword == null) || strPassword.trim().equals("")) {
				nPassword = null;
			} else {
				nPassword = strPassword.toCharArray();
			}
			// 加载密钥库,使用密码"password"
			ks.load(fis, nPassword);
			fis.close();
			// System.out.println("keystore type=" + ks.getType());
			// Now we loop all the aliases, we need the alias to get keys.
			// It seems that this value is the "Friendly name" field in the
			// detals tab <-- Certificate window <-- view <-- Certificate
			// Button <-- Content tab <-- Internet Options <-- Tools menu
			// In MS IE 6.
			// 列出此密钥库的所有别名
			Enumeration<String> enumas = ks.aliases();
			String keyAlias = null;
			if (enumas.hasMoreElements())// we are readin just one certificate.
			{
				keyAlias = (String) enumas.nextElement();
				if (flagShowCertificateInfo) {
					System.out.println("alias=[" + keyAlias + "]");
				}
				//
			}
			// Now once we know the alias, we could get the keys.
			if (flagShowCertificateInfo) {
				System.out.println("is key entry=" + ks.isKeyEntry(keyAlias));
				System.out.println("ks.getProvider().toString():[" + ks.getProvider().toString() + "]");
				System.out.println("ks.size():[" + ks.size() + "]");
			}
			// 获得别名为xxx所对应的私钥
			PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);

			// 返回与给定别名关联的证书
			Certificate cert = ks.getCertificate(keyAlias);
			PublicKey pubkey = cert.getPublicKey();
			if (flagShowCertificateInfo) {
				System.out.println("cert class = " + cert.getClass().getName());
				System.out.println("cert = " + cert);
				System.out.println("public key = " + pubkey);
				System.out.println("private key = " + prikey);
			}
			certificateInfo.setPrivateKey(prikey);
			certificateInfo.setPublicKey(pubkey);
			certificateInfo.setAlias(keyAlias);
			return certificateInfo;
		} catch (Exception e) {
			log.error("根据路径和密码获取证书信息出错,pfxPath:[" + strPfxPath + "]", e);
			throw new EdiBizException("根据路径和密码获取证书信息出错,pfxPath:[" + strPfxPath + "]",
					EdiBizException.LOAD_CERTIFICATE_IS_ERROR);
		}
	}
	
	

}
