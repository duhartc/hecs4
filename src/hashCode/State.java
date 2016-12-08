package hashCode;
public enum State {
	IsInHash("IsInHash"), Error("Error"), Init("Init");

	private String name;
	State (String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}