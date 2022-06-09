package set;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class MultiIntervalSet<L> {
	
	//TODO fields
	private final Map<L, IntervalSet<Integer>> multiIntervalMap = new HashMap<>();
	
	// Abstract function:
	// represent a MultiIntervalSet with start, end and IntervalSet<Integer>
	
	// Representation invariant:
	// the end is bigger than start, the start > 0
	
	// Safety from rep exposure:
	// all fields are private final
	
	//TODO checkRep
	private void checkRep(){
        for (L l : multiIntervalMap.keySet()){
            CommonIntervalSet<Integer> intervalMap = (CommonIntervalSet<Integer>) multiIntervalMap.get(l);
            for (int i = 0; i < intervalMap.labels().size(); i++) {
                if (intervalMap.start(i) < 0 || intervalMap.start(i) > intervalMap.end(i)){
                    assert false;
                    return;
                }
            }
        }
	}
	
	//TODO constructor
	public MultiIntervalSet() {
		
	}

	/**
	 * initialize a multiIntervalSet, use the data in initial to fulfill the multiIntervalSet
	 * the label in multiIntervalSet is the label in intervalSet 
	 * @param initial is intervalSet, put initial into multiIntervalSet
	 */
	public MultiIntervalSet(IntervalSet<L> initial) { 
		for (L tmpLabel : initial.labels()) {
			IntervalSet<Integer> tmpIntervalSet = new CommonIntervalSet<>();
			tmpIntervalSet.insert(initial.start(tmpLabel), initial.end(tmpLabel), 0);
			multiIntervalMap.put(tmpLabel, tmpIntervalSet);
		}
	}
	
	/**
	 * if multiIntervalMap contains label, we search a suitable place for the new label and its interval. 
	 * it should be placed in chronological order
	 * if multiIntervalMap does not contain label, we just put a new intervalset in it
	 * @param start
	 * @param end
	 * @param label
	 */
	public void insert(long start, long end, L label) {
		if (multiIntervalMap.containsKey(label)) {
			IntervalSet<Integer> tmpInterval =  multiIntervalMap.get(label);
			int n = tmpInterval.labels().size();
			if (end <= tmpInterval.start(0)) {
				//如果在所有时间段最前面
				for (int j=n ; j>0; j--) {
					tmpInterval.insert(tmpInterval.start(j-1), tmpInterval.end(j-1), j);
				}
				tmpInterval.insert(start, end, 0);
				multiIntervalMap.put(label, tmpInterval);
				return;
			}
			else if (start >= tmpInterval.end(n-1)){
				//如果在所有时间段后面
				tmpInterval.insert(start, end, n);
				multiIntervalMap.put(label, tmpInterval);
			}
			
			for (int i=1; i<n; i++) {
				//如果在第二个到最后一个时间段中间
				if (start>=tmpInterval.end(i-1) && end<=tmpInterval.start(i)) {
					for (int j=n; j>i; j--) {
						tmpInterval.insert(tmpInterval.start(j-1), tmpInterval.end(j-1), j);
					}
					tmpInterval.insert(start, end, i);
				}
				multiIntervalMap.put(label, tmpInterval);
				return;
			}
			//没有合适时间段，即有重叠
			return;
		}
		
		else {
			IntervalSet<Integer> tmpInterval = new CommonIntervalSet<>();
			tmpInterval.insert(start, end, 0);
			multiIntervalMap.put(label, tmpInterval);
		}
		checkRep();
	}
	
	public Set<L> labels(){
		return new HashSet<L>(multiIntervalMap.keySet()); 
	}
	
	public boolean remove(L label) {
		if(!multiIntervalMap.containsKey(label)) {
			return false;
		}
		multiIntervalMap.remove(label);
		return true;
	}
	
	public IntervalSet<Integer> intervals(L label){
		return multiIntervalMap.get(label);
	}
	
	public String toString(){
        String tempString = "";
        for (L tempLabel : multiIntervalMap.keySet()){
            tempString += tempLabel.toString() + ": " +multiIntervalMap.get(tempLabel).toString() + "\n";
        }
        return tempString;
    }
}
