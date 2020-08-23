#include<math.h>
#include<time.h>
#include<stdexcept>
#include<iostream>
#include<cstdlib> //for abs(x)
#include<stdio.h>

using namespace std;

int NUMBER_OF_ELEMENTS  = 1<<12;
int SIZE  = NUMBER_OF_ELEMENTS*sizeof(int);
int VECTOR_SIZE = 1<<4;

__global__ void kernel_multiplication( int* A,  int* B, int* C,int N,int M);


int main()
{

   //allocate memory for host vectors

	int* hostA = (int*)malloc(VECTOR_SIZE*sizeof(int));
	int* hostB = (int*)malloc(SIZE*VECTOR_SIZE);
	int* hostC = (int*)malloc(VECTOR_SIZE*sizeof(int));

	int* deviceA,*deviceB,*deviceC;

	srand(time(0));
	int i,j;

	//initialize host vector by random elements
	for(i=0;i<VECTOR_SIZE;i++)
	{
		hostA[i] = rand();
		
	}

	//initialize matrix by random elements
	for(i=0;i<NUMBER_OF_ELEMENTS;i++)
	{
		for(j=0;j<VECTOR_SIZE;j++)
		{
			hostB[i*VECTOR_SIZE+j] = rand();
		}
	}




	//allocate memory for device vectors

	cudaMalloc(&deviceA,VECTOR_SIZE*sizeof(int));
	cudaMalloc(&deviceB,NUMBER_OF_ELEMENTS*VECTOR_SIZE*sizeof(int));
	cudaMalloc(&deviceC,VECTOR_SIZE*sizeof(int));

	//kernel function
	
	cudaMemcpy(deviceA,hostA,VECTOR_SIZE*sizeof(int),cudaMemcpyHostToDevice);
	
	cudaMemcpy(deviceB,hostB,SIZE*VECTOR_SIZE,cudaMemcpyHostToDevice);

	kernel_multiplication<<<NUMBER_OF_ELEMENTS,1>>>(deviceA,deviceB,deviceC,NUMBER_OF_ELEMENTS,VECTOR_SIZE);


	cudaDeviceSynchronize();

    cudaMemcpy(hostC,deviceC,VECTOR_SIZE*sizeof(int),cudaMemcpyDeviceToHost);


	cudaFree(deviceA);
	cudaFree(deviceB);
	cudaFree(deviceC);

	double error = 0;

    int* answer = (int*) malloc(VECTOR_SIZE*sizeof(int));
	for(int i=0;i<NUMBER_OF_ELEMENTS;i++)
	{
	int sum = 0;
	for(int j=0;j<VECTOR_SIZE;j++)
	{
		sum += hostA[j]*hostB[i*VECTOR_SIZE+j];
	}
	answer[i] = sum;
	}

	for(int k=0;k<VECTOR_SIZE;k++)
	{
	//cout<<k<<")"<< "Expected value = "<<answer[k]<<" Actual value = "<<hostC[k]<<"\n";
	error += double(abs(answer[k]-hostC[k]));
	}

	error=sqrt(error);
	cout<<"error = "<<error<<"\n";

	delete[] hostA;
    delete[] hostB;
    delete[] hostC;



    return cudaDeviceSynchronize();

}




__global__ void kernel_multiplication( int* A,  int* B, int* C, int N,int M)
{
	int index =  threadIdx.x + blockIdx.x * blockDim.x;
	int sum = 0;

	//printf("index = %d  and blockId is %d\n",index,blockIdx.x);


	if(index<N)
	{
		for(int i=0;i<M;i++)
		sum+=A[i]*B[(index*M)+i];
		C[index] = sum;

		

		//printf("index = %d and value is %d\n",index,C[index]);
	}
}