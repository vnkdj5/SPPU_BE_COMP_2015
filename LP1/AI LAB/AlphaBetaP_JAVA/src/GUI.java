import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JTextField;

public class GUI {
 JFrame frame;
 int height;
 JTextField array[];
 
 public GUI()
 {
	 frame=new JFrame("Alpha Beta Pruning");
	 frame.setBounds(0,0,800,800);
	 frame.setVisible(true);
	 frame.setBackground(Color.WHITE);
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setLayout(null);
 }
 
 public void addElements(int score[], int height)
 {
	 this.height=height;
	 int totalNodes=(int) (Math.pow(2, height+1)-1); //for graph e.g. 15
	 System.out.println("Total Nodes: "+totalNodes);
	 
	 array=new JTextField[totalNodes];
	 int dataArray[]=new int[totalNodes];
	 
	 //Fill Array with values from score and dummy values
	 int start=score.length-1;
	 int j=0;
	 while(start<totalNodes)
	 {
		 dataArray[start++]=score[j++];
	 }
	 start=0;
	 while(start<score.length-1)
	 {
		 dataArray[start++]=-99;
	 }
	 
	 int rows_of_frame=600/height; //for finding height of each row start //GUI  things //200
	 int boxheight=60,boxWidth=50;
	 Font f=new Font("Serif", Font.PLAIN, 24);
	 int treelevel=height;
	 
	 while(treelevel>=0) //Now actually draw GUI
	 {
		 int noOfElements=(int) Math.pow(2, treelevel);
		 
		 int columns_of_frame=800/noOfElements; //100 value
		 
		 int offset=columns_of_frame-boxWidth; //100-50=50
		 offset=offset/2; //25
		 
		 int x=offset;
		 int y=rows_of_frame*treelevel+boxheight;
		 
		 start=noOfElements-1; //start index for data at level
		 
		 for(int i=0,k=start;i<noOfElements;i++,k++)
		 {
			 array[k]=new JTextField(" "+dataArray[k]);
			 array[k].setBounds(x,y,boxWidth,boxheight);
			 array[k].setFont(f);
			 array[k].setBackground(Color.RED);
			 frame.add(array[k]);
			 frame.repaint();
			 
			 x=x+boxWidth+2*offset; //Important
		 }
		 treelevel--; //reduce level for drawing
	 }
	 
 }
 
 //for setting 
 public void setColor(int node) {
	 int offset=(int) (Math.pow(2,height)-1);
	 array[offset+node].setBackground(Color.GREEN);
 }
 public void setText(int ans,int depth,int nodeNo) {
	 int offset=(int) Math.pow(2, depth)-1;
	 array[offset+nodeNo].setText(" "+ans);
	 array[offset+nodeNo].setBackground(Color.GRAY);
 }
 
 void setRoot(int ans)
 {
     array[0].setText("  "+ans);
     array[0].setBackground(new Color(150, 150, 150));

 }
}
