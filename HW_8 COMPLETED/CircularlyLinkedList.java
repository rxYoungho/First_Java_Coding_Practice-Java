 

import java.util.Iterator;

public class CircularlyLinkedList<E> implements Iterable<E> {
    private static class Node<E> {
        private E e;
        private Node<E> next;
        public Node(E e, Node<E> n) { this.e = e; this.next = n; }
        public E getElement() { return e; }
        public Node<E> getNext() { return next; }
        public void setNext(Node<E> n) { next = n; }
    }
    private static class NodeIterator<E> implements Iterator<E> {
        private Node<E> head, curr;
        public NodeIterator(Node<E> tail) {
            this.head = tail == null ? null : tail.next;
            this.curr = null;
        }
        public boolean hasNext() {
            return head == null ? false : (curr == null || curr != head);
        }
        public E next() {
            if(curr == null)    curr = head;
            if(curr == null)    return null;
            E e = curr.e;
            curr = curr.next;
            return e;
        }        
    }
    private Node<E> tail;
    private int size;
    
    public CircularlyLinkedList() {}
    
    //interface Iterable
    public Iterator<E> iterator() {
        return new NodeIterator<E>(tail.next);
    }
    
    public int size()   { return size; }
    public boolean isEmpty() { return size == 0; }
    public E first() {
        return isEmpty() ? null : tail.getNext().getElement();
    }
    public E last() {
        return isEmpty() ? null : tail.getElement();
    }
    public void addFirst(E e) {
        if(isEmpty()) {
            tail = new Node<E>(e, null);
            tail.setNext(tail);
        }
        else {
            tail.setNext(new Node<E>(e, tail.getNext()));
        }
        size++;
    }
    public void addLast(E e) {
        addFirst(e);
        tail = tail.getNext();
    }
    public E removeFirst() {
        if(isEmpty()) 
            return null;
        Node<E> head = tail.getNext();
        if(head == tail)
            tail = null;
        else
            tail.setNext(head.getNext());
        size--;
        return head.getElement();
    }
    public E removeLast() {
        if(isEmpty()) 
            return null;
        E ret = tail.getElement();
        Node<E> n = tail;
        if(n.getNext() == tail) {   //1 element
            size = 0;
            tail = null;
            return ret;
        }
        else {
            while(n.getNext() != tail)
                n = n.getNext();
            n.setNext(tail.getNext());
            size--;
            tail = n;
            return ret;
        }
    }
}
