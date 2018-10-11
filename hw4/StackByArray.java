 

public class StackByArray<E> implements Stack<E> {
    private static final int defCapacity = 1;
    private int capacity;
    private E[] arr;
    private int sp;
    
    public StackByArray() {
        this(defCapacity);
    }
    @SuppressWarnings("unchecked")
    public StackByArray(int capacity) {
        this.capacity = capacity;
        arr = (E[])new Object[capacity];
        sp = 0;
    }
    
    //interface Stack
    public int size() {
        return sp;
    }
    public boolean isEmpty() {
        return sp == 0;
    }
    public void push(E e) {
        if(sp == capacity)
            resize(capacity * 2);   //dynamic array
        arr[sp++] = e;
    }
    public E top() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("empty stack");
        return arr[sp-1];
    }
    public E pop() {
        if(isEmpty())
            throw new IndexOutOfBoundsException("empty stack");
        E e = arr[--sp];
        arr[sp] = null; //to help garbage collection
        return e;
    }
    
    @SuppressWarnings("unchecked")  //dynamic array
    private void resize(int cap) {
        E[] tmp = (E[])new Object[cap];
        for(int i = 0; i < sp; i++)
            tmp[i] = arr[i];
        arr = tmp;
        capacity = cap;
    }
}
