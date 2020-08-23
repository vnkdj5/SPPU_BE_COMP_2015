/*
 	Program to Implement Common SubExpression Elimination in Java
PICT
*/
import java.io.*;
import java.util.*;

class Common_SubExpression
{
	public static void main(String args[])throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));     // Read Input file
		PrintWriter pw=new PrintWriter(new FileOutputStream(new File("output.txt")),true);                 // Prepare Output file
		
		Vector L = new Vector();
		
		String s;
		Boolean flag=false;
	
		while((s=br.readLine())!=null)
		{
			flag=false;
			String r=s.substring(s.indexOf("=")+1);		// Evaluate Right-Hand Side of Expression
			
			for(int i=0;i < L.size();i++)
			{
				if((L.elementAt(i)).equals(r))	
					flag=true; 		// If Expression already present in Vector do nothing 
					
			}
			
			if(!flag)
			{
				L.addElement(r);	// If Expression not present in Vector, Add it inside Vector and Print it to output file
				pw.println(s);
			}
		}
		
	}
}
