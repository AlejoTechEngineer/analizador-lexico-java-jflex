package codigo;

import static codigo.Tokens.*;

%%

%class Lexer
%type Tokens
%unicode
%line
%column
%public

%{
    /* Aquí se va a construir la tabla de símbolos */
    private TablaSimbolos tabla;

    public void setTabla(TablaSimbolos t) {
        this.tabla = t;
    }

    private Tokens registrar(Tokens tipo) {
        if (tabla != null) {
            tabla.agregar(new Simbolo(yytext(), tipo, yyline + 1, yycolumn + 1));
        }
        return tipo;
    }
%}

/* ============ DEFINICIONES ============ */
L         = [a-zA-Z_]
D         = [0-9]
ID        = {L}({L}|{D})*
ENTERO    = {D}+
DECIMAL   = {D}+"."{D}+
CADENA    = \"([^\"\\]|\\.)*\"
CARACTER  = \'([^\'\\]|\\.)\'
ESPACIO   = [ \t\r\n]+
COM_LINE  = "//".*
COM_BLOCK = "/*"([^*]|\*+[^*/])*\*+"/"

%%

/* ============ REGLAS ============ */

/* Palabras reservadas — deben ir ANTES que los identificadores */
"abstract"|"assert"|"boolean"|"break"|"byte"|"case"|"catch"|"char"|
"class"|"const"|"continue"|"default"|"do"|"double"|"else"|"enum"|
"extends"|"final"|"finally"|"float"|"for"|"goto"|"if"|"implements"|
"import"|"instanceof"|"int"|"interface"|"long"|"native"|"new"|
"package"|"private"|"protected"|"public"|"return"|"short"|"static"|
"strictfp"|"super"|"switch"|"synchronized"|"this"|"throw"|"throws"|
"transient"|"try"|"void"|"volatile"|"while"
    { return registrar(RESERVADA); }

/* Literales booleanos y nulo */
"true"|"false"   { return registrar(BOOLEANO); }
"null"           { return registrar(NULO); }

/* Identificadores */
{ID}             { return registrar(IDENTIFICADOR); }

/* Números */
{DECIMAL}        { return registrar(DECIMAL); }
{ENTERO}         { return registrar(ENTERO); }

/* Cadenas y caracteres */
{CADENA}         { return registrar(CADENA); }
{CARACTER}       { return registrar(CARACTER); }

/* Comentarios */
{COM_LINE}       { return registrar(COMENTARIO_LINEA); }
{COM_BLOCK}      { return registrar(COMENTARIO_BLOQUE); }

/* Operadores de asignación compuesta */
"+=" | "-=" | "*=" | "/=" | "%="
    { return registrar(OP_ASIG_COMPUESTO); }

/* Incremento y decremento */
"++"             { return registrar(OP_INCREMENTO); }
"--"             { return registrar(OP_DECREMENTO); }

/* Operadores relacionales */
"==" | "!=" | ">=" | "<=" | ">" | "<"
    { return registrar(OP_RELACIONAL); }

/* Operadores lógicos */
"&&" | "||" | "!"
    { return registrar(OP_LOGICO); }

/* Operadores aritméticos */
"+" | "-" | "*" | "/" | "%"
    { return registrar(OP_ARITMETICO); }

/* Operador de asignación */
"="              { return registrar(OP_ASIGNACION); }

/* Separadores */
"(" | ")" | "{" | "}" | "[" | "]" | ";" | "," | "." | ":"
    { return registrar(SEPARADOR); }

/* Espacios en blanco — se ignoran */
{ESPACIO}        { /* ignorar */ }

/* Cualquier otro carácter es un ERROR */
.                { return registrar(ERROR); }
