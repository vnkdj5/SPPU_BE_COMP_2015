/**
 * Copyright 1993-2012 NVIDIA Corporation.  All rights reserved.
 *
 * Please refer to the NVIDIA end user license agreement (EULA) associated
 * with this source code for terms and conditions that govern your use of
 * this software. Any use, reproduction, disclosure, or distribution of
 * this software and related documentation outside the terms of the EULA
 * is strictly prohibited.
 */
#include <stdio.h>
#include<cuda.h>
#include <stdlib.h>
#include<time.h>

#define SIZE 6

__global__ void max(int *a, int *c)
{
	extern __shared__ int sdata[];
	unsigned int tid=threadIdx.x;
	unsigned int i=blockIdx.x*blockDim.x+threadIdx.x;

	sdata[tid]=a[i];

	__syncthreads();

	for(unsigned int s=blockDim.x/2; s>=1; s=s/2)
	{
		if(tid<s)
		{
			if(sdata[tid]<sdata[tid+s])
			{
				sdata[tid]=sdata[tid+s];
			}
		}
		__syncthreads();
	}
	if(tid==0)
	{
		*c=sdata[0];
	}
}

__global__ void min(int *a, int *c)
{
	extern __shared__ int sdata[];
	unsigned int tid=threadIdx.x;
	unsigned int i=blockIdx.x*blockDim.x+threadIdx.x;

	sdata[tid]=a[i];

	__syncthreads();

	for(unsigned int s=blockDim.x/2; s>=1; s=s/2)
	{
		if(tid<s)
		{
			if(sdata[tid]>sdata[tid+s])
			{
				sdata[tid]=sdata[tid+s];
			}
		}
		__syncthreads();
	}
	if(tid==0)
	{
		*c=sdata[0];
	}
}

__global__ void calcavg(int *a, float *avg)
{
	*avg=0;
	for(int i=0;i<SIZE;i++)
	{
		*avg=*avg+a[i];
	}
	*avg=*avg/SIZE;
}


int main(void)
{
	int i;
	srand(time(NULL));
	int a[SIZE];
	int c,d;
	float avg;

	int *dev_a,*dev_c,*dev_d;
	float *average;

	cudaMalloc((void **)&dev_a, SIZE*sizeof(int));
	cudaMalloc(&dev_c, sizeof(int));
	cudaMalloc(&dev_d, sizeof(int));
	cudaMalloc(&average, sizeof(float));

	for(i=0;i<SIZE;i++)
	{
		a[i] = rand()%20+1;
	}

	printf("\nThe array is:\n");
	for(i=0;i<SIZE;i++)
	{
		printf("%d  ",a[i]);
	}


	cudaMemcpy(dev_a,a,sizeof(a),cudaMemcpyHostToDevice);
	max<<<1,SIZE>>>(dev_a,dev_c);
	min<<<1,SIZE>>>(dev_a,dev_d);
	calcavg<<<1,1>>>(dev_a,average);
	cudaMemcpy(&c,dev_c,sizeof(int),cudaMemcpyDeviceToHost);
	cudaMemcpy(&d,dev_d,sizeof(int),cudaMemcpyDeviceToHost);
	cudaMemcpy(&avg,average,sizeof(float),cudaMemcpyDeviceToHost);

	printf("\nMax is: %d",c);
	printf("\nMin is: %d",d);
	printf("\nAverage is: %f",avg);
	cudaFree(dev_a);
	cudaFree(dev_c);
	cudaFree(dev_d);
	cudaFree(average);
	return 0;
}
