 

import java.util.Iterator;

public class Scanner implements Iterable<String> {
    private char[] buff;
    
    public static boolean isWhiteSpace(char c) {
        return c == ' ' || c == '\t' || c == '\n' || c == '\r';
    }
    public static boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
    public static boolean isAlpha(char c ) {
        return 'a' <= c && c <= 'z' || 'A' <= c && c <= 'Z';
    }
    public Scanner(String str) {
        buff = (str + '$').toCharArray();
    }
    public Iterator<String> iterator() {
        return new TokenIterator();
    }
    public class TokenIterator implements Iterator<String> {
        int pos;
        public TokenIterator() {
            pos = 0;
        }
        public boolean hasNext() {
            return pos < buff.length;
        }
        public String next() {
            while(pos < buff.length && isWhiteSpace(buff[pos])) //skip white spaces
                pos++;
            if(pos == buff.length)          //end of string
                return "";
            if(isDigit(buff[pos])) {        //number
                int begin = pos;
                while(isDigit(buff[pos]))
                    pos++;
                return new String(buff, begin, pos-begin);
            }
            else if(isAlpha(buff[pos])) {   //identifier
                int begin = pos;
                while(isAlpha(buff[pos]) || isDigit(buff[pos]))
                    pos++;
                return new String(buff, begin, pos-begin);
            }
            else {                          //punctuation marks
                pos++;
                return new String(buff, pos-1, 1);
            }
        }
    }
}
