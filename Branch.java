public class Branch<E> {
	E info;
	Branch right;
	Branch left;
	boolean isLeaf;
	
	public Branch(Branch r, Branch l) {
		right = r;
		left = l;
		isLeaf = false;
	}
	public Branch(E e) {
		info = e;
		isLeaf = true;
	}
}
