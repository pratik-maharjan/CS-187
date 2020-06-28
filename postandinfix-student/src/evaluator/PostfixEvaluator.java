package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class PostfixEvaluator extends Evaluator {
	
	private LinkedStack<Integer> stack = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<Integer> getStack() { return stack; }
	
	/** This method performs one step of evaluation of a postfix expression.
	 *  The input is a token. Follow the postfix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			int number = 0;
			number = Integer.parseInt(token);
			stack.push(number);
			// TODO: What do we do if the token is an operand?
		} else {
			int result = 0;
			int numberA = 0;
			int numberB = 0;
			if(stack.isEmpty()) {
				throw new Exception("too few operands"); 
			}
			if(token.equals("+")) {
				numberB = stack.pop();
				if(stack.isEmpty()) {
					throw new Exception("too few operands"); 
				}
				numberA = stack.pop();
				result = numberA + numberB;
			}
			else if(token.equals("-")) {
				numberB = stack.pop();
				if(stack.isEmpty()) {
					throw new Exception("too few operands"); 
				}
				numberA = stack.pop();
				result = numberA - numberB;
			}
			else if(token.equals("*")) {
				numberB = stack.pop();
				if(stack.isEmpty()) {
					throw new Exception("too few operands"); 
				}
				numberA = stack.pop();
				result = numberA * numberB;
			}
			else if(token.equals("/")) {
				numberB = stack.pop();
				if(stack.isEmpty()) {
					throw new Exception("too few operands"); 
				}
				numberA = stack.pop();
				if(numberB == 0) {
					throw new Exception("division by zero");
				}
				result = numberA / numberB;
			}
			else if(token.equals("!")) {
				numberA = stack.pop();
				result = -numberA;
			}
			else {
				throw new Exception("invalid operator");
			}
			stack.push(result);
			
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message
			 */ 
		}		
	}
	/** This method evaluates a postfix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception
	 *  if the postfix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		// The stack should have exactly one operand after evaluation is done
		if(stack.size()>1) {
			throw new Exception("too many operands");
		} else if (stack.size()<1) {
			throw new Exception("too few operands");
		}
		return stack.pop(); 
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new PostfixEvaluator().evaluate("50 25 ! / 3 +"));
	}
}