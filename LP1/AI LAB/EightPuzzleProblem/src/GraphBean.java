import java.util.Arrays;

public class GraphBean {
	//int startState[][] = { { 1, 8, 3 }, { 6, 0, 7 }, { 4, 2, 5 } };
	//int goalState[][] = { { 1, 2, 3 }, { 8, 0, 4 }, { 7, 6, 5 } };
	int state[][];
	int hn ;		//heuristic
	int gn;		//depth
	int fn;		//total cost function h+g
	public GraphBean() {
		this.hn = 0;
		this.gn = 0;
		this.fn = 0;
	}

	int step = 0;
	
	@Override
	public String toString() {
		//return "GraphBean [startState=" + Arrays.toString(startState) + ", goalState=" + Arrays.toString(goalState)
			//	+ "]";
		String str = new  String("");
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				str += ("\t" + state[i][j]);
			}
			str += "\n";
		}
		return str;
	}
}
