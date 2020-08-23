%{

FILE* yyin;
%}
DIGIT [0-9]
NUMBER {DIGIT}+
REAL {DIGIT}*[.]{NUMBER}
TEXT [A-Za-z]
KEYWORDS "auto"|"static"
DATATYPE "int"|"float"|"double"|"long"|"void"|"char"
CONDITIONAL "if"|"else"|"else if"|"switch"|"case"
ITERATIVE "for"|"while"|"do"
PREPROCESSOR "#include"[^\n]*";"
SC ";"
IDENTIFIER {TEXT}({DIGIT}|{TEXT}|"_")*
NONIDENTIFIER {DIGIT}({TEXT}|{DIGIT}|"_")*
ARITH_OP "+"|"-"|"/"|"%"|"*";
LOGICAL_OP "&&"|"||"|"!"|"!="
REL_OP "<"|">"|"<="|">="|"=="
UNARY "++"|"--"


%%
[ \n\t]+ ;
"#include<stdio.h>" {printf("%s\t==> PREPROCESSOR\n",yytext);}
{CONDITIONAL} { printf("%s\t==> CONDITIONAL\n",yytext);}
{ITERATIVE} {printf("%s\t==> ITERATIVE CONSTRUCT\n",yytext);}

{DATATYPE}  {printf("%s\t==> DATATYPE\n",yytext);}
{KEYWORDS} {printf("%s\t==> KEYWORDS\n",yytext);}
{IDENTIFIER} {printf("%s\t==> IDENTIFIER\n",yytext);}
{REAL} {printf("%s\t==> REAL CONSTANT\n",yytext);}
{NUMBER} {printf("%s\t==> CONSTAINT INTEGER\n",yytext);}
{NONIDENTIFIER} {printf("%s\t==> NONIDENTIFIER\n",yytext);}
{SC} {printf("%s\t==> DELIMITER\n",yytext);}
{UNARY} {printf("%s\t==> UNARY OP\n",yytext);}
{ARITH_OP} {printf("%s\t==> ARITHMETIC OPERATOR\n",yytext);}
{LOGICAL_OP} {printf("%s\t==> LOGICAL OP\n",yytext);}
{REL_OP} {printf("%s\t==> RELATIONAL OP\n",yytext);}
"=" {printf("%s\t==> ASSIGNMENT OP\n",yytext);}
"{"  {printf("%s\t==> BLOCK BEGIN\n",yytext);}
"}"  {printf("%s\t==> BLOCK END\n",yytext);}
"("  {printf("%s\t==> PARANTHESIS BEGIN\n",yytext);}
")"  {printf("%s\t==> PARENTHESIS END\n",yytext);}
. ;


%%
int yywrap()
{
	return 1;
}

int main(int argc,char* argv[])
{
	yyin=fopen(argv[1],"r");
	yylex();
	fclose(yyin);
}

