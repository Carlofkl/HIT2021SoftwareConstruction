package set;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CommonIntervalSet<L> implements IntervalSet<L>{
	
	//TODO fields
	private final Map<L, List<Long>> labelsMap = new HashMap<>();
	// Abstract function:
	// represent a intervalSet with start, end, name
	
	// Representation invariant:
	// the end is larger than start
	// the start > 0
	
	// Safety from rep exposure
	// all fields are private final
	
	//TODO constructor
	public CommonIntervalSet(){
		
	}
	
	//TODO checkRep
	private void checkRep() {
		for (List<Long> tmp : labelsMap.values()) {
			if (tmp.get(0) < 0 || tmp.get(0) > tmp.get(1)) {
				assert false;
				break;
			}
		}
	}
	
	@Override
	public void insert(long start, long end, L label){
		List<Long> interval = new ArrayList<>();
		interval.add(start);
		interval.add(end);
		labelsMap.put(label, interval);
		checkRep();
	}
	
	@Override
	public boolean remove(L label) {
		if (!labelsMap.containsKey(label))
			return false;
		labelsMap.remove(label);
		return true;
	}
	
	@Override
	public Set<L> labels() {
		return new HashSet<L>(labelsMap.keySet());
	}
	
	@Override
	public long end(L label) {
		if (!labelsMap.containsKey(label))
			return -1;
		return labelsMap.get(label).get(1);
	}
	
	@Override
	public long start(L label) {
		if (!labelsMap.containsKey(label))
			return -1;
		return labelsMap.get(label).get(0);
	}
	
	@Override
	public String toString() {
		String tmp = "";
		for (L l : labelsMap.keySet()) {
			tmp += l.toString() + " = [" + labelsMap.get(l).get(0)
					+ ", " + labelsMap.get(l).get(1) + "] \n";
		}
		return tmp;
	}
}
