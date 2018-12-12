 

public class Parser {
    private static class Node {
        public Integer num;
        public Character opr;
        public Node(Integer num) { this.num = num; }
        public Node(Character opr) { this.opr = opr; }
        public String toString() {
            return num != null ? num.toString()
                :  opr != null ? opr.toString()
                :  ""
                ;
        }
    }
    
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
        act['#']['/'] = act['(']['/'] = act['+']['/'] = act['-']['/'] =                                 Action.Push;
                                                                        act['*']['/'] = act['/']['/'] = Action.Pop;
        act['#']['$'] = act['(']['$'] = act['+']['$'] = act['-']['$'] = act['*']['$'] = act['/']['$'] = Action.Pop;
    }
    
    public static BinaryTree<Node> parseExpr(String expr) {
        Scanner scan = new Scanner(expr);
        Stack<LinkedBinaryTree<Node>> tree = new StackByArray<LinkedBinaryTree<Node>>();
        Stack<Character> opr = new StackByArray<Character>();
        opr.push('#');
        String tmp = "";
        for(String input: scan) {
            char c = input.charAt(0);
            if(Scanner.isDigit(c)) {
                tmp += c + " ";
            }
            else {
                while(!opr.isEmpty() && act[opr.top()][c] == Action.Pop) {
                    char Op = opr.pop();
                    if(Op != '(' && Op != ')' && Op != '#'){
                        
                        tmp += Character.toString(Op) + " ";
                    }
                }

                opr.push(c);
            }
        }
        Scanner scans = new Scanner(tmp);
        for(String input : scans){
            char c = input.charAt(0);
            if(Scanner.isDigit(c)){
                LinkedBinaryTree LBT = new LinkedBinaryTree();
                LBT.addRoot(input);
                tree.push(LBT);
            }
            else if(!Scanner.isDigit(c) && c != '$'){
                LinkedBinaryTree LBT = new LinkedBinaryTree();
                LBT.addRoot(input.charAt(0));
                LinkedBinaryTree LBT2 = tree.pop();
                LinkedBinaryTree LBT1 = tree.pop();
                LBT.attach(LBT.root(), LBT1, LBT2);
                tree.push(LBT);
            }
            
        }
        return tree.top();
    }
    public static double evalExpr(BinaryTree<Node> tree) {
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) tree;
        Stack<Double> num = new StackByArray<Double>();
        for(Position<Node> input : parseTree.postorder()){
            char c = ("" + input.getElement()).charAt(0);
            if(Scanner.isDigit(c)){
                num.push(Double.parseDouble(("" + input.getElement())));
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
        //TODO: - evalExpr will be similar to evalPostfixExpr function that evaluates
        //        postfix expressions.
        //      - While traversing the nodes of the parseTree in the post-order,
        //        evaluate the expression by pushing/popping operands to/from the stack num 
    }
    
    public static String infixToPrefix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.preorder())
            strExp += p.getElement() + " ";
        return strExp;
    }

    public static String infixToPostfix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.postorder())
            strExp += p.getElement() + " ";
        return strExp;
    }
    
    public static String infixToInfix(String expr) {
        String strExp = "";
        LinkedBinaryTree<Node> parseTree = (LinkedBinaryTree<Node>) parseExpr(expr);
        for(Position<Node> p: parseTree.inorder())
            strExp += p.getElement() + " ";
        return strExp;
    }
}
