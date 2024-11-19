package to404hanga;

import com.to404hanga.utils.SHA256Utils;
import org.junit.jupiter.api.Test;

public class TestMd5Utils {
    @Test
    public void testEncrypt() {
        String[] pwds = new String[]{"123", "ABC", "abc"};
        for (String pwd : pwds) {
            System.out.println(pwd);
            System.out.println(SHA256Utils.encrypt(pwd));
            System.out.println("================================");
        }
    }
}
