package specific;

import check.*;


import java.util.List;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DutyIntervalSetTest  {

    /**testing strategy
     *
     */

    @Test
    public void InsertTest() throws IntervalConflictException {
        DutyIntervalSet<String> dutyInterval = new DutyIntervalSet<>(1, 7);
        dutyInterval.Insert(2, 5, "b");
        dutyInterval.Insert(1, 2, "a");
        assertFalse(dutyInterval.checkNoBlank());
        dutyInterval.Insert(5, 7, "c");
        assertTrue(dutyInterval.checkNoBlank());
        assertEquals(1, dutyInterval.start("a"));
        assertEquals(2, dutyInterval.end("a"));
        assertEquals(2, dutyInterval.start("b"));
        assertEquals(5, dutyInterval.end("b"));
        assertEquals(5, dutyInterval.start("c"));
        assertEquals(7, dutyInterval.end("c"));
    }

    @Test
    public void RemoveTest() throws IntervalConflictException {
        DutyIntervalSet<String> dutyInterval = new DutyIntervalSet<>(1, 7);
        dutyInterval.Insert(1, 2, "a");
        dutyInterval.Insert(2, 5, "b");
        dutyInterval.remove("a");
        assertFalse(dutyInterval.labels().contains("a"));
        assertTrue(dutyInterval.labels().contains("b"));
    }

    @Test
    public void sortTest() throws IntervalConflictException {
        DutyIntervalSet<String> dutyInterval = new DutyIntervalSet<>(1, 7);
        dutyInterval.Insert(1, 2, "b");
        dutyInterval.Insert(2, 5, "a");
        List<String> sorted = dutyInterval.sort();
        assertEquals(2, dutyInterval.start(sorted.get(1)));
    }
}
