import java.util.Arrays;

public class TripleSDES {
    public static void main(String[] args) {
        byte[] key1, key2, plaintext, ciphertext;

        System.out.print("--- Part 2 ---");
        System.out.println();

        System.out.print("Raw Key 1    Raw Key 2    Plaintext  Ciphertext");
        System.out.println();

        key1 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        key2 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        plaintext = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        ciphertext = Encrypt(key1, key2, plaintext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 };
        key2 = new byte[] { 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 };
        plaintext = new byte[] { 1, 1, 0, 1, 0, 1, 1, 1 };
        ciphertext = Encrypt(key1, key2, plaintext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 };
        key2 = new byte[] { 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 };
        plaintext = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0 };
        ciphertext = Encrypt(key1, key2, plaintext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        key2 = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        plaintext = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0 };
        ciphertext = Encrypt(key1, key2, plaintext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 };
        key2 = new byte[] { 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 };
        ciphertext = new byte[] { 1, 1, 1, 0, 0, 1, 1, 0 };
        plaintext = Decrypt(key1, key2, ciphertext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 0, 1, 1, 1, 0, 1, 1, 1, 1 };
        key2 = new byte[] { 0, 1, 1, 0, 1, 0, 1, 1, 1, 0 };
        ciphertext = new byte[] { 0, 1, 0, 1, 0, 0, 0, 0 };
        plaintext = Decrypt(key1, key2, ciphertext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        key2 = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        ciphertext = new byte[] { 1, 0, 0, 0, 0, 0, 0, 0 };
        plaintext = Decrypt(key1, key2, ciphertext);
        printResult(key1, key2, plaintext, ciphertext);

        key1 = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        key2 = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        ciphertext = new byte[] { 1, 0, 0, 1, 0, 0, 1, 0 };
        plaintext = Decrypt(key1, key2, ciphertext);
        printResult(key1, key2, plaintext, ciphertext);
    }

    public static void printResult(byte[] rawkey1, byte[] rawkey2, byte[] plaintext, byte[] ciphertext) {
        printArray(rawkey1);
        printArray(rawkey2);
        printArray(plaintext);
        printArray(ciphertext);
        System.out.println();
    }

    public static void printArray(byte[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.print("   ");
    }

    public static byte[] Encrypt(byte[] rawkey1, byte[] rawkey2, byte[] plaintext) {
        byte[] afterEncrypt = SDES.Encrypt(rawkey1, plaintext);
        byte[] afterDecrypt = SDES.Decrypt(rawkey2, afterEncrypt);
        byte[] ciphertext = SDES.Encrypt(rawkey1, afterDecrypt);
        return ciphertext;
    }

    public static byte[] Decrypt(byte[] rawkey1, byte[] rawkey2, byte[] ciphertext) {
        byte[] afterDecrypt = SDES.Decrypt(rawkey1, ciphertext);
        byte[] afterEncrpyt = SDES.Encrypt(rawkey2, afterDecrypt);
        byte[] plaintext = SDES.Decrypt(rawkey1, afterEncrpyt);
        return plaintext;
    }

    public static byte[] decryptMessage(byte[] key1, byte[] key2, byte[] ciphertextTotal) {
        byte[] plaintextTotal = new byte[ciphertextTotal.length];
        byte[] eightBitPlaintext = new byte[8];
        byte[] eightBitCiphertext = new byte[8];
        for (int j = 0; j < ciphertextTotal.length - 9; j = j + 8) {
            int counter = j;
            for (int k = 0; k < 8; k++) {
                eightBitCiphertext[k] = ciphertextTotal[counter];
                counter++;
            }
            eightBitPlaintext = Decrypt(key1, key2, eightBitCiphertext);
            // printResult(key, eightBitPlaintext, eightBitCiphertext);
            counter = j;
            for (int k = 0; k < 8; k++) {
                plaintextTotal[counter] = eightBitPlaintext[k];
                counter++;
            }
        }
        return plaintextTotal;
    }

    public static void printArrayInCASCII(byte[] arr) {
        for (int j = 0; j < arr.length; j = j + 5) {
            System.out.print(
                    CASCII.Convert(
                            Arrays.copyOfRange(
                                    arr, j, j + 5)));
        }
        System.out.println();
    }
}
