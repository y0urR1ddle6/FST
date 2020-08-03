package factory.insite.kernel.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {
    public static String str2Sha256(String str){
        String hash = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(
                    str.getBytes(StandardCharsets.UTF_8));
            hash = bytesToHex(encodedHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }
    private static String bytesToHex(byte[] hash) {
        StringBuffer hexString = new StringBuffer();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
