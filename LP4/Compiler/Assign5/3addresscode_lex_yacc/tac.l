%{
 #include<stdio.h>
 #include"y.tab.h"
 char ch='a';

%}
%%
[0-9]+ {yylval.dval=yytext[0]; return NUM;} /*this would store value of integer
 in yyval;nd return token integer to yacc*/
\n {return 0;}
. {return yytext[0];}
%%
char intgencode(char ch1,char first,char op,char second)
{
 printf("\n%c = %c %c %c\n",ch,first,op,second);
 return ch++;
}
void yyerror(char* str)
{
 printf("\n%s",str);
}
