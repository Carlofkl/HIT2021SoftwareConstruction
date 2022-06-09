package set;

import java.util.Set;

public interface IntervalSet<L> {
	
	/**
	 * create an empty set
	 * 
	 * @param <L> is empolyee or pid or course, must be immutable
	 * @return new empty set
	 */
	public static <L> IntervalSet<L> empty() {
		return new CommonIntervalSet<L>();
	}
	
	/**
	 * get all names of labels
	 * 
	 * @return a set of names
	 */
	public Set<L> labels();
	
	/**
	 * choose a suitable interval and insert a new interval and label
	 * 
	 * @param start
	 * @param end
	 * @param label
	 */
	public void insert(long start, long end, L label);
	
	/**
	 * remove a label from current object
	 * 
	 * @param label
	 * @return true if set contains label, otherwise return false 
	 */
	public boolean remove(L label); 
	
	/**
	 * get the end time of the label's interval
	 * 
	 * @param label
	 * @return the end time (long) if the object contains label, otherwise return -1
	 */
	public long end(L label); 
	
	/**
	 * get the start time of the label's interval
	 * 
	 * @param label
	 * @return the start time (long) if the object contains label, otherwise return -1
	 */
	public long start(L label); 
}
