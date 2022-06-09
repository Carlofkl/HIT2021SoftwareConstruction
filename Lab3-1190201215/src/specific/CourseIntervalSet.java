package specific;

import java.util.Set;

import check.NonPeriodicIntervalSet;
import check.NonPeriodicIntervalSetImpl;
import check.PeriodicConflictException;
import set.IntervalSet;
import set.MultiIntervalSet;

public class CourseIntervalSet<L> extends MultiIntervalSet<L> {
	
    private final NonPeriodicIntervalSet<L> npis;
    private final MultiIntervalSet<L> multiIntervalSet = new MultiIntervalSet<>();

    public CourseIntervalSet(long periodStart, long periodLength) {
        npis = new NonPeriodicIntervalSetImpl<>(periodStart, periodLength);
    }

    /**
     * �ȼ���Ƿ���������ԣ��ٽ��в���
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
     * @throws PeriodicConflictException ����ʱ�䲻�ǵ�һ��ʱ���
     */

    public void Insert(long start, long end, L label) {
        //npis.checkNonPeriodic(start, end);
        multiIntervalSet.insert(start, end, label);
    }

    @Override
    public Set<L> labels() {
        return multiIntervalSet.labels();
    }

    @Override
    public boolean remove(L label) {
        return multiIntervalSet.remove(label);
    }

    @Override
    public IntervalSet<Integer> intervals(L label) {
        return multiIntervalSet.intervals(label);
    }

    @Override
    public String toString() {
        return multiIntervalSet.toString();
    }

}