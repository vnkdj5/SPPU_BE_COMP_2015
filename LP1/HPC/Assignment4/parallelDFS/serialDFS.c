#include<stdio.h>
#include<stdlib.h>
#include<time.h>

//utility function for printing BST inorderly(ascending order)
void printInorder(int* arr,int start,int end)
{
	if(start > end) 
    return; 
  
  // print left subtree 
  printInorder(arr, start*2 + 1, end); 
  
  // print root 
  printf("%d\t", arr[start]); 
  
  // print right subtree 
  printInorder(arr, start*2 + 2, end); 
}


/*
In order to prove that parallel programming is good over serial one we require large graph(for smaller graphs difference is not appreciable
so having large graph is our first need).

The idea is graph will have root node and 4 children. Each child will be a BST with say 1024 nodes. Nodes may increase as per need.
now for each child BST say for 1st child , node data will be 1-1024 
for 2nd child 1025-2048
for 3rd child = 2049-3072 
and for 4th child 3073-4096

constructing BST is 1st challenege.
buildTree function constructs a BST from values contained in array(must be sorted and withour duplicates) and returns root of BST to caller.
we have called these function 4 times i.e. for every child BST with appropriate values in array 'arr'.
*/
void buildTree(int* arr,int start,int end,int*,int,int,int);

int main(int argc, char const *argv[])
{
	int N = 1024;
	int arr[N];
	int tree[N];
	int i;
	int turns = 1;

	int children =4;

	int store[children][N];
	
	clock_t  cpu_start = clock();
	for(;turns<=children;turns++)
	{
	int offset = (turns-1)*N;
	
	buildTree(arr,0,N,tree,0,offset,N);
	//tree[N-1]=N;
	 

	 /*for(i=0;i<N;i++)
	 	store[turns-1][i]=tree[i];*/

	
	printInorder(tree,0,N-1);
	
	}
	clock_t cpu_finish = clock();
	

	
	printf("\ntime elapsed in traversing is = %d millisecinds\n",(cpu_finish - cpu_start) );

	return 0;
}



void buildTree(int* arr,int start,int end,int* tree,int k,int offset,int N)
{
	
	if(start<=end && k<=N-1){
	
	int mid = (end+start+1)/2;
	//printf(" k = %d and element = %d\n",k,arr[mid] );
	tree[k] = mid+offset;
	


	buildTree(arr,start,mid-1,tree, k*2+1,offset,N);
	buildTree(arr,mid+1,end,tree,k*2+2,offset,N);
}





	

}