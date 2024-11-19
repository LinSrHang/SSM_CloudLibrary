package to404hanga;

import com.to404hanga.utils.VerifyEmail;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class TestVerifyEmail {
    @Test
    public void testIsValid() {
        List<String> emails = new ArrayList<>();
        emails.add("fdg.123@163.com");
        emails.add("hdfh.abc@qq.com");
        for (String email : emails) {
            System.out.println(email);
            System.out.println(VerifyEmail.isValid(email));
        }
    }
}
