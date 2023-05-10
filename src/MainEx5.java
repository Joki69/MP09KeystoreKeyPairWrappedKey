import java.io.FileInputStream;
import java.security.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.Scanner;

public class MainEx5 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        RandomGenerate randomGenerate = new RandomGenerate();
        try {
            // Generar un par de claves RSA de 2048 bits
            KeyPair keyPair =  randomGenerate.randomGenerate(2048);
            PrivateKey privateKey = keyPair.getPrivate();

            // Define los datos a firmar
            System.out.println("Introduce el mensaje a firmar");
            String message = scanner.nextLine();
            byte[] data = message.getBytes("UTF-8");

            // Firma los datos con la clave privada
            byte[] signature = signData(data, privateKey);

            // Imprime la firma por pantalla
            System.out.println(Arrays.toString(signature));
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
}
