package set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class MultiIntervalSetTest {

    @Test
    public void MultiIntervalSetTest(){
        IntervalSet<String> tmpInterval = new CommonIntervalSet<>();
        tmpInterval.insert(1, 2, "fkl");
        MultiIntervalSet<String> emptyInstance = new MultiIntervalSet<>(tmpInterval);
        CommonIntervalSet<Integer> tmpIntervals = (CommonIntervalSet<Integer>) emptyInstance.intervals("fkl");
        assertEquals(1, tmpIntervals.start(0));
        assertEquals(2, tmpIntervals.end(0));
    }

    @Test
    public void Insert_intervals_Test() {
        MultiIntervalSet<String> emptyInstance = new MultiIntervalSet<>();
        emptyInstance.insert(1, 2, "fkl");
        emptyInstance.insert(2, 3, "fkl");
        emptyInstance.insert(0, 1, "fkl");
        CommonIntervalSet<Integer> tmpIntervals = (CommonIntervalSet<Integer>) emptyInstance.intervals("fkl");
        assertEquals(0, tmpIntervals.start(0));
        assertEquals(1, tmpIntervals.end(0));
        assertEquals(1, tmpIntervals.start(1));
        assertEquals(2, tmpIntervals.end(1));
        assertEquals(2, tmpIntervals.start(2));
        assertEquals(3, tmpIntervals.end(2));
    }

    @Test
    public void labelsTest(){
        MultiIntervalSet<String> emptyInstance = new MultiIntervalSet<>();
        emptyInstance.insert(0, 1, "fkl");
        emptyInstance.insert(2, 3, "fkl");
        emptyInstance.insert(1, 2, "ln");
        Set<String> tmpSet = new HashSet<>();
        tmpSet.add("fkl");
        tmpSet.add("ln");
        assertTrue(emptyInstance.labels().containsAll(tmpSet));
    }

    @Test
    public void removeTest(){
        MultiIntervalSet<String> emptyInstance = new MultiIntervalSet<>();
        assertFalse(emptyInstance.remove("fkl"));
        emptyInstance.insert(0, 1, "fkl");
        emptyInstance.insert(2, 3, "fkl");
        assertTrue(emptyInstance.labels().contains("fkl"));
        emptyInstance.insert(1, 2, "ln");
        assertTrue(emptyInstance.remove("fkl"));
        assertFalse(emptyInstance.labels().contains("fkl"));
    }

    @Test
    public void toStringTest(){
        MultiIntervalSet<String> tmpMultiIntervalSet = new MultiIntervalSet<>();
        tmpMultiIntervalSet.insert(1, 2, "fkl");
        tmpMultiIntervalSet.insert(2, 3, "fkl");
        tmpMultiIntervalSet.insert(0, 1, "fkl");
        tmpMultiIntervalSet.insert(1, 2, "lnn");
        assertEquals("fkl: 0 = [0, 1] \n" +
                "1 = [1, 2] \n" +
                "2 = [2, 3] \n" +
                "\n" +
                "lnn: 0 = [1, 2] \n" +
                "\n", tmpMultiIntervalSet.toString());
    }
}
