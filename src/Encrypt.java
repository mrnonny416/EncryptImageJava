import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

public class Encrypt {
//Properties
	File browseFile;
	String fileName;
	String encryptName;
	private static SecretKeySpec secretKey;
	private static byte[] key;

// Constructed
	public Encrypt(File file, String fileName) throws Exception {
		browseFile = new File(file, fileName);
		this.fileName = fileName;
	}// End Encrypt(File,String)

// Method
//---------------------------Encrypt algorithm------------------------------------------------
	void encrypt(String secret)
			throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IOException {
		setKey(secret);
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		CipherInputStream fileIn = new CipherInputStream(new FileInputStream(new File(this.fileName)), cipher);
		FileOutputStream fileOut = new FileOutputStream(new File(encryptName));
		int fileInByte;
		while ((fileInByte = fileIn.read()) != -1) {
			fileOut.write(fileInByte);
		}
		JOptionPane.showMessageDialog(null, "Your Encrypt Correct","Encryption Sucessful",JOptionPane.PLAIN_MESSAGE);
		fileIn.close();
		fileOut.close();
	}

//----------------------------generate key-----------------------------------------	
	void setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest sha = null;
		key = myKey.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16); // use only first 128 bit
		secretKey = new SecretKeySpec(key, "AES");
	}

//--------------------------Change name-------------------------------------------
	String changName() {
		String[] arrName = fileName.split("\\.", 2);
		encryptName = arrName[0] + ".encrypt." + arrName[1];
		return encryptName;
	}// End ChangName()

}// end Main
