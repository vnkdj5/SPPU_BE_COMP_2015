#include <iostream>
#include <chrono>
#define TOGG(k) ( ( ( (k) & 1 ) << 5 ) )
#define RAN(charac) ( 65 + ( charac % 26 ) + TOGG ( charac ) )

using namespace std;
using namespace std::chrono;

string RunLengthEncoding(char *s, int n) {
    string ans;
    int val = 0;
    for(int i = 1; i <= n; ++i) {
        while (i < n && s[i] == s[i-1])
            ++i;
        ans += s[val] + to_string(i-val);
        val = i;
    }
    return ans;
}

int main () {
    int n;
    cin >> n;

    char *s = new char[n];
    int i = 0;

    for( ; i < n; ) {
        char in = RAN ( rand() );
        int loop = in & 63, k = i;
        while(i < min(k+loop, n))
            s[i++] = in;
    }
    s[i] = '\0';
    time_point <system_clock> start, end;

    start = system_clock::now();
    string ans = RunLengthEncoding(s, n);
    end = system_clock::now();

    duration<double> elapsed_seconds = end - start;

    cout << "Result: " << ans << endl;

    cout << "Elapsed Time: " << elapsed_seconds.count()*1000 << "ms." << endl;
    cin >> n;
}