import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyStore;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.util.Scanner;

public class MainEx4 {
    public static void main(String[] args) throws Exception {
        Scanner scanner=new Scanner(System.in);
        LoadKeystore loadKeystore = new LoadKeystore();
        // Path to the keystore
        System.out.println("Introduce el path del keystore");
        String keystorePath = scanner.nextLine();
        // Password to access the keystore
        System.out.println("Introduce la contraseña");
        String keystorePassword = scanner.nextLine();
        // Alias of the key to extract the PublicKey from
        System.out.println("Introduce el alias");
        String keyAlias = scanner.nextLine();
        // Password to access the key
        System.out.println("Introduce la contra de la key");
       // String keyPassword = scanner.nextLine();

        // Load the keystore
        KeyStore keystore = KeyStore.getInstance(KeyStore.getDefaultType());
        FileInputStream keystoreFile = new FileInputStream(keystorePath);
        keystore = loadKeystore.loadKeyStore(keystorePath,keystorePassword);
      //  keystore.load(keystoreFile, keystorePassword.toCharArray());

        // Obtenemos la PublicKey de la clave asimétrica
        PublicKey publicKey =  keystore.getCertificate(keyAlias).getPublicKey();

        // Imprimimos la PublicKey por pantalla
        System.out.println(publicKey);
    }
}

