package hw214_3;

public class SetImpl<E extends Comparable<E>> implements Set<E> {
    private CircularlyDblLinkedList<E> list;
    
    public SetImpl() {
        list = new CircularlyDblLinkedList<E>();
    }
    public SetImpl(SetImpl<E> set) {
        list = set.copyList();
        dedupe();
    }
    public SetImpl(E[] arr) {
        list = new CircularlyDblLinkedList<E>();
        for(int i = 0; i < arr.length; i++)
            list.add(i, arr[i]);
        dedupe();
    }
    
    //TODO: implement interface Set
    public boolean isEqual(Set<E> set) {
        SetImpl<E> s = (SetImpl<E>)set;
        if(list.size() != s.list.size())
            return false;
        for(int i = 0; i < list.size(); i++)
            if(list.get(i).compareTo(s.list.get(i)) != 0)
                return false;
        return true;
    }
    
    //helper methods
    private CircularlyDblLinkedList<E> copyList() {
        CircularlyDblLinkedList<E> dst = new CircularlyDblLinkedList<E>();
        int i = 0;
        for(E e : list)
            dst.add(i++, e);
        return dst;
    }
    private void dedupe() {
        //TODO: 1) sort
        //      2) remove any consecutive duplicate elements
    }
    private void sort() {   //insertion-sort
        //TODO: using the insertion-sort, sort list
    }
}
