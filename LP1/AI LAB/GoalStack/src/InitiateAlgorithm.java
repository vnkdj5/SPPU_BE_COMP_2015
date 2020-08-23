
public class InitiateAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GoalStackAlgorithm goalStackAlgorithm = new GoalStackAlgorithm();
		goalStackAlgorithm.acceptGoalState();
		goalStackAlgorithm.acceptInitialState();
		goalStackAlgorithm.displayGoalState();
		goalStackAlgorithm.displayStates();
		goalStackAlgorithm.algorithm();
	}

}
/*

----------------->> GOAL STATE <<------------------

Enter number of Stacks : 3
Stack 0 : 
	 Size : 
3
Element 0 : 
1
Element 1 : 
2
Element 2 : 
3
Stack 1 : 
	 Size : 
0
Stack 2 : 
	 Size : 
0

----------------->> INITIAL STATE <<------------------

Enter number of Stacks : 3
Stack 0 : 
	 Size : 
2
Element 0 : 
1
Element 1 : 
3
Stack 1 : 
	 Size : 
1
Element 0 : 
2
Stack 2 : 
	 Size : 
0

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=0], StackBean [state=3, heuristicValue=0]]
Stack 1 : [StackBean [state=2, heuristicValue=0]]
Stack 2 : []

----------------->> GOAL STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=0], StackBean [state=2, heuristicValue=0], StackBean [state=3, heuristicValue=0]]
Stack 1 : []
Stack 2 : []

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=3, heuristicValue=-1]]
Stack 1 : [StackBean [state=2, heuristicValue=-1]]
Stack 2 : []

============================== PART 1 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : [StackBean [state=2, heuristicValue=-1]]
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=3, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=3, heuristicValue=-1], StackBean [state=2, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=3, heuristicValue=-1], StackBean [state=2, heuristicValue=-1]]

============================== PART 2 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=3, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[]
*/

/*

----------------->> GOAL STATE <<------------------

Enter number of Stacks : 3
Stack 0 : 
	 Size : 
6
Element 0 : 
1
Element 1 : 
2
Element 2 : 
3
Element 3 : 
4
Element 4 : 
5
Element 5 : 
6
Stack 1 : 
	 Size : 
0
Stack 2 : 
	 Size : 
0

----------------->> INITIAL STATE <<------------------

Enter number of Stacks : 3
Stack 0 : 
	 Size : 
2
Element 0 : 
1
Element 1 : 
4
Stack 1 : 
	 Size : 
2
Element 0 : 
2
Element 1 : 
5
Stack 2 : 
	 Size : 
2
Element 0 : 
3
Element 1 : 
6

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=0], StackBean [state=4, heuristicValue=0]]
Stack 1 : [StackBean [state=2, heuristicValue=0], StackBean [state=5, heuristicValue=0]]
Stack 2 : [StackBean [state=3, heuristicValue=0], StackBean [state=6, heuristicValue=0]]

----------------->> GOAL STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=0], StackBean [state=2, heuristicValue=0], StackBean [state=3, heuristicValue=0], StackBean [state=4, heuristicValue=0], StackBean [state=5, heuristicValue=0], StackBean [state=6, heuristicValue=0]]
Stack 1 : []
Stack 2 : []

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=4, heuristicValue=-1]]
Stack 1 : [StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]
Stack 2 : [StackBean [state=3, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]
-------------------------------------------------------->> Iteration 0

============================== PART 1 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : [StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]
Stack 2 : [StackBean [state=3, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=3, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

============================== PART 2 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=3, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=2, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=2, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=4, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]
-------------------------------------------------------->> Iteration 1

============================== PART 1 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=4, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=4, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=4, heuristicValue=-1]]

============================== PART 2 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1], StackBean [state=3, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]
-------------------------------------------------------->> Iteration 2

============================== PART 1 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1], StackBean [state=6, heuristicValue=-1]]

============================== PART 2 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[StackBean [state=4, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]
-------------------------------------------------------->> Iteration 3

============================== PART 1 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1]]
Stack 1 : []
Stack 2 : [StackBean [state=6, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

----------------->> TEMP STACK <<------------------
[]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1], StackBean [state=5, heuristicValue=-1]]

============================== PART 2 ==================================

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1], StackBean [state=5, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[StackBean [state=6, heuristicValue=-1]]

----------------->>CURRENT STATE <<------------------
Stack 0 : [StackBean [state=1, heuristicValue=1], StackBean [state=2, heuristicValue=1], StackBean [state=3, heuristicValue=1], StackBean [state=4, heuristicValue=1], StackBean [state=5, heuristicValue=1], StackBean [state=6, heuristicValue=1]]
Stack 1 : []
Stack 2 : []

----------------->> TEMP STACK <<------------------
[]
*/