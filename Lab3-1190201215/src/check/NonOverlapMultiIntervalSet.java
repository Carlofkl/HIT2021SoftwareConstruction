package check;

public interface NonOverlapMultiIntervalSet<L> {
    public void Insert(long start, long end, L label) throws IntervalConflictException;
}

