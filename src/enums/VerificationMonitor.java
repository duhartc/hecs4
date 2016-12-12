package enums;
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
		this.currentState = State.Init;
		this.id = 0;
	}
	
	public VerificationMonitor(int id) {
		this.currentState = State.Init;
		this.id = id;
	}
	
	
	public void updateState(Event e) {
		/*
		System.out.println("Previous state: " + this.currentState);
		System.out.println();
		switch (this.currentState) {
		case DoHasMoreElements:
			switch (e) {
			case hasMoreElements:
				this.currentState = State.DoNextElement;
				break;
			case nextElement:
				this.currentState = State.Error;
				break;
			}
			break;
		case DoNextElement:
			switch (e) {
			case hasMoreElements:
				break;
			case nextElement:
				this.currentState = State.DoHasMoreElements;
				break;
			}
			break;
		case Error:
			// No need to execute any
			break;
		}
		System.out.println("New state: " + this.currentState);
		*/
		
		
		switch (this.currentState) {
		case Init:
			switch(e) {
			case update:
				this.currentState = State.Updated;
				break;
			case elements:
				this.currentState = State.UpToDate;
				break;
			}

			break;
		case UpToDate:
			switch(e) {
			case update:
				this.currentState = State.Updated;
				break;
			}
			break;
		}
		
		// Special end state for errors
		if(e == Event.error) {
			this.currentState = State.Error;
		}
	}
	

	public Verdict currentVerdict () {
		//System.out.println(this.currentState.getName());
		if (this.currentState != State.Error) {
			return Verdict.CURRENTLY_TRUE;
		}
		return Verdict.FALSE;
		
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
