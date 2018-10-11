package hw214_2;

public interface Field extends Ring {
    public Ring mulIdentity();
    public Ring mulInverse() throws ArithmeticException;
}
