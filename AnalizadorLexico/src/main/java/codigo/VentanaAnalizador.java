package codigo;

import javax.swing.*;
import java.awt.*;
import java.io.StringReader;

public class VentanaAnalizador extends JFrame {

    private final JTextArea txtEntrada = new JTextArea();
    private final JTextArea txtSalida  = new JTextArea();
    private final JButton btnAnalizar  = new JButton("Analizar");
    private final JButton btnLimpiar   = new JButton("Limpiar");

    public VentanaAnalizador() {
        super("Analizador Léxico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 650);
        setLocationRelativeTo(null);

        // ===== PANEL PRINCIPAL =====
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BoxLayout(panelPrincipal, BoxLayout.Y_AXIS));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(15, 20, 20, 20));

        // ===== TÍTULO =====
        JLabel lblTitulo = new JLabel("ANALIZADOR LÉXICO");
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        panelPrincipal.add(lblTitulo);
        panelPrincipal.add(Box.createVerticalStrut(15));

        // ===== PANEL BOTONES (alineado al ancho completo) =====
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 5));
        btnAnalizar.setPreferredSize(new Dimension(150, 40));
        btnLimpiar.setPreferredSize(new Dimension(150, 40));
        panelBotones.add(btnAnalizar);
        panelBotones.add(btnLimpiar);

        panelPrincipal.add(panelBotones);
        panelPrincipal.add(Box.createVerticalStrut(15));

        // ===== ÁREAS DE TEXTO =====
        Font fuenteCodigo = new Font("Consolas", Font.PLAIN, 14);
        txtEntrada.setFont(fuenteCodigo);
        txtSalida.setFont(fuenteCodigo);
        txtSalida.setEditable(false);

        txtEntrada.setText(
            "public class Ejemplo {\n" +
            "  public static void main(String[] args) {\n" +
            "    int edad = 20;\n" +
            "    float salario = 1500.50;\n" +
            "    // Verificar condición\n" +
            "    if (edad >= 18 && true) {\n" +
            "      salario += 500.0;\n" +
            "      edad++;\n" +
            "    }\n" +
            "  }\n" +
            "}\n" +
            "@ # $\n"
        );

        JScrollPane spEntrada = new JScrollPane(txtEntrada);
        JScrollPane spSalida  = new JScrollPane(txtSalida);

        spEntrada.setBorder(BorderFactory.createTitledBorder("Entrada"));
        spSalida.setBorder(BorderFactory.createTitledBorder("Salida"));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spEntrada, spSalida);
        split.setResizeWeight(0.5);
        split.setDividerLocation(0.5);
        split.setAlignmentX(Component.CENTER_ALIGNMENT);
        split.setPreferredSize(new Dimension(1000, 450));

        panelPrincipal.add(split);

        add(panelPrincipal);

        // ===== EVENTOS =====
        btnAnalizar.addActionListener(e -> analizar());
        btnLimpiar.addActionListener(e -> {
            txtEntrada.setText("");
            txtSalida.setText("");
        });
    }

    private void analizar() {
        try {
            String texto = txtEntrada.getText();

            TablaSimbolos tabla = new TablaSimbolos();
            Lexer lexer = new Lexer(new StringReader(texto));
            lexer.setTabla(tabla);

            while (lexer.yylex() != null) { }

            StringBuilder sb = new StringBuilder();

            String titulo = "TABLA DE SÍMBOLOS";
            int ancho = 80;
            int padding = (ancho - titulo.length()) / 2;

            sb.append(" ".repeat(Math.max(0, padding)))
              .append(titulo)
              .append("\n");
            sb.append("=".repeat(ancho)).append("\n");

            sb.append(String.format("| %-25s | %-20s | %-6s | %-7s |\n",
                    "LEXEMA", "TOKEN", "LÍNEA", "COLUMNA"));
            sb.append("-".repeat(ancho)).append("\n");

            for (Simbolo s : tabla.getTabla()) {
                sb.append(String.format("| %-25s | %-20s | %6d | %7d |\n",
                        recortar(s.getLexema(), 25),
                        s.getTipo(),
                        s.getLinea(),
                        s.getColumna()));
            }

            sb.append("=".repeat(ancho)).append("\n");
            sb.append("Total tokens: ").append(tabla.getTabla().size()).append("\n\n");

            if (tabla.getErrores().isEmpty()) {
                sb.append("No se encontraron errores léxicos.\n");
            } else {
                sb.append("========== ERRORES LÉXICOS ==========\n");
                for (Simbolo e : tabla.getErrores()) {
                    sb.append("ERROR -> ")
                      .append(String.format("'%s' en línea %d, col %d\n",
                              e.getLexema(), e.getLinea(), e.getColumna()));
                }
                sb.append("Total errores: ").append(tabla.getErrores().size()).append("\n");
            }

            txtSalida.setText(sb.toString());

        } catch (Exception ex) {
            txtSalida.setText("Error: " + ex.getMessage());
        }
    }

    private static String recortar(String s, int max) {
        if (s == null) return "";
        if (s.length() <= max) return s;
        return s.substring(0, Math.max(0, max - 3)) + "...";
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaAnalizador().setVisible(true));
    }
}