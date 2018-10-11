package hw214_1;

public class Comp {
    public static boolean ge(Object a, Object b) {    //greater than or equal to
        Ordered _a = (Ordered)a;
        Ordered _b = (Ordered)b;
        return _a.ge(_b);
    }
    public static boolean gt(Object a, Object b) {    //greater than
    	
        return ge(a, b) && ne(a, b);
    }
    public static boolean le(Object a, Object b) {    //less than or equal to
        return ge(b, a);
    }
    public static boolean lt(Object a, Object b) {    //less than
        return ge(b, a) && ne(a, b);
    }
    public static boolean eq(Object a, Object b) {    //equal
        return ge(a, b) && ge(b, a);
    }
    public static boolean ne(Object a, Object b) {    //not equal
        return !eq(a, b);
    }
}
