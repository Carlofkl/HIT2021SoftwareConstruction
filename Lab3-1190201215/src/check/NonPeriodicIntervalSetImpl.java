package check;

public class NonPeriodicIntervalSetImpl<L> implements NonPeriodicIntervalSet<L>{
	
	private final long begin;
	private final long length;
	
	public NonPeriodicIntervalSetImpl(long begin, long length) {
		this.begin = begin;
		this.length = length;
	}
	
	@Override
	public void checkNonPeriodic(long start, long end) throws PeriodicConflictException{
		if (end>begin + length || start < begin) {
			throw new PeriodicConflictException("The interval can not be inserted in this period!");
		}
	}
}
