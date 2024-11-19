package to404hanga;

import com.to404hanga.utils.RedisPoolUtils;
import org.junit.jupiter.api.Test;
import redis.clients.jedis.Jedis;

public class TestRedisPoolUtils {
    @Test
    public void testRedis() {
        Jedis redis = RedisPoolUtils.getJedis();
        if (redis == null) {
            System.out.println("redis is null");
            return;
        }
        redis.set("k", "v");
        System.out.println(redis.get("k"));
        redis.close();
    }
}
