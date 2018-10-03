package gnomenwald;

import java.lang.reflect.InvocationTargetException;

import genericoop.BinarySearchTree;
import genericoop.LList;
import oopstructures.Gnome;
import throwing.NotFoundException;
import throwing.NotOnlyChildException;

public class GnomeStorage<T extends Gnome> {

	public BinarySearchTree<T> idTree, ageTree, nameTree;
	public LList<T> storage;

	public GnomeStorage(Class<T> clazz) throws NoSuchMethodException, SecurityException {
		this.idTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getId"));
		this.ageTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getAge"));
		this.nameTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getName"));
		this.storage = new LList<T>();
	}
	// instantiates the three BSTs, which sorty by Id, Age, and Name

	public GnomeStorage(Class<T> clazz, T g) throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SecurityException {
		this.idTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getId"));
		this.ageTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getAge"));
		this.nameTree = new BinarySearchTree<T>(clazz.getDeclaredMethod("getName"));
		nameTree.insert(g);
		idTree.insert(g);
		ageTree.insert(g);
		storage.append(g);
	}
	// instantiates the three BSTs, which sort by ID, age, and Name. also sets
	// root node.
	
	public void insert(T g) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		nameTree.insert(g);
		idTree.insert(g);
		ageTree.insert(g);
		storage.append(g);
	}
	//appends the given object to the three trees
	
	public void delete(T g) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException,
			NotFoundException, NotOnlyChildException {
		nameTree.delete(g);
		idTree.delete(g);
		ageTree.delete(g);
		storage.remove(g);
	}
	//removes the given object from the three trees
	
	public T findByAge(int age)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return ageTree.findByValue(age);
	}
	//finds the T in the age tree with the given age, if not found throws a notfoudnexception

	public T findById(int id)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return idTree.findByValue(id);
	}
	//finds the T in the id tree with the given id, if not found throws a notfoundexception

	public T findByName(String name)
			throws IllegalArgumentException, IllegalAccessException, InvocationTargetException, NotFoundException {
		return nameTree.findByValue(name);
	}
	//finds the T in the name tree with the given name, if not found throws an exception
	
	public String toString() {
		if (this.nameTree.isEmpty()) {
			return "empty";
		}
		return "" + nameTree;

	}
}
