 

public class ListQueue<E> implements Queue<E> {
    private CircularlyLinkedList<E> list;
    
    public ListQueue()       { list = new CircularlyLinkedList<E>(); }
    public int size()        { return list.size(); }
    public boolean isEmpty() { return list.isEmpty(); }
    public void enqueue(E e) { list.addLast(e); }
    public E dequeue()       { return list.removeFirst(); }
    public E first()         { return list.first(); }
}
