//package chatbot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;


//// run in eclipse 

public class chatbot1
{

	
	Map<String,String>nouns=new HashMap<String, String>();
	Map<String,String>welcome=new HashMap<String, String>();
	Map<String,String>farewell=new HashMap<String, String>();
	
	
	boolean exit=false;
	
	ArrayList<String> keywords;
	
	public chatbot1()
	{
		
		
		nouns.put("money", "Where??");
		nouns.put("finance", "Many firms provide loan options");
		nouns.put("invest", "Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?");
		nouns.put("bank", "Yes,Offcourse.Basically there are many options to invest.Regional Or Investment Banks In which section would you like to invest?");
		nouns.put("shares", "Which company Shares?");
		nouns.put("loan", "Which loan?? Housing ,Personal,Educational.I recommend to visit SBI banks for this");
		nouns.put("investment","Well there are many such as UBS,Barclays,Deutsche bank,HSBC,Wells Fargo");
		nouns.put("regional", "There are many SBI,IDBI,KotaK Mahindra \n SBI offers 10% etc");
		
		
		welcome.put("hii","Welcome! How can I help you??");
		welcome.put("hey","hey,how I can help you??");
		welcome.put("hello","hello,How can I help you??");
		welcome.put("Thank U", "Welcome");
		
		farewell.put("bye","bye");
		farewell.put("bbye","bbye");
		
		
		
	}
	
	public static void main(String[] args)
	{
		
		Scanner s=new Scanner(System.in);
		chatbot1 c=new  chatbot1();
		
		while(true)
		{
			String input=s.nextLine();
			
			String output=c.giveans(input);
			
			System.out.println(output);
			
			if(c.exit)
			{
				break;
			}
			
			
			
		}
	}
	public String giveans(String input)
	{
		
		Random rand=new Random();
		
		keywords=new ArrayList<String>();
		
		String tokens[]=input.split("\\s");
		//System.out.println("hhhh");
		
		for(int i=0;i<tokens.length;i++)
		{
		
			if(welcome.containsKey(tokens[i].toLowerCase()))
			{
				return welcome.get(tokens[i]);
				
			}
			else if(farewell.containsKey(tokens[i].toLowerCase()))
			{
				return farewell.get(tokens[i]);
			}
			else if(nouns.containsKey(tokens[i].toLowerCase()))
			{
				return nouns.get(tokens[i]);
				
			}
			
		}
		
		return ("I am sorry. I don't get this.");		
		
		
	}

}
