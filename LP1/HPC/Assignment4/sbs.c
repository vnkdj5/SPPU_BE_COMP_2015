#include<stdio.h>
#include<stdlib.h>
#include<time.h>
int arr[4096];
int cmpfunc(const void* a,const void* b){
	return ( *(int*)a - *(int*)b);
}

int binarySearch(int* arr,int element_to_search,int start,int end);

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
int elemtoS;	


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
clock_t cpu_start = clock();
int index = binarySearch(arr,elemtoS,0,modified_N);
clock_t cpu_finish = clock();

if(index!=-1)
printf("Element found at index = %d\n",index);

printf("program took %d milliseconds\n",(cpu_finish-cpu_start));

}


int binarySearch(int arr[], int x , int l, int r) 
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
