
public class weight implements structure {

	private double w;

	public weight(double w) {
		this.w = w;
	}

	@Override
	public boolean isMobile() {
		return false;
	}

	@Override
	public boolean isWeight() {
		return true;
	}

	@Override
	public boolean isBalanced() {
		return true;
	}

	@Override
	public double totalWeight() {
		return w;
	}

}
