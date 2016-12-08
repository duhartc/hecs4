package enums;
public enum State {
	DoHasMoreElements("DoHasMoreElements"), DoNextElement("DoNextElement"), Error("Error");

	private String name;
	State (String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
}