package hw214_3;

public interface BooleanAlgebra {
    public BooleanAlgebra or(BooleanAlgebra a);
    public BooleanAlgebra and(BooleanAlgebra a);
    public BooleanAlgebra not();
    public BooleanAlgebra orIdentity();
    public BooleanAlgebra andIdentity();
    
    //Additional methods
    public boolean isEqual(BooleanAlgebra a);
}
