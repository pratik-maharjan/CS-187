package evaluator;

import parser.ArithParser;
import stack.LinkedStack;

public class InfixEvaluator extends Evaluator {
	
	private LinkedStack<String> operators = new LinkedStack<String>();
	private LinkedStack<Integer> operands  = new LinkedStack<Integer>();
	
	/** return stack object (for testing purpose) */
	public LinkedStack<String> getOperatorStack() { return operators; }
	public LinkedStack<Integer> getOperandStack()  { return operands;  }
	
	
	/** This method performs one step of evaluation of a infix expression.
	 *  The input is a token. Follow the infix evaluation algorithm
	 *  to implement this method. If the expression is invalid, throw an
	 *  exception with the corresponding exception message.
	 */	
	public void evaluate_step(String token) throws Exception {
		if(isOperand(token)) {
			int number = 0;
			number = Integer.parseInt(token);
			operands.push(number);
			// TODO: What do we do if the token is an operand?
		} else {
			if(token.equals("(")) {
				operators.push(token);
			}
			else if(token.equals("!") || token.equals("*") || token.equals("/") || token.equals("+") || token.equals("-")) {
				String operatorPre = "";
				operatorPre = operators.top();
				if(operators.isEmpty() || precedence(token) > precedence(operatorPre)) {
					operators.push(token);
				}
				else {
					if(!operators.isEmpty()) {
						process_operator();
					}
					operators.push(token);
				}
			}
			else if(token.equals(")")) {
				while(!operators.top().equals("(")) {
					process_operator();
					if(operators.isEmpty()) {
						throw new Exception("missing (");
					}
				}
				operators.pop();
				
			}
			else {
				while(!operators.isEmpty()) {
					process_operator();
				}
				throw new Exception("invalid operator");
			}
			/* TODO: What do we do if the token is an operator?
			   If the expression is invalid, make sure you throw
			   an exception with the correct message.
			   
			   You can call precedence(token) to get the precedence
			   value of an operator. It's already defined in 
			   the Evaluator class.
			 */ 
		}	
	}
	/** This method evaluates an infix expression (defined by expr)
	 *  and returns the evaluation result. It throws an Exception object
	 *  if the infix expression is invalid.
	 */
	public Integer evaluate(String expr) throws Exception {
		
		for(String token : ArithParser.parse(expr)) {
			evaluate_step(token);
		}
		
		/* TODO: what do we do after all tokens have been processed? */
			while(!operators.isEmpty()) {
				process_operator();
			}
		// The operand stack should have exactly one operand after the evaluation is done
		if(operands.size()>1)
			throw new Exception("too many operands");
		else if(operands.size()<1)
			throw new Exception("too few operands");
		
		return operands.pop();
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(new InfixEvaluator().evaluate("5+(5+2*(5+9))/!8"));
	}
	
	public void process_operator() throws Exception {
		String sign = operators.pop();
		int result = 0;
		int numberA = 0;
		int numberB = 0;
		if(operands.isEmpty()) {
			throw new Exception("too few operands"); 
		}
		if(sign.equals("!")) {
			numberB = operands.pop();
			result = -1 * numberB;
		}
		else if(sign.equals("*")) {
			numberB = operands.pop();
			if(operands.isEmpty()) {
				throw new Exception("too few operands"); 
			}
			numberA = operands.pop();
			result = numberA * numberB;
		}
		else if(sign.equals("/")) {
			numberB = operands.pop();
			if(operands.isEmpty()) {
				throw new Exception("too few operands"); 
			}
			numberA = operands.pop();
			if(numberB == 0) {
				throw new Exception("division by zero");
			}
			result = numberA / numberB;
		}
		else if(sign.equals("+")) {
			numberB = operands.pop();
			if(operands.isEmpty()) {
				throw new Exception("too few operands"); 
			}
			numberA = operands.pop();
			result = numberA + numberB;
		}
		else if(sign.equals("-")) {
			numberB = operands.pop();
			if(operands.isEmpty()) {
				throw new Exception("too few operands"); 
			}
			numberA = operands.pop();
			result = numberA - numberB;
		}
		else {
			throw new Exception("invalid operator");
		}
		operands.push(result);
	}
}
