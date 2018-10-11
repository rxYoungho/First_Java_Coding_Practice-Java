 

public interface List<E> {
    public int size();
    public boolean isEmpty();
    public E get(int i) throws IndexOutOfBoundsException;            //get e at index i
    public E set(int i, E e) throws IndexOutOfBoundsException;       //set e at index i
    public void add(int i, E e) throws IndexOutOfBoundsException;    //add e at index i
    public E remove(int i) throws IndexOutOfBoundsException;         //remove node at i and return its value
}
