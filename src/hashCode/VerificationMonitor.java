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
		this.currentState = State.Error;
		this.id = 0;
	}
	
	public VerificationMonitor(int id) {
		this.currentState = State.Error;
		this.id = id;
	}
	
	
	public void updateState(Event e) {
		switch (this.currentState) {
		case DoHasNext:
			switch (e) {
			case add:
				this.currentState = State.DoNext;
				break;
			case remove:
				this.currentState = State.Error;
				break;
			}
			break;
		case DoNext:
			switch (e) {
			case add:
				break;
			case remove:
				this.currentState = State.DoHasNext;
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
		System.out.println(currentVerdict());
	}

	public Verdict receiveEvent(Event e) {
		System.out.println("=> Monitor "+this.id+": received event "+e);
		updateState(e);
		emitVerdict();
		return currentVerdict();
	}

}
