package specific;

import java.util.List;
import java.util.Set;

import check.IDutyIntervalSet;
import check.IntervalConflictException;
import check.NoBlankIntervalSet;
import check.NoBlankIntervalSetImpl;
import check.NonOverlapIntervalSet;
import check.NonOverlapIntervalSetImpl;
import check.PeriodicConflictException;
import l.Employee;
import set.CommonIntervalSet;
import set.IntervalSet;
import set.MultiIntervalSet;

public class DutyIntervalSet<L> extends CommonIntervalSet<L> implements IDutyIntervalSet<L> {

    private final NonOverlapIntervalSet<L> nois;
    private final NoBlankIntervalSet<L> nbis;
    private final IntervalSet<L> intervalSet = new CommonIntervalSet<>();
    private final MultiIntervalSet<L>  multiIntervalSet = new MultiIntervalSet<>();

    // Abstraction function:
    // Represent a dutyInterval with Employee, arrangement and time

    // Representation invariant:
    // there are no blanks, no overlap and no periodic in arrangement

    // Safety from rep exposure:
    // All fields are private final

    public DutyIntervalSet(long startTime, long endTime) {
        this.nois = new NonOverlapIntervalSetImpl<>(intervalSet);
        this.nbis = new NoBlankIntervalSetImpl<>(startTime, endTime, intervalSet);
    }

    /**
     *¼ì²éÊÇ·ñ´æÔÚ¿Õ°×
     */
    @Override
    public boolean checkNoBlank() {
        return nbis.checkNoBlank();
    }

 
    @Override
    public void Insert(long start, long end, L label) throws IntervalConflictException{
        nois.Insert(start, end, label);
        intervalSet.insert(start, end, label);
    }

    @Override
    public Set<L> labels() {
        return intervalSet.labels();
    }

    @Override
    public boolean remove(L label) {
        return intervalSet.remove(label);
    }

    @Override
    public long start(L label) {
        return intervalSet.start(label);
    }

    @Override
    public long end(L label) {
        return intervalSet.end(label);
    }

    @Override
    public String toString() {
        return intervalSet.toString();
    }

    @Override
    public List<L> sort() {
        return nbis.sort();
    }

	@Override
	public void checkNonPeriodic(long start, long end) throws PeriodicConflictException {
		// TODO Auto-generated method stub
		
	}

	public IntervalSet<Integer> intervals(L label) {
		// TODO Auto-generated method stub
		return multiIntervalSet.intervals(label);
	}
}