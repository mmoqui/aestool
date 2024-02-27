# AES Tool

A cryptographic to to generate randomly an AES 256 bits key and with such a key to encrypt and 
decrypt a text.

The generated key is generated in hexadecimal. The cipher key is then expected also to be 
encoded in hexadecimal when encrypting and decrypting a text.

The cipher text is returned in base64 and a padding AES algorithm is used for its encryption. 
The returned encrypted text is built with the IV parameter used in the encryption. The decrypt 
command is also expecting the IV parameter is present in the encrypted text. This is why this tool
can be used to decrypt text only for those encrypted with this tool.

### How to build

To build a distribution of the tool, just do:

```sh
./gradlew clean build distZip
```

This will create a zip archive with the both the libraries and the binaries (shell scripts) to run the program.
The zip archive is available in the directory *build/distributions/*

## How to use

### In the project

To generate a key:

```sh
./gradflew run --args 'generate'
```

To encrypt a text (`My text` in the example) with the key `5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f`:

```sh
./gradlew run --args 'encrypt -t "My text" -k 5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f'
```

To decrypt an encrypted text (`tleuCvqFY5hCOLDjyqSCVkoTFGp4b6FWYwsegaqHIoo=` here) with the key `5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f`:

```sh
./gradlew run --args 'decrypt -t tleuCvqFY5hCOLDjyqSCVkoTFGp4b6FWYwsegaqHIoo= -k 5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f
```

### From the distribution

To generate a key:

```sh
./encryptor generate
```

To encrypt a text (`My text` in the example) with the key `5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f`:

```sh
./encryptor encrypt -t "My text" -k 5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f
```

To decrypt an encrypted text (`tleuCvqFY5hCOLDjyqSCVkoTFGp4b6FWYwsegaqHIoo=` here) with the key `5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f`:

```sh
./encryptor decrypt -t tleuCvqFY5hCOLDjyqSCVkoTFGp4b6FWYwsegaqHIoo= -k 5bdf30f02c2e66613ef61a8085620b0589c8e25b62dc593778f2febf0652d89f
```

