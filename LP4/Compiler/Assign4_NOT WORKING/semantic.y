%{
#include <stdio.h>
#include <stdlib.h>
void yyerror(char*);
int yylex();
int yywrap();
%}
%token ID
%left '+' '-'
%left '*' '/'
%left UMINUS
%%
S : E
E : E'+'{A1();}T{A2();}
| E'-'{A1();}T{A2();}
| T;
T : T'*'{A1();}F{A2();}
| T'/'{A1();}F{A2();}
| F;
F : '('E{A2();}')'
| '-'{A1();}F{A2();}
| ID{A3();};
%%

char st[100];
int top=0;
main()
{
printf("Enter infix expression: ");
yyparse();
printf("\n");
}
A1() //push
{
	st[top++]=yylval;
	printf(" yylval[0]= %c", yylval);
}
A2() //pop
{
	printf("%c",st[--top]);
}
A3()
{
	printf("%c",yylval);
}
yyerror(char *s)
{
  fprintf(stderr, "%s\n",s);
}
yywrap()
{
  return(1);
}

