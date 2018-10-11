package hw214_1;

public interface Field extends Ring {
    public Ring mulIdentity();
    public Ring mulInverse() throws ArithmeticException;
}
