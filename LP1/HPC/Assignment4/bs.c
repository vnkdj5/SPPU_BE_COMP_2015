#include<stdio.h>
#include<stdlib.h>
#include<mpi.h>
#define send_data_tag 2001
#define return_data_tag 2002

int array2[4096];
int arr[4096];
int cmpfunc(const void* a,const void* b){
	return ( *(int*)a - *(int*)b);
}

int binarySearch(int* arr,int element_to_search,int start,int end,int id);

int removeDuplicates(int* arr, int n) 
{ 
    // Return, if array is empty 
    // or contains a single element 
    if (n==0 || n==1) 
        return n; 
  
    int temp[n]; 
  
    // Start traversing elements 
    int j = 0,i; 
    for ( i=0; i<n-1; i++) 
  
        // If current element is not equal 
        // to next element then store that 
        // current element 
        if (arr[i] != arr[i+1]) 
            temp[j++] = arr[i]; 
  
    // Store the last element as whether 
    // it is unique or repeated, it hasn't 
    // stored previously 
    temp[j++] = arr[n-1]; 
  
    // Modify original array 
    for ( i=0; i<j; i++) 
        arr[i] = temp[i]; 
  
    return j; 
} 

int main(int argc,char** argv)
{
	int ierr;
	
    MPI_Status status;

	int my_id, root_process, num_rows, num_procs,
         an_id, num_elements_to_receive, avg_elements_per_process, 
         sender, num_elements_received, start_row, end_row, num_rows_to_send,elemtoS;

    int isFound;
	double start,finish;

    ierr = MPI_Init(&argc, &argv); 
	start = MPI_Wtime();
      
    root_process = 0;

    ierr = MPI_Comm_rank(MPI_COMM_WORLD, &my_id);
    ierr = MPI_Comm_size(MPI_COMM_WORLD, &num_procs);

	//printf("my id = %d\n",my_id);
    if(my_id == root_process)
    {
		srand(0);
	int i;
	int N =4096;

	for(i=0;i<N;i++)
	{
		arr[i]=rand()%15000;
	}

	qsort(arr,N,sizeof(int),cmpfunc);

	int ammu = 1<<12;

	int modified_N = removeDuplicates(arr,ammu);
	printf("modified N = %d\n",modified_N);
	elemtoS = arr[856];


    avg_elements_per_process = modified_N / num_procs;
	printf("average elelemts per process = %d\n",avg_elements_per_process);

        /*distribute a portion of the array to each child process*/
		int id;
        for( id=1;id<num_procs;id++)
        {
            start_row = (id)*avg_elements_per_process;
			
            end_row = start_row+avg_elements_per_process-1;

			//printf("start = %d and end = %d at id = %d\n",start_row,end_row,id);
			//printf("start elelemnt = %d and end element  = %d\n",arr[start_row],arr[end_row]);
            if(end_row>modified_N)
                end_row = modified_N;
			

            ierr = MPI_Send(&avg_elements_per_process,1,MPI_INT,id,send_data_tag,MPI_COMM_WORLD);

            ierr = MPI_Send(&arr[start_row],avg_elements_per_process,MPI_INT,id,send_data_tag,MPI_COMM_WORLD);
			ierr = MPI_Send(&elemtoS,1,MPI_INT,id,send_data_tag,MPI_COMM_WORLD);

        }

        /*binary search for elements assignned to root process itself*/
        isFound = binarySearch(arr,elemtoS,0,avg_elements_per_process-1,0);

        if(isFound>=0)
            printf("Element found at index = %d\n",isFound );

        
        /* collect status from each slave process.
           slave process returns index of element if found in assigned segment
           to it and if not found returns -1.
        */
        for( id=1;id<num_procs;id++)
        {
            ierr = MPI_Recv( &isFound, 1, MPI_INT, MPI_ANY_SOURCE,
                  return_data_tag, MPI_COMM_WORLD, &status);
  			//printf("isFound = %\n",isFound);
            sender = status.MPI_SOURCE;


            if(isFound>=0){}
                



		}
		finish = MPI_Wtime();
		printf("Execution time = %f seconds\n",(finish-start));

    	
    }
    else
    {
        /*I must be a slave process so I will accept data from master and
        work accordingly*/

        ierr = MPI_Recv(&num_elements_to_receive,1,MPI_INT,root_process,send_data_tag,MPI_COMM_WORLD,&status);

        ierr = MPI_Recv(&array2,num_elements_to_receive,MPI_INT,root_process,send_data_tag,MPI_COMM_WORLD,&status);

		ierr = MPI_Recv(&elemtoS,1,MPI_INT,root_process,send_data_tag,MPI_COMM_WORLD,&status);

		isFound = -1;
		isFound = binarySearch(array2,elemtoS,0,num_elements_to_receive,my_id);
		
		if(isFound>=0)
		{
			printf("Element found at index from slave  = %d\n",my_id*718+isFound );
		}

	

        ierr = MPI_Send( &isFound, 1, MPI_INT, root_process, 
               return_data_tag, MPI_COMM_WORLD);

    }
 	
    ierr = MPI_Finalize();

	//printf("Execution time = %f seconds\n",(finish-start));



}

int binarySearch(int arr[], int x , int l, int r,int id) 
{ 
  while(r >= l) 
   { 
        int mid = (l + (r))/2; 
 
        if (arr[mid] == x){
			 
            return mid;
		} 
  
        if (arr[mid] < x)
				l=mid+1; 
             
  
        else
		r=mid-1;
   } 
  
 	return -1; 
} 
