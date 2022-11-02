public class CrackTripleSDES {
    public static void main(String[] args) {
        System.out.print("--- Part 3 ---");
        System.out.println();

        System.out.println("3) Crack msg2");

        System.out.println("Brute force output for each possible key");
        byte[] msg2 = new byte[] {
                0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1, 1, 1, 1,
                1,
                0, 1, 1, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 1, 0, 1, 1, 1, 1, 0,
                0, 1, 0, 1, 0, 1, 0, 1, 0,
                1, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 1, 1,
                0, 0, 1, 1, 0, 1, 0, 1, 1,
                1, 1, 1, 1, 1, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 1 };

        bruteForceMsg(msg2);

        System.out.println("We found the key!!! So lets decrypt the hole message.");
        byte[] key1 = new byte[] { 1, 1, 1, 0, 0, 0, 0, 1, 0, 1 };
        byte[] key2 = new byte[] { 0, 1, 0, 1, 1, 0, 0, 0, 1, 1 };
        System.out.print("Key 1");
        TripleSDES.printArray(key1);
        System.out.print("Key 2");
        TripleSDES.printArray(key2);
        // ciphertext
        String s = "00011111100111111110011111101100111000000011001011110010101010110001011101001101000000110011010111111110000000001010111111000001010010111001111001010101100000110111100011111101011100100100010101000011001100101000000101111011000010011010111100010001001000100001111100100000001000000001101101000000001010111010000001000010011100101111001101111011001001010001100010100000";
        char[] ch = s.toCharArray();
        byte[] ciphertextMsg2 = new byte[ch.length];
        for (int l = 0; l < ch.length; l++) {
            ciphertextMsg2[l] = Byte.valueOf(String.valueOf(ch[l]));
        }
        // decrpyt ciphertext with keys
        byte[] plaintextTotal = TripleSDES.decryptMessage(key1, key2, ciphertextMsg2);
        // print result in CASCII
        System.out.println();
        TripleSDES.printArrayInCASCII(plaintextTotal);
        System.out.println();
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
                TripleSDES.printArray(key1);
                System.out.print("key 2 " + j + " ");
                TripleSDES.printArray(key2);
                System.out.print(" --> ");

                /*
                 * for (int l = 0; l < ch.length; l++) {
                 * ciphertextMsg1[l] = Byte.valueOf(String.valueOf(ch[l]));
                 * }
                 */
                // System.out.println(ch.length);
                // decrpyt ciphertext with key
                byte[] plaintextTotal2 = TripleSDES.decryptMessage(key1, key2, msg);
                // print result in CASCII
                TripleSDES.printArrayInCASCII(plaintextTotal2);
                System.out.println();
            }
        }

        // Step 3: Print the results and grep for common letter combination
        // e.g. make sdes | grep TH
        // make triple-sdes | grep there
    }

}
