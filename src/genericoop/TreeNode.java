package genericoop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TreeNode<T> {
	private T data; // we use the value of count to handle repeated values
	private TreeNode<T> left, right, parent;
	Method field;
	// this field allows sorting the tree based some property of the object
	// (name, id, etc)
	// allows the creation of multiple trees, each optimized for a different
	// property
	public int balance;

	public TreeNode(T n, Method field, TreeNode<T> parent) {
		this.balance = 0;
		this.field = field;
		this.data = n;
		this.parent = parent;
	}

	public TreeNode(T n, Method field) {
		this(n, field, null);
	} // here we don't set the parent node -- probably only called for the root
		// node

	public T getData() {
		return this.data;
	} // returns the object contained in the node

	public Object getField() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		return field.invoke(data);
	} // returns the value of the field used for comparison for this specific
		// node
		// for example, might return the name of the gnome (if this node is used
		// for names)

	public void setData(T a) {
		this.data = a;
	} // sets the object contained in the node

	public TreeNode<T> getLeft() {
		return this.left;
	} // gets left child

	public TreeNode<T> getRight() {
		return this.right;
	} // gets right child

	public TreeNode<T> getParent() {
		return this.parent;
	} // gets parent

	public void setLeft(TreeNode<T> L) {
		this.left = L;
	} // sets left child

	public void setRight(TreeNode<T> R) {
		this.right = R;
	} // sets right child

	public void setParent(TreeNode<T> P) {
		this.parent = P;
	} // sets parent

	public boolean hasLeft() {
		return this.left != null;
	} // true if it has a left child, false otherwise

	public boolean hasRight() {
		return this.right != null;
	} // true if it has a right child, false otherwise

	public String toString() {
		return this.data.toString();
	}

}