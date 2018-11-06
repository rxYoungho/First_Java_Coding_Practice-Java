 

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
        private Node<E> tail, curr;
        public NodeIterator(Node<E> tail) {
            //TODO: implement this method
            this.tail = tail;
            if(this.tail == null){ this.curr = null;}
            else
                this.curr = tail.next;
        }
        public boolean hasNext() {
            //TODO: implement this method
            if(curr == null)
                return false;
            else
                return true;
        }
        public E next() {
            //TODO: implement this method
            if(hasNext() == true){
                E tmp = curr.e;
                if(curr != tail){
                    curr = curr.next;
                }
                else{
                    curr = null;
                }
                return tmp;
            }
            else
                return null;
        }        
    }
    private Node<E> tail;
    private int size;
    
    public CircularlyLinkedList() {}
    
    //interface Iterable
    public Iterator<E> iterator() {
        return new NodeIterator<E>(tail);
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
    
    private static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");            
    }
    
    @SuppressWarnings("unused")
    public static void main(String[] args) {
        CircularlyLinkedList<Integer> list = new CircularlyLinkedList<Integer>();
        
        for(int i : list)
            onFalseThrow(false);
        
        list.addFirst(3);
        list.addFirst(2);
        list.addFirst(1);
        list.addLast(4);
        list.addLast(5);

        int j = 1;
        for(int i : list)
            onFalseThrow(i == j++);
        
        for(int i = 1; i <= 3; i++)
            onFalseThrow(i == list.removeFirst());
        
        onFalseThrow(list.removeLast() == 5);
        onFalseThrow(list.removeLast() == 4);
        
        onFalseThrow(list.removeLast() == null);
        onFalseThrow(list.removeFirst() == null);
        
        System.out.println("Success!");        
    }
}
