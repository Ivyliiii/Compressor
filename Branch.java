public class Branch<E> {
	E info;
	Branch right;
	Branch left;
	boolean isLeaf;
	
	public Branch(E e, Branch r, Branch l, boolean isLeaf) {
		info = e;
		right = r;
		left = l;
		this.isLeaf = isLeaf;
	}
}
