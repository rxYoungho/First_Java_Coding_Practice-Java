 

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public abstract class AbstractTree<E> implements Tree<E> {
    //Interface Tree
    public boolean isInternal(Position<E> p) { return numChildren(p) > 0; }
    public boolean isExternal(Position<E> p) { return numChildren(p) == 0; }
    public boolean isRoot(Position<E> p)     { return p == root(); }
    public boolean isEmpty()                 { return size() == 0; }
    
    //Iterator of elements
    private class ElementIterator implements Iterator<E> {
        Iterator<Position<E>> posIterator = positions().iterator();
        public boolean hasNext() {
            return posIterator.hasNext();
        }
        public E next() {
            return posIterator.next().getElement();
        }
        public void remove() {
            posIterator.remove();
        }
    }
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    //Iterable collection of positions
    public Iterable<Position<E>> positions() {
        return preorder();
    }
    
    //Class specific methods
    //preorder traversal
    private void preorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        snapshot.add(snapshot.size(), p);
        for(Position<E> c: children(p))
            preorderSubtree(c, snapshot);
    }
    public Iterable<Position<E>> preorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            preorderSubtree(root(), snapshot);
        return snapshot;
    }
    
    //postorder traversal
    private void postorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        for(Position<E> c: children(p))
            postorderSubtree(c, snapshot);
        snapshot.add(snapshot.size(), p);
    }
    public Iterable<Position<E>> postorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty())
            postorderSubtree(root(), snapshot);
        return snapshot;
    }
    
    //breadth first traversal
    public Iterable<Position<E>> breadthFirst() {
        List<Position<E>> snapshot = new ArrayList<>();
        if(!isEmpty()) {
            Queue<Position<E>> queue = new LinkedList<>();
            queue.add(root());
            while(!queue.isEmpty()) {
                Position<E> p = queue.remove();
                snapshot.add(p);
                for(Position<E> c: children(p))
                    queue.add(c);
            }
        }
        return snapshot;
    }
    
    //depth & height
    public int depth(Position<E> p) {
        return isRoot(p) ? 0: 1 + depth(parent(p));
    }
    public int height(Position<E> p) {
        int h = 0;
        for(Position<E> c : children(p))
            h = Math.max(h,  1 + height(c));
        return h;
    }
    public int height() {
        return height(root());
    }
}
