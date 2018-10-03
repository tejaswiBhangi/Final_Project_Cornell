package genericoop;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import throwing.CannotRotateException;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;

public class BinarySearchTree<T> {
	//TODO make private
	public TreeNode<T> root;
	private Method field;
		//this field method allows insertion and searching by a field of the objects
		//ex. sorting the tree based on the name of a gnome
		//otherwise there would be no way to "compare" gnomes and sort them
		//also allows for the creation of multiple trees, each optimized for finding based on name/age/id/etc

	public BinarySearchTree(Method field) {
		this.field = field;

	}
	//instantiates the method of sorting the Ts
	
	public void setRoot(TreeNode<T> r) {
		this.root = r;
	}
	//sets the root node via a treenode containing the T
	
	public void setRoot(TreeIterator<T> i) {
		this.root = i.getNode();
	}
	//sets the root node via a treeiterator containing the T

	public boolean isEmpty() {
		return root == null;
	}

	public void insert(T n) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException { // helper
																													// method
		if (isEmpty())
			root = new TreeNode<T>(n, this.field);
		else {
			insert(n, new TreeIterator<T>(this.root));
		}
	} //helper method

	private void insert(T n, TreeIterator<T> i)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException { // recursive
																									// method
		if (field.invoke(n).toString().compareTo(i.getField().toString()) < 0) { // should
																					// put
																					// on
																					// left
			if (i.hasLeft())
				insert(n, i.pointLeft());
			else{
				TreeIterator<T> temp = new TreeIterator<T>(n, i);
				i.setLeft(temp);
				insertionUpdate(temp);
			}
		} else { // should put on right
			if (i.hasRight())
				insert(n, i.pointRight());
			else{
				TreeIterator<T> temp = new TreeIterator<T>(n, i);
				i.setRight(temp);
				insertionUpdate(temp);
			}
				
		}
		
	}//inserts into the tree, based on the value of the field of the objects
	
	private void insertionUpdate(TreeIterator<T> i){
		TreeIterator<T> parent = i.pointUp();
		if (parent.getData() != null){
			if (parent.pointRight().getData()==i.getData()){
				if (++parent.getNode().balance == 0){
					return;
				}
				if (parent.getNode().balance > 1){
					if (i.getNode().balance == 1){
						rotateLeft(parent, i);
					}
					else {
						TreeIterator<T> tempLeft = i.pointLeft();
						rotateRight(i, tempLeft);
						rotateLeft(parent, tempLeft);
					}
					return;
				}
			}
			else{
				if (--parent.getNode().balance == 0){
					return;
				}
				if (parent.getNode().balance < -1){
					if (i.getNode().balance == -1){
						rotateRight(parent, i);
					}
					else {
						TreeIterator<T> tempRight = i.pointRight();
						rotateLeft(i, tempRight);
						rotateRight(parent, tempRight);
					}
					return;
				}
				
			}
			if (i.getNode().getParent() != null){
				insertionUpdate(i.pointUp());
			}
		}
	} //recursively updates balances while deleting
	
	private void deletionUpdate(TreeIterator<T> i){
		if (i.getNode().getParent() == null){
			return;
		}
		TreeIterator<T> parent = i.pointUp();
		if (parent.getData() != null){
			if (parent.pointRight().getData()==i.getData()){
				if (parent.getNode().balance == 0){
					parent.getNode().balance--;
					return;
				}
				else if (parent.getNode().balance > 0){
					parent.getNode().balance--;
					deletionUpdate(parent);
					return;
				}
				else {
					parent.getNode().balance--;
					if (parent.pointLeft().getNode().balance == 0){
						rotateRight(parent, parent.pointLeft());
						return;
					}
					else if (parent.pointLeft().getNode().balance < 0){
						TreeIterator<T> tempLeft = parent.pointLeft();
						rotateRight(parent, tempLeft);
						deletionUpdate(tempLeft);
						return;
					}
					else {
						TreeIterator<T> tempRight = new TreeIterator<T> (parent.pointLeft().pointRight());
						rotateLeft(parent.pointLeft(), tempRight);
						rotateRight(parent, tempRight);
						deletionUpdate(tempRight);
						return;
					}
				}
			}
			else{
				if (parent.getNode().balance == 0){
					parent.getNode().balance++;
					return;
				}
				else if (parent.getNode().balance<0){
					parent.getNode().balance++;
					deletionUpdate(parent);
					return;
				}
				else {
					parent.getNode().balance++;
					if (parent.pointRight().getNode().balance == 0){
						rotateLeft(parent, parent.pointRight());
						return;
					}
					else if (parent.pointRight().getNode().balance > 0){
						TreeIterator<T> tempRight = parent.pointRight();
						rotateLeft(parent, tempRight);
						deletionUpdate(tempRight);
						return;
					}
					else {
						TreeIterator<T> tempLeft = new TreeIterator<T> (parent.pointRight().pointLeft());
						rotateRight(parent.pointRight(), tempLeft);
						rotateLeft(parent, tempLeft);
						deletionUpdate(tempLeft);
						return;
					}
				}
				
			}
		}
	} //recursively updates balances when deleting

	private TreeIterator<T> find(T n)
			throws NotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException { // should
																													// really
		// use a
		// NotFoundException
		return find(n, root); // returns null if not found and a new
								// TreeIterator if found
		
	} //helper method

	private TreeIterator<T> find(T n, TreeNode<T> t)
			throws NotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (field.invoke(n).toString().compareTo(t.getField().toString()) == 0)
			return new TreeIterator<T>(t);
		if (field.invoke(n).toString().compareTo(t.getField().toString()) < 0)
			if (t.getLeft() == null)
				throw new NotFoundException(n);
			else
				return find(n, t.getLeft());
		else if (t.getRight() == null)
			throw new NotFoundException(n);
		else
			return find(n, t.getRight());
	} //finds the node containing an object

	public T findByValue(Object n)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return findByValue(n, root).getData();
	} //helper method

	private TreeIterator<T> findByValue(Object n, TreeNode<T> t)
			throws NotFoundException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (n.toString().compareTo(t.getField().toString()) == 0)
			return new TreeIterator<T>(t);
		if (n.toString().compareTo(t.getField().toString()) < 0)
			if (t.getLeft() == null)
				throw new NotFoundException(n);
			else
				return findByValue(n, t.getLeft());
		else if (t.getRight() == null)
			throw new NotFoundException(n);
		else
			return findByValue(n, t.getRight());
	} //finds an object by the value of its field

	public void rotateLeft(T parent, T rightChild) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException, CannotRotateException {
		rotateLeft(find(parent).getNode(), find(rightChild).getNode());
	} //helper method

	public void rotateRight(T parent, T leftChild) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotFoundException, CannotRotateException {
		rotateRight(find(parent).getNode(), find(leftChild).getNode());
	} //helper method

	public void rotateLeft(TreeNode<T> parent, TreeNode<T> rightChild) throws CannotRotateException {
		if (parent.getRight() != rightChild) {
			throw new CannotRotateException(parent, rightChild);
		}
		rotateLeft(new TreeIterator<T>(parent), new TreeIterator<T>(rightChild));
	} //helper method

	public void rotateRight(TreeNode<T> parent, TreeNode<T> leftChild) throws CannotRotateException {
		if (parent.getLeft() != leftChild) {
			throw new CannotRotateException(parent, leftChild);
		}
		rotateRight(new TreeIterator<T>(parent), new TreeIterator<T>(leftChild));
	} //helper method

	private void rotateLeft(TreeIterator<T> parent, TreeIterator<T> rightChild) {
		TreeIterator<T> tempLeft = rightChild.pointLeft();
		TreeIterator<T> tempParent = parent.pointUp();
		if (parent.getNode().balance == 2){
			if (rightChild.getNode().balance == -1){
				parent.getNode().balance = 1;
				rightChild.getNode().balance = -2;
			}
			else if (rightChild.getNode().balance == 0){
				parent.getNode().balance = 1;
				rightChild.getNode().balance = -1;
			}
			else if (rightChild.getNode().balance == 2){
				parent.getNode().balance = -1;
				rightChild.getNode().balance = 0;
			}
			else {
				parent.getNode().balance = 0;
				rightChild.getNode().balance = 0;
			}
		}
		else if (parent.getNode().balance == 1){
			if (rightChild.getNode().balance == -1){
				parent.getNode().balance = 0;
				rightChild.getNode().balance = -2;
			}
			else if (rightChild.getNode().balance == 0){
				parent.getNode().balance = 0;
				rightChild.getNode().balance = -1;
			}
			else if (rightChild.getNode().balance == 2){
				parent.getNode().balance = -2;
				rightChild.getNode().balance = -1;
			}
			else {
				parent.getNode().balance = -1;
				rightChild.getNode().balance = -1;
			}
			
		}
		
		
		if (tempLeft.getData() != null) {
			parent.setRight(tempLeft);
			tempLeft.setParent(parent);
		} else {
			parent.nullifyRight();
		}
		rightChild.setLeft(parent);
		parent.setParent(rightChild);
		if (parent.getNode() == root) {
			root = rightChild.getNode();
			rightChild.getNode().setParent(null);
		} else {
			if (tempParent.pointRight().getData() == parent.getData()) {
				tempParent.setRight(rightChild);
			} else {
				tempParent.setLeft(rightChild);
			}
			rightChild.setParent(tempParent);
		}
	} //rotates two nodes left, used for rebalancing

	private void rotateRight(TreeIterator<T> parent, TreeIterator<T> leftChild) {
		TreeIterator<T> tempRight = leftChild.pointRight();
		TreeIterator<T> tempParent = parent.pointUp();
		if (parent.getNode().balance == -2){
			if (leftChild.getNode().balance == -1){
				parent.getNode().balance = 0;
				leftChild.getNode().balance = 0;
			}
			else if (leftChild.getNode().balance == 0){
				parent.getNode().balance = -1;
				leftChild.getNode().balance = 1;
			}
			else if (leftChild.getNode().balance == -2){
				parent.getNode().balance = 1;
				leftChild.getNode().balance = 0;
			}
			else {
				parent.getNode().balance = -1;
				leftChild.getNode().balance = 2;
			}
		}
		else if (parent.getNode().balance == -1){
			if (leftChild.getNode().balance == -1){
				parent.getNode().balance = 1;
				leftChild.getNode().balance = 1;
			}
			else if (leftChild.getNode().balance == 0){
				parent.getNode().balance = 0;
				leftChild.getNode().balance = 1;
			}
			else if (leftChild.getNode().balance == -2){
				parent.getNode().balance = 2;
				leftChild.getNode().balance = 1;
			}
			else {
				parent.getNode().balance = 0;
				leftChild.getNode().balance = 2;
			}
			
		}
		
		if (tempRight.getData() != null) {
			parent.setLeft(tempRight);
			tempRight.setParent(parent);
		} else {
			parent.nullifyLeft();
		}
		leftChild.setRight(parent);
		parent.setParent(leftChild);
		if (parent.getNode() == root) {
			root = leftChild.getNode();
			leftChild.getNode().setParent(null);
		} else {
			if (tempParent.pointRight().getData() == parent.getData()){
				tempParent.setRight(leftChild);
			} else {
				tempParent.setLeft(leftChild);
			}
			leftChild.setParent(tempParent);
		}
	} //rotates two nodes right, used for rebalancing

	public void delete(T n) throws NotFoundException, IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NotOnlyChildException { // deletes a
																// single
		// occurrence of n,
		// could be made
		// much more
		// succinct, but
		// easier to
		// understand this
		// way
		TreeIterator<T> found = find(n); // null if not found, though better to
											// handle via exceptions // not
											// there to
											// delete !!
		// after here we know that the value exists in the tree to be deleted

		if (found.isLeaf()) {
			if (found.isRoot())
				found.nullifyNode(); // so was the only term in the tree
			else { // i.e. it's a leaf and there's other stuff in the tree
				deletionUpdate(found);
				found.goUp(); // moves found to point to found's parent
				if (field.invoke(n).toString().compareTo(found.getField().toString()) < 0){
					
					found.nullifyLeft(); // so the node to be zapped was on the
											// left
				}
				else {
					found.nullifyRight();
				}
			}
			return; // to save having nested else if
		} // end found being a leaf,
			// hence not a leaf if we pass this line
		if (found.numChildren() == 1) { // case of having just one child
			TreeIterator<T> child = found.point2onlyChild();
			if (found.isRoot()){
				setRoot(child);
				child.getNode().setParent(null);
			}
			else { // to skip a generation ...
				deletionUpdate(found);
				found.goUp(); // moves found to point to found's parent
				if (field.invoke(n).toString().compareTo(found.getField().toString()) < 0){
					found.setLeft(child);
				}
				else{
					found.setRight(child);
				}
				child.setParent(found);
			} // to ensure that the child points upwards
									// to its new parent (ex-grandparent),
									// possibly null
			return; // to save having nested else if
		} // end found having only one child
			// hence has two children if we pass this line -- we'll first find
			// the biggest value to the left // print
			// statements
			// to
			// help
			// you
			// see
			// what's
			// happening
			// :)
		TreeIterator<T> lefty = new TreeIterator<T>(found); // cloning the found
		// iterator
		lefty.goLeft(); // hopping one step to the left
		if (!lefty.hasRight()) { // this is a special case where lefty's child
									// must become found's left child
			found.copyData(lefty); // so copies the biggest value (and count) on
									// the left to become the found node's value
									// (and count)
			deletionUpdate(lefty);
			found = lefty.pointUp();
			found.setLeft(lefty.pointLeft());
			if (lefty.pointLeft().getData() != null){
				lefty.pointLeft().setParent(found);
			}
												// TreeIterator(lefty.getNode().getLeft()));
		} else { // here lefty can move to the right
			while (lefty.hasRight())
				lefty.goRight(); // so moves lefty rightwards until no longer
									// possible

			found.copyData(lefty); // so copies the biggest value (and count) on
									// the left to become the found node's value
									// (and count)
			deletionUpdate(lefty);
			lefty.pointUp().setRight(lefty.pointLeft()); // make lefty's parent
															// point to lefty's
															// only child
			if (lefty.pointLeft().getData() != null){
				lefty.pointLeft().setParent(lefty.pointUp());
			}
		}

	} // end delete method

	public String toString() {
		String temp = "";
		if (!isEmpty())
			temp += LR_output(root); // calling recursive method
		return temp;
	}

	private String LR_output(TreeNode<T> t) {
		String temp = "";
		if (t.hasLeft())
			temp += LR_output(t.getLeft());
		temp += t + " ";
		if (t.hasRight())
			temp += LR_output(t.getRight());
		return temp;
	} //returns a string of everything to the left, then itself, then everything to its right

	private class TreeIterator<J> {
		public TreeNode<J> me;

		public TreeIterator(TreeNode<J> t) {
			this.me = t;
		}

		public TreeIterator(TreeIterator<J> i) {
			this.me = i.getNode();
		}

		public TreeIterator(J a, TreeIterator<J> p) {
			this.me = new TreeNode<J>(a, field, p.getNode());
		} // data = a, parent node = p

		public J getData() {
			if (me != null)
				return this.me.getData();
			return null;
		} //returns the thing stored in the iterator (might be null)

		public Object getField() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
			return this.me.getField();

		} //returns the value of the field that the tree uses to compare elements

		public TreeNode<J> getNode() {
			return this.me;
		}

		public void nullifyNode() {
			if (this.me == root && this.numChildren() == 0) {
				root = null;
			}
			this.me = null;
		} // used in delete method

		public void nullifyLeft() {
			this.me.setLeft(null);
		} // nulls the left node of the node I'm pointing to

		public void nullifyRight() {
			this.me.setRight(null);
		} // nulls the right node of the node I'm pointing to

		public void goLeft() {
			if (this.me != null)
				this.me = this.me.getLeft();
		} //moves left (and down)

		public void goRight() {
			if (this.me != null)
				this.me = this.me.getRight();
		} //moves right (and down)

		public void goUp() {
			if (this.me != null && this.me != root)
				this.me = this.me.getParent();
		} //moves up one level

		public TreeIterator<J> pointLeft() {
			return new TreeIterator<J>(this.me.getLeft());
		} // note that this node might be null

		public TreeIterator<J> pointRight() {
			return new TreeIterator<J>(this.me.getRight());
		} // note that this node might be null

		public TreeIterator<J> pointUp() {
			return new TreeIterator<J>(this.me.getParent());
		} // note that this node might be null

		public TreeIterator<J> point2onlyChild() throws NotOnlyChildException {
			if (numChildren() != 1) { // better to throw an appropriate
										// exception
				throw new NotOnlyChildException();
			}
			return hasLeft() ? new TreeIterator<J>(this.me.getLeft()) : new TreeIterator<J>(this.me.getRight());
		} //returns a tree iterator pointing to its only child

		public boolean hasLeft() {
			return this.me.hasLeft();
		} //true if it has a left child, false otherwise

		public boolean hasRight() {
			return this.me.hasRight();
		} //true if it has a right child, false otherwise


		@SuppressWarnings("unused")
		public void setData(J n) {
			this.me.setData(n);
		} // sets the data of the node I'm pointing to

		public void setLeft(TreeIterator<J> i) {
			this.me.setLeft(i.getNode());
		} // sets the left node of the node I'm pointing to

		public void setRight(TreeIterator<J> i) {
			this.me.setRight(i.getNode());
		} // sets the right node of the node I'm pointing to

		public void setParent(TreeIterator<J> i) {
			this.me.setParent(i.getNode());
		} // sets the parent node of the node I'm pointing to

		public boolean isLeaf() {
			return !(hasLeft() || hasRight());
		} //true if it has no children, false otherwise

		public boolean isRoot() {
			return this.me == root;
		} //true if it is at the root, false otherwise

		public void copyData(TreeIterator<J> i) {
			this.me.setData(i.getData());
		} //copies another TreeIterator's data into itself.

		public int numChildren() {
			if (isLeaf())
				return 0;
			if (hasLeft() && hasRight())
				return 2;
			return 1; // since would otherwise have already returned
		} //returns the number of children that it has

	}

}
