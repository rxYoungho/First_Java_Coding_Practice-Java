package hw214_2;

public interface Ring {
    public Ring add(Ring a);
    public Ring addIdentity();
    public Ring addInverse();
    public Ring mul(Ring a);
}

