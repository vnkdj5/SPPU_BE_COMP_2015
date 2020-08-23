#include<iostream>
#include<cstdio>

using namespace std;


__global__ void var(int *a,int *b,int n,float mean)
{


	int block=256*blockIdx.x;
	float sum=0;


	for(int i=block;i<min(block+256,n);i++)
	{


	sum=sum+(a[i]-mean)*(a[i]-mean);


	}
	b[blockIdx.x]=sum;

}




__global__ void sum(int *a,int *b,int n)
{


	int block=256*blockIdx.x;
	int sum=0;


	for(int i=block;i<min(block+256,n);i++)
	{


	sum=sum+a[i];


	}
	b[blockIdx.x]=sum;

}


int main()
{


cout<<"Enter the no of elements"<<endl;
int n;
cin>>n;


int a[n];

for(int i=0;i<n;i++)

{

a[i]=i+1;


}


int *ad,*bd;

int size=n*sizeof(int);


cudaMalloc(&ad,size);
cudaMemcpy(ad,a,size,cudaMemcpyHostToDevice);


int grids=ceil(n*1.0f/256.0f);

cudaMalloc(&bd,grids*sizeof(int));

dim3 grid(grids,1);
dim3 block(1,1);


int p=n;


cudaEvent_t start,end;

cudaEventCreate(&start);
cudaEventCreate(&end);

cudaEventRecord(start);



while(n>1)
{

	sum<<<grid,block>>>(ad,bd,n);
	n=ceil(n*1.0f/256.0f);
	cudaMemcpy(ad,bd,n*sizeof(int),cudaMemcpyDeviceToDevice);

}


cudaEventRecord(end);
cudaEventSynchronize(end);


float time=0;


cudaEventElapsedTime(&time,start,end);


cout<<"The time is"<<time<<endl;




int add[2];


n=p;


cudaMemcpy(add,ad,4,cudaMemcpyDeviceToHost);


cout<<"The sum is  "<<add[0]<<endl;

float mean=0.0f;

mean=add[0]/(n*1.0f);

cout<<"The mean is   "<<mean<<endl;



////////////////////sd part


cudaMalloc(&ad,size);
cudaMemcpy(ad,a,size,cudaMemcpyHostToDevice);

cudaMalloc(&bd,grids*sizeof(int));

var<<<grid,block>>>(ad,bd,n,mean);
n=ceil(n*1.0f/256.0f);	




sum<<<grid,block>>>(bd,ad,n);



cudaMemcpy(add,ad,4,cudaMemcpyDeviceToHost);

float sd=sqrt(add[0]/p*1.0f);

cout<<"The standard deviation is "<<sd<<endl;


}
