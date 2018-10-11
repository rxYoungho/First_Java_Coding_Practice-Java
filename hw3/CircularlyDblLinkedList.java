package hw214_3;

import java.util.Iterator;

public class CircularlyDblLinkedList<E> implements List<E>, Iterable<E> {
    protected static class Node<E> {
        public E e;
        public Node<E> prev, next;
        public Node() { 
            this.e = null; this.prev = this; this.next = this;
        }
        public Node(E e, Node<E> prev, Node<E> next) {
            this.e = e; this.prev = prev; this.next = next;
        }
    }
    public static class NodeIterator<E> implements Iterator<E> {
        private Node<E> head, curr;
        public NodeIterator(Node<E> head, Node<E> curr) {
            this.head = head; this.curr = curr;
        }
        //TODO: implement Iterator<E>
    }
    
    protected Node<E> head;
    protected int size;

    //constructor
    public CircularlyDblLinkedList() {
        head = new Node<E>();
        size = 0;
    }
    
    //TODO: implement interface List
    public E get(int i) {
        return findNode(i).e;
    }

    //TODO: implement interface Iterable

    //helper methods
    protected Node<E> findNode(int i) {
        if(i < 0 || i >= size)
            throw new IndexOutOfBoundsException("invalid index: " + i + " is not in [ 0, " + size + ")");
        
        //TODO: find the node at index i and return it 
    }
}
