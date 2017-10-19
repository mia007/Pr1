package ua.nure.mykytenko.SummaryTask3.entity;

import java.util.ArrayList;
import java.util.List;
import ua.nure.mykytenko.SummaryTask3.entity.plane.Plane;

/**
 * This class describes list of planes entity
 * 
 */
public class Planes {

	private List<Plane> planes;

	/**
	 * Empty class constructor. Create Plane list
	 */
	public Planes() {
		planes = new ArrayList<Plane>();
	}

	/**
	 * Method count planes in the list
	 * 
	 * @return quantity of planes
	 */
	public int size() {
		return planes.size();
	}

	/**
	 * Return Plane object by it index
	 * 
	 * @param index
	 *            index of the plane in the list
	 * @return Plane object
	 */
	public Plane getPlane(int index) {
		return planes.get(index);
	}

	/**
	 * Return list with Plane objects
	 * 
	 * @return list of Plane
	 */
	public List<Plane> getPlanes() {
		return planes;
	}

	/**
	 * Add a new Plane in the end of the list
	 * 
	 * @param value
	 *            new Plane object
	 */
	public void add(Plane value) {
		this.planes.add(value);
	}

	/**
	 * Add Plane object in the index position of the list
	 * 
	 * @param index
	 *            insert index
	 * @param value
	 *            Plane object
	 */
	public void setPlane(int index, Plane value) {
		this.planes.add(index, value);
	}

	/**
	 * Override toString() method to determine text output of the all planes of
	 * the list
	 */
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder("");
		for (Plane p : planes) {
			if (!p.toString().equals("")) {
				result.append(p + "\n\n");
			}
		}
		if (!result.toString().equals("")) {
			return result.toString();
		} else {
			return "planes not found";
		}
	}

}