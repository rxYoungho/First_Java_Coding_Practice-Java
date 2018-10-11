 

public class Euclidean {
    protected static Ring euclidean(Ring a, Ring b) { // ring으로 받는거 주의하기.
      //TODO: return a if comp.eq(a, b)
      //      return b if comp.eq(a, a.addIdentity())
      //      return a if comp.eq(b, b.addIdentity())
      //      Otherwise, make a recursive call after mod()
    	if(Comp.eq(a,b))
    		return a;
    	if(Comp.eq(a, a.addIdentity())) // 수하나와 더ㅇ했을때 자기 자신이 되는 수. =addIdentity = 0
    		return b;
    	if(Comp.eq(b, b.addIdentity()))
    		return a;
    	if(Comp.gt(a, b)) {
    		return euclidean(((Modulo)a).mod(b), b); // ring a를 modulo로 가져와야만 mod를 통해서 값을 산출  
    	}
    	if(Comp.gt(b, a)) { //
    		return euclidean(a, ((Modulo)b).mod(a));
    	}
    	return new Int(0); // else send 0
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
