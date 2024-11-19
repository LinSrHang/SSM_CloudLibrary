package to404hanga;

import com.to404hanga.utils.VerifyCode;
import org.junit.jupiter.api.Test;

public class TestVerifyCode {
    @Test
    public void testGenVerifyCode() {
        for (int i = 1; i < 6; i++) {
            System.out.println(VerifyCode.genVerifyCode(i));
        }
        for (int i = 1; i < 6; i++) {
            System.out.println(VerifyCode.genVerifyCode(4));
        }
    }
}
