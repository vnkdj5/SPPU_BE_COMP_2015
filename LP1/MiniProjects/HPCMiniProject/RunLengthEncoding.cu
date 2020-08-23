#include <iostream>
#include <cstdio>
#include <string>
#define TOGG(k) ( ( ( (k) & 1 ) << 5 ) )
#define RAN(charac) ( 65 + ( charac % 26 ) + TOGG ( charac ) ) 
using namespace std;

__global__ 
void RunLengthEncodingComputation (char *orig, int *_encoXst, int n) {
    int index = ( (blockIdx.x * blockDim.x) + threadIdx.x );
    index <<= 7;

    if(orig[index] == orig[index-1]) 
        while(index < n && orig[index] == orig[index-1])
            ++index;
    
    for (int i = index; i < fminf(index + 128, n); )
    {
        char temp = orig[i];
        int t_ = i;
        while (i < n && temp == orig[i])
            ++i;
        _encoXst[t_] = i;
    }
}

int main()  {
    int n;
    cin >> n;
    char *s = new char[n];
    int i = 0;

    for( ; i < n; ) {
        char in = RAN ( rand() );
        int loop = rand() & 63, k = i;
        while(i < min(k+loop, n))
            s[i++] = in;
    }
    s[i] = '\0';
    fprintf(fopen("input.txt", "w"), "%s", s);
    char *cudas;
    int *_encoXst, *_inter = new int[n];

    int threads = (1 << 7);
    int blocks = ( ( n>>14 ) + ( ( n & ( (1<<14)-1 ) ) != 0 )  );

    cout << threads << " : " << blocks << endl;

    cudaMalloc (&cudas, n*sizeof(char));
    cudaMalloc (&cudas, n*sizeof(char));
    cudaMalloc (&_encoXst, n*sizeof(int));

    cudaMemcpy (cudas, s, n*sizeof(char), cudaMemcpyHostToDevice);

    RunLengthEncodingComputation <<<blocks, threads>>> (cudas, _encoXst, n);
    cudaDeviceSynchronize();
    cudaMemcpy(_inter, _encoXst, n*sizeof(int), cudaMemcpyDeviceToHost);

    string ans;
    int sum = 0;
    for(int i = 0; i < n; i = _inter[i]) {
        ans += s[i] + to_string(_inter[i]-i);
        sum += _inter[i]-i;
    }
    // cout << ans << endl;
    fprintf(fopen("output.txt", "w"), "%s", ans.c_str());
    int length_ans = ans.length();
    printf("Length: %d\nCompressed Length: %d\nCompression Achievement: %f\n", sum, length_ans, (float)(sum+length_ans-1)/(length_ans));
}