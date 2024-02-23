package org.silverpeas.security.aes;

import org.apache.commons.codec.binary.Hex;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.concurrent.Callable;

/**
 * A decryptor AES of a given encrypted text encoded in base64 by using a specified AES key in
 * hexadecimal.
 * @author mmoquillon
 */
@Command(name = "decrypt",
    description = "Decrypt the specified encrypted text by using the given AES key")
public class AESTextDecryptor implements Callable<String> {

  @Option(names={"-k", "--key"}, required = true,
      description = "An AES key in hexadecimal with which the text will be decrypted")
  private String key;

  @Option(names={"-t", "--text"}, required = true,
      description = "The encrypted text in base64 to decrypt with the AES key")
  private String text;

  @Override
  public String call() throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(Hex.decodeHex(key), "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    byte[] encryptedData = Base64.getDecoder().decode(text);
    byte[][] extractedData = extractEncryptionData(encryptedData, cipher.getBlockSize());
    byte[] cipherText = extractedData[0];
    byte[] iv = extractedData[1];
    cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(iv));
    byte[] decryptedData = cipher.doFinal(cipherText);
    return new String(decryptedData, StandardCharsets.UTF_8);
  }

  private static byte[][] extractEncryptionData(byte[] encryptedText, int blockSize) {
    byte[][] data = new byte[2][];
    if (encryptedText!= null) {
      int dataLength = encryptedText.length - blockSize;
      data[0] = new byte[dataLength];
      data[1] = new byte[blockSize];
      System.arraycopy(encryptedText, 0, data[1], 0, blockSize);
      System.arraycopy(encryptedText, blockSize, data[0], 0, dataLength);
      return data;
    } else {
      return data;
    }
  }
}
  