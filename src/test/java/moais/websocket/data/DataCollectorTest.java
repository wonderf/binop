package moais.websocket.data;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Stas on 21.12.2016.
 */
public class DataCollectorTest {
    @Test
    public void getData() throws Exception {
        System.out.println(DataCollector.getInstance().getData());
        Assert.fail();
    }

}