package enums;

public enum Event {
	hasMoreElements("hasMoreElements"), nextElement("nextElement"), elements("elements"), update("update"), error("error");

	private String name;
	Event (String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
}