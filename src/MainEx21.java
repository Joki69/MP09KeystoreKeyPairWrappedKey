import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.PrivateKey;
import java.security.PublicKey;

public class MainEx21 {





    public static byte[][] encryptWrappedData(byte[] data, PublicKey pub) {
        byte[][] encWrappedData = new byte[2][];
        try {
            // Genera una clave simetrica (AES de 128 bits)
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            kgen.init(128);
            SecretKey sKey = kgen.generateKey();

            //Algoritmo de cifrado simetrico
            Cipher cipher = Cipher.getInstance("AES");


            //Datos cifradoscon clave simetrica
            cipher.init(Cipher.ENCRYPT_MODE, sKey);
            byte[] encMsg = cipher.doFinal(data);

            //Algoristmo asimetrico para cifrar clave simetrica
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");

            //Clabe publica de B para cifrar la clave simetrica
            cipher.init(Cipher.WRAP_MODE, pub);
            byte[] encKey = cipher.wrap(sKey);

            // Datos y clave simetrica
            encWrappedData[0] = encMsg;
            encWrappedData[1] = encKey;

        } catch (Exception  ex) {
            System.err.println("Ha succeït un error xifrant: " + ex);
        }
        // Devuelve el array que contiene tanto los datos cifrados con AES como la clave envuelta con RSA
        return encWrappedData;
    }


    public static byte[] decryptWrappedData(byte[][] data, PrivateKey priv) {
        byte[] decUnwrappedData = new byte[0];
        try {
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            //  Desciframos la clave del parametro data[1] con la clave privada
            cipher.init(Cipher.UNWRAP_MODE, priv);
            SecretKey secretkey = (SecretKey) cipher.unwrap(data[1], "AES", Cipher.SECRET_KEY);
            // Cramos un Chiper auxiliar para descifrar el mensaje
            Cipher cipher2 = Cipher.getInstance("AES");
            cipher2.init(Cipher.DECRYPT_MODE, secretkey);
            // El mensaje cifrado
            decUnwrappedData = cipher2.doFinal(data[0]);
        } catch (Exception  ex) {
            System.err.println("Ha succeït un error xifrant: " + ex);
        }
        return decUnwrappedData;

    }
}
