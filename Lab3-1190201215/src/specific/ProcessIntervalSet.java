package specific;

import java.util.Set;

import check.IProcessIntervalSet;
import check.IntervalConflictException;
import check.NonOverlapMultiIntervalSet;
import check.NonOverlapMultiIntervalSetImpl;
import set.IntervalSet;
import set.MultiIntervalSet;

public class ProcessIntervalSet<L> extends MultiIntervalSet<L> implements IProcessIntervalSet<L> {

    private final NonOverlapMultiIntervalSet<L> noms;
    private final MultiIntervalSet<L> multiIntervalSet = new MultiIntervalSet<>();

    // Abstraction function:
    // Represent a dutyInterval with Process and arrangement

    // Representation invariant:
    // No overlap, no periodic

    // Safety from rep exposure:
    // All fields are private final, and safety from rep exposure.

    public ProcessIntervalSet() {
        noms = new NonOverlapMultiIntervalSetImpl<>(multiIntervalSet);
    }

    /**
     * ����ʱ����Ƿ�����ص�
     * @param start ��ʼʱ��
     * @param end ����ʱ��
     * @param label ��ǩ
     * @throws IntervalConflictException �����ص�
     */
    @Override
    public void Insert(long start, long end, L label) throws IntervalConflictException {
        noms.Insert(start, end, label);
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
