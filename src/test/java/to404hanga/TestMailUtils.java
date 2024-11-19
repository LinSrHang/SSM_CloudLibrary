package to404hanga;

import com.to404hanga.utils.MailUtils;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

public class TestMailUtils {
    @Test
    public void testSend() {
        Set<String> set = new HashSet<>();
        set.add("1023948565@qq.com");
        MailUtils.sendEmail(set, "测试标题", "测试内容");
    }
}
