package ua.nure.mykytenko.SummaryTask3.entity.plane;

/**
 * This class Describes parameters of the plane
 * 
 */
public class Parameters {

	private double planeLength;
	private double planeWidth;
	private double planeHeight;
	private String unit;

	public Parameters() {
	}

	/**
	 * Class constructor
	 * 
	 * @param unit
	 *            Units of measurement
	 * @param length
	 *            Plane length
	 * @param width
	 *            Plane width
	 * @param height
	 *            Plane height
	 */
	public Parameters(String unit, double length, double width, double height) {
		this.unit = unit;
		this.planeLength = length;
		this.planeWidth = width;
		this.planeHeight = height;
	}

	public double getPlaneLength() {
		return planeLength;
	}

	public void setPlaneLength(double value) {
		this.planeLength = value;
	}

	public double getPlaneWidth() {
		return planeWidth;
	}

	public void setPlaneWidth(double value) {
		this.planeWidth = value;
	}

	public double getPlaneHeight() {
		return planeHeight;
	}

	public void setPlaneHeight(double value) {
		this.planeHeight = value;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String value) {
		this.unit = value;
	}

	/**
	 * Override toString() method to determine text output of the plane
	 * parameters
	 */
	@Override
	public String toString() {
		String addUnit = "(" + unit + ")";
		StringBuilder result = new StringBuilder("Parameters:");
		result.append("\n\tlength\t");
		result.append(planeLength + addUnit);
		result.append("\n\twidth\t");
		result.append(planeWidth + addUnit);
		result.append("\n\theight\t");
		result.append(planeHeight + addUnit);
		return result.toString();
	}

}