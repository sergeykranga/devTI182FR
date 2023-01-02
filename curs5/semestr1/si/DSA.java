import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.Signature;
import java.util.Scanner;

public class DSA {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter text to sign:");
        String message = sc.nextLine();
        
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(2048);
        
        KeyPair pair = keyPairGenerator.generateKeyPair();
        Signature signature = Signature.getInstance("SHA256withDSA");
        
        signature.initSign(pair.getPrivate());
        byte[] messageBytes = message.getBytes();
        signature.update(messageBytes);
        
        byte[] signatureBytes = signature.sign();
        System.out.println("Digital signature for given text: " + new String(signatureBytes, "UTF8"));

        // verify signature with public key
        Signature verifySignature = Signature.getInstance("SHA256withDSA");
        verifySignature.initVerify(pair.getPublic());
        verifySignature.update(messageBytes);
        
        if (verifySignature.verify(signatureBytes)) {
            System.out.println("Signature is valid with a matching public key");   
        } else {
            throw new RuntimeException("Invalid signature");
        }

        // show that using a different public key will not work
        KeyPair newPair = keyPairGenerator.generateKeyPair();
        Signature verifySignatureWrong = Signature.getInstance("SHA256withDSA");

        verifySignatureWrong.initVerify(newPair.getPublic());
        verifySignatureWrong.update(messageBytes);
        
        if (!verifySignatureWrong.verify(signatureBytes)) {
            System.out.println("Signature is not valid with a non-matching public key");   
        } else {
            throw new RuntimeException("Signature is valid");
        }
    }
}
