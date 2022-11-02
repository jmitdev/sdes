public class CrackSDES {
    public static void main(String[] args) {
        System.out.print("--- Part 3 ---");
        System.out.println();

        System.out.println("1) Encrypt the following word: 'CRYPTOGRAPHY'");

        byte[] key = new byte[] { 0, 1, 1, 1, 0, 0, 1, 1, 0, 1 };
        byte[] plaintextTotal = CASCII.Convert("CRYPTOGRAPHY");
        SDES.printArray(plaintextTotal);
        System.out.println();
        System.out.print("Raw Key     Plaintext  Ciphertext");
        System.out.println();

        byte[] ciphertextTotal = SDES.encryptMessage(key, plaintextTotal);

        System.out.println("Encryption in bytes and CASCII");
        SDES.printArray(ciphertextTotal);
        System.out.println();
        SDES.printArrayInCASCII(ciphertextTotal);
        System.out.println();

        System.out.println("2) Crack msg1");

        // Step 1: Select the first 64 bit of the message (its enough for some real
        // words)
        System.out.println("Brute force output for each possible key");
        byte[] msg1 = new byte[] { 1, 0, 1, 1, 0, 1, 1, 0, 0, 1, 1, 1, 1,
            0, 0, 1, 0, 0, 1, 0, 1, 1, 1, 0, 1,
            1, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
            1, 1, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1 };
        bruteForceMsg(msg1);

        System.out.println("We found the key!!! So lets decrypt the hole message.");
        key = new byte[] { 1, 0, 1, 1, 1, 1, 0, 1, 0, 0 };
        SDES.printArray(key);
        // ciphertext
        String s = "1011011001111001001011101111110000111110100000000001110111010001111011111101101100010011000000101101011010101000101111100011101011010111100011101001010111101100101110000010010101110001110111011111010101010100001100011000011010101111011111010011110111001001011100101101001000011011111011000010010001011101100011011110000000110010111111010000011100011111111000010111010100001100001010011001010101010000110101101111111010010110001001000001111000000011110000011110110010010101010100001000011010000100011010101100000010111000000010101110100001000111010010010101110111010010111100011111010101111011101111000101001010001101100101100111001110111001100101100011111001100000110100001001100010000100011100000000001001010011101011100101000111011100010001111101011111100000010111110101010000000100110110111111000000111110111010100110000010110000111010001111000101011111101011101101010010100010111100011100000001010101110111111101101100101010011100111011110101011011";
        char[] ch = s.toCharArray();
        byte[] ciphertextMsg1 = new byte[ch.length];
        for (int l = 0; l < ch.length; l++) {
            ciphertextMsg1[l] = Byte.valueOf(String.valueOf(ch[l]));
        }
        // decrpyt ciphertext with key
        byte[] plaintextTotal2 = SDES.decryptMessage(key, ciphertextMsg1);
        // print result in CASCII
        System.out.println();
        SDES.printArrayInCASCII(plaintextTotal2);
        System.out.println();
    }

    public static void bruteForceMsg(byte[] msg) {
        byte[] key = new byte[10];
        // Step 2: For each key (1024 possible keys) print the decrypted message
        for (int i = 0; i < 1024; i++) {

            // ----- convert integer i to 10 bit key
            String s = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
            // System.out.println(s);
            char[] ch = s.toCharArray();
            for (int l = 0; l < 10; l++) {
                key[l] = Byte.valueOf(String.valueOf(ch[l]));
            }
            System.out.print("key " + i + " --> ");
            SDES.printArray(key);
            // ----- now we have the 10 bit key

            /*
             * for (int l = 0; l < ch.length; l++) {
             * ciphertextMsg1[l] = Byte.valueOf(String.valueOf(ch[l]));
             * }
             */
            // System.out.println(ch.length);
            // decrpyt ciphertext with key
            byte[] plaintextTotal2 = SDES.decryptMessage(key, msg);
            // print result in CASCII
            SDES.printArrayInCASCII(plaintextTotal2);
            System.out.println();
        }

        // Step 3: Print the results and grep for common letter combination
        // e.g. make sdes | grep TH
    }
}
