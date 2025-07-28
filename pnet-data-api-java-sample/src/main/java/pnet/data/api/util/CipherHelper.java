package pnet.data.api.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class CipherHelper {

    private static final Charset CHARSET = StandardCharsets.UTF_8;

    /**
     * Creates a CipherHelper using the key for a Blowfish algorithm
     *
     * @param base64EncodedKey the key
     * @return the CipherHelper
     * @deprecated Non-compliant as criticized by Sonar
     */
    @Deprecated
    public static CipherHelper ofBlowfishKey(String base64EncodedKey) {
        return new CipherHelper(new SecretKeySpec(Base64.getDecoder().decode(base64EncodedKey), "Blowfish"), 0) {
            @Override
            protected Cipher buildCipher(SecretKey secretKey, int mode, byte[] iv)
                throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
                Cipher cipher = Cipher.getInstance("Blowfish");

                cipher.init(mode, secretKey);

                return cipher;
            }
        };
    }

    /**
     * Creates a CipherHelper using the key for a AES/GCM/NoPadding algorithm
     *
     * @param base64EncodedKey the key, 256 bits
     * @return the CipherHelper
     */
    public static CipherHelper ofAesKey(String base64EncodedKey) {
        return new CipherHelper(new SecretKeySpec(Base64.getDecoder().decode(base64EncodedKey), "AES"), 12) {
            @Override
            protected Cipher buildCipher(SecretKey secretKey, int mode, byte[] iv)
                throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
                Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
                GCMParameterSpec parameterSpec = new GCMParameterSpec(128, iv);

                cipher.init(mode, secretKey, parameterSpec);

                return cipher;
            }
        };
    }

    private final SecureRandom random = new SecureRandom();
    private final SecretKey secretKey;
    private final int ivLength;

    protected CipherHelper(SecretKey secretKey, int ivLength) {
        super();
        this.secretKey = secretKey;
        this.ivLength = ivLength;
    }

    protected abstract Cipher buildCipher(SecretKey secretKey, int mode, byte[] iv)
        throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException;

    public String encode(String s) {
        if (s == null || s.length() == 0) {
            return null;
        }

        return Base64.getEncoder().encodeToString(encode(s.getBytes(CHARSET)));
    }

    public byte[] encode(byte[] bytes) {
        byte[] encodedBytes;
        byte[] iv = null;

        if (ivLength > 0) {
            iv = new byte[ivLength];

            random.nextBytes(iv);
        }

        Cipher cipher;

        try {
            cipher = buildCipher(secretKey, Cipher.ENCRYPT_MODE, iv);
        } catch (
            NoSuchAlgorithmException
            | NoSuchPaddingException
            | InvalidKeyException
            | InvalidAlgorithmParameterException e
        ) {
            throw new IllegalArgumentException("Invalid cipher", e);
        }

        try {
            encodedBytes = cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }

        if (iv != null) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(4 + iv.length + encodedBytes.length);

            byteBuffer.putInt(iv.length);
            byteBuffer.put(iv);
            byteBuffer.put(encodedBytes);

            encodedBytes = byteBuffer.array();
        }

        return encodedBytes;
    }

    public String decode(String base64EncodedBytes) {
        if (base64EncodedBytes == null || base64EncodedBytes.length() == 0) {
            return null;
        }

        return new String(decode(Base64.getDecoder().decode(base64EncodedBytes)));
    }

    public byte[] decode(byte[] bytes) {
        byte[] iv = null;

        if (ivLength > 0) {
            ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);

            int currentIvLength = byteBuffer.getInt();

            if (currentIvLength != ivLength) {
                throw new IllegalArgumentException("Invalid iv length");
            }

            iv = new byte[ivLength];

            byteBuffer.get(iv);

            bytes = new byte[bytes.length - 4 - iv.length];

            byteBuffer.get(bytes);
        }

        Cipher cipher;

        try {
            cipher = buildCipher(secretKey, Cipher.DECRYPT_MODE, iv);
        } catch (
            NoSuchAlgorithmException
            | NoSuchPaddingException
            | InvalidKeyException
            | InvalidAlgorithmParameterException e
        ) {
            throw new IllegalArgumentException("Invalid cipher", e);
        }

        try {
            return cipher.doFinal(bytes);
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            throw new IllegalArgumentException("Invalid input", e);
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.print("Generate key using algorithm? ");

            String algorithm = reader.readLine();

            if (algorithm == null || algorithm.length() == 0) {
                return;
            }

            KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm.trim());

            keyGenerator.init(256);

            System.out.println(Base64.getEncoder().encodeToString(keyGenerator.generateKey().getEncoded()));
        }
    }
}
