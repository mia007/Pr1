package ua.nure.mykytenko.SummaryTask3.util;

import java.util.Collections;
import java.util.Comparator;

import ua.nure.mykytenko.SummaryTask3.entity.Planes;
import ua.nure.mykytenko.SummaryTask3.entity.plane.Plane;

/**
 * This class describes planes sort functions
 * 
 */
public class Sorter {

	/**
	 * Sorts planes by model
	 */
	public static final Comparator<Plane> SORT_PLANES_BY_MODEL = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return o1.getModel().compareTo(o2.getModel());
		}
	};

	/**
	 * Sorts planes by origin
	 */
	public static final Comparator<Plane> SORT_PLANES_BY_ORIGIN = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return o1.getOrigin().compareTo(o2.getOrigin());
		}
	};

	/**
	 * Sorts planes by plane type
	 */
	public static final Comparator<Plane> SORT_PLANES_BY_LENGTH = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return (int) (100 * (o1.getParameters().getPlaneLength() - o2.getParameters().getPlaneLength()));
		}
	};

	/**
	 * Sorts planes by plane price
	 */
	public static final Comparator<Plane> SORT_PLANES_BY_PRICE = new Comparator<Plane>() {
		@Override
		public int compare(Plane o1, Plane o2) {
			return (int) (100 * (o1.getPrice().getValue() - o2.getPrice().getValue()));
		}
	};

	/**
	 * Sorts planes in a predetermined manner
	 * 
	 * @param planes
	 *            List of the Planes
	 * @param comparator
	 *            Sort method
	 */
	public static final void sortPlanes(Planes planes, Comparator<Plane> comparator) {
		Collections.sort(planes.getPlanes(), comparator);
	}

}
