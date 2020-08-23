%{
#include<stdio.h>
void yyerror(char*);
int yylex();
int yywrap();
%}
%union
{
char dval; /*to define possible symbol types,is to allow
 storing different kind of objects into node emitted by lex*/
}
%token <dval> NUM
%type <dval> E
%left '+' '-'
%left '*' '/'
%%
statement : E {printf("\n t= %c\n This is Three Address Code ...\n",$1);}
E : E '+' E {$$=intgencode($$,$1,'+',$3);}
 | E '-' E {$$=intgencode($$,$1,'-',$3);}
 | E '*' E {$$=intgencode($$,$1,'*',$3);}
 | E '/' E {$$=intgencode($$,$1,'/',$3);}
 | '(' E ')' {$$=$2;}
 | NUM {$$=$1;}
 ;
%%
main()
{
 yyparse();
 return 0;
}
int yywrap(){
return 1;
}
