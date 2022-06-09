package check;

import set.CommonIntervalSet;
import set.MultiIntervalSet;

public class NonOverlapMultiIntervalSetImpl<L> implements NonOverlapMultiIntervalSet<L> {

    private final MultiIntervalSet<L> multiIntervalSet;

    public NonOverlapMultiIntervalSetImpl(MultiIntervalSet<L> multiIntervalSet) {
        this.multiIntervalSet = multiIntervalSet;
    }

    public void Insert(long start, long end, L label) throws IntervalConflictException{
        for (L tempLabel : multiIntervalSet.labels()) {
            CommonIntervalSet<Integer> intervalSet = (CommonIntervalSet<Integer>) multiIntervalSet.intervals(tempLabel);
            for (int i = 0; i < intervalSet.labels().size(); i++) {
            	boolean flag = true;
                if ( intervalSet.start(i) < start && start < intervalSet.end(i)){
                    flag = false;
                }
                if (intervalSet.start(i) < end && end < intervalSet.end(i)) {
                	flag = false;
                }
                if (flag == false) {
                	throw new IntervalConflictException("不允许存在重叠，无法插入");
                }
            }
        }
    }
}
