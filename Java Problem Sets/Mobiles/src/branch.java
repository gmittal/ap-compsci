
public class branch {

	private double length;
	private structure struct;

	public branch(double l, structure struct) {
		length = l;
		this.struct = struct;
	}

	public double getLength() {
		return length;
	}

	public structure getStruct() {
		return struct;
	}

	public double getTorque() {
		return length * struct.totalWeight();
	}

}
