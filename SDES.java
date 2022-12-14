
import java.util.*;

public class SDES {
    public static void main(String[] args) {
        byte[] key, plaintext, ciphertext;

        System.out.print("--- Part 1 ---");
        System.out.println();
        System.out.print("Raw Key     Plaintext  Ciphertext");
        System.out.println();

        key = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        plaintext = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 0 };
        plaintext = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 1, 1, 0, 0, 0, 1, 1, 1, 0 };
        plaintext = new byte[] { 0, 1, 0, 1, 0, 1, 0, 1 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        plaintext = new byte[] { 1, 0, 1, 0, 1, 0, 1, 0 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        plaintext = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };
        plaintext = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 };
        plaintext = new byte[] { 0, 0, 0, 0, 0, 0, 0, 0 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 0, 0, 0, 0, 0, 1, 1, 1, 1, 1 };
        plaintext = new byte[] { 1, 1, 1, 1, 1, 1, 1, 1 };
        ciphertext = Encrypt(key, plaintext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 };
        ciphertext = new byte[] { 0, 0, 0, 1, 1, 1, 0, 0 };
        plaintext = Decrypt(key, ciphertext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 1, 0, 0, 0, 1, 0, 1, 1, 1, 0 };
        ciphertext = new byte[] { 1, 1, 0, 0, 0, 0, 1, 0 };
        plaintext = Decrypt(key, ciphertext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 0, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        ciphertext = new byte[] { 1, 0, 0, 1, 1, 1, 0, 1 };
        plaintext = Decrypt(key, ciphertext);
        printResult(key, plaintext, ciphertext);

        key = new byte[] { 0, 0, 1, 0, 0, 1, 1, 1, 1, 1 };
        ciphertext = new byte[] { 1, 0, 0, 1, 0, 0, 0, 0 };
        plaintext = Decrypt(key, ciphertext);
        printResult(key, plaintext, ciphertext);
    }

    public static void printResult(byte[] rawkey, byte[] plaintext, byte[] ciphertext) {
        printArray(rawkey);
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

    public static byte[] Encrypt(byte[] rawkey, byte[] plaintext) {
        byte[] afterLS1 = keyLS1(keyP10(rawkey));
        byte[] afterLS2 = keyLS1(keyLS1(afterLS1));
        byte[] key1 = keyP8(afterLS1);
        byte[] key2 = keyP8(afterLS2);
        byte[] afterIP = functionIP(plaintext);
        byte[] fk1 = fSubK(afterIP, key1);
        byte[] switched = switchFunction(fk1);
        byte[] fk2 = fSubK(switched, key2);
        byte[] ciphertext = inverseIP(fk2);
        return ciphertext;
    }

    public static byte[] Decrypt(byte[] rawkey, byte[] ciphertext) {
        byte[] afterLS1 = keyLS1(keyP10(rawkey));
        byte[] afterLS2 = keyLS1(keyLS1(afterLS1));
        byte[] key1 = keyP8(afterLS1);
        byte[] key2 = keyP8(afterLS2);
        byte[] afterIP = functionIP(ciphertext);
        byte[] fk1 = fSubK(afterIP, key2);
        byte[] switched = switchFunction(fk1);
        byte[] fk2 = fSubK(switched, key1);
        byte[] plaintext = inverseIP(fk2);
        return plaintext;
    }

    public static byte[] keyP10(byte[] inputKey) {
        byte[] permutedKey = new byte[10];
        permutedKey[0] = inputKey[2];
        permutedKey[1] = inputKey[4];
        permutedKey[2] = inputKey[1];
        permutedKey[3] = inputKey[6];
        permutedKey[4] = inputKey[3];
        permutedKey[5] = inputKey[9];
        permutedKey[6] = inputKey[0];
        permutedKey[7] = inputKey[8];
        permutedKey[8] = inputKey[7];
        permutedKey[9] = inputKey[5];
        return permutedKey;
    }

    public static byte[] keyLS1(byte[] inputKey) {
        byte[] leftShift1 = new byte[10];
        leftShift1[0] = inputKey[1];
        leftShift1[1] = inputKey[2];
        leftShift1[2] = inputKey[3];
        leftShift1[3] = inputKey[4];
        leftShift1[4] = inputKey[0];
        leftShift1[5] = inputKey[6];
        leftShift1[6] = inputKey[7];
        leftShift1[7] = inputKey[8];
        leftShift1[8] = inputKey[9];
        leftShift1[9] = inputKey[5];
        return leftShift1;
    }

    public static byte[] keyP8(byte[] inputKey) {
        byte[] permutedKey = new byte[8];
        permutedKey[0] = inputKey[5];
        permutedKey[1] = inputKey[2];
        permutedKey[2] = inputKey[6];
        permutedKey[3] = inputKey[3];
        permutedKey[4] = inputKey[7];
        permutedKey[5] = inputKey[4];
        permutedKey[6] = inputKey[9];
        permutedKey[7] = inputKey[8];
        return permutedKey;
    }

    public static byte[] functionIP(byte[] plaintext) {
        byte[] ip = new byte[8];
        ip[0] = plaintext[1];
        ip[1] = plaintext[5];
        ip[2] = plaintext[2];
        ip[3] = plaintext[0];
        ip[4] = plaintext[3];
        ip[5] = plaintext[7];
        ip[6] = plaintext[4];
        ip[7] = plaintext[6];
        return ip;
    }

    public static byte[] inverseIP(byte[] input) {
        byte[] ip = new byte[8];
        ip[0] = input[3];
        ip[1] = input[0];
        ip[2] = input[2];
        ip[3] = input[4];
        ip[4] = input[6];
        ip[5] = input[1];
        ip[6] = input[7];
        ip[7] = input[5];
        return ip;
    }

    public static byte[] bitXOR(byte[] x, byte[] y) {
        byte[] xor = new byte[x.length];
        for (int i = 0; i < x.length; i++) {
            if (x[i] != y[i]) {
                xor[i] = 1;
            } else {
                xor[i] = 0;
            }
        }
        return xor;
    }

    public static byte[] combineArray(byte[] x, byte[] y) {
        byte[] combined = new byte[x.length + y.length];
        System.arraycopy(x, 0, combined, 0, x.length);
        System.arraycopy(y, 0, combined, x.length, y.length);
        return combined;
    }

    public static byte[] fSubK(byte[] input, byte[] key) {
        byte[] left4 = Arrays.copyOfRange(input, 0, 4);
        byte[] right4 = Arrays.copyOfRange(input, 4, input.length);
        byte[] xor = bitXOR(left4, mappingF(right4, key));
        byte[] combined = combineArray(xor, right4);
        return combined;
    }

    public static byte[] ep(byte[] number) {
        byte[] ep = new byte[8];
        ep[0] = number[3];
        ep[1] = number[0];
        ep[2] = number[1];
        ep[3] = number[2];
        ep[4] = number[1];
        ep[5] = number[2];
        ep[6] = number[3];
        ep[7] = number[0];
        return ep;
    }

    public static byte[] sBox0(byte[] row) {
        int[][] s0 = { { 1, 0, 3, 2 },
                { 3, 2, 1, 0 },
                { 0, 2, 1, 3 },
                { 3, 1, 3, 2 } };
        int s0Row = row[0] * 2 + row[3] * 1;
        int s0Col = row[1] * 2 + row[2] * 1;
        byte[] output = new byte[2];
        int num = s0[s0Row][s0Col];
        if (num == 3) {
            output[0] = 1;
            output[1] = 1;
        } else if (num == 2) {
            output[0] = 1;
            output[1] = 0;
        } else if (num == 1) {
            output[0] = 0;
            output[1] = 1;
        } else {
            output[0] = 0;
            output[1] = 0;
        }
        return output;
    }

    public static byte[] sBox1(byte[] row) {
        int[][] s0 = { { 0, 1, 2, 3 },
                { 2, 0, 1, 3 },
                { 3, 0, 1, 0 },
                { 2, 1, 0, 3 } };
        int s0Row = row[0] * 2 + row[3] * 1;
        int s0Col = row[1] * 2 + row[2] * 1;
        byte[] output = new byte[2];
        int num = s0[s0Row][s0Col];
        if (num == 3) {
            output[0] = 1;
            output[1] = 1;
        } else if (num == 2) {
            output[0] = 1;
            output[1] = 0;
        } else if (num == 1) {
            output[0] = 0;
            output[1] = 1;
        } else {
            output[0] = 0;
            output[1] = 0;
        }
        return output;
    }

    public static byte[] perm4(byte[] input) {
        byte[] output = new byte[4];
        output[0] = input[1];
        output[1] = input[3];
        output[2] = input[2];
        output[3] = input[0];
        return output;
    }

    public static byte[] mappingF(byte[] number, byte[] subkey) {
        byte[] ep = ep(number);
        byte[] xor = bitXOR(ep, subkey);
        byte[] firstRow = Arrays.copyOfRange(xor, 0, 4);
        byte[] secondRow = Arrays.copyOfRange(xor, 4, xor.length);
        byte[] s0 = sBox0(firstRow);
        byte[] s1 = sBox1(secondRow);
        byte[] combined = combineArray(s0, s1);
        byte[] p4 = perm4(combined);
        return p4;
    }

    public static byte[] switchFunction(byte[] input) {
        byte[] output = new byte[8];
        output[0] = input[4];
        output[1] = input[5];
        output[2] = input[6];
        output[3] = input[7];
        output[4] = input[0];
        output[5] = input[1];
        output[6] = input[2];
        output[7] = input[3];
        return output;
    }

    public static byte[] encryptMessage(byte[] key, byte[] plaintextTotal) {
        byte[] ciphertextTotal = new byte[64];
        byte[] eightBitPlaintext = new byte[8];
        byte[] eightBitCiphertext = new byte[8];
        for (int i = 0; i < 57; i = i + 8) {
            int counter = i;
            for (int j = 0; j < 8; j++) {
                eightBitPlaintext[j] = plaintextTotal[counter];
                counter++;
            }
            eightBitCiphertext = Encrypt(key, eightBitPlaintext);
            // printResult(key, eightBitPlaintext, eightBitCiphertext);
            counter = i;
            for (int j = 0; j < 8; j++) {
                ciphertextTotal[counter] = eightBitCiphertext[j];
                counter++;
            }
        }
        return ciphertextTotal;
    }

    public static byte[] decryptMessage(byte[] key, byte[] ciphertextTotal) {
        byte[] plaintextTotal = new byte[ciphertextTotal.length];
        byte[] eightBitPlaintext = new byte[8];
        byte[] eightBitCiphertext = new byte[8];
        for (int j = 0; j < ciphertextTotal.length - 9; j = j + 8) {
            int counter = j;
            for (int k = 0; k < 8; k++) {
                eightBitCiphertext[k] = ciphertextTotal[counter];
                counter++;
            }
            eightBitPlaintext = Decrypt(key, eightBitCiphertext);
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
