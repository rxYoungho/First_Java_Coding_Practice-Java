 
public class Expression {
    private static enum Action { Push, Pop }
    private static Action[][] act;
    
    static {
        act = new Action[128][128]; //[stack top][input token]
        act['#']['('] = act['(']['('] = act['+']['('] = act['-']['('] = act['*']['('] = act['/']['('] = Action.Push;
                        act['('][')'] = act['+'][')'] = act['-'][')'] = act['*'][')'] = act['/'][')'] = Action.Pop;
        act['#']['+'] = act['(']['+'] =                                                                 Action.Push;
                                        act['+']['+'] = act['-']['+'] = act['*']['+'] = act['/']['+'] = Action.Pop;
        act['#']['-'] = act['(']['-'] =                                                                 Action.Push;
                                        act['+']['-'] = act['-']['-'] = act['*']['-'] = act['/']['-'] = Action.Pop;
        act['#']['*'] = act['(']['*'] = act['+']['*'] = act['-']['*'] =                                 Action.Push;
                                                                        act['*']['*'] = act['/']['*'] = Action.Pop;
        act['#']['/'] = act['(']['/'] = act['+']['/'] = act['-']['*'] =                                 Action.Push;
                                                                        act['*']['/'] = act['/']['/'] = Action.Pop;
        act['#']['$'] = act['(']['$'] = act['+']['$'] = act['-']['$'] = act['*']['$'] = act['/']['$'] = Action.Pop;
    }
    
    public static String infixToPostfix(String expr) {
        //TODO: implement this method
    	Scanner scan = new Scanner(expr);
    	Stack<Character> Oper = new StackByArray<Character>();
    	Oper.push('#');
    	String tmp = "";
    	for(String input: scan) {
    		char c = input.charAt(0);
    		if(Scanner.isDigit(c)) {
    			tmp += c + " ";
    		}
    		else {
    			while(!Oper.isEmpty() && act[Oper.top()][c] == Action.Pop) {
    				char Op = Oper.pop();
    				if(Op != '(' && Op != ')' && Op != '#'){
    				    
    				    tmp += Character.toString(Op) + " ";
    				}
    			}

    			Oper.push(c);
    		}
    	}
        return tmp;
    }
    
    public static double evalPostfixExpr(String expr) {
        //TODO: implement this method
        Scanner scan = new Scanner(expr);
    	Stack<Double> num = new StackByArray<Double>();
        for(String input : scan){
            char c = input.charAt(0);
            if(Scanner.isDigit(c)){
                num.push(Double.parseDouble(input));  // if the first digit is a digit, then change string to double
            }
            else if(c != '$'){
                double n1 = num.pop();
                double n2 = num.pop();
                if(c == '+')num.push(n2+n1);
                else if(c == '-') num.push(n2-n1);
                else if(c == '*') num.push(n2*n1);
                else if(c == '/') num.push(n2/n1);   
            }
        }
        
        return num.top();
    }    
}
