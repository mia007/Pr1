package ua.nure.mykytenko.SummaryTask3.entity.plane;

/**
 * Describes the plane entity
 * 
 */
public class Plane {
	
	private String planeModel;
	private String planeOrigin;
	private Chars planeChars;
	private Parameters planeParameters;
	private Price planePrice;

	public Plane() {

	}

	public String getModel() {
		return planeModel;
	}

	public void setModel(String value) {
		this.planeModel = value;
	}

	public String getOrigin() {
		return planeOrigin;
	}

	public void setOrigin(String value) {
		this.planeOrigin = value;
	}

	public Chars getChars() {
		return planeChars;
	}

	public void setChars(Chars value) {
		this.planeChars = value;
	}

	public Parameters getParameters() {
		return planeParameters;
	}

	public void setParameters(Parameters value) {
		this.planeParameters = value;
	}

	public Price getPrice() {
		return planePrice;
	}

	public void setPrice(Price value) {
		this.planePrice = value;
	}

	/**
	 * Override toString() method to determine text output of the all plane
	 * characteristics
	 */
	@Override
	public String toString() throws NullPointerException {
		StringBuilder result;
		try {
			result = new StringBuilder("Model:\t").append(planeModel);
			result.append("\nOrigin:\t" + planeOrigin);
			result.append("\n" + planePrice.toString());
			result.append("\n" + planeChars.toString());
			result.append("\n" + planeParameters.toString());
			return result.toString();
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return "";
		}
	}

}