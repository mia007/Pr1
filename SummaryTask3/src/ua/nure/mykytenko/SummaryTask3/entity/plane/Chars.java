package ua.nure.mykytenko.SummaryTask3.entity.plane;

/**
 * This class describes characteristics of the plane
 * 
 */
public class Chars {
	
	private String type = "";
	private Byte seats;
	private Ammunition ammunition;
	private Boolean radar;

	public String getType() {
		return type;
	}

	public void setType(String value) {
		this.type = value;
	}

	public Byte getSeats() {
		return seats;
	}

	public void setSeats(Byte value) {
		this.seats = value;
	}

	public Ammunition getAmmunition() {
		return ammunition;
	}

	public void setAmmunition(Ammunition value) {
		this.ammunition = value;
	}

	public Boolean getRadar() {
		return radar;
	}

	public void setRadar(Boolean value) {
		this.radar = value;
	}

	/**
	 * Override toString() method to determine text output of the plane chars
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("Chars:");
		if (!type.isEmpty()) {
			result.append("\n\tType:\t" + type);
		}
		if (seats != null) {
			result.append("\n\tSeats:\t" + seats);
		}
		if (ammunition != null) {
			result.append("\n\t" + ammunition.toString());
		}
		if (radar != null) {
			result.append("\n\tRadar:\t" + radar);
		}
		return result.toString();
	}
}