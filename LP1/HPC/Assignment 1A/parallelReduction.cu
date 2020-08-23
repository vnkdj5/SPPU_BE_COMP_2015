#include<iostream>
#include<cstdio>
#include<cstdlib>
#include<cuda_runtime.h>
using namespace std;


__global__ void minimum(int *input)
{
	int tid=threadIdx.x;
	auto step_size=1;
  int number_of_threads=blockDim.x;
  
  while(number_of_threads>0)
  {
      if(tid<number_of_threads)
      {
          int first=tid*step_size*2;
          int second=first+step_size;
          if(input[second]<input[first])
            input[first]=input[second];
      }
      step_size=step_size*2;
      number_of_threads/=2;
  }

}

__global__ void max(int *input)
{
   int tid=threadIdx.x;
   auto step_size=1;
   int number_of_threads=blockDim.x;
   
   while(number_of_threads>0)
   {
       if(tid<number_of_threads)
       {
           int first=tid*step_size*2;
           int second=first+step_size;
           if(input[second]>input[first])
            input[first]=input[second];
       }
       step_size*=2;
       number_of_threads/=2;
   }
}

__global__ void sum(int *input)
{
    const int tid=threadIdx.x;
    auto  step_size=1;
    int number_of_threads=blockDim.x;
    while(number_of_threads>0)
    {
        if(tid<number_of_threads)
        {
            const int first=tid*step_size*2;
            const int second=first+step_size;
            input[first]=input[first]+input[second];
        }
    step_size = step_size*2;; 
		number_of_threads =number_of_threads/2;
    }
}

__global__ void average(int *input) //You can use above sum() to calculate sum and divide it by num_of_elememts
{
    const int tid=threadIdx.x;
    auto  step_size=1;
    int number_of_threads=blockDim.x;
    int totalElements=number_of_threads*2;
    while(number_of_threads>0)
    {
        if(tid<number_of_threads)
        {
            const int first=tid*step_size*2;
            const int second=first+step_size;
            input[first]=input[first]+input[second];
        }
        step_size = step_size*2;; 
		number_of_threads =number_of_threads/2;
    }
    input[0]=input[0]/totalElements;
}

int main()
{

	cout<<"Enter the no of elements"<<endl;
	int n;
	n=10;
  srand(n);
	int *arr=new int[n];
  int min=20000;
   //# Generate Input array using rand()
	for(int i=0;i<n;i++)
	{
		arr[i]=rand()%20000;
      if(arr[i]<min)
        min=arr[i];
    cout<<arr[i]<<" ";
	}

	int size=n*sizeof(int); //calculate no. of bytes for array
	int *arr_d,result1;
	
  //# Allocate memory for min Operation
	cudaMalloc(&arr_d,size);
	cudaMemcpy(arr_d,arr,size,cudaMemcpyHostToDevice);

  minimum<<<1,n/2>>>(arr_d);

	cudaMemcpy(&result1,arr_d,sizeof(int),cudaMemcpyDeviceToHost);

	cout<<"The minimum element is \n "<<result1<<endl;
  
  cout<<"The min element (using CPU) is"<<min;
   
    
  //#MAX OPERATION 
  int *arr_max,maxValue;
  cudaMalloc(&arr_max,size);
	cudaMemcpy(arr_max,arr,size,cudaMemcpyHostToDevice);

  max<<<1,n/2>>>(arr_max);

	cudaMemcpy(&maxValue,arr_max,sizeof(int),cudaMemcpyDeviceToHost);

	cout<<"The maximum element is \n "<<maxValue<<endl;
    
  //#SUM OPERATION 
  int *arr_sum,sumValue;
  cudaMalloc(&arr_sum,size);
	cudaMemcpy(arr_sum,arr,size,cudaMemcpyHostToDevice);

  sum<<<1,n/2>>>(arr_sum);

	cudaMemcpy(&sumValue,arr_sum,sizeof(int),cudaMemcpyDeviceToHost);

	cout<<"The sum of elements is \n "<<sumValue<<endl; 
   
  cout<<"The average of elements is \n "<<(sumValue/n)<<endl; 
  
  //# OR-----------
   
  //#AVG OPERATION 
  int *arr_avg,avgValue;
  cudaMalloc(&arr_avg,size);
	cudaMemcpy(arr_avg,arr,size,cudaMemcpyHostToDevice);

  average<<<1,n/2>>>(arr_avg);

	cudaMemcpy(&avgValue,arr_avg,sizeof(int),cudaMemcpyDeviceToHost);

	cout<<"The average of elements is \n "<<avgValue<<endl; 
  
   
  //# Free all allcated device memeory
   cudaFree(arr_d);
   cudaFree(arr_sum);
   cudaFree(arr_max);
   cudaFree(arr_avg);
    
    
 

return 0;

}
