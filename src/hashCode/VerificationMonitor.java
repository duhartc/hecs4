package hashCode;
public class VerificationMonitor {
	
	private State currentState;
	public State getCurrentState() {
		return currentState;
	}

	public void setCurrentState(State currentState) {
		this.currentState = currentState;
	}

	private int id;
	
	public VerificationMonitor() {
		this.currentState = State.Init;
		this.id = 0;
	}
	
	public VerificationMonitor(int id) {
		this.currentState = State.Init;
		this.id = id;
	}
	
	
	public void updateState(Event e) {
		switch (this.currentState) {
		case Init:
			switch (e) {
			case addInHash:
				this.currentState = State.IsInHash;
				break;
			}
			break;
		case IsInHash:
			switch (e) {
			case removeFromHash:
				this.currentState = State.Init;
				break;
			case update:
				this.currentState = State.Error;
				break;
			}
			break;
		case Error:
			// No need to execute any
			break;
		}
	}
	

	public Verdict currentVerdict () {
		switch(this.currentState) {
		case Init:
			return Verdict.CURRENTLY_TRUE;
		case IsInHash:
			return Verdict.CURRENTLY_TRUE;
		case Error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
		}
	}

	public void emitVerdict () {
		System.out.println(currentVerdict());
	}

	public Verdict receiveEvent(Event e) {
		System.out.println("=> Monitor "+this.id+": received event "+e);
		updateState(e);
		emitVerdict();
		return currentVerdict();
	}

}
