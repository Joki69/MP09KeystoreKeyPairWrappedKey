import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.io.FileOutputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.util.Enumeration;
import java.util.Scanner;

public class MainEx2 {
    public static void main(String[] args)throws Exception {
      //2.1
        LoadKeystore loadKeystore= new LoadKeystore();
        Scanner scanner=new Scanner(System.in);

        // Indica la ubicación del keystore y la contraseña
        System.out.println("Introduce la ruta de tu fichero keystores");
        String ksFile = scanner.nextLine();
        System.out.println("Introduce tu contraseña");
        String ksPwd = scanner.nextLine();

            // Carga el keystore
            KeyStore ks=loadKeystore.loadKeyStore(ksFile, ksPwd);


            // Obtiene el tipo de keystore
            String type = ks.getType();
            System.out.println("Tipo de keystore: " + type);

            // Obtiene el tamaño del almacén
            int size = ks.size();
            System.out.println("Tamaño del almacén: " + size);

            // Obtiene los aliases de las claves almacenadas
            Enumeration<String> aliases = ks.aliases();
            System.out.println("Aliases de las claves almacenadas:");
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                System.out.println("- " + alias);
            }

            // Obtiene el certificado de una de las claves
            System.out.println("Introduce tu alias");
            String keyAlias = scanner.nextLine();
            Certificate cert = ks.getCertificate(keyAlias);
            System.out.println("Certificado de la clave " + keyAlias + ":");
            System.out.println(cert);

            // Obtiene el algoritmo de cifrado de una de las claves
            String keyAlgorithm = ks.getKey(keyAlias, ksPwd.toCharArray()).getAlgorithm();
            System.out.println("Algoritmo de cifrado de la clave " + keyAlias + ": " + keyAlgorithm);


        //2.2
        // Genera una nueva clave simétrica
        System.out.println("Ahora se creara una nueva clave simtrica:");
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();

        // Guarda la clave simétrica en el KeyStore
        KeyStore.SecretKeyEntry entry = new KeyStore.SecretKeyEntry(secretKey);
        KeyStore.ProtectionParameter protParam = new KeyStore.PasswordProtection(ksPwd.toCharArray());
        System.out.println("Introduce el alias para las nuevas claves:");
        ks.setEntry(scanner.nextLine(), entry, protParam);

        // Guarda el KeyStore en el archivo
        FileOutputStream out = new FileOutputStream(ksFile);
        ks.store(out, ksPwd.toCharArray());
        out.close();

        // Imprime la información del KeyStore
        System.out.println("Tipo de KeyStore: " + ks.getType());
        System.out.println("Número de claves en el KeyStore: " + ks.size());

        // Obtiene los aliases de las claves almacenadas
        System.out.println("Aliases de las claves almacenadas:");
        while (aliases.hasMoreElements()) {
            String alias = aliases.nextElement();
            System.out.println("- " + alias);
        }
        SecretKey loadedKey = (SecretKey) ks.getKey(keyAlias, ksPwd.toCharArray());
        System.out.println("Algoritmo de cifrado: " + loadedKey.getAlgorithm());

    }
}



