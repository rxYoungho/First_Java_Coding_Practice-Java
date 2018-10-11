 

//Integer modulo m (m is a prime number)
public class IntMod implements Field, Ordered { //field and oredered 메소드 가져오기field에서 ring도 다 챙겨오
    private int n, m;

	public IntMod(int n, int m) { //두 정수를 모두 저장 나머지값은 저장 x
    	if(m <= 0)
    		throw new IllegalArgumentException("Not a positive divisior");
    	n = n % m;
    	n = n < 0 ? n + m : n; //if n is negative, make it positive
    	this.n = n;
    	this.m = m;
    }
	public int publicN() { //Unboxing N
		return n;
	}
	public int publicM() {
		return m;
	}
	public Ring mulIdentity() {//두 수를 곱해서 같은것 1 % m
		return new IntMod(1,m); 
	}
    public Ring mulInverse() throws ArithmeticException{
    	for(int i = 0; i <= m; i++) {
    		if((i*n) % m == 1) {
    			return new IntMod(i, m);
    		}
    	}
    	return new IntMod(0,m);//FOR JAVA ㅡ ㅡ.... 
    }
    
    public Ring add(Ring a) { // 어차피 뒤에 수는 같아야하니 앞에 만 더하기 
    	IntMod that = (IntMod)a;
    	return new IntMod(this.publicN() + that.publicN(), this.publicM());
    }
    
    public Ring addIdentity() {
    	return new IntMod(0,m);//this랑 that이랑 분모가 같아야 
    }
    
    public Ring addInverse() {
    	return new IntMod(this.publicM() - this.publicN(), m);
    }
    
    public Ring mul(Ring a) {
    	IntMod that = (IntMod)a; // 앞에 값만 곱하면 
    	return new IntMod(this.publicN() * that.publicN(), this.publicM());
    }
    
    public boolean ge(Ordered a) {
    	IntMod that = (IntMod)a;
    	if(this.publicN() >= that.publicN()) {
    		return true;
    	}
    	return false;
    	
			
    }
}
