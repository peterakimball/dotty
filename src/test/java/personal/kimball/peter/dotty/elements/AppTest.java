package personal.kimball.peter.dotty.elements;

import org.junit.Assert;
import org.junit.Test;

import personal.kimball.peter.dotty.App;

public class AppTest {

    @Test
    public void testNFromOneToThree() {
        String[] args = new String[1];

        for (int i = 1; i < 4; i++) {
            args[0] = Integer.toString(i);
            int result = App.main(args);
            Assert.assertEquals("Expect that the app is successful for N=" + i, 0, result);
        }
    }

}
