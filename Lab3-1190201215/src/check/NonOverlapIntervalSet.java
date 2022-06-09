package check;

public interface NonOverlapIntervalSet<L> {
	public void Insert(long start, long end, L label) throws IntervalConflictException;
}
