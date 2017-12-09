package task3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LoginProcessorTest {

    private  LoginChecker check;

    @BeforeEach
    public void create(){
        check = new LoginChecker();
    }

    @Test
    public void testLoginProcessor_validParam_returnReverseValue() throws NoSuchFieldException {
        check.setName("Julie");
        LoginProcessor.startProcessor(check);
        String output = check.getLogin();
        String toCompare = new StringBuffer(check.getName()).reverse().toString();
        assertEquals(output,toCompare);
        assertNotNull(output);
        System.out.println(output);
    }
    @Test
    public void testLoginProcessor_nullParam_returnNull() throws NoSuchFieldException {
        check.setName("");
        LoginProcessor.startProcessor(check);
        String output = check.getLogin();
        assertNull(check.getLogin());
        System.out.println(output);
    }
}
