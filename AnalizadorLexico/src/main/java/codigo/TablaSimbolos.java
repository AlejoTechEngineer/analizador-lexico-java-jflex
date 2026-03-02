package codigo;

import java.util.ArrayList;
import java.util.List;

/**
 * Tabla de símbolos: almacena todos los tokens encontrados durante
 * el análisis léxico, incluyendo errores con su posición.
 */
public class TablaSimbolos {

    private List<Simbolo> tabla;
    private List<Simbolo> errores;

    public TablaSimbolos() {
        tabla   = new ArrayList<>();
        errores = new ArrayList<>();
    }

    public void agregar(Simbolo s) {
        tabla.add(s);
        if (s.getTipo() == Tokens.ERROR) {
            errores.add(s);
        }
    }

    public List<Simbolo> getTabla()   { return tabla;   }
    public List<Simbolo> getErrores() { return errores; }

    public void imprimirTabla() {
        System.out.println("\n========== TABLA DE SÍMBOLOS ==========");
        System.out.printf("| %-20s | %-20s | %-12s | %-8s |%n",
                "LEXEMA", "TOKEN", "LÍNEA", "COLUMNA");
        System.out.println("-".repeat(75));
        for (Simbolo s : tabla) {
            System.out.println(s);
        }
        System.out.println("=".repeat(75));
        System.out.println("Total tokens: " + tabla.size());
    }

    public void imprimirErrores() {
        if (errores.isEmpty()) {
            System.out.println("\n✅ No se encontraron errores léxicos.");
        } else {
            System.out.println("\n========== ERRORES LÉXICOS ==========");
            for (Simbolo e : errores) {
                System.out.println("❌ ERROR -> " + e);
            }
            System.out.println("Total errores: " + errores.size());
        }
    }
}