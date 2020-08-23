import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

public class AStarAlgorithm {
	ArrayList<GraphBean> graphQueue = new ArrayList<GraphBean>();
	GraphBean goalState = new GraphBean();
	int heuristicValues[] = new int[4];
	int step;

	public void algorithmInitiate(ArrayList<GraphBean> gq, GraphBean gs) {
		// TODO Auto-generated method stub
		graphQueue = gq;
		goalState = gs;
		int i = 1;
		while (!goalStateReached()) { // Till goal state not reached
			System.out.println("\n*********** iteration " + i + " ***********");
			algorithmStep(i);
			i++;
		}
	}

	public boolean goalStateReached() {
		GraphBean gb = new GraphBean();
		gb = graphQueue.get(graphQueue.size()-1);	//getting topmost state | most recent state
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (gb.state[i][j] != goalState.state[i][j])
					return false;
			}
		}
		// TODO Auto-generated method stub
		return true;
	}

	public int[][] moveLeft(int g[][]) {
		int temp;
		int flag = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (g[i][j] == 0 && j != 0) {
					temp = g[i][j];
					g[i][j] = g[i][j - 1];
					g[i][j - 1] = temp;
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}
		return g;
	}

	public int[][] moveRight(int g[][]) {
		int temp;
		int flag = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (g[i][j] == 0 && j != 2) {
					temp = g[i][j];
					g[i][j] = g[i][j + 1];
					g[i][j + 1] = temp;
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}
		return g;
	}

	public int[][] moveUp(int g[][]) {
		int temp;
		int flag = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (g[i][j] == 0 && i != 0) {
					temp = g[i][j];
					g[i][j] = g[i - 1][j];
					g[i - 1][j] = temp;
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}
		return g;
	}

	public int[][] moveDown(int g[][]) {
		int temp;
		int flag = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (g[i][j] == 0 && i != 2) {
					temp = g[i][j];
					g[i][j] = g[i + 1][j];
					g[i + 1][j] = temp;
					flag = 1;
					break;
				}
			}
			if (flag == 1)
				break;
		}
		return g;
	}

	// Here
	public int[][] initializeTempGraph() {
		int g[][] = { { 1, 8, 3 }, { 6, 0, 7 }, { 4, 2, 5 } };// dummy assignments

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g[i][j] = graphQueue.get(0).state[i][j];
			}
		}
		return g;
	}

	public int heuristicFunction(int g[][]) {
		int count = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (g[i][j] != goalState.state[i][j] && g[i][j]!=0)
					count++;
			}
		}
		return count;
	}

	public void algorithmStep(int s) { // step is g(x)
		int g[][] = {};
		step = s; // initialize step i.e. g(x)
		g = initializeTempGraph();
		g = moveLeft(g);
		heuristicValues[0] = heuristicFunction(g);
		displayIteration(g);

		g = initializeTempGraph();
		g = moveRight(g);
		heuristicValues[1] = heuristicFunction(g);
		displayIteration(g);

		g = initializeTempGraph();
		g = moveUp(g);
		heuristicValues[2] = heuristicFunction(g);
		displayIteration(g);

		g = initializeTempGraph();
		g = moveDown(g);
		heuristicValues[3] = heuristicFunction(g);
		displayIteration(g);
		
		int min = 0;
		// Finding the minimum heuristic value; L/R/U/D order
		for (int i = 0; i < 4; i++) {
			if (heuristicValues[min] > heuristicValues[i])
				min = i;
		}

		if (min == 0)
			g = moveLeft(graphQueue.get(0).state);
		else if (min == 1)
			g = moveRight(graphQueue.get(0).state);
		else if (min == 2)
			g = moveUp(graphQueue.get(0).state);
		else if (min == 3)
			g = moveDown(graphQueue.get(0).state);

		// add the current graph to to the queue and move to next level
		System.out.print("\n\n------------->>SELECTED PUZZLE : \n");
		graphQueue.add(displayIteration(g));

	}
	public GraphBean displayIteration(int[][] g) {

		GraphBean gb = new GraphBean();
		gb.state = g;
		gb.hn = heuristicFunction(g);
		gb.gn = step;
		gb.fn = gb.gn + gb.hn;
		System.out.print("\nPuzzle : \n" + gb.toString());
		System.out.print("\nf(n) = "+gb.fn+"\t,h(n) = "+gb.hn+"\t,g(n) = "+gb.gn);
		return gb;
	}
}
