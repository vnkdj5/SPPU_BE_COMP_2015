
public class InitiateAlgorithm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateGraph createGraph = new CreateGraph();
		int i=0;
		createGraph.acceptData();
		createGraph.algorithm();
	}
}
/*
--->>input
Start State : 
0	1	3
4	2	5
7	8	6
Goal State : 
1	2	3
4	5	6
7	8	0
*/
/*
--->>Output
Start State : 
0	1	3
4	2	5
7	8	6
Goal State : 
1	2	3
4	5	6
7	8	0

Start State : 
	0	1	3
	4	2	5
	7	8	6

Goal State : 
	1	2	3
	4	5	6
	7	8	0

*********** iteration 1 ***********

Puzzle : 
	1	0	3
	4	2	5
	7	8	6

f(n) = 4	,h(n) = 3	,g(n) = 1
*********** iteration 2 ***********

Puzzle : 
	1	2	3
	4	0	5
	7	8	6

f(n) = 4	,h(n) = 2	,g(n) = 2
*********** iteration 3 ***********

Puzzle : 
	1	2	3
	4	5	0
	7	8	6

f(n) = 4	,h(n) = 1	,g(n) = 3
*********** iteration 4 ***********

Puzzle : 
	1	2	3
	4	5	6
	7	8	0

f(n) = 4	,h(n) = 0	,g(n) = 4
*/
/*--------->> WITH ITERATION
 * Start State : 
0	1	3
4	2	5
7	8	6
Goal State : 
1	2	3
4	5	6
7	8	0

Start State : 
	0	1	3
	4	2	5
	7	8	6

Goal State : 
	1	2	3
	4	5	6
	7	8	0

*********** iteration 1 ***********

Puzzle : 
	0	1	3
	4	2	5
	7	8	6

f(n) = 5	,h(n) = 4	,g(n) = 1
Puzzle : 
	1	0	3
	4	2	5
	7	8	6

f(n) = 4	,h(n) = 3	,g(n) = 1
Puzzle : 
	0	1	3
	4	2	5
	7	8	6

f(n) = 5	,h(n) = 4	,g(n) = 1
Puzzle : 
	4	1	3
	0	2	5
	7	8	6

f(n) = 6	,h(n) = 5	,g(n) = 1

------------->>SELECTED PUZZLE : 

Puzzle : 
	1	0	3
	4	2	5
	7	8	6

f(n) = 4	,h(n) = 3	,g(n) = 1
*********** iteration 2 ***********

Puzzle : 
	0	1	3
	4	2	5
	7	8	6

f(n) = 6	,h(n) = 4	,g(n) = 2
Puzzle : 
	1	3	0
	4	2	5
	7	8	6

f(n) = 6	,h(n) = 4	,g(n) = 2
Puzzle : 
	1	0	3
	4	2	5
	7	8	6

f(n) = 5	,h(n) = 3	,g(n) = 2
Puzzle : 
	1	2	3
	4	0	5
	7	8	6

f(n) = 4	,h(n) = 2	,g(n) = 2

------------->>SELECTED PUZZLE : 

Puzzle : 
	1	2	3
	4	0	5
	7	8	6

f(n) = 4	,h(n) = 2	,g(n) = 2
*********** iteration 3 ***********

Puzzle : 
	1	2	3
	0	4	5
	7	8	6

f(n) = 6	,h(n) = 3	,g(n) = 3
Puzzle : 
	1	2	3
	4	5	0
	7	8	6

f(n) = 4	,h(n) = 1	,g(n) = 3
Puzzle : 
	1	0	3
	4	2	5
	7	8	6

f(n) = 6	,h(n) = 3	,g(n) = 3
Puzzle : 
	1	2	3
	4	8	5
	7	0	6

f(n) = 6	,h(n) = 3	,g(n) = 3

------------->>SELECTED PUZZLE : 

Puzzle : 
	1	2	3
	4	5	0
	7	8	6

f(n) = 4	,h(n) = 1	,g(n) = 3
*********** iteration 4 ***********

Puzzle : 
	1	2	3
	4	0	5
	7	8	6

f(n) = 6	,h(n) = 2	,g(n) = 4
Puzzle : 
	1	2	3
	4	5	0
	7	8	6

f(n) = 5	,h(n) = 1	,g(n) = 4
Puzzle : 
	1	2	0
	4	5	3
	7	8	6

f(n) = 6	,h(n) = 2	,g(n) = 4
Puzzle : 
	1	2	3
	4	5	6
	7	8	0

f(n) = 4	,h(n) = 0	,g(n) = 4

------------->>SELECTED PUZZLE : 

Puzzle : 
	1	2	3
	4	5	6
	7	8	0

f(n) = 4	,h(n) = 0	,g(n) = 4
*/