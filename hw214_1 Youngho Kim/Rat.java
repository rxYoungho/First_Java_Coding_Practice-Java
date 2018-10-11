 

//Rational number
public class Rat implements Field, Modulo, Ordered {
	private int n,d; //numerator, denominator
    public Rat(int numerator, int denumerator) {
    	 //TODO: make numerator and denominator prime to
        //each other using Euclidean.gcd
    	Int GCD = (Int)Euclidean.GCD(new Int(numerator), new Int(denumerator)); //Ring 으로 받으니깐 Int로 변환.
    	int newGCD = GCD.getInt();    	// GEtint로 리얼 정수 가져오기.
    	n = numerator / newGCD; //분자를 최대공약수로 나눠줘서 약분 시작
    	d = denumerator / newGCD; //분모로 나눠주기 약분 완료.
    }
    public int publicN() { //private n에서 n return시키기
    	return n;
    }
    public int publicD() {
    	return d;
    }
    public Ring quo(Ring a) {
    	Rat that = (Rat)a; //Ring을 받고 있는 a를 Rat으로 생각하게 하기
    	return new Rat(this.n * that.publicD(), this.d * that.publicN()); //분자부터 받는거 생각하
    }
    public Ring mulIdentity() { // 곱했을때 자신이 나오는
    	return new Rat(1, 1); // 1/1 == always 1
    }
  
    public Ring mulInverse() throws ArithmeticException{ //두 레셔널 넘버를 곱해서 1이 나오는것
    	return new Rat(this.d, this.n); // 
    }
    
    public Ring add(Ring a) {
    	Rat that = (Rat)a;
    	return new Rat(this.n * that.publicD()+ this.d * that.publicN(), this.d * that.publicD()); //분자끼리는 크로스로 곱하고, 분모는 서로 곱하기 / 분모는 바로 더해도 가능
    }
    
    
    public Ring addIdentity() {
    	return new Rat(0, 1); // 0/1 == always 0
    }
    
    public Ring addInverse() {//둘이 더해서 0이 되는것
    	return new Rat(this.n * -1, this.d);
    }
    
    public Ring mul(Ring a) {
    	Rat that = (Rat)a;
    	return new Rat(this.n * that.publicN(), this.d * that.publicD());//,는 over라고 생각
    }

    //***
    public Ring mod(Ring a) {
    	Rat r = (Rat)a;
    	if(r.n == 0)
    		throw new ArithmeticException("Division by zero");
    	return new Rat((n*r.d)% (d*r.n), d*r.d);
    	//return new Rat((원래 분자 * 받는 분모) % (원래 분모 * 받는 분자), 분모 * 받는 분모);
    }
    //Ordered
    public boolean ge(Ordered a) {
    	Rat r = (Rat)a;
    	return n*r.d >= d*r.n;
    }
}
