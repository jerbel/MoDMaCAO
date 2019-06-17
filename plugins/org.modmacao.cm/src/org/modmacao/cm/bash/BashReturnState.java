package org.modmacao.cm.bash;

/*
 * copied
 */
public class BashReturnState {
	private int exitValue;
	private String stateMessage;
	
	public String getStateMessage() {
		return stateMessage;
	}

	public int getExitValue() {
		return exitValue;
	}

	public BashReturnState(int exitValue, String stateMessage) {
		this.exitValue = exitValue;
		this.stateMessage = stateMessage;
	}
}
