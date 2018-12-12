 

public class Lambda {
    public static final double EPS = 1e-5;

    //Interfaces
    public interface Func<T, R> {
        public R apply(T a);
    }
    public interface Func2<T1, T2, R> {
        public R apply(Func2<?, T2, R> self, T2 b);
    }
    public interface Rec<F, T, R> {
        public R func(Rec<F, T, R> self, T a);
        public default R apply(T a) { return func(this, a); }
    }
    public interface Rec2<F, T1, T2, R> {
        public R func(Rec2<F, T1, T2, R> self, T1 a, T2 b);
        public default R apply(T1 a, T2 b) { return func(this, a, b); }
    }
    
    public interface Bisection {
        public double func(double x);
        public default double solve(double xl, double xr) {
            /*TODO: implement this function recursively*/
            if(Math.abs(xl - xr) < EPS)
                return xl;
            else if(func(xl)*func((xl+xr)/ 2) <= 0){
                return solve(xl, (xl+xr)/2);
            }
            else if(func(xr)*func((xl+xr) / 2) <= 0){
                return solve((xl+xr)/2, xr);
            }
            else{
                throw new ArrayIndexOutOfBoundsException("Wrong!");
            }
            
        }
    }
    
    public interface Newton {
        public double func(double x);
        public default double solve(double x0) {
            return fixedPoint.apply(toNewton.apply((Func<Double, Double>)x -> func(x)), x0);
        }
    }

    //Factorial
    public static Rec<Rec<?, Integer, Integer>, Integer, Integer> 
        fact = (self, a) -> a <= 1 ? a : a * self.apply(a - 1);
        
    public static Func2<Func2<?, Integer, Integer>, Integer, Integer> 
        fact2 = (self, a) -> a <= 1 ? a : a * self.apply(self, a - 1);
        /*TODO: implement this function*/
    
    public static Func<Func<Double, Double>, Func<Double, Double>> 
        derivative = f -> x -> (f.apply(x + EPS) - f.apply(x))/EPS;
        
    public static Func<Func<Double, Double>, Func<Double, Double>> 
        toNewton = f -> x -> x - f.apply(x) / derivative.apply(f).apply(x);
        /*TODO: implement this function*/
    public static Rec2<?, Func<Double, Double>, Double, Double> 
        fixedPoint = (self, f, x0) -> Math.abs(f.apply(x0) - x0) < EPS ? x0 : self.apply(f, f.apply(x0));
        /*TODO: implement this function*/
    
    public static Rec<?, Func<Double, Double>, Func<Double, Double>> 
        fixedPoint2 = (self, f) -> x0 -> Math.abs(f.apply(x0) - x0) < EPS ? x0 : self.apply(f).apply(f.apply(x0));
        /*TODO: implement this function*/ 

    public static Func<Double, Double> sqrt = x -> ((Newton) y -> y * y - x).solve(1.0);
    
    public static void main(String[] args) {
        //Finding a root
        Bisection b = x -> x*x + 4*x - 8;
        System.out.println("ans: " + b.solve(-10,  10));

        //Recursion
        System.out.println("ans: " + fact.apply(5));
        
        System.out.println("ans: " + fact2.apply(fact2, 5));
        
        //Fixed point
        Func<Double, Double> cx = x -> Math.cos(x);
        System.out.println("ans: " + fixedPoint.apply(cx, 1.0));
        System.out.println("ans: " + fixedPoint2.apply(cx).apply(1.0));

        //Newton's method
        Newton n = x -> x*x + 4*x - 8;
        System.out.println("ans: " + n.solve(-10));
        System.out.println("ans: " + sqrt.apply(2.0));
    }
} 
