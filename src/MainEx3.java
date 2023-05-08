import java.io.FileInputStream;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class MainEx3 {
    public static void main(String[] args)throws Exception {
        Scanner scanner=new Scanner(System.in);

        System.out.println("Introduce la ruta del fichero .cer");
        try {
                PublicKey publicKey = getPublicKeyFromCert(scanner.nextLine());
                System.out.println("Algorithm: " + publicKey.getAlgorithm());
                System.out.println("Format: " + publicKey.getFormat());
                System.out.println("Encoded: " + publicKey.getEncoded());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
    }

        public static PublicKey getPublicKeyFromCert(String filePath) throws Exception {
        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        FileInputStream is = new FileInputStream(filePath);
        X509Certificate cer = (X509Certificate) fact.generateCertificate(is);
        PublicKey key = cer.getPublicKey();
        is.close();
        return key;
    }

}
