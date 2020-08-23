
public class AlphaBeta {
	int scores[]={3, 5, 6,9,1,2,0,-1};
	int INFINITY=1000;
	int MINUSINFINITY=-1000;
	GUI gui;
	public AlphaBeta()
	{
		gui=new GUI();
	}
	public int minMax(int depth, int nodeNo, boolean isMax, int alpha, int beta)
	{
		if(depth==3)
		{
			gui.setColor(nodeNo);
			return scores[nodeNo];
			
		}
		
		if(isMax) //maximizing player
		{
			int bestValue=MINUSINFINITY;
			for(int i=0;i<2;i++)
			{
				int value=minMax(depth+1, nodeNo*2+i, false, alpha, beta);
				bestValue=Math.max(value, bestValue);
				alpha=Math.max(bestValue, alpha);
				if(beta<=alpha)
					break;
			}
			
			
			gui.setText(bestValue, depth, nodeNo);
			return bestValue;
		}
		else //minimizing player
		{
			int bestValue=INFINITY;
			for(int i=0;i<2;i++)
			{
				int value=minMax(depth+1, 2*nodeNo+i, true, alpha, beta);
				bestValue=Math.min(bestValue, value);
				beta=Math.min(beta, bestValue);
				if(beta<=alpha)
					break;
			}
			gui.setText(bestValue, depth, nodeNo);

			return bestValue;
		}
	}
	
public int getheight(int n)
{
	if(n==1)
		return 0;
	else
		return 1+getheight(n/2);
}
	public static void main(String[] args) {
		
		AlphaBeta alphaBeta=new AlphaBeta();
		
		int height=alphaBeta.getheight(alphaBeta.scores.length);
		alphaBeta.gui.addElements(alphaBeta.scores, height);
		System.out.println();
		System.out.println("Optimal Value "+alphaBeta.minMax(0, 0, true, alphaBeta.MINUSINFINITY, alphaBeta.INFINITY));
	}

}
