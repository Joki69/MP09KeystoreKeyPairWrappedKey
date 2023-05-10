import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Arrays;
import java.util.Scanner;

public class MainEx6 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        RandomGenerate randomGenerate = new RandomGenerate();
        try {
            // Generar un par de claves RSA de 2048 bits
            KeyPair keyPair =  randomGenerate.randomGenerate(2048);
            PrivateKey privateKey = keyPair.getPrivate();
            PublicKey publicKey = keyPair.getPublic();

            // Define los datos a firmar
            System.out.println("Introduce el mensaje a firmar");
            String message = scanner.nextLine();
            byte[] data = message.getBytes("UTF-8");

            // Firma los datos con la clave privada
            byte[] signature = signData(data, privateKey);

            // Imprime la firma por pantalla
            System.out.println(Arrays.toString(signature));

            // Comprobar la validez de la firma digital
            boolean isValid = validateSignature(data, signature, publicKey);

            if (isValid) {
                System.out.println("La firma digital es válida.");
            } else {
                System.out.println("La firma digital no es válida.");
            }


        } catch (Exception ex) {
            System.err.println("Error: " + ex);
        }
    }

    public static byte[] signData(byte[] data, PrivateKey priv) {
        byte[] signature = null;

        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initSign(priv);
            signer.update(data);
            signature = signer.sign();
        } catch (Exception ex) {
            System.err.println("Error signant les dades: " + ex);
        }
        return signature;
    }

    public static boolean validateSignature(byte[] data, byte[] signature, PublicKey pub) {
        boolean isValid = false;
        try {
            Signature signer = Signature.getInstance("SHA1withRSA");
            signer.initVerify(pub);
            signer.update(data);
            isValid = signer.verify(signature);
        } catch (Exception ex) {
            System.err.println("Error validant les dades: " + ex);
        }
        return isValid;
    }
}

