import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.util.concurrent.TimeUnit;

public class MainTest {
    @Disabled("Ручное включение.")
    @Test
    @Timeout(value = 22, unit = TimeUnit.SECONDS)
    public void testMain() throws Exception {
        Main.main(new String[]{});
    }

}
