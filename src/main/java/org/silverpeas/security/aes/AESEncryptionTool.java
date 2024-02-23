package org.silverpeas.security.aes;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.HelpCommand;

/**
 * The tool to generate a 256 bits AES key and to encrypt in AES text by using a given AES key.
 *
 * @author mmoquillon
 */
@Command(name = "aes", description =
    """
    Tool to generate a 256 bits AES key or to encrypt text in AES with a given AES key
    """,
    mixinStandardHelpOptions = true, version = "aes 4.0",
    subcommands = {HelpCommand.class, AESKeyGenerator.class, AESTextEncryptor.class,
        AESTextDecryptor.class})
public class AESEncryptionTool {

  public static void main(String[] args) {
    var commandLine = new CommandLine(new AESEncryptionTool());
    int exitCode = commandLine.execute(args);
    commandLine.setExecutionStrategy(new CommandLine.RunAll());
    CommandLine.ParseResult parseResult = commandLine.getParseResult();
    if (parseResult.subcommand() != null) {
      CommandLine sub = parseResult.subcommand().commandSpec().commandLine();
      String result = sub.getExecutionResult();
      if (result != null && !result.isBlank()) {
        System.out.println(result);
      }
    }
    System.exit(exitCode);
  }
}
  