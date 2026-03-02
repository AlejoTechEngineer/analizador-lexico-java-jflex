package codigo;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

/**
 * Clase principal del compilador.
 * Ejecuta el análisis léxico y muestra la tabla de símbolos y errores.
 */
public class FCompilador {

    public static void main(String[] args) {

        // ── Fix de encoding para mostrar tildes y ñ correctamente ──
        try {
            System.setOut(new PrintStream(System.out, true, "UTF-8"));
            System.setErr(new PrintStream(System.err, true, "UTF-8"));
        } catch (Exception e) {
            // Si falla el encoding, continúa normal
        }

        // ── Código de prueba ──
        String codigoFuente =
            "public class Ejemplo {\n" +
            "    public static void main(String[] args) {\n" +
            "        int edad = 20;\n" +
            "        float salario = 1500.50;\n" +
            "        String nombre = \"Juan\";\n" +
            "        boolean activo = true;\n" +
            "        // Verificar condición\n" +
            "        if (edad >= 18 && activo) {\n" +
            "            salario += 500.0;\n" +
            "            edad++;\n" +
            "        } else {\n" +
            "            System.out.println(\"Menor de edad\");\n" +
            "        }\n" +
            "        /* Fin del programa */\n" +
            "    }\n" +
            "}\n" +
            "@ # $";  // <- errores léxicos intencionales

        try {
            TablaSimbolos tabla = new TablaSimbolos();
            Lexer lexer = new Lexer(new StringReader(codigoFuente));
            lexer.setTabla(tabla);

            // Consumir todos los tokens
            Tokens token;
            while ((token = lexer.yylex()) != null) {
                // El registro ya se hace dentro del Lexer
            }

            // Mostrar resultados
            tabla.imprimirTabla();
            tabla.imprimirErrores();

        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}