package check;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import set.IntervalSet;

public class NoBlankIntervalSetImpl<L> implements NoBlankIntervalSet<L>{
	
	private final long start;
    private final long end;
    private final IntervalSet<L> intervalSet;

    public NoBlankIntervalSetImpl(long start, long end, IntervalSet<L> intervalSet) {
        this.start = start;
        this.end = end;
        this.intervalSet = intervalSet;
    }

    @Override
    public List<L> sort(){
    	List<L> sortedList = new ArrayList<>();
    	Set<L> tmpSet = new HashSet<>();
    	 long flagTime;
         L tempLabel = null;
         for (int i=0; i<intervalSet.labels().size(); i++) {
        	 flagTime = Long.MAX_VALUE;
        	 for (L l : intervalSet.labels()) {
        		 if (intervalSet.start(l)<flagTime && !tmpSet.contains(l)) {
        			 flagTime = intervalSet.start(l);
        			 tempLabel = l;
        		 }
        	 }
        	 sortedList.add(tempLabel);
        	 tmpSet.add(tempLabel);
         }
         return sortedList;
    }

    @Override
    public boolean checkNoBlank() {
        List<L> sortedList = sort();
        if (sortedList.isEmpty())
        	return false;
        if (!(intervalSet.start(sortedList.get(0)) == start)) 
        	return false;
        if (!(intervalSet.end(sortedList.get(sortedList.size() - 1)) == end)) 
        	return false;
        for (int i = 0; i < sortedList.size() - 1; i++){
            if (!(intervalSet.end(sortedList.get(i)) == intervalSet.start(sortedList.get(i + 1))))
                return false;
        }
        return true;
    }
}
