import javax.crypto.Cipher;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

public class MainEx1 {
    public static void main(String[] args)throws Exception {
        RandomGenerate randomGenerate = new RandomGenerate();
        // Generar un par de claves RSA de 1024 bits
        KeyPair keyPair =  randomGenerate.randomGenerate(1024);
        PublicKey publicKey = keyPair.getPublic();
        PrivateKey privateKey = keyPair.getPrivate();

        // Mostrar información de las claves generadas
        System.out.println("Clave pública: " + publicKey);
        System.out.println("Clave privada: " + privateKey);

        // Introducir mensaje a cifrar por el teclado
        Scanner scanner = new Scanner(System.in);
        System.out.print("Introduce un mensaje a cifrar: ");
        String message = scanner.nextLine();

        // Cifrar el mensaje con la clave pública
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        byte[] encryptedMessage = cipher.doFinal(message.getBytes());
        System.out.print("Mensaje cifrado: ");
        System.out.println(new String(encryptedMessage));

        // Descifrar el mensaje con la clave privada
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] decryptedMessage = cipher.doFinal(encryptedMessage);
        System.out.println("Mensaje descifrado: " + new String(decryptedMessage));
    }


}


