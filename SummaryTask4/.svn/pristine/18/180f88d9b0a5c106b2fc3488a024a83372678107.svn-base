package ua.nure.mykytenko.SummaryTask4.db.bean;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import ua.nure.mykytenko.SummaryTask4.db.entity.Carriage;
import ua.nure.mykytenko.SummaryTask4.db.entity.CarriageType;

/**
 * Creates a list of carriages.
 * 
 * @author A.Mykytenko
 * 
 */
public class CarriageListBean {

	private List<Carriage> carriages;

	public CarriageListBean(List<Carriage> carriages) {
		this.carriages = carriages;
	}

	public Set<CarriageType> getTypes() {
		Set<CarriageType> types = new TreeSet<>();
		for (Carriage c : carriages) {
			types.add(c.getType());
		}
		return types;
	}

	public List<Carriage> getCarriages() {
		return carriages;
	}

	@Override
	public String toString() {
		return "CarriageListBean [carriages=" + carriages + "]";
	}
}
