package set;



import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommonIntervalSetTest {

    @Test
    public void InsertTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        commonInterval.insert(1, 2, "a");
        commonInterval.insert(2, 3, "b");
        assertEquals(1, commonInterval.start("a"));
        assertEquals(2, commonInterval.end("a"));
        assertEquals(2, commonInterval.start("b"));
        assertEquals(3, commonInterval.end("b"));
    }

    @Test
    public void labelsTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        commonInterval.insert(0, 1, "fkl");
        commonInterval.insert(1, 2, "ln");
        Set<String> tempSet = new HashSet<>();
        tempSet.add("fkl");
        tempSet.add("ln");
        assertTrue(commonInterval.labels().containsAll(tempSet));
    }

    public void removeTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        assertFalse(commonInterval.remove("fkl"));
        commonInterval.insert(1, 2, "fkl");
        assertTrue(commonInterval.labels().contains("fkl"));
        commonInterval.insert(2, 3, "ln");
        assertTrue(commonInterval.remove("fkl"));
        assertFalse(commonInterval.labels().contains("fkl"));
    }

    @Test
    public void startTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        commonInterval.insert(1, 2, "fkl");
        assertEquals(1, commonInterval.start("fkl"));
    }

    @Test
    public void endTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        commonInterval.insert(1, 2, "ln");
        assertEquals(2, commonInterval.end("ln"));
    }

    @Test
    public void toStringTest(){
        CommonIntervalSet<String> commonInterval = new CommonIntervalSet<>();
        commonInterval.insert(1, 2, "fkl");
        commonInterval.insert(2, 3, "ln");
        assertEquals("ln = [2, 3] \n" + "fkl = [1, 2] \n", commonInterval.toString());
    }
}
