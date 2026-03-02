package codigo;

/**
 * Enumeración completa de tokens para el analizador léxico de Java.
 * Nivel: Ingeniero — con categorías extendidas para tabla de símbolos.
 */
public enum Tokens {
    RESERVADA,
    IDENTIFICADOR,
    ENTERO,
    DECIMAL,
    CADENA,
    CARACTER,
    OP_ARITMETICO,
    OP_RELACIONAL,
    OP_LOGICO,
    OP_ASIGNACION,
    OP_INCREMENTO,
    OP_DECREMENTO,
    OP_ASIG_COMPUESTO,
    SEPARADOR,
    COMENTARIO_LINEA,
    COMENTARIO_BLOQUE,
    BOOLEANO,
    NULO,
    ERROR
}