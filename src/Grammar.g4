grammar Grammar;
start:    (expr NEWLINE)* ;
expr :    exp_arith
     |    exp_assgn
     |    exp_input
     |    exp_output
     ;
exp_input:    VAR SPACE* '=' SPACE* 'int(input())' ;
exp_output:   'print(' SPACE* VAR SPACE* ')' ;
exp_assgn:    VAR SPACE* '=' SPACE* exp_arith ;
exp_arith:    exp_arith SPACE* ('*'|'/') SPACE* exp_arith
         |    exp_arith SPACE* ('+'|'-') SPACE* exp_arith
         |    INT
         |    VAR
         |    '(' SPACE* exp_arith SPACE* ')'
         ;

NEWLINE : [\r\n]+ ;
SPACE   : [ \t] ;

VAR     : [a-zA-Z][a-zA-Z0-9_]* ;
INT     : [0-9]+ ;
