package hw214_2;

import ring.Comp;
import ring.Field;

@SuppressWarnings("unchecked")
public class Poly<F extends Field> implements Ring, Modulo, Ordered {
    private F[] coef;
    public Poly(F[] coef) {
        int n = coef.length;
        while(n >= 2 && Comp.eq(coef[n-1], coef[0].addIdentity()))
            n--;
        this.coef = (F[])new Field[n];
        for(int i = 0; i < n; i++)
            this.coef[i] = coef[i];
            
    }    
    //TODO: implement the rest
}
