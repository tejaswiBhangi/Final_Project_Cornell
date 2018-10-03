package throwing;

import genericoop.TreeNode;

@SuppressWarnings("serial")
public class CannotRotateException extends Exception {
	public CannotRotateException(TreeNode<?> a, TreeNode<?> b){
		super("The nodes containing: "+a+" and " +b+" could not be rotated!");
	}
}
