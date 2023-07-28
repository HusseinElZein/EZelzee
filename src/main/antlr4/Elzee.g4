grammar Elzee;

start: expr* EOF;

expr: assigningExpr
    | reassigningExpr
    | additionExpr
    | printExpr
    ;

assigningExpr: type ID ('er' | 'is') content;
reassigningExpr: ID ('er' | 'is') ID;
additionExpr: 'plus' ID ('med' | 'with') (NUMBER | ID);
subtractionExpr: 'minus' ID ('med' | 'with') (NUMBER | ID);
multiplyExpr: 'gang' ID ('med' | 'with') (NUMBER | ID);
divideExpr: 'divider' ID ('med' | 'with') (NUMBER | ID);
printExpr: ('skriv' | 'udskriv' | 'print') value; // Update printExpr rule to support NUMBER as well

value: NUMBER | STRING | ID;
content: NUMBER | STRING;

type: 'tallet' | 'ordet';
NUMBER: [0-9]+;
ID: [a-zA-Z_]+ [a-zA-Z0-9_]*;

STRING: '"' ('\\' . | ~["\\])* '"';
// The STRING rule now correctly allows double quotes and escaped characters within the string.

WHITESPACE: [ \t\n\r] -> skip;
COMMENT: '//' ~[\n]* -> skip;
MULTICOMMENT: '/*' .*? '*/' -> skip;
