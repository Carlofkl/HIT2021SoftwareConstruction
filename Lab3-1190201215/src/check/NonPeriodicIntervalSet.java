package check;

public interface NonPeriodicIntervalSet<L> {
	
	public void checkNonPeriodic(long start, long end) throws PeriodicConflictException;

}
