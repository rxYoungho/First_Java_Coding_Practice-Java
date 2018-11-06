
public class Newton {
    public static final double EPS = 1e-10; 
    public interface Func<T, R> {
        public R apply(T a);
    }
    
    public static double newton(Func<Double, Double> f, double x) {
        //TODO: implement the Newton method that finds a root of f
        //      starting from x
        double fx = f.apply(x);
        double xy = (f.apply(x+EPS)-fx / EPS);
        double firstX = x - fx / xy;
        if(Math.abs(firstX - x) < EPS){
            return firstX;
        }
        return newton(f, firstX);
        
    }
    
    public static double sqrt(double x) {
        //TODO: implement sqrt using newton
        return newton(a -> x * x - a, 1);
    }
    
    public static void main(String[] args) {
        System.out.println("ans: " + newton(x -> x * x - 2  * x - 5, 10));
        System.out.println("ans: " + sqrt(2));
    }
}