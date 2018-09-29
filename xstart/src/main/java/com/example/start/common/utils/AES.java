package com.example.start.common.utils;

import java.security.*;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

public class AES {
	/** 
     * 密钥算法 
     */  
    private static final String ALGORITHM = "AES";  
    /** 
     * 加解密算法/工作模式/填充方式 
     */  
    private static final String ALGORITHM_MODE_PADDING = "AES/ECB/PKCS7Padding";  
	
	static{
		if (Security.getProvider(BouncyCastleProvider.PROVIDER_NAME) == null){
			Security.addProvider(new BouncyCastleProvider());
		}
	}
    /**
     * AES解密
     * @param content 密文
     * @return
     * @throws InvalidAlgorithmParameterException
     * @throws NoSuchProviderException
     */
    public static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) throws InvalidAlgorithmParameterException {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            Key sKeySpec = new SecretKeySpec(keyByte, "AES");

            cipher.init(Cipher.DECRYPT_MODE, sKeySpec, generateIV(ivByte));// 初始化
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    /**
    * AES解密   
    * @param content 密文
    * @param keyByte key
    * @return
    * @throws InvalidAlgorithmParameterException
    */
   public static String decrypt(byte[] content, byte[] keyByte) throws InvalidAlgorithmParameterException {
       try {    
           Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);    
           Key sKeySpec = new SecretKeySpec(keyByte, ALGORITHM);
           cipher.init(Cipher.DECRYPT_MODE, sKeySpec);
           return new String(cipher.doFinal(content),"utf-8");    
       } catch (NoSuchAlgorithmException e) {    
           e.printStackTrace();      
       } catch (NoSuchPaddingException e) {    
           e.printStackTrace();      
       } catch (InvalidKeyException e) {    
           e.printStackTrace();    
       } catch (IllegalBlockSizeException e) {    
           e.printStackTrace();    
       } catch (BadPaddingException e) {    
           e.printStackTrace();    
       }catch (Exception e) {    
           e.printStackTrace();    
       }    
       return null;    
   }
    //生成iv
    public static AlgorithmParameters generateIV(byte[] iv) throws Exception{
        AlgorithmParameters params = AlgorithmParameters.getInstance("AES");
        params.init(new IvParameterSpec(iv));
        return params;
    }
    /**
    * AES加密  
    * @param content 加密内容
    * @param keyByte key
    * @return
    * @throws Exception
    */
   public static byte[] encryptData(String content,byte[] keyByte) throws Exception {  
       Cipher cipher = Cipher.getInstance(ALGORITHM_MODE_PADDING);  
       Key sKeySpec = new SecretKeySpec(keyByte, ALGORITHM);
       cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);  
       return cipher.doFinal(content.getBytes());  
   }
   /*public static void main(String[] args) throws Exception {
	   String machId = "1485607242";
	   String content="<root>"
			    +"<out_refund_no><![CDATA[G18092817040034]]></out_refund_no>"
				+"<out_trade_no><![CDATA[G1809281555048402]]></out_trade_no>"
				+"<refund_account><![CDATA[REFUND_SOURCE_RECHARGE_FUNDS]]></refund_account>"
				+"<refund_fee><![CDATA[100]]></refund_fee>"
				+"<refund_id><![CDATA[50000108142018092806545400059]]></refund_id>"
				+"<refund_recv_accout><![CDATA[支付用户零钱]]></refund_recv_accout>"
				+"<refund_request_source><![CDATA[API]]></refund_request_source>"
				+"<refund_status><![CDATA[SUCCESS]]></refund_status>"
				+"<settlement_refund_fee><![CDATA[100]]></settlement_refund_fee>"
				+"<settlement_total_fee><![CDATA[100]]></settlement_total_fee>"
				+"<success_time><![CDATA[2018-09-28 17:09:29]]></success_time>"
				+"<total_fee><![CDATA[100]]></total_fee>"
				+"<transaction_id><![CDATA[4200000204201809285131327825]]></transaction_id>"
				+"</root>";
	   byte[] keyByte = MD5.encrypt(machId).getBytes();
	   String encryptData = Base64.encode(AES.encryptData(content, keyByte));
	   System.out.println(encryptData);
	   String decrypt = AES.decrypt(Base64.decode(encryptData), keyByte);
	   System.out.println(decrypt);
   }*/
}  