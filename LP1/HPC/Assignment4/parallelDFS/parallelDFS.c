#include<stdio.h>
#include<stdlib.h>
#include<time.h>
#include<mpi.h>
#define send_data_tag 2001
#define return_data_tag 2002
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

	int slaveArray[N];
	int i;
	int turns = 1;

	int children =4;


	int ierr;
	
    MPI_Status status;

    int my_id, root_process, num_procs,
         an_id, num_elements_to_receive, 
         sender, num_elements_receive , num_rows_to_send;

    
	double start,finish;

	ierr = MPI_Init(&argc, &argv); 
	start = MPI_Wtime();
      
    root_process = 0;

    ierr = MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
    ierr = MPI_Comm_size(MPI_COMM_WORLD, &num_procs);

    if(my_id == root_process)
    {
    	turns = 1;
    	/*
    	for loop is basically constructing every child branch and immediately giving it to slave.
    	One may find it weird.
    	If you wish you can maintain separate 2D array to store every child array and then apply another for loop to send each 
    	child to slave.
    	It basically involves extra memory space and unnecessary time.

    	*/
    	for(;turns<=children;turns++)
	    {
			int offset = (turns-1)*N;
	
			buildTree(arr,0,N,tree,0,offset,N);

			ierr = MPI_Send(&N,1,MPI_INT,turns-1,send_data_tag,MPI_COMM_WORLD);

			ierr = MPI_Send(&tree,N,MPI_INT,turns-1,send_data_tag,MPI_COMM_WORLD);
    	}


    	/*Now wait for slaves to finish*/


	}
	else
	{
		ierr = MPI_Recv(&num_elements_to_receive,1,MPI_INT,root_process,send_data_tag,MPI_COMM_WORLD,&status);

		int received = num_elements_to_receive;

        ierr = MPI_Recv(&slaveArray,num_elements_to_receive,MPI_INT,root_process,send_data_tag,MPI_COMM_WORLD,&status);

        printInorder(slaveArray,0,received-1);
	}
	finish = MPI_Wtime();
	printf("Execution time = %f seconds\n",(finish-start));
	MPI_Finalize();

}


void buildTree(int* arr,int start,int end,int* tree,int k,int offset,int N)
{
	
	if(start<=end && k<=N-1)
	{
	
	int mid = (end+start+1)/2;
	//printf(" k = %d and element = %d\n",k,arr[mid] );
	tree[k] = mid+offset;
	


	buildTree(arr,start,mid-1,tree, k*2+1,offset,N);
	buildTree(arr,mid+1,end,tree,k*2+2,offset,N);
   }

}