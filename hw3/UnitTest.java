package hw214_3;

public class UnitTest {
    //Constraints to be Boolean Algebra
    public static boolean testOrCommunitativity(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testOrCommunitativity...");
        BooleanAlgebra x = a.or(b);
        BooleanAlgebra y = b.or(a);
        return x.isEqual(y);
    }
    public static boolean testAndCommunitativity(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testAndCommunitativity...");
        BooleanAlgebra x = a.and(b);
        BooleanAlgebra y = b.and(a);
        return x.isEqual(y);
    }
    public static boolean testOrDistributivity(BooleanAlgebra a, BooleanAlgebra b, BooleanAlgebra c) {
        System.out.println("testOrDistributivity...");
        BooleanAlgebra x = a.or(b.and(c));
        BooleanAlgebra y = a.or(b).and(a.or(c));
        return x.isEqual(y);
    }
    public static boolean testAndDistributivity(BooleanAlgebra a, BooleanAlgebra b, BooleanAlgebra c) {
        System.out.println("testAndDistributivity...");
        BooleanAlgebra x = a.and(b.or(c));
        BooleanAlgebra y = a.and(b).or(a.and(c));
        return x.isEqual(y);
    }
    public static boolean testOrIdentity(BooleanAlgebra a) {
        System.out.println("testOrIdentity...");
        BooleanAlgebra x = a.or(a.orIdentity());
        return x.isEqual(a);
    }
    public static boolean testAndIdentity(BooleanAlgebra a) {
        System.out.println("testAndIdentity...");
        BooleanAlgebra x = a.and(a.andIdentity());
        return x.isEqual(a);
    }
    public static boolean testOrInverse(BooleanAlgebra a) {
        System.out.println("testOrInverse...");
        BooleanAlgebra x = a.or(a.not());
        return x.isEqual(a.andIdentity());
    }
    public static boolean testAndInverse(BooleanAlgebra a) {
        System.out.println("testAndInverse...");
        BooleanAlgebra x = a.and(a.not());
        return x.isEqual(a.orIdentity());
    }
    public static boolean testIdentitiesDiff(BooleanAlgebra a) {
        System.out.println("testIdentitiesDiff...");
        BooleanAlgebra x = a.orIdentity();
        BooleanAlgebra y = a.andIdentity();
        return !x.isEqual(y);
    }
    
    //Theorems
    public static boolean testOrIdempotent(BooleanAlgebra a) {
        System.out.println("testOrIdempotent...");
        BooleanAlgebra x = a.or(a);
        return x.isEqual(a);
    }
    public static boolean testAndIdempotent(BooleanAlgebra a) {
        System.out.println("testAndIdempotent...");
        BooleanAlgebra x = a.and(a);
        return x.isEqual(a);
    }
    public static boolean testOrDominance(BooleanAlgebra a) {
        System.out.println("testOrDominance...");
        BooleanAlgebra x = a.or(a.andIdentity());
        return x.isEqual(a.andIdentity());
    }
    public static boolean testAndDominance(BooleanAlgebra a) {
        System.out.println("testAndDominance...");
        BooleanAlgebra x = a.and(a.orIdentity());
        return x.isEqual(a.orIdentity());
    }
    public static boolean testOrAbsorption(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testOrAbsorption...");
        BooleanAlgebra x = a.and(a.or(b));
        return x.isEqual(a);
    }
    public static boolean testAndAbsorption(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testAndAbsorption...");
        BooleanAlgebra x = a.or(a.and(b));
        return x.isEqual(a);
    }
    public static boolean testOrAssociativity(BooleanAlgebra a, BooleanAlgebra b, BooleanAlgebra c) {
        System.out.println("testOrAssociativity...");
        BooleanAlgebra x = a.or(b.or(c));
        BooleanAlgebra y = a.or(b).or(c);
        return x.isEqual(y);
    }
    public static boolean testAndAssociativity(BooleanAlgebra a, BooleanAlgebra b, BooleanAlgebra c) {
        System.out.println("testAndAssociativity...");
        BooleanAlgebra x = a.and(b.and(c));
        BooleanAlgebra y = a.and(b).and(c);
        return x.isEqual(y);
    }
    public static boolean testDoubleNot(BooleanAlgebra a) {
        System.out.println("testDoubleNot...");
        BooleanAlgebra x = a.not().not();
        return x.isEqual(a);
    }
    public static boolean testOrDeMorgan(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testOrDeMorgan...");
        BooleanAlgebra x = a.or(b).not();
        BooleanAlgebra y = a.not().and(b.not());
        return x.isEqual(y);
    }
    public static boolean testAndDeMorgan(BooleanAlgebra a, BooleanAlgebra b) {
        System.out.println("testAndDeMorgan...");
        BooleanAlgebra x = a.and(b).not();
        BooleanAlgebra y = a.not().or(b.not());
        return x.isEqual(y);
    }
    public static boolean testNotOrIdentity(BooleanAlgebra a) {
        System.out.println("testNotOrIdentity...");
        BooleanAlgebra x = a.orIdentity().not();
        return x.isEqual(a.andIdentity());
    }
    public static boolean testNotAndIdentity(BooleanAlgebra a) {
        System.out.println("testNotAndIdentity...");
        BooleanAlgebra x = a.andIdentity().not();
        return x.isEqual(a.orIdentity());
    }

    //Test properties
    public static void testAll(BooleanAlgebra a, BooleanAlgebra b, BooleanAlgebra c) {
        onFalseThrow( testOrCommunitativity(a, b) );
        onFalseThrow( testAndCommunitativity(a, b) );
        onFalseThrow( testOrDistributivity(a, b, c) );
        onFalseThrow( testAndDistributivity(a, b, c) );
        onFalseThrow( testOrIdentity(a) );
        onFalseThrow( testAndIdentity(a) );
        onFalseThrow( testOrInverse(a) );
        onFalseThrow( testAndInverse(a) );
        onFalseThrow( testIdentitiesDiff(a) );
        
        //Theorems
        onFalseThrow( testOrIdempotent(a) );
        onFalseThrow( testAndIdempotent(a) );
        onFalseThrow( testOrDominance(a) );
        onFalseThrow( testAndDominance(a) );
        onFalseThrow( testOrAbsorption(a, b) );
        onFalseThrow( testAndAbsorption(a, b) );
        onFalseThrow( testOrAssociativity(a, b, c) );
        onFalseThrow( testAndAssociativity(a, b, c) );
        onFalseThrow( testDoubleNot(a) );
        onFalseThrow( testOrDeMorgan(a, b) );
        onFalseThrow( testAndDeMorgan(a, b) );
        onFalseThrow( testNotOrIdentity(a) );
        onFalseThrow( testNotAndIdentity(a) );
        System.out.println("SUCCESS!");
    }
    
    public static void testSubset() {
        System.out.println("testSubset...");
        SetImpl<Integer> x = new SetImpl<Integer>(new Integer[] {1, 1, 1});
        SetImpl<Integer> y = new SetImpl<Integer>(new Integer[] {1, 2, 1, 2});
        SetImpl<Integer> z = new SetImpl<Integer>(new Integer[] {3, 2, 3, 3});
        SetImpl<Integer> u = new SetImpl<Integer>(new Integer[] {1, 2, 3});
        Subset<Integer> a = new Subset<Integer>(x, u);
        Subset<Integer> b = new Subset<Integer>(y, u);
        Subset<Integer> c = new Subset<Integer>(z, u);
        
        testAll(a, b, c);
        System.out.println("testSubset done");
    }
    
    protected static void onFalseThrow(boolean b) {
        if(!b)
            throw new RuntimeException("Error: unexpected");
    }
}
