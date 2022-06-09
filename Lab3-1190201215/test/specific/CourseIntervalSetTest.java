package specific;

import check.PeriodicConflictException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseIntervalSetTest {

    @Test
    void insert() throws PeriodicConflictException {
        CourseIntervalSet<String> courseInterval = new CourseIntervalSet<>(1, 7);
        courseInterval.Insert(1, 2, "a");
        courseInterval.Insert(1, 4, "a");
        courseInterval.Insert(3, 5, "b");
        assertTrue(courseInterval.labels().contains("a"));
        assertTrue(courseInterval.labels().contains("b"));
        assertEquals(1, courseInterval.intervals("a").start(0));
        assertEquals(5, courseInterval.intervals("b").end(0));
    }

    @Test
    void remove() throws PeriodicConflictException {
        CourseIntervalSet<String> courseInterval = new CourseIntervalSet<>(1, 7);
        courseInterval.Insert(1, 2, "a");
        courseInterval.Insert(1, 4, "a");
        courseInterval.Insert(3, 5, "b");
        courseInterval.remove("a");
        assertFalse(courseInterval.labels().contains("a"));
        assertTrue(courseInterval.labels().contains("b"));
    }
}