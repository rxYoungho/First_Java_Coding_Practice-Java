public class BinarySearchTree<E extends Comparable<E>> {
    private static class Node<E extends Comparable<E>> {
        public E e;
        public Node<E> left, right;
        public Node(E e, Node<E> left, Node<E> right) {
            this.e = e; this.left = left; this.right = right;
        }
    }
    
    private Node<E> root;
    
    public BinarySearchTree() {}
    public void add(E e) {
        if(root != null)
            add(e, root);
        else
            root = new Node<E>(e, null, null);
    }
    private void add(E e, Node<E> node) {
        //add e to a subtree rooted at node
        if(e.compareTo(node.e) < 0){
            if( node.left == null){
                node.left = new Node(e, null, null);
            }
            else{
                add(e, node.left);
            }
        }
        else{
            if(node.right == null){
                node.right = new Node(e, null, null);
            }
            else{
                add(e, node.right);
            }
        }
    }
    public void visit() {
        visit(root);
    }
    private void visit(Node<E> node) {
        if(node == null)
            return;
        //TODO: visit node.left
        //      print node.e
        //      visit node. right

            visit(node.left);
            System.out.println(node.e);
            visit(node.right);
    
    }
    
    public static <E extends Comparable<E>> void sort(E[] arr) {
        BinarySearchTree<E> node = new BinarySearchTree<E>();
        for(E e : arr)
            node.add(e);
        node.visit();
    }
    public static void main(String[] args) {
        Integer[] arr = {5, 3, 6, 2, 8, 1, 9, 4, 7};
        sort(arr);
    }
}