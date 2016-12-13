package enum2;
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
		System.out.println("Previous state: " + this.currentState);
		System.out.println();
		switch (this.currentState) {
		case Init:
			switch (e) {
			case create:
				this.currentState = State.WithEnum;
				break;
			}
			break;
		case WithEnum:
			switch (e) {
			case update:
				this.currentState = State.Updated;
				break;
			}
			break;
		case Updated:
			switch (e) {
			case next:
				this.currentState = State.Error;
				break;
			case hasMore:
				this.currentState = State.Error;
				break;
			}
			break;
		case Error:
			// No need to execute any
			break;
		}
		System.out.println("New state: " + this.currentState);
	}
	

	public Verdict currentVerdict () {
		//System.out.println(this.currentState.getName());
		switch(this.currentState) {
		case Error:
			return Verdict.FALSE;
		default:
			return Verdict.CURRENTLY_TRUE;
		}
	}

	public void emitVerdict () {
		System.out.println("Current verdict: " + currentVerdict());
	}

	public Verdict receiveEvent(Event e) {
		System.out.println("=> Monitor "+this.id+": received event "+e);
		updateState(e);
		emitVerdict();
		return currentVerdict();
	}

}
