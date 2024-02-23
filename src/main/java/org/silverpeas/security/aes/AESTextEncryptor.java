package org.silverpeas.security.aes;

import org.apache.commons.codec.binary.Hex;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.util.Base64;
import java.util.concurrent.Callable;

/**
 * An encryptor AES of a given texts by using a specified AES key in hexadecimal. The result of the
 * encryption is returned encoded in base64.
 * @author mmoquillon
 */
@Command(name = "encrypt",
    description = "Encrypt in AES the specified text by using the given AES key")
public class AESTextEncryptor implements Callable<String> {

  @Option(names={"-k", "--key"}, required = true,
      description = "An AES key in hexadecimal with which the text will be encrypted")
  private String key;

  @Option(names={"-t", "--text"}, required = true,
      description =
          """
          The text to encrypt in AES with the AES key. The encrypted text will be encored in base64
          """)
  private String text;

  @Override
  public String call() throws Exception {
    SecretKeySpec keySpec = new SecretKeySpec(Hex.decodeHex(key), "AES");
    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
    cipher.init(Cipher.ENCRYPT_MODE, keySpec);
    AlgorithmParameters params = cipher.getParameters();
    byte[] iv = params.getParameterSpec(IvParameterSpec.class).getIV();
    byte[] encryptedText = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));
    byte[] combinedEncryptedText = combineEncryptionData(encryptedText, iv);
    return Base64.getEncoder().encodeToString(combinedEncryptedText);
  }

  private static byte[] combineEncryptionData(byte[] encryptedText, byte[] iv) {
    if (encryptedText!= null && iv != null) {
      final byte[] joinedArray = new byte[iv.length + encryptedText.length];
      System.arraycopy(iv, 0, joinedArray, 0, iv.length);
      System.arraycopy(encryptedText, 0, joinedArray, iv.length, encryptedText.length);
      return joinedArray;
    } else {
      return encryptedText;
    }
  }
}
  