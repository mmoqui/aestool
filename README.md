# AES Tool

A cryptographic to to generate randomly an AES 256 bits key and with such a key to encrypt and 
decrypt a text.

The generated key is generated in hexadecimal. The cipher key is then expected also to be 
encoded in hexadecimal when encrypting and decrypting a text.

The cipher text is returned in base64 and a padding AES algorithm is used for its encryption. 
The returned encrypted text is built with the IV parameter used in the encryption. The decrypt 
command is also expecting the IV parameter is present in the encrypted text. This is why this tool
can be used to decrypt text only for those encrypted with this tool.