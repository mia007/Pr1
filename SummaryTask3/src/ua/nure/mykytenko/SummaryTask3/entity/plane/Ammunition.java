package ua.nure.mykytenko.SummaryTask3.entity.plane;

/**
 * This class describes ammunition characteristics of the plane
 * 
 */
public class Ammunition {


	private Boolean value;
	private Byte rocket;

	public Ammunition() {

	}

	/**
	 * Class constructor
	 * 
	 * @param value
	 *            Boolean ammunition describes whether the plane has  ammunition
	 * @param rocket
	 *            Rockets count
	 */
	public Ammunition(Boolean value, byte rocket) {
		this.value = value;
		this.rocket = rocket;
	}

	/**
	 * Class constructor
	 * 
	 * @param value
	 *            Boolean ammunition describes whether the plane has  ammunition
	 */
	public Ammunition(Boolean value) {
		this.value = value;
	}

	public Boolean getValue() {
		return value;
	}

	public void setValue(Boolean value) {
		this.value = value;
	}

	public Byte getRocket() {
		return rocket;
	}

	public void setRocket(Byte value) {
		this.rocket = value;
	}

	/**
	 * Override toString() method to determine text output of the ammunition
	 */
	@Override
	public String toString() {
		if (value != null) {
			if (value) {
				return "Ammunition:\t" + rocket + "(rocket)";
			} else {
				return "Ammunition:\tno ammunition";
			}
		} else {
			return "";
		}
	}

}