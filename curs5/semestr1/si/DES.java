import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

// https://paginas.fe.up.pt/~ei10109/ca/des.html
public class DES {

    private static int[] IP = {
        58, 50, 42, 34, 26, 18, 10 , 2,
        60, 52, 44, 36, 28, 20, 12, 4,
        62, 54, 46, 38, 30, 22, 14, 6,
        64, 56, 48, 40, 32, 24, 16, 8,
        57, 49, 41, 33, 25, 17, 9, 1,
        59, 51, 43, 35, 27, 19, 11, 3,
        61, 53, 45, 37, 29, 21, 13, 5,
        63, 55, 47, 39, 31, 23, 15, 7
	};
	
	private static int[] IPi = {
		40, 8, 48, 16, 56, 24, 64, 32,
		39, 7, 47, 15, 55, 23, 63, 31,
		38, 6, 46, 14, 54, 22, 62, 30,
		37, 5, 45, 13, 53, 21, 61, 29,
		36, 4, 44, 12, 52, 20, 60, 28,
		35, 3, 43 ,11, 51, 19, 59, 27,
		34, 2, 42, 10, 50, 18, 58, 26,
		33, 1, 41, 9, 49, 17, 57, 25
	};
	
	private long[] K = new long[16];
	
	private static String binToHex(String bin) {
		BigInteger b = new BigInteger(bin, 2);
		String ciphertext = b.toString(16);
		
		return ciphertext;
	}
	
	private static String hexToBin(String hex) {
		BigInteger b = new BigInteger(hex, 16);
		String bin = b.toString(2);
		
		return bin;
	}
	
	private static String binToUTF(String bin) {
		// Convert back to String
		byte[] ciphertextBytes = new byte[bin.length()/8];
		String ciphertext = null;
		for(int j = 0; j < ciphertextBytes.length; j++) {
	        String temp = bin.substring(0, 8);
	        byte b = (byte) Integer.parseInt(temp, 2);
	        ciphertextBytes[j] = b;
	        bin = bin.substring(8);
	    }
		
		try {
			ciphertext = new String(ciphertextBytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ciphertext.trim();
	}
	
	private static String utfToBin(String utf) {
		// Convert to binary
		byte[] bytes = null;
		try {
			bytes = utf.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String bin = "";
		for (int i = 0; i < bytes.length; i++) {
		     int value = bytes[i];
		     for (int j = 0; j < 8; j++)
		     {
		        bin += ((value & 128) == 0 ? 0 : 1);
		        value <<= 1;
		     }
		}
		return bin;
	}

    public static long hash(String string) {
		long h = 1125899906842597L; // prime
		int len = string.length(); 

		for (int i = 0; i < len; i++) {
			h = 31*h + string.charAt(i);
		}
		return h;
	}
	
	public String encrypt(String key, String plaintext) {
		computeSubkeys(hash(key));
		
		String binPlaintext = plaintext;
		
		// Add padding if necessary
		int remainder = binPlaintext.length() % 64;
		if (remainder != 0) {
			for (int i = 0; i < (64 - remainder); i++)
				binPlaintext = "0" + binPlaintext;
		}
		
		// Separate binary plaintext into blocks
		String[] binPlaintextBlocks = new String[binPlaintext.length()/64];
		int offset = 0;
		for (int i = 0; i < binPlaintextBlocks.length; i++) {
			binPlaintextBlocks[i] = binPlaintext.substring(offset, offset+64);
			offset += 64;
		}
		
		String[] binCiphertextBlocks = new String[binPlaintext.length()/64];
		
		// Encrypt the blocks
		for (int i = 0; i < binCiphertextBlocks.length; i++)
			try {
				binCiphertextBlocks[i] = processBlock(binPlaintextBlocks[i], true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		String binCiphertext = "";
		for (int i = 0; i < binCiphertextBlocks.length; i++) 
			binCiphertext += binCiphertextBlocks[i];
			
		
		return binCiphertext;
	}
	
	public String decrypt(String key, String plaintext) {
		computeSubkeys(hash(key));
		
		String binPlaintext = null;
		
		binPlaintext = plaintext;
		
		// Add padding if necessary
		int remainder = binPlaintext.length() % 64;
		if (remainder != 0) {
			for (int i = 0; i < (64 - remainder); i++)
				binPlaintext = "0" + binPlaintext;
		}
		
		// Separate binary plaintext into blocks
		String[] binPlaintextBlocks = new String[binPlaintext.length()/64];
		int offset = 0;
		for (int i = 0; i < binPlaintextBlocks.length; i++) {
			binPlaintextBlocks[i] = binPlaintext.substring(offset, offset+64);
			offset += 64;
		}
		
		String[] binCiphertextBlocks = new String[binPlaintext.length()/64];
		
		// Decrypt the blocks
		for (int i = 0; i < binCiphertextBlocks.length; i++) {
			try {
				binCiphertextBlocks[i] = processBlock(binPlaintextBlocks[i], false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Build the ciphertext binary string from the blocks
		String binCiphertext = "";
		for (int i = 0; i < binCiphertextBlocks.length; i++) 
			binCiphertext += binCiphertextBlocks[i];
			
		return binCiphertext;
	}
	
	public String processBlock(String plaintextBlock, boolean encrypt) throws Exception {
		int length = plaintextBlock.length();
		if (length != 64)
			throw new RuntimeException("Input block length is not 64 bits!");
		
		String initiallyPermuted = "";
		for (int i = 0; i < IP.length; i++) {
			initiallyPermuted = initiallyPermuted + plaintextBlock.charAt(IP[i] - 1);	
		}
			
		String l0 = initiallyPermuted.substring(0, 32);
		String r0 = initiallyPermuted.substring(32);

		// iterate 16 times each time using the i'th 48bit subkey
		for (int i = encrypt ? 0 : 16; encrypt ? i < 16 : i > 0; i = encrypt ? i+1 : i-1) {
			// subkey to use in f()
			String subKey = Long.toBinaryString(K[encrypt ? i : i - 1]);
			
			// Get 32-bit result from f()
			String fResult = f(r0, subKey);
			
			// right 32 bits in the current step are computed XORing the left 32 bits
			// of the previous step with the result of the f function
			long r = Long.parseLong(l0, 2) ^ Long.parseLong(fResult, 2);
			String rString = Long.toBinaryString(r);
			
			while(rString.length() < 32)
				rString = "0" + rString;
			
			// we take the right 32 bits of the previous result
			// and make them the left 32 bits of the current step
			l0 = r0;
			
			r0 = rString;
		}
		
		// apply a final permutation IP-1
		String in = r0 + l0;
		String output = "";
		for (int i = 0; i < IPi.length; i++) {
			output = output + in.charAt(IPi[i] - 1);
		}
		
		return output;
	}
	
	public void computeSubkeys(long key) {
		String binaryKey = Long.toBinaryString(key);
		
		// Add leading zeros if not at key length for ease of computations
		while (binaryKey.length() < 64) 
			binaryKey = "0" + binaryKey;
		
		// For the 56-bit permuted key 
		String permutedKey = "";

        int[] PC1 = {
            57, 49, 41, 33, 25, 17,  9,
            1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13,  5, 28, 20, 12,  4
	    };

        int[] PC2 = {
            14, 17, 11, 24,  1,  5,
            3, 28, 15,  6, 21, 10,
            23, 19, 12,  4, 26,  8,
            16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32
        };
		
		// permute the key with PC1
		for (int i = 0; i < PC1.length; i++)
			permutedKey = permutedKey + binaryKey.charAt(PC1[i]-1);
        
        // split the permutedKey in two parts, c0 and d0
		int c0 = Integer.parseInt(permutedKey.substring(0, 28), 2);
		int d0 = Integer.parseInt(permutedKey.substring(28), 2);

	    int[] subkeyShifts = {
            1,  1,  2,  2,  2,  2,  2,  2,  1,  2,  2,  2,  2,  2,  2,  1
        };
		
		// build the keys
		for (int i = 0; i < K.length; i++) {
			// left shifts according to subkeyShifts
			c0 = Integer.rotateLeft(c0, subkeyShifts[i]);
			d0 = Integer.rotateLeft(d0, subkeyShifts[i]);
			
			// merge the two halves
			long merged = ((long)c0 << 28) + d0;
			
			String stringMerged = Long.toBinaryString(merged);
			
			// fix length if leading zeros absent
			while (stringMerged.length() < 56)
				stringMerged = "0" + stringMerged;
			
			String permuted48bitKey = "";
			
			// permute the key with PC2 to get a 48 bit key
			for (int j = 0; j < PC2.length; j++)
				permuted48bitKey = permuted48bitKey + stringMerged.charAt(PC2[j]-1);
			
			K[i] = Long.parseLong(permuted48bitKey, 2);
		}
	}
	
	public static String f(String block32bits, String key) {
		int[] E = {
            32,  1,  2,  3,  4,  5,
            4,  5,  6,  7,  8,  9, 
            8,  9, 10, 11, 12, 13, 
            12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 
            20, 21, 22, 23, 24, 25, 
            24, 25, 26, 27, 28, 29, 
            28, 29, 30, 31, 32,  1
        };

        // expand 32 bit block into 48 bits by using E table
		String block48bits = "";
		for (int i = 0; i < E.length; i++) {
			block48bits = block48bits + block32bits.charAt(E[i] - 1);
		}
		
		long lBlock48Bits =  Long.parseLong(block48bits, 2);	
		long lKey = Long.parseLong(key, 2);
		
		// XOR expanded message block and key block
		String xored = Long.toBinaryString(lBlock48Bits ^ lKey);

		// Making sure the string is 48 bits
		while (xored.length() < 48) {
			xored = "0" + xored;
		}
		
		// Split xored into eight 6-bit strings
		String[] eight6bitStrings = new String[8];
		for (int i = 0; i < 8; i++) {
			eight6bitStrings[i] = xored.substring(0, 6);
			xored = xored.substring(6);
		}

		// Do S-Box calculations
		int[][] s1 = {
			{14, 4, 13,  1,  2, 15, 11,  8,  3, 10,  6, 12,  5,  9,  0,  7},
			{0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11,  9,  5,  3,  8},
			{4, 1, 14,  8, 13,  6, 2, 11, 15, 12,  9,  7,  3, 10,  5,  0},
			{15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13}
		};

		int[][] s2 = {
			{15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10},
			{3, 13,  4, 7, 15,  2,  8, 14, 12,  0, 1, 10,  6,  9, 11,  5},
			{0, 14, 7, 11, 10,  4, 13,  1,  5,  8, 12,  6,  9,  3,  2, 15},
			{13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14,  9}
		};
		
		int[][] s3 = {
			{10, 0, 9, 14, 6, 3, 15, 5,  1, 13, 12, 7, 11, 4, 2,  8},
			{13, 7, 0, 9, 3,  4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1},
			{13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14,  7},
			{1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12}
		};
		
		int[][] s4 = {
			{7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15},
			{13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14,  9},
			{10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4},
			{3, 15, 0, 6, 10, 1, 13, 8, 9,  4, 5, 11, 12, 7, 2, 14}
		};
		
		int[][] s5 = {
			{2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9},
			{14, 11, 2, 12,  4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6},
			{4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14},
			{11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3}
		};
		
		int[][] s6 = {
			{12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11},
			{10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8},
			{9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6},
			{4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13}
		};
		
		int[][] s7 = {
			{4, 11, 2, 14, 15,  0, 8, 13 , 3, 12, 9 , 7,  5, 10, 6, 1},
			{13 , 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6},
			{1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2},
			{6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12}
		};
		
		int[][] s8 = {
			{13, 2, 8,  4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7},
			{1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6 ,11, 0, 14, 9, 2},
			{7, 11, 4, 1, 9, 12, 14, 2,  0, 6, 10 ,13, 15, 3, 5, 8},
			{2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6 ,11}
		};
		
		int[][][] sBoxes = {s1, s2, s3, s4, s5, s6, s7, s8};

		String[] sBoxOutput = new String[8];

		for (int i = 0 ; i < 8; i++) {
			int[][] sBox = sBoxes[i];
			String sixBitString = eight6bitStrings[i];
		
			// get row and column values
			int row = Integer.parseInt(sixBitString.charAt(0) + "" + sixBitString.charAt(5), 2);
			int col = Integer.parseInt(sixBitString.substring(1, 5), 2);
			
			// Do S-Box table lookup
			sBoxOutput[i] = Integer.toBinaryString(sBox[row][col]);
			
			// Make sure the string is 4 bits
			while(sBoxOutput[i].length() < 4)
				sBoxOutput[i] = "0" + sBoxOutput[i];
		}
		
		// Merge S-Box outputs into one 32-bit string
		String merged = "";
		for (int i = 0; i < 8; i++) {
			merged = merged + sBoxOutput[i];
		}
		
		// apply final permutation
		int[] p = {
			16,  7, 20, 21, 
			29, 12, 28, 17, 
			1, 15, 23, 26, 
			5, 18, 31, 10, 
			2,  8, 24, 14, 
			32, 27,  3,  9, 
			19, 13, 30,  6, 
			22, 11,  4, 25
		};
		
		String permuted = "";
		for (int i = 0; i < p.length; i++) {
			permuted = permuted + merged.charAt(p[i] - 1);
		}
		
		return permuted;
	}
	
	public static void main(String[] args) {
		DES des = new DES();
		
		boolean enc = true;
		String key1 = null, key2 = null, key3 = null, message = null, result = null;
		
		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-k1"))
				key1 = args[++i];
			else if (args[i].equals("-k2"))
				key2 = args[++i];
			else if (args[i].equals("-k3"))
				key3 = args[++i];
			else if (args[i].equals("-m"))
				message = args[++i];
			else if (args[i].equals("-d"))
				enc = false;
		}
		
		if (enc) {
			if (message == null) {
				System.out.println("No message given to encrypt. Exiting..");
				System.exit(0);
			} else if (key1 == null) {
				System.out.println("Improper use of key arguments. Exiting..");
				System.exit(0);
			}
			
			if (key2 == null) {
				if (key3 != null) {
					System.out.println("Improper use of key arguments. Exiting..");
					System.exit(0);
				}
				result = des.encrypt(key1, utfToBin(message));
				System.out.println(binToHex(result));
			} else {
				if (key3 == null) {
					System.out.println("Improper use of key arguments. Exiting..");
					System.exit(0);
				}
				result = des.encrypt(key3, des.decrypt(key2, des.encrypt(key1, utfToBin(message))));
				System.out.println(binToHex(result));
			}
		} else {
			if (message == null) {
				System.out.println("No data given to decrypt. Exiting..");
				System.exit(0);
			} else if (key1 == null) {
				System.out.println("Improper use of key arguments. Exiting..");
				System.exit(0);
			}
			
			if (key2 == null) {
				if (key3 != null) {
					System.out.println("Improper use of key arguments. Exiting..");
					System.exit(0);
				}
				result = des.decrypt(key1, hexToBin(message));
				System.out.println(binToUTF(result));
			} else {
				if (key3 == null) {
					System.out.println("Improper use of key arguments. Exiting..");
					System.exit(0);
				}
				result = des.decrypt(key1, des.encrypt(key2, des.decrypt(key3, hexToBin(message))));
				System.out.println(binToUTF(result));
			}
		}
		
	}
}
