package hw214_2;

public class Euclidean {
    protected static Ring euclidean(Ring a, Ring b) {
      //TODO: return a if comp.eq(a, b)
      //      return b if comp.eq(a, a.addIdentity())
      //      return a if comp.eq(b, b.addIdentity())
      //      Otherwise, make a recursive call after mod()

    }
    public static Ring GCD(Ring a, Ring b) {
        if(Comp.lt(a, a.addIdentity()))     //if a < 0
            a = a.addInverse();
        if(Comp.lt(b, b.addIdentity()))     //if b < 0
            b = b.addInverse();
        return euclidean(a, b);
    }
    public static Ring LCM(Ring a, Ring b) {
        Ring gcd = GCD(a, b);
        Ring q = ((Modulo)b).quo(gcd);
        return a.mul(q);
    }
}
