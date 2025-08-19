package vitals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class VitalsCheckerTest {
   @Test
  public void testNotOkWhenAny() throws InterruptedException {
   // English alert
       assertTrue( VitalsChecker.vitalsOk(94.5f, 102f, 85f, Locale.ENGLISH));

        // Spanish alert
        assertTrue(VitalsChecker.vitalsOk(94.5f, 102f, 85f, new Locale("es"));

        // French alert (assuming messages_fr.properties present)
        assertTrue(VitalsChecker.vitalsOk(94.5f, 102f, 85f, Locale.FRENCH));
  }
}



