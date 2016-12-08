package hashCode;

public enum Event {
	addInHash("addInHash"),update("update"), removeFromHash("removeFromHash");

	private String name;
	Event (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}