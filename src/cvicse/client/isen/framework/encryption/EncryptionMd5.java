package cvicse.client.isen.framework.encryption;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class EncryptionMd5 {
	
	 public static String Md5(String pwd){
        try
        {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] md5 = md.digest(pwd.getBytes());
            String refPassWord = new BigInteger(1, md5).toString(16).toUpperCase(java.util.Locale.getDefault());
            return refPassWord;
        }
        catch (NoSuchAlgorithmException e)
        {
            return null;
        }
    }
	 public static String Md5Twice(String pwd){
		 return Md5(Md5(pwd));
	 }
 
}
