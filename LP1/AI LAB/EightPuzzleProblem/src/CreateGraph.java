import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class CreateGraph {
	ArrayList<GraphBean> graphQueue = new ArrayList<GraphBean>();
	GraphBean goalState = new GraphBean();
	Scanner scan = new Scanner(System.in);

	public int[][] acceptGraph() {
		int g[][] = new int[3][3];
		// TODO Auto-generated method stub
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				g[i][j] = scan.nextInt();
			}
		}
		return g;
	}

	public void acceptData() {
		GraphBean graphBean = new GraphBean();
		System.out.println("Start State : ");
		graphBean.state = acceptGraph();
		System.out.println("Goal State : ");
		goalState.state = acceptGraph();
		graphQueue.add(graphBean);
		
		System.out.print("\nStart State : \n"+graphBean.toString());
		System.out.print("\nGoal State : \n"+goalState.toString());
	}

	public void algorithm() {
		AStarAlgorithm a = new AStarAlgorithm();
		a.algorithmInitiate(graphQueue,goalState);
	}

}
