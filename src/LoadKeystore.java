import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;

public class LoadKeystore {
    public KeyStore loadKeyStore(String ksFile, String ksPwd) throws Exception {
        KeyStore ks = KeyStore.getInstance("JCEKS");
        File f = new File(ksFile);
        if (f.isFile()) {
            FileInputStream in = new FileInputStream (f);
            ks.load(in, ksPwd.toCharArray());
        }
        return ks;
    }
}
