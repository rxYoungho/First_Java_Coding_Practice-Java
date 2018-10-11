package hw214_1;

public class UnitTest {
    //Ring
    public static boolean TestAddCommutativity(Ring a, Ring b) {
        Ring x = a.add(b);
        Ring y = b.add(a);
        return Comp.eq(x, y);
    }
    public static boolean TestAddAssociativity(Ring a, Ring b, Ring c) {
        Ring x = a.add(b.add(c));
        Ring y = a.add(b).add(c);
        return Comp.eq(x, y);
    }
    public static boolean TestAddIdentity(Ring a) {
        Ring x = a.add(a.addIdentity());
        Ring y = a.addIdentity().add(a);
        return Comp.eq(x, a) &&
               Comp.eq(y, a);
    }
    public static boolean TestAddInverse(Ring a) {
        Ring b = a.addInverse();
        Ring x = a.add(b);
        Ring y = b.add(a);
        return Comp.eq(x, a.addIdentity()) &&
               Comp.eq(y, a.addIdentity());
    }
    public static boolean TestMulAssociativity(Ring a, Ring b, Ring c) {
        Ring x = a.mul(b.mul(c));
        Ring y = a.mul(b).mul(c);
        return Comp.eq(x, y);
    }
    public static boolean TestDistributivity(Ring a, Ring b, Ring c) {
        Ring w = a.mul(b.add(c));
        Ring x = a.mul(b).add(a.mul(c));
        Ring y = b.add(c).mul(a);
        Ring z = b.mul(a).add(c.mul(a));
        return Comp.eq(w, x) &&
               Comp.eq(y, z);
    }
    //Field
    public static boolean TestMulIdentity(Field a) {
        Ring x = a.mul(a.mulIdentity());
        Ring y = a.mulIdentity().mul(a);
        return Comp.eq(x, a) &&
               Comp.eq(y, a);
    }
    public static boolean TestMulInverse(Field a) {
        Ring b = a.mulInverse();
        Ring x = a.mul(b);
        Ring y = b.mul(a);
        return Comp.eq(x, a.mulIdentity()) &&
               Comp.eq(y, a.mulIdentity());
    }
    //Ordered (partial order)
    public static boolean TestReflexivity(Ordered a) {
        return a.ge(a);
    }
    public static boolean TestAntisymmetry(Ordered a, Ordered b) {
        if(a.ge(b))
            return !b.ge(a);
        else
            return b.ge(a);
    }
    public static boolean TestTransitivity(Ordered a, Ordered b, Ordered c) {
        if(a.ge(b) && b.ge(c))  return a.ge(c);
        if(a.ge(c) && c.ge(b))  return a.ge(b);
        if(b.ge(a) && a.ge(c))  return b.ge(c);
        if(b.ge(c) && c.ge(a))  return b.ge(a);
        if(c.ge(a) && a.ge(b))  return c.ge(b);
        if(c.ge(b) && b.ge(a))  return c.ge(a);
        throw new RuntimeException("Error: unexpected");
    }
    //Euclidean Algorithm
    public static boolean TestGCD(Ring a, Ring b) {
        Ring gcd = Euclidean.GCD(a, b);
        Ring c = ((Modulo)a).quo(gcd);
        Ring d = ((Modulo)b).quo(gcd);
        return Comp.eq(gcd.mul(c), a) &&
               Comp.eq(gcd.mul(d), b);
    }
    public static boolean TestLCM(Ring a, Ring b) {
        Ring lcm = Euclidean.LCM(a, b);
        Ring gcd = Euclidean.GCD(a, b);
        Ring c = ((Modulo)lcm).quo(a);
        Ring d = ((Modulo)lcm).quo(b);
        return Comp.eq(c.mul(gcd), b) &&
               Comp.eq(d.mul(gcd), a);
    }
    //Test properties
    protected static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");
    }
    public static void TestOrdered(Ordered a, Ordered b, Ordered c) {
        onFalseThrow(TestReflexivity(a));
        onFalseThrow(TestAntisymmetry(a, b));
        onFalseThrow(TestTransitivity(a, b, c));
        System.out.println("TestOrdered: Success.");
    }
    public static void TestRing(Ring a, Ring b, Ring c) {
        onFalseThrow(TestAddCommutativity(a, b));
        onFalseThrow(TestAddAssociativity(a, b, c));
        onFalseThrow(TestAddIdentity(a));
        onFalseThrow(TestAddInverse(a));
        onFalseThrow(TestMulAssociativity(a, b, c));
        onFalseThrow(TestDistributivity(a, b, c));
        System.out.println("TestRing: Success.");
    }    
    public static void TestField(Field a, Field b, Field c) {
        onFalseThrow(TestMulIdentity(a));


        System.out.println("TestField: Success.");
    }
    public static void TestEuclidean(Ring a, Ring b, Ring c) {
        onFalseThrow(TestGCD(a, b));
        onFalseThrow(TestLCM(a, b));
        System.out.println("TestEuclidean: Success.");
    }
    //Test for each type
    public static void TestInt() {
        System.out.println("TestInt...");
        Int a = new Int(12);
        Int b = new Int(30);
        Int c = new Int(7);
        TestOrdered(a, b, c);
        TestRing(a, b, c);
        TestEuclidean(a, b, c);
        System.out.println("TestInt done");
    }
    public static void TestIntMod() {
        System.out.println("TestIntMod...");
        IntMod a = new IntMod(12, 11);
        IntMod b = new IntMod(30, 11);
        IntMod c = new IntMod(7, 11);
        TestOrdered(a, b, c);
        TestRing(a, b, c);
        TestField(a, b, c);
        System.out.println("TestIntMod done");
    }
    public static void TestRat() {
        System.out.println("TestRat...");
        Rat a = new Rat(12, 5);
        Rat b = new Rat(30, 7);
        Rat c = new Rat(7, 2);
        TestOrdered(a, b, c);
        TestRing(a, b, c);
        TestField(a, b, c);
        TestEuclidean(a, b, c);
        System.out.println("TestRat done");
    }
}
