import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;

import org.omg.CORBA._IDLTypeStub;

//.contains line 124

public class GoalStackAlgorithm {
//StackBean stack[] = new StackBean();
	int noOfStacks;
    Stacks stacks[]; 
    Stacks goalStacks[]; 
    Stacks tempStack = new Stacks();
    Scanner scan = new Scanner(System.in);
	int element;

	public void acceptInitialState(){
		System.out.println("\n----------------->> INITIAL STATE <<------------------");
		System.out.print("\nEnter number of Stacks : ");
		noOfStacks = scan.nextInt();
		stacks = new Stacks[noOfStacks];
		for(int i=0;i<noOfStacks;i++){
			System.out.println("Stack "+i+" : \n\t Size : ");
			stacks[i] = new Stacks();
			stacks[i].stackSize = scan.nextInt();
			for(int j = 0;j<stacks[i].stackSize;j++){
				System.out.println("Element "+j+" : ");
				element = scan.nextInt();
				stacks[i].stack.add(new StackBean(element));
				stacks[i].stack.get(j).stackNumber = j;
			}
		}
		displayStates();
		StackBean e;
		for(int i=0;i<noOfStacks;i++){
			Iterator<StackBean> itr = stacks[i].stack.listIterator();
			while(itr.hasNext()){
				e=itr.next();
				calculateHeuristic(e,i,stacks[i].stack.indexOf(e));		//element and stack number and position of element in stack
			}
		}
	}
	public void acceptGoalState(){
		System.out.println("\n----------------->> GOAL STATE <<------------------");
		System.out.print("\nEnter number of Stacks : ");
		noOfStacks = scan.nextInt();
		goalStacks = new Stacks[noOfStacks];
		for(int i=0;i<noOfStacks;i++){
			System.out.println("Stack "+i+" : \n\t Size : ");
			goalStacks[i] = new Stacks();
			goalStacks[i].stackSize = scan.nextInt();
			for(int j = 0;j<goalStacks[i].stackSize;j++){
				System.out.println("Element "+j+" : ");
				element = scan.nextInt();
				goalStacks[i].stack.add(new StackBean(element));
				goalStacks[i].stack.get(j).stackNumber = j;
			}
		}
	}
	
	public void displayStates(){
		System.out.println("\n----------------->>CURRENT STATE <<------------------");
		for(int i=0;i<noOfStacks;i++){
			System.out.println("Stack "+i+" : "+stacks[i].stack.toString());
		}
		
	}
	public void displayGoalState(){
		System.out.println("\n----------------->> GOAL STATE <<------------------");
		for(int i=0;i<noOfStacks;i++){
			System.out.println("Stack "+i+" : "+goalStacks[i].stack.toString());
		}
		
	}
	public void algorithm(){
		StackBean e;
		int iteration = 0;
		while(!goalStateReached()) {
			System.out.println("-------------------------------------------------------->> Iteration "+iteration);
			System.out.println("\n============================== PART 1 ==================================");
			//Part 1: Add wrong states to the temp stack
			for(int i=0;i<noOfStacks;i++){
				Iterator<StackBean> itr = stacks[i].stack.listIterator();
				while(itr.hasNext()){
					e = itr.next();
					if(e.heuristicValue ==-1){
						tempStack.stack.add(e);
						itr.remove();   // We can't modify the stack if an iterator is traversing it					
					}
				}
				
				displayStates();
				displayTempStack();
			}
			System.out.println("\n============================== PART 2 ==================================");
	
			//Part 2 : pop each state from temp stack and add to the right place
			boolean flag =false;//to mark if element at right position
			while(!tempStack.stack.isEmpty()){
				e = tempStack.stack.pop();
				e.heuristicValue = 1;
				for(int i=0;i<noOfStacks;i++){
					stacks[i].stack.push(e);
					//check if the element is added at correct place
					if((flag=calculateHeuristic(e,i,stacks[i].stack.indexOf(e)))==true){ //means at right position
						break; 
					}else { //means at wrong position
						stacks[i].stack.pop();//try other stack; pop from this stack
						flag=false;
					}
				}
				if(flag==false){//means no correct position found in any of the stacks
					e.heuristicValue = -1;
					stacks[noOfStacks-1].stack.push(e); //Thus push on last stack
				}
				displayStates();
				displayTempStack();
			}
			iteration++;
		}
		
	}
	public void displayTempStack(){
		System.out.println("\n----------------->> TEMP STACK <<------------------");
		System.out.println(""+tempStack.toString());
		
	}
	public boolean calculateHeuristic(StackBean e,int stackNumber,int pos){
		//match the stack number and position of element with the goal position
		//position : stack number
		int goalPos;	
		//Checking if same position in goal state
		if((goalPos = test(stackNumber,e))==pos) {//goalStacks[stackNumber].stack.contains(e)){
			stacks[stackNumber].stack.get(pos).heuristicValue = 1;
			return true;
		}
		stacks[stackNumber].stack.get(pos).heuristicValue = -1;
	return false;
			
		
	}
	public int test(int stackNumber,StackBean e) {
		Iterator<StackBean> itr = goalStacks[stackNumber].stack.listIterator();
		StackBean s ;
		
		while(itr.hasNext()) {
			s = itr.next();
			if(s.state == e.state) {
				return goalStacks[stackNumber].stack.indexOf(s);			
			}
		}
		return -1;
	}
	public boolean goalStateReached() {
		for(int i=0;i<noOfStacks;i++) {
			Iterator<StackBean> itr = stacks[i].stack.listIterator();
			while(itr.hasNext()) {
				if(itr.next().heuristicValue !=1)
						return false;
			}
		}
		return true;
	}
}



//System.out.println("Element : "+e);
		//System.out.println("\tstackBean : "+stacks[stackNumber].stack.get(pos)+"\tnum : "+stackNumber);
		//System.out.println("\tgoal stack : "+goalStacks[stackNumber].stack.get(pos));
		//System.out.println("T/F : "+goalStacks[stackNumber].stack.contains(e));//How to compare if two objects are equal
		





