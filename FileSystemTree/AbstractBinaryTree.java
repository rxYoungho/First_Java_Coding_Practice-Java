 
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractBinaryTree<E> extends AbstractTree<E> 
                                            implements BinaryTree<E> {
    //Interface BinaryTree
    public Position<E> sibling(Position<E> p) {
        Position<E> parent = parent(p);
        return  parent == null ? null
            :   p == left(parent) ? right(parent)
            :   right(parent)
            ;
    }
    
    //Interface Tree
    public int numChildren(Position<E> p) {
        int count = 0;
        if(left(p) != null)
            count++;
        if(right(p) != null)
            count++;
        return count;
    }
    
    public Iterable<Position<E>> children(Position<E> p) {
        List<Position<E>> snapshot = new ArrayList<Position<E>>(2);
        if(left(p) != null)
            snapshot.add(0, left(p));
        if(right(p) != null)
            snapshot.add(snapshot.size(), right(p));
        return snapshot;
    }
}
