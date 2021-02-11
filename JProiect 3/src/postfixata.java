import java.util.ArrayDeque;
import java.util.Deque;

public class postfixata {
	static private String expresie;
	private Deque<Character> stiva = new ArrayDeque<Character>();

	public postfixata(String infixexpresie) {
		expresie = infixexpresie;
	}

	public String infixToPostfix() {
		String postfixString = "";

		for (int index = 0; index < expresie.length(); ++index) {
			char value = expresie.charAt(index);
			if (value == '(') {
				stiva.push('('); 
			} else if (value == ')') {
				Character oper = stiva.peek();

				while (!(stiva.isEmpty()) && !(oper.equals('('))) {
					stiva.pop();
					postfixString += oper.charValue();
					if (!stiva.isEmpty()) 
						oper = stiva.peek();
				}
				stiva.pop(); 
			} else if (value == '+' || value == '-') {
				if (stiva.isEmpty()) {
					stiva.push(value);
				} else {
					Character oper = stiva.peek();
					while (!(stiva.isEmpty() || oper.equals(('(')) || oper.equals((')')))) {
						stiva.pop();
						postfixString += oper.charValue();
						oper = stiva.peek();
					}
					stiva.push(value);
				}
			} else if (value == '*' || value == '/') {
				if (stiva.isEmpty()) {
					stiva.push(value);
				} else {
					Character oper = stiva.peek();
					while (!stiva.isEmpty() && !oper.equals(('(')) && !oper.equals(('+')) && !oper.equals(('-'))) {
						stiva.pop();
						postfixString += oper.charValue();
						oper = stiva.peek();
					}
					stiva.push(value);
				}
			} else if (value == '^') {
					stiva.push(value);
			} else {
				postfixString += value;
			}
		}

		while (!stiva.isEmpty()) {
			Character oper = stiva.peek();
			if (!oper.equals(('('))) {
				stiva.pop();
				postfixString += oper.charValue();
			}
		}
		return postfixString;
	}
	
	public static int evaluare(String tokens) {
        Deque<Integer> stiva = new ArrayDeque<>();
        for(int i = 0; i < tokens.length(); i++){
            if(tokens.charAt(i) == '+'){
                int a = stiva.pop();
                int b = stiva.pop();
                stiva.push(a+b);
            } else if(tokens.charAt(i) == '-'){
                int a = stiva.pop();
                int b = stiva.pop();
                stiva.push(b - a);
            } else if(tokens.charAt(i) == '*'){
                int a = stiva.pop();
                int b = stiva.pop();
                stiva.push(a * b);
            } else if(tokens.charAt(i) == '/'){
                int a = stiva.pop();
                int b = stiva.pop();
                stiva.push(b / a);
            } else if(tokens.charAt(i) == '^'){
                int a = stiva.pop();
                int b = stiva.pop();
                int b1 = b;
                while(a > 1) {
                	b = b * b1; a--; 
                }
                stiva.push(b);
            }else {
                stiva.push(Character.getNumericValue(tokens.charAt(i)));
            }
        }
        return stiva.pop();
    }

	public static void main(String[] args) {
		String expresie = "3+(2+1)*2^3^2-8/(5-1*2/2)";
		expresie = expresie.replaceAll("\\s+", "");
		postfixata convert = new postfixata(expresie);
		String after = convert.infixToPostfix();
		System.out.println("Forma postfixata: \n" + after);
		System.out.println("Evaluarea expresiei:\n" + evaluare(after));
	}
}