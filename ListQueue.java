package pathByQueue;

public class ListQueue<E> implements Queue<E> {
    private static class Node<E> {
        public Node<E> next;
        public E e;
        public Node() {}
        public Node(E e, Node<E>next) { this.e = e; this.next = next; }
    }
    
    private Node<E> head, tail;
    private int size;
    
    public ListQueue() {
        head = tail = new Node<E>();
        size = 0;
    }
    public int size()        { return size; }
    public boolean isEmpty() { return size == 0; }
    public void enqueue(E e) {
        //TODO: add a new node at head.next,
        //      move head, and increase size
    	head.next = new Node<E>(e, head.next);
    	head = head.next;
    	size++;
    }
    public E dequeue() {
        E data = first();
        //TODO: move tail and decrease size
        tail = tail.next;
        size --;
        return data;
    }
    public E first() {
        if(isEmpty())
            throw new IllegalStateException("Queue is empty");
        return tail.next.e;
    }
}
