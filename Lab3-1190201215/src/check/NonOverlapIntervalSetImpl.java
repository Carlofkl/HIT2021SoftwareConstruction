package check;

import set.IntervalSet;

public class NonOverlapIntervalSetImpl<L> implements NonOverlapIntervalSet<L> {
	
	private final IntervalSet<L> intervalSet;
	
	public NonOverlapIntervalSetImpl(IntervalSet<L> intervalSet) {
		this.intervalSet = intervalSet;
	}

	@Override
    public void Insert(long start, long end, L label) throws IntervalConflictException{
		boolean flag = true;
		for (L l : intervalSet.labels()) {
			if (intervalSet.start(l)<start && start < intervalSet.end(l)) {
				flag = false;
			}
			if (intervalSet.start(l)<end && end < intervalSet.end(l)) {
				flag = false;
			}
		}
		if (flag == false) {
			throw new IntervalConflictException("There is a time conflict in the interval!");
		}
	}
	
}
