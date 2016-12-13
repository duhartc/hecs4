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
		// Replaced State.error
		this.currentState = State.DoHasNext;
		this.id = 0;
	}
	
	public VerificationMonitor(int id) {
		this.currentState = State.DoHasNext;
		this.id = id;
	}
	
	
	public void updateState(Event e) {
		System.out.println("Previous state: " + this.currentState);
		System.out.println();
		switch (this.currentState) {
		case DoHasNext:
			switch (e) {
			case hasNext:
				this.currentState = State.DoNext;
				break;
			case next:
				this.currentState = State.Error;
				break;
			}
			break;
		case DoNext:
			switch (e) {
			case hasNext:
				break;
			case next:
				this.currentState = State.DoHasNext;
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
		case DoHasNext:
			return Verdict.CURRENTLY_TRUE;
		case DoNext:
			return Verdict.CURRENTLY_TRUE;
		case Error:
			return Verdict.FALSE;
		default:
			return Verdict.FALSE;
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
