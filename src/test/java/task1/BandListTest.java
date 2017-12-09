package task1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BandListTest {

    private BandList<String> testList;

    @BeforeEach
    public void createList() {
        testList = new BandList<String>();
    }

    @Test
    public void testAdd_validParam_returnTrue() {

        assertTrue(testList.add("test"));

    }

    @Test
    public void testAdd_wrongParam_returnFalse() {
        int x = testList.size();
        assertFalse(testList.add(null));
        assertEquals(x, testList.size());

    }

    @Test
    public  void testRemoveValue_validParam_returnTrue() {
        testList.add("test1");
        assertTrue(testList.remove("test1"));

    }

    @Test
    public  void testRemoveValue_wrongParam_returnFalse() {
        testList.add("smth");
        testList.add(null);
        String y = "value";
        assertFalse(testList.remove(y));

    }

    @Test
    public void testRemoveIndex_validParam_returnTrue() {
        int x = 2;
        testList.add("1");
        testList.add("two");
        testList.add("three");
        assertTrue(testList.remove(x));
    }

    @Test
    public void testRemoveIndex_zeroParam_returnTrue() {
        int x = 0;
        testList.add("smth");
        testList.add("smth");
        assertTrue(testList.remove(x));
    }

    @Test
    public void testGetValue_validParam_returnValue(){
        String value = "value";
        testList.add(value);
        String result = testList.get(0);
        assertTrue(value.equals(result));

    }

    @Test
    public void testGetValue_wrongParam_returnFalse() {
        assertThrows(NullPointerException.class, () -> testList.get(2));
    }

    @Test
    public void testIsEmpty_emptyList_returnTrue() {
        assertTrue(testList.isEmpty());
    }

    @Test
    public void testIsEmpty_notEmptyList_returnFalse() {
        testList.add("value");
        assertFalse(testList.isEmpty());
    }

    @Test
    public void testSize_validParam_returnTrue() {
        assertEquals(0, testList.size());
        testList.add("smth");
        testList.add("smth");
        assertEquals(2, testList.size());
    }
}
