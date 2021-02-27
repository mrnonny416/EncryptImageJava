import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

public class Decrypt {
	// Properties
	File browseFile;
	String fileName;
	String decryptName;
	private static SecretKeySpec secretKey;
	private static byte[] key;

	// Constructed
	public Decrypt(File file, String fileName) throws Exception {
		browseFile = new File(file, fileName);
		this.fileName = fileName;
	}// End Encrypt(File,String)

	// Method
// ---------------------------Encrypt algorithm------------------------------------------------
	void decrypt(String secret) throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException,
			FileNotFoundException {
		setKey(secret);
		Cipher cipher = Cipher.getInstance("AES");
		try {
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		CipherInputStream fileIn = new CipherInputStream(new FileInputStream(new File(this.fileName)), cipher);
		FileOutputStream fileOut = new FileOutputStream(new File(decryptName));
		int fileInByte;
		try {
			while ((fileInByte = fileIn.read()) != -1) {
				fileOut.write(fileInByte);
			}
			fileIn.close();
			fileOut.close();
		} catch (IOException e) {
			try {
				fileOut.close();
				File f = new File(decryptName);
				f.delete();
			} catch (IOException e1) {
			}
		}

	}

// ----------------------------generate key-----------------------------------------
	void setKey(String myKey) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		MessageDigest sha = null;
		key = myKey.getBytes("UTF-8");
		sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16); // use only first 128 bit
		secretKey = new SecretKeySpec(key, "AES");
	}

// --------------------------Change name-------------------------------------------
	String changName() {
		String[] arrName = fileName.split("\\.", 3);
		decryptName = arrName[0] + "." + arrName[2];
		return decryptName;
	}// End ChangName()

//----------------------Delete File------------------------------------------------
}// end Main
