#include<iostream>

using namespace std;


const int n=8;

bool checksafe(int board[n][n],int row,int col,bool checkrow[])
{

	for(int i=0;i<=col;i++)			//// checking left rows
	{
	
		if(board[row][i]==1)
			return false;

	}

	
	for(int i=row,j=col;i>=0 && j>=0;i--,j--)		//checking left upper diagonal
	{

		if(board[i][j]==1)
			return false;

	}

	for(int i=row,j=col;i<n && j>=0;i++,j--)  //checing bottom right diagonal
	{

		if(board[i][j]==1)
			return false;
		

	}

	if(checkrow[row]==1)
		return false;


	return true;


}

bool nqueensuntil(int board[n][n],int col,bool checkrow[])
{

	int flag=1;
	
	for(int i=0;i<n;i++)
	{
		if(checkrow[i]==false)
		{
			flag=0;
			break;
			
		}
	}


	if(flag==1)
	{

		return true;
	}



	for(int row=0;row<n;row++)
	{

		if(checksafe(board,row,col,checkrow))
		{

			board[row][col]=1;
			checkrow[row]=true;


			if(nqueensuntil(board,(col+1)%n,checkrow))
				return true;
			else
			{

				board[row][col]=0;
				checkrow[row]=false;
			}
		}
	}


	return false;

}









void solve()
{

	int board[n][n];
	

	for(int i=0;i<n;i++)
	{

		for(int j=0;j<n;j++)
		{

			board[i][j]=0;

		}


	}


	bool checkrow[n]={false};


	
	nqueensuntil(board,2,checkrow);

	
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
