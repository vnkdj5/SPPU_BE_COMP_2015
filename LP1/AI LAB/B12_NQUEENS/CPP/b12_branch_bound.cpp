#include<iostream>

using namespace std;

const int n=8;




bool checksafe(int row,int col,int slashcode[n][n],int backslashcode[n][n],bool checkrow[],bool checkslashcode[],bool checkbackslashcode[])
{

	if(checkrow[row])
		return false;

	if(checkslashcode[slashcode[row][col]])
		return false;

	if(checkbackslashcode[backslashcode[row][col]])
		return false;

	return true;

}

bool nqueensuntil(int board[n][n],int col,int slashcode[n][n],int backslashcode[n][n],bool checkrow[],bool checkslashcode[],bool checkbackslashcode[])
{


	int flag=0;


	for(int i=0;i<n;i++)
	{

		if(checkrow[i]==false)
		{
			flag=1;
			break;
		}
	


	}

	if(flag==0)
	 return true;


	for(int row=0;row<n;row++)
	{

		
		if(checksafe(row,col,slashcode,backslashcode,checkrow,checkslashcode,checkbackslashcode))
		{


			checkrow[row]=true;
			board[row][col]=1;
			checkslashcode[slashcode[row][col]]=true;
			checkbackslashcode[backslashcode[row][col]]=true;


			if(nqueensuntil(board,(col+1)%n,slashcode,backslashcode,checkrow,checkslashcode,checkbackslashcode))
			{

				return true;

			}

			else
			{

				checkrow[row]=false;
				board[row][col]=0;
				checkslashcode[slashcode[row][col]]=false;
				checkbackslashcode[backslashcode[row][col]]=false;
			}
		
		}

	}

	return false;

}

void solve()
{

	int board[n][n];


	int slashcode[n][n];
	int backslashcode[n][n];

	for(int i=0;i<n;i++)
	{

		for(int j=0;j<n;j++)
		{

			slashcode[i][j]=i+j;
			backslashcode[i][j]=i-j+n-1;	
			board[i][j]=0;	
	
		
		}

	}

	bool checkrow[n]={false};
	bool checkslashcode[2*n-1]={false};
	bool checkbackslashcode[2*n-1]={false};


	
	nqueensuntil(board,3,slashcode,backslashcode,checkrow,checkslashcode,checkbackslashcode);  //// change 0 to 1,2,3,4.... for different solns


	for(int i=0;i<n;i++)
	{


		for(int j=0;j<n;j++)
		{

			cout<<board[i][j]<<" ";

		}

		cout<<endl;

	}

}


int main()
{

	solve();

}
