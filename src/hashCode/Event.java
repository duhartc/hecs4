package hashCode;

public enum Event {
	add ("add"), remove("remove"), add_collec("add_collec"), remove_collec("remove_collec");

	private String name;
	Event (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}