/*
 	Program to implement Loop Optimization in Java

*/
import java.util.*;
import java.io.*;

class LoopOptimization {
	static List<String> variables;
	static List<String> lines;
	
	public static void main(String args[]) throws Exception {
		variables = new LinkedList<>();
		lines = new LinkedList<>();
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
		String s = new String();
		char loop_iterator = 'x';
		while((s = br.readLine()) != null) {
			lines.add(s);
			StringTokenizer st = new StringTokenizer(s, " ();", false);
			if(st.nextToken().equals("for")) {
				loop_iterator = st.nextToken().charAt(0);
				s = br.readLine();
				// we directly move to the next line and start parsing the expression
				String left = s.substring(0, s.indexOf('=')).trim();
				String right = s.substring(s.indexOf('=')+1, s.length()).trim();
				parseRight(left, right, loop_iterator);
			}
		}
		
		PrintWriter out = new PrintWriter(new FileOutputStream("output.txt"), true);
		Iterator<String> iterator = variables.iterator();
		int ctr = 0;
		while(iterator.hasNext()) {
			ctr++;
			out.println("t" + ctr + " = " + iterator.next());
		}
		out.println();
		iterator = lines.iterator();
		while(iterator.hasNext()) {
			out.println(iterator.next());
		}
	}
	
	static void parseRight(String left, String right, char c) {
		String loop_iterator = "[" + c + "]";
		String temp_line = new String();
		int open, close = -1;
		do {
			open = right.indexOf('(');
			if(open != -1) {
				// found an expression to check
				close = right.indexOf(')');
				temp_line += right.substring(0, open+1);
				String s = right.substring(open+1, close);
				// 's' is the expression to check
				if(s.indexOf(loop_iterator) == -1) {
					// This expression does not contain the loop iterator
					try {
						Integer.parseInt(s);
						// If successful, 's' is a constant
						// So no need to create a new variable for it
						temp_line += Integer.parseInt(s) + ")";
					} catch(Exception e) {
						// Else it is an expression or a variable
						if(containsOperator(s)) {
							// 's' is an expression, so substitute as variable
							variables.add(s);
							temp_line += "t" + variables.size() + ")";
						} else {
							// 's' is a variable so leave it as it is
							temp_line += s + ")";
						}
					}
				} else {
					temp_line += right.substring(open+1, close+1);
				}
				right = right.substring(close+1, right.length());
			}
		} while(open != -1);
		lines.add("    " + left + " = " + temp_line + ";");
	}
	
	static boolean containsOperator(String s) {
		if(s.indexOf('+') == -1) {
			if(s.indexOf('-') == -1) {
				if(s.indexOf('*') == -1) {
					if(s.indexOf('/') == -1) {
						return false;
					}
				}
			}
		}
		return true;
	}
}
