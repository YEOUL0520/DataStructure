public class TreeNode <E> implements Position <E> {
    private E element;
    private TreeNode <E> left, right;

    public TreeNode(E element){ this.element = element; }

    public TreeNode(E element, TreeNode<E> left, TreeNode<E> right)
    { this.element = element; this.left = left; this.right = right; }

    public E getElement() throws IllegalStateException {return element;}

    public String toString() { return "노드(" +element+ ")";}

}
