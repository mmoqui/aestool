package org.silverpeas.security.aes;

import org.apache.commons.codec.binary.Hex;
import picocli.CommandLine.Command;

import javax.crypto.SecretKey;
import java.util.concurrent.Callable;

import static javax.crypto.KeyGenerator.getInstance;

/**
 * Generator of a random 256 bits AES key. The key is returned in hexadecimal.
 * @author mmoquillon
 */
@Command(name="generate",
    description = "Generate a 256 bits AES key in hexadecimal")
public class AESKeyGenerator implements Callable<String> {

  @Override
  public String call() throws Exception {
    var keyGenerator = getInstance("AES");
    keyGenerator.init(256);
    SecretKey key = keyGenerator.generateKey();
    return Hex.encodeHexString(key.getEncoded());
  }
}
  