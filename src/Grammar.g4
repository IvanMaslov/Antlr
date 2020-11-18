grammar Grammar;
start:    (if_statement | funct | (expr NEWLINE))* ;
expr :    exp_notf
     |    exp_assgn
     |    exp_input
     |    exp_output
     ;
exp_input:    VAR SPACE* '=' SPACE* 'int(input())' ;
exp_output:   'print(' SPACE* VAR SPACE* ')' ;
exp_assgn:    VAR SPACE* '=' SPACE* exp_notf ;
exp_notf: exp_bool
        | exp_arith
        ;
exp_arith:    exp_arith SPACE* ('*'|'/') SPACE* exp_arith
         |    exp_arith SPACE* ('+'|'-') SPACE* exp_arith
         |    INT
         |    funct_call
         |    VAR
         |    '(' SPACE* exp_arith SPACE* ')'
         ;
exp_bool:    exp_bool_not SPACE* exp_bool
        |    exp_bool SPACE* exp_bool_and SPACE* exp_bool
        |    exp_bool SPACE* exp_bool_or SPACE* exp_bool
        |    exp_bool_var
        |    exp_bool_true
        |    exp_bool_false
        |    '(' SPACE* exp_bool_ SPACE* ')'
        ;
exp_bool_: exp_bool ;
exp_bool_var: VAR ;
exp_bool_true: BOOL_TRUE ;
BOOL_TRUE : 'True' ;
exp_bool_false: BOOL_FALSE ;
BOOL_FALSE: 'False';

exp_bool_or: BOOL_OR ;
BOOL_OR   : 'or'   ;
exp_bool_and: BOOL_AND ;
BOOL_AND  : 'and'   ;
exp_bool_not: BOOL_NOT ;
BOOL_NOT  : 'not'   ;

offset_statemnts:       (NEWLINE+ SPACE+ (expr | exp_ret) )+ ;
if_statement_core:      'if' SPACE+ exp_bool SPACE* ':' offset_statemnts ;
if_statement_else:      if_statement_core NEWLINE 'else:' offset_statemnts ;
if_statement:           if_statement_else NEWLINE
            |           if_statement_core NEWLINE
            ;

funct_name:     VAR ;
funct_arg:      VAR ;
funct:          'def ' funct_name '(' funct_arg '):' offset_statemnts NEWLINE ;
exp_ret:        'return ' VAR ;
funct_call:     funct_name '(' funct_arg ')'
          |     funct_name '(' exp_arith ')'
          ;

NEWLINE : [\r\n]+ ;
SPACE   : [ \t] ;
TAB     : [\t]  ;
VAR     : [a-zA-Z][a-zA-Z0-9_]* ;
INT     : [0-9]+ ;
