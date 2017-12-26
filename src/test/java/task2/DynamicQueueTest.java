package task2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DynamicQueueTest {

    private DynamicQueue<String> test;

    @BeforeEach
    public void createQueue(){
        test = new DynamicQueue<String>();
    }

    @Test
    public void testEnqueue_validParam_returnTrue() { assertTrue(test.enqueue("test_string")); }

    @Test
    public void testDequeue_validParam_returnValue() {
        String value = "value";
        test.enqueue(value);
        String result = test.dequeue();
        assertEquals(value, result);
    }

    @Test
    public void testPrintQueue_validParam_returnArrayToString() {
        test.enqueue("12");
        test.enqueue("1");
        test.enqueue("4");
        String output = test.toString();
        String result = test.printQueue();
        assertEquals(output, result);
    }

    @Test
    public void testResize_validParam_returnNewCapacity() { assertTrue(test.reSize()); }
}
