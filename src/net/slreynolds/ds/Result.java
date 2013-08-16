package net.slreynolds.ds;

/**
 * A container for the Result from doWork
 *
 * @param <S> Type of object that has memory reference accumulated during
 * doWork
 */
public class Result<S> {

	private final S memoryRef;
	private final int param;
	
	public Result(S s,int param) {
		this.memoryRef = s;
		this.param = param;
	}
	
	public int getIntParam() {
		return param;
	}

	public S getMemoryRef() {
		return memoryRef;
	}
}
