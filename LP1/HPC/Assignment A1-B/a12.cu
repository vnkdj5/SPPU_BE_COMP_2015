#include<iostream>
#include<cstdio>

using namespace std;



__global__ void mini1(int *a,int *b,int n)
{


	int block=256*blockIdx.x;


	int mini=7888888;

	for(int i=block;i<min(256+block,n);i++)
	{


		if(mini>a[i])
		{

			mini=a[i];
	
		}





	}
	b[blockIdx.x]=mini;

}




int main()
{


	cout<<"Enter the size of array"<<endl;
	int n;
	cin>>n;
	int a[n];

	cudaEvent_t start,end;

	cudaEventCreate(&start);
	cudaEventCreate(&end);

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

	
	cudaEventRecord(start);

	

	while(n>1)

	{

		mini1<<<grids,block>>>(ad,bd,n);
		n=ceil(n*1.0f/256.0f);
		cudaMemcpy(ad,bd,n*sizeof(int),cudaMemcpyDeviceToDevice);


	}

	
	cudaEventRecord(end);
	cudaEventSynchronize(end);


	float time=0;
	
	cudaEventElapsedTime(&time,start,end);
	

	int ans[2];

	cudaMemcpy(ans,ad,4,cudaMemcpyDeviceToHost);
	
	cout<<"The minimum element is"<<ans[0]<<endl;
	
	cout<<"The time required dor it is";
	cout<<time<<endl;
	

} 
