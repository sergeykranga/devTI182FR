import java.io.DataInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;

// https://www.comparitech.com/blog/information-security/rsa-encryption/
public class RSA
{
    private BigInteger n;
    private BigInteger e;
    private BigInteger d;
    private int bitLength = 1024;

    public RSA() {
        Random random = new Random();

        // find two prime numbers p and q
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        System.out.println("Generated p: " + p);

        BigInteger q = BigInteger.probablePrime(bitLength, random);
        System.out.println("Generated q: " + q);

        // calculate n = p * q
        n = p.multiply(q);
        System.out.println("Result n (shared publicly and privately): " + n);

        // carmichael function
        BigInteger cm = lcm(p.subtract(BigInteger.ONE), q.subtract(BigInteger.ONE));
        System.out.println("Carmichael function value cm: " + cm);

        // generate public key e
        e = findE(cm);
        System.out.println("Generated e (public key): " + e);

        // generate private key d
        d = e.modInverse(cm);
        System.out.println("Generated d (private key): " + d);
    }

    private BigInteger findE(BigInteger cm) {
        // e is a number coprime with cm that is less than cm, greater than 1
        // initializing the search from cm / 2
        
        // two numbers are said to be coprime or mutually prime 
        // if the greatest common divisor of them is 1
        for (BigInteger i = cm.divide(new BigInteger("2")); i.compareTo(cm) == -1; i = i.add(BigInteger.ONE)) {
            if (i.gcd(cm).equals(BigInteger.ONE)) {
                return i;
            }
        }

        throw new RuntimeException("could not find e");
    }

    // https://www.baeldung.com/java-least-common-multiple#lcm-biginteger
    private BigInteger lcm(BigInteger number1, BigInteger number2) {
        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd);
    }

    public byte[] encrypt(String message) {
        return (new BigInteger(message.getBytes())).modPow(e, n).toByteArray();
    }

    public String decrypt(byte[] message) {
        return new String((new BigInteger(message)).modPow(d, n).toByteArray());
    }

    private static String bytesToString(byte[] encrypted) {
        String test = "";
        for (byte b : encrypted)
        {
            test += Byte.toString(b);
        }
        return test;
    }

    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();
        

        DataInputStream in = new DataInputStream(System.in);
        System.out.println("Enter the plain text:");
        String teststring = in.readLine();

        // encrypt
        byte[] encrypted = rsa.encrypt(teststring);
        System.out.println("Encrypted string: " + bytesToString(encrypted));

        // decrypt
        String decrypted = rsa.decrypt(encrypted);
        System.out.println("Decrypted String: " + decrypted);
    }
}
