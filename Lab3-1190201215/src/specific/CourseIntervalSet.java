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
     * 先检查是否符合周期性，再进行插入
     * @param start 开始时间
     * @param end 结束时间
     * @param label 标签
     * @throws PeriodicConflictException 输入时间不是第一个时间段
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