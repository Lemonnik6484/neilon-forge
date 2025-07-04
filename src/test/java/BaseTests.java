import net.snackbag.neilon.NText;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BaseTests {
    @Test
    public void testSimpleText() {
        assertEquals("123", NText.of("123").getString());
    }
}
