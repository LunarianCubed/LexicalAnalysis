# LexicalAnalysis
**This is an assignment of LSU CSC 4101(Prog. Lang)**


This is a Lexical Analyzer, which reads individual characters from the input source code and group them into the words of the language: identifiers, constants, and symbols.
An individual word(a lexeme) is then represented as a token object and returned to the parser(printed to the screen)

### How to use
The program will ask you to type the file name of the source code and will generate the equivalent sequence of tokens and print them to the screen.
Each token will be on a new line.

### Language symbols list:

| Symbol    | Token  |     | Symbol | Token  |     | Symbol | Token |
|-----------|--------|-----|--------|--------|-----|--------|-------|
| if        | IF     |     | =      | ASSIGN |     | ++     | INC   | 
| for       | FOR    |     | +      | ADD_OP |     | (      | LP    | 
| while     | WHILE  |     | -      | SUB_OP |     | )      | RP    |
| procedure | PROC   |     | *      | MUL_OP |     | {      | LB    |
| return    | RETURN |     | /      | DIV_OP |     | }      | RB    |
| int       | INT    |     | %      | MOD_OP |     | \      | OR    | 
| else      | ELSE   |     | <      | LT     |     | &      | AND   |
| do        | DO     |     | <      | GT     |     | ==     | EE    | 
| break     | BREAK  |     | >=     | GE     |     | !      | NEG   | 
| end       | END    |     | <=     | LE     |     | ,      | COMMA |
|           |        |     |        |        |     | ;      | SEMI  |
- Identifier (procedure names, variable names) are alphanumeric; However, they must start with an alphabetical character. The token is **IDENT**.
- The token for integers is **INT_CONST**
- the token for a String is **STR**, for a string values is **STR_CONST**
- Comments are ignored. The syntax is similar as Java:
  - *// for inline comments*
  - */\*...\*/ for comments blocks*
