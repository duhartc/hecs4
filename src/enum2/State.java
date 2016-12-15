package enum2;
public enum State {
	Init("Init"), WithEnum("WithEnum"), Updated("Updated"), Error("Error");

	private String name;
	State (String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}