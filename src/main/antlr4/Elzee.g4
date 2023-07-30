grammar Elzee;

start: expr* EOF;

expr: assigningExpr
    | reassigningExpr
    | additionExpr
    | printExpr
    | subtractionExpr
    | multiplyExpr
    | divideExpr
    ;

assigningExpr: type ID 'er' content;
reassigningExpr: ID 'er' ID;
additionExpr: 'plus' ID 'med' (NUMBER | ID);
subtractionExpr: 'minus' ID 'med' (NUMBER | ID);
multiplyExpr: 'gang' ID 'med' (NUMBER | ID);
divideExpr: 'divider' ID 'med' (NUMBER | ID);
printExpr: ('skriv' | 'udskriv' | 'print') value;

value: NUMBER | STRING | ID;
content: NUMBER | STRING | BOOLEAN;

type: 'tallet' | 'ordet' | 'sandheden';
NUMBER: [0-9]+;
BOOLEAN: 'sand' | 'falsk';
ID: [a-zA-Z_]+ [a-zA-Z0-9_]*;

STRING: '"' ('\\' . | ~["\\])* '"';

WHITESPACE: [ \t\n\r] -> skip;
COMMENT: '//' ~[\n]* -> skip;
MULTICOMMENT: '/*' .*? '*/' -> skip;
