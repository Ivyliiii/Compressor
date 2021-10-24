public class Branch<E> {
	// this variable keeps track of the info in the branch
	// in this case, it would be the node
	E info;
	// this variable keeps track of the node to the right
	Branch right;
	// node to the left
	Branch left;
	// makes our lives easier by telling us if it contains any info
	boolean isLeaf;
	
	// the first type of branch without information except for the node on the left and right
	public Branch(Branch r, Branch l) {
		right = r;
		left = l;
		isLeaf = false;
	}
	// just keeps the information
	public Branch(E e) {
		info = e;
		isLeaf = true;
	}
	
	// printing hack
	public String toString() {
		return info.toString();
	}
}
