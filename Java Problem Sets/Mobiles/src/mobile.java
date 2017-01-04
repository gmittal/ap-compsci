
public class mobile implements structure {

	private branch leftBranch, rightBranch;

	public mobile(branch left, branch right) {
		leftBranch = left;
		rightBranch = right;
	}

	public branch getLeftBranch() {
		return leftBranch;
	}

	public branch getRightBranch() {
		return rightBranch;
	}

	@Override
	public boolean isMobile() {
		return true;
	}

	@Override
	public boolean isWeight() {
		return false;
	}

	@Override
	public boolean isBalanced() {
		// TODO Auto-generated method stub
		return (leftBranch.getTorque() == rightBranch.getTorque()) && leftBranch.getStruct().isBalanced()
				&& rightBranch.getStruct().isBalanced();
	}

	@Override
	public double totalWeight() {
		return leftBranch.getStruct().totalWeight() + rightBranch.getStruct().totalWeight();
	}

}