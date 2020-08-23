import java.util.Stack;


public class Stacks {
	//This class represents one stack 
	//Each stack stores a state that has
	//value and heuristic associated with it
	Stack<StackBean> stack;
	int stackSize;
	public Stacks() {
		super();
		this.stack  = new Stack<StackBean>();
		this.stackSize = 0;
	}
	@Override
	public String toString() {
		String str;
		str = stack.toString();
		return str;
	}
}
