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

        System.out.print("--- Part 3 ---");
        System.out.println();

        System.out.println("3) Crack msg2");

        /*
         * comment as we only need to run this once to find the message
         * System.out.println("Brute force output for each possible key");
         * // byte[] msg2 = new byte[] { 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1,
         * 1,
         * // 1, 1, 0, 0, 1, 1, 1, 1, 1, 1,
         * // 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1,
         * 0,
         * // 0, 1, 0, 1, 0, 1, 0, 1, 0,
         * // 1, 1 };
         * byte[] msg2 = new byte[] {
         * 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1,
         * 1,
         * 0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0,
         * 0, 1, 0, 1, 0, 1, 0, 1, 0,
         * 1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1,
         * 0, 0, 1, 1, 0, 1, 0, 1, 1,
         * 1, 1, 1, 1, 1, 1,
         * 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 };
         * 
         * bruteForceMsg(msg2);
         */

        System.out.println("We found the key!!! So lets decrypt the hole message.");
        key1 = new byte[] { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 };
        key2 = new byte[] { 0, 1, 0, 1, 1, 0, 0, 0, 1, 1 };
        System.out.print("Key 1");
        printArray(key1);
        System.out.print("Key 2");
        printArray(key2);
        // ciphertext
        String s = "00011111100111111110011111101100111000000011001011110010101010110001011101001101000000110011010111111110000000001010111111000001010010111001111001010101100000110111100011111101011100100100010101000011001100101000000101111011000010011010111100010001001000100001111100100000001000000001101101000000001010111010000001000010011100101111001101111011001001010001100010100000";
        char[] ch = s.toCharArray();
        byte[] ciphertextMsg2 = new byte[ch.length];
        for (int l = 0; l < ch.length; l++) {
            ciphertextMsg2[l] = Byte.valueOf(String.valueOf(ch[l]));
        }
        // decrpyt ciphertext with keys
        byte[] plaintextTotal = decryptMessage(key1, key2, ciphertextMsg2);
        // print result in CASCII
        System.out.println();
        printArrayInCASCII(plaintextTotal);
        System.out.println();
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

    public static void bruteForceMsg(byte[] msg) {
        byte[] key1 = new byte[10];
        byte[] key2 = new byte[10];
        // Step 2: For each key (1024 possible keys) print the decrypted message
        for (int i = 0; i < 1024; i++) {

            // ----- convert integer i to 10 bit key
            String s = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
            // System.out.println(s);
            char[] ch = s.toCharArray();
            for (int l = 0; l < 10; l++) {
                key1[l] = Byte.valueOf(String.valueOf(ch[l]));
            }
            // ----- now we have the 10 bit key

            for (int j = 0; j < 1024; j++) {
                // ----- convert integer i to 10 bit key
                String s2 = String.format("%10s", Integer.toBinaryString(j)).replace(' ', '0');
                // System.out.println(s);
                char[] ch2 = s2.toCharArray();
                for (int l = 0; l < 10; l++) {
                    key2[l] = Byte.valueOf(String.valueOf(ch2[l]));
                }
                // ----- now we have the 10 bit key
                System.out.print("key 1 " + i + " ");
                printArray(key1);
                System.out.print("key 2 " + j + " ");
                printArray(key2);
                System.out.print(" --> ");

                /*
                 * for (int l = 0; l < ch.length; l++) {
                 * ciphertextMsg1[l] = Byte.valueOf(String.valueOf(ch[l]));
                 * }
                 */
                // System.out.println(ch.length);
                // decrpyt ciphertext with key
                byte[] plaintextTotal2 = decryptMessage(key1, key2, msg);
                // print result in CASCII
                printArrayInCASCII(plaintextTotal2);
                System.out.println();
            }
        }

        // Step 3: Print the results and grep for common letter combination
        // e.g. make sdes | grep TH
        // make triple-sdes | grep there
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
