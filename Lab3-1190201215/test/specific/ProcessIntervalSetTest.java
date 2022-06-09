package specific;

import check.IntervalConflictException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProcessIntervalSetTest {

    @Test
    void insert() throws IntervalConflictException {
        ProcessIntervalSet<String> processInterval = new ProcessIntervalSet<>();
        processInterval.Insert(1, 2, "fkl");
        processInterval.Insert(5, 6, "fkl");
        processInterval.Insert(2, 3, "ln");
        assertTrue(processInterval.labels().contains("fkl"));
        assertTrue(processInterval.labels().contains("ln"));
        assertEquals(1, processInterval.intervals("fkl").start(0));
        assertEquals(6, processInterval.intervals("fkl").end(1));
    }

    @Test
    void remove() throws IntervalConflictException {
        ProcessIntervalSet<String> processInterval = new ProcessIntervalSet<>();
        processInterval.Insert(1, 2, "a");
        processInterval.Insert(5, 6, "a");
        processInterval.Insert(2, 3, "b");
        processInterval.remove("a");
        assertFalse(processInterval.labels().contains("a"));
        assertTrue(processInterval.labels().contains("b"));
    }

}