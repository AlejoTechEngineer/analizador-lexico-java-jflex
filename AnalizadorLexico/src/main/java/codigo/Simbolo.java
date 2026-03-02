package codigo;

/**
 * Representa una entrada en la Tabla de Símbolos.
 * Almacena el lexema, tipo de token, línea y columna donde aparece.
 */
public class Simbolo {
    private String lexema;
    private Tokens tipo;
    private int linea;
    private int columna;

    public Simbolo(String lexema, Tokens tipo, int linea, int columna) {
        this.lexema = lexema;
        this.tipo   = tipo;
        this.linea  = linea;
        this.columna = columna;
    }

    public String getLexema()  { return lexema;  }
    public Tokens getTipo()    { return tipo;    }
    public int getLinea()      { return linea;   }
    public int getColumna()    { return columna; }

    @Override
    public String toString() {
        return String.format("| %-20s | %-20s | Línea: %3d | Col: %3d |",
                lexema, tipo, linea, columna);
    }
}