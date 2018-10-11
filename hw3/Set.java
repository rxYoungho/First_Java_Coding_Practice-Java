package hw214_3;

public interface Set<E extends Comparable<E>> {
    public boolean isEqual(Set<E> set);
    public Set<E> union(Set<E> set);
    public Set<E> intersection(Set<E> set);
    public Set<E> difference(Set<E> set);
}
