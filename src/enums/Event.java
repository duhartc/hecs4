package enums;

public enum Event {
	hasMoreElements("hasMoreElements"), nextElement("nextElement"), elements("elements"), update("update");

	private String name;
	Event (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}