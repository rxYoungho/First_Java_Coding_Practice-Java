 

import java.util.ArrayList;
import java.util.List;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
    protected static class Node<E> implements Position<E> {
        private E e;
        private Node<E> parent, left, right;
        public Node(E e, Node<E> parent, Node<E> left, Node<E> right) {
            this.e = e;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
        //accessor
        public E getElement() { return e; }
        public Node<E> getParent() { return parent; }
        public Node<E> getLeft() { return left; }
        public Node<E> getRight() { return right; }
        //update
        public void setElement(E e) { this.e = e; }
        public void setParent(Node<E> parent) { this.parent = parent; }
        public void setLeft(Node<E> left)     { this.left= left; }
        public void setRight(Node<E> right)   { this.right = right; }
    }
    
    //factory method
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }
    
    protected Node<E> root;
    private int size;
    
    //Constructor
    public LinkedBinaryTree() {}
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if(!(p instanceof Node))
            throw new IllegalArgumentException("Invalid Position type");
        Node<E> node = (Node<E>) p;
        if(node.getParent() == node)    //our convention to defunct a node
            throw new IllegalArgumentException("p is not in the tree");
        return node;
    }
    
    //Interface Tree 
    public int size() { return size; }
    public Position<E> root() { return root; }
    public Position<E> parent(Position<E> p) { return validate(p).getParent(); }
    
    //Interface BinaryTree
    public Position<E> left(Position<E> p)   { return validate(p).getLeft(); }
    public Position<E> right(Position<E> p)  { return validate(p).getRight(); }
    
    //Class specific methods
    public Position<E> addRoot(E e) throws IllegalStateException {
        if(!isEmpty())
            throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if(parent.getLeft() != null)
            throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if(parent.getRight() != null)
            throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }
    public E set(Position<E> p, E e) {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2)
                                                              throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(t1 != null && !t1.isEmpty()) {
            if(node.getLeft() != null)
                throw new IllegalArgumentException("p already has a left child");
            node.setLeft(t1.root);
            size += t1.size();
            t1.root.setParent(node);
            t1.root = null;
            t1.size = 0;
        }
        if(t2 != null && !t2.isEmpty()) {
            if(node.getRight() != null)
                throw new IllegalArgumentException("p already has a right child");
            node.setRight(t2.root);
            size += t2.size();
            t2.root.setParent(node);
            t2.root = null;
            t2.size = 0;
        }
    }
    //remove the node at p and replace it with its child
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if(numChildren(p) == 2)
            throw new IllegalArgumentException("p has two children");
        Node<E> child = node.getLeft();
        if(child == null)
            child = node.getRight();
        if(child != null)
            child.setParent(node.getParent());
        if(node == root)
            root = child;
        else {
            Node<E> parent = node.getParent();
            if(node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null);
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); //our convention for defunct node
        return temp;
    }
    
    //inorder traversal
    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if(left(p) != null)
            inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if(right(p) != null)
            inorderSubtree(right(p), snapshot);
    }
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            inorderSubtree(root(), snapshot);
        return snapshot;
    }    
    public Iterable<Position<E>> positions() {
        return inorder();
    }
}
