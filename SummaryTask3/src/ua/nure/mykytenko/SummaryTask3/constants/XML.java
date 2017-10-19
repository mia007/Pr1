package ua.nure.mykytenko.SummaryTask3.constants;

/**
 * Holds entities declared in XSD document.
 * 
 */

public enum XML {
	// elements names
	PLANES("Planes"),

	PLANE("Plane"),

	MODEL("model"), ORIGIN("origin"), CHARS("chars"),
	// chars elements names
	AMMUNITION("ammunition"), SEATS("seats"), TYPE("type"), RADAR("radar"),

	PARAMETERS("parameters"),
	// parameters elements names
	WIDTH("width"), HEIGHT("height"), LENGTH("length"),

	PRICE("price"),

	// attribute names
	UNIT("unit"), ROCKET("rocket");

	private String value;

	XML(String value) {
		this.value = value;
	}
	
	/**
	 * Determines if a name is equal to the string value wrapped by this enum element.<br/>
	 * If a SAX/StAX parser make all names of elements and attributes interned you can use
	 * <pre>return value == name;</pre> instead <pre>return value.equals(name);</pre>
	 * @param name string to compare with value. 
	 * @return value.equals(name)
	 */
	public boolean equalsTo(String name) {
		return value.equals(name);
	}

	public String getValue() {
		return value;
	}
}