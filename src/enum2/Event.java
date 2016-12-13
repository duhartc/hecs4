package enum2;
public enum Event {
	hasMore("hasMore"), next("next"), create("create"), update("update");

	private String name;
	Event (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}