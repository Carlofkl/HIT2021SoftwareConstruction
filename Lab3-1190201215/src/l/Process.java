package l;

public class Process {
	
	private final String pid;
	private final String pName;
	private final long minTime;
	private final long maxTime;
	
	public Process(String pid, String pName, long minTime, long maxTime) {
		this.maxTime = maxTime;
		this.minTime = minTime;
		this.pid = pid;
		this.pName = pName;
	}
	
	public String getPid() {
		return pid;
	}
	
	public String getPname() {
		return pName;
	}
	
	public long getMinTime() {
		return minTime;
	}

	public long getMaxTime() {
		return maxTime;
	}
}