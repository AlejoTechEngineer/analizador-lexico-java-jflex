<div align="center">

# 🔍 Analizador Léxico — Java + JFlex

![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![JFlex](https://img.shields.io/badge/JFlex-1.9.1-4A90D9?style=for-the-badge)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)
![NetBeans](https://img.shields.io/badge/NetBeans-IDE-1B6AC6?style=for-the-badge&logo=apachenetbeanside&logoColor=white)
![Status](https://img.shields.io/badge/Status-✅%20Completado-brightgreen?style=for-the-badge)

**Procesadores de Lenguajes**  
Fundación Universitaria Internacional de La Rioja

</div>

---

## 📌 Descripción

Implementación de un **analizador léxico completo para el lenguaje Java** usando **JFlex** como generador automático de autómatas finitos deterministas (DFA). El proyecto incluye tabla de símbolos con posicionamiento exacto, manejo robusto de errores léxicos e interfaz gráfica interactiva construida con **Swing**.

---

## ✨ Características

- ✅ Reconocimiento de **19 categorías léxicas** del lenguaje Java
- ✅ **51 palabras reservadas** de Java completamente definidas
- ✅ **Tabla de símbolos** con lexema, tipo, línea y columna
- ✅ **Detección de errores léxicos** sin interrumpir el análisis
- ✅ **Interfaz gráfica** con entrada de código y visualización de resultados
- ✅ **Generación automática** del Lexer mediante Maven + jflex-maven-plugin
- ✅ Soporte completo **Unicode** y codificación **UTF-8**

---

## 🗂️ Estructura del Proyecto
```
AnalizadorLexico/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── codigo/
│   │   │       ├── Tokens.java
│   │   │       ├── Simbolo.java
│   │   │       ├── TablaSimbolos.java
│   │   │       ├── FCompilador.java
│   │   │       └── VentanaAnalizador.java
│   │   └── jflex/
│   │       └── Definition.flex
├── target/
│   └── generated-sources/
│       └── jflex/codigo/
│           └── Lexer.java
└── pom.xml
```

---

## 🔤 Tokens Reconocidos

| Token | Expresión Regular | Descripción |
|---|---|---|
| `RESERVADA` | `int\|float\|if\|else\|...` | 51 palabras clave de Java |
| `IDENTIFICADOR` | `[a-zA-Z_][a-zA-Z0-9]*` | Variables, métodos, clases |
| `ENTERO` | `[0-9]+` | Números enteros |
| `DECIMAL` | `[0-9]+\.[0-9]+` | Números decimales |
| `CADENA` | `"([^"\\]\|.)*"` | Literales de texto |
| `CARACTER` | `'([^'\\]\|.)'` | Literales de carácter |
| `OP_ARITMETICO` | `+\|-\|*\|/\|%` | Operadores matemáticos |
| `OP_RELACIONAL` | `==\|!=\|>=\|<=\|>\|<` | Comparaciones |
| `OP_LOGICO` | `&&\|\|\|\|!` | AND, OR, NOT |
| `OP_ASIGNACION` | `=` | Asignación |
| `OP_INCREMENTO` | `++` | Incremento |
| `OP_DECREMENTO` | `--` | Decremento |
| `OP_ASIG_COMPUESTO` | `+=\|-=\|*=\|/=` | Asignación combinada |
| `SEPARADOR` | `( ) { } [ ] ; , . :` | Delimitadores |
| `COMENTARIO_LINEA` | `//.*` | Comentario de línea |
| `COMENTARIO_BLOQUE` | `/* ... */` | Comentario multilínea |
| `BOOLEANO` | `true\|false` | Literales booleanos |
| `NULO` | `null` | Referencia nula |
| `ERROR` | `.` | Carácter no reconocido |

---

## 🚀 Cómo ejecutar
```bash
git clone https://github.com/AlejoTechEngineer/analizador-lexico-java-jflex.git
```

1. Abrir en NetBeans → **File → Open Project**
2. Click derecho → **Clean and Build**
3. Click derecho → **Run Project**

---

## 📊 Ejemplo de Salida
```
TABLA DE SÍMBOLOS
================================================================================
| LEXEMA                    | TOKEN                | LÍNEA  | COLUMNA |
--------------------------------------------------------------------------------
| public                    | RESERVADA            |      1 |       1 |
| class                     | RESERVADA            |      1 |       8 |
| edad                      | IDENTIFICADOR        |      3 |      13 |
| 20                        | ENTERO               |      3 |      20 |
| 1500.50                   | DECIMAL              |      4 |      25 |
| @                         | ERROR                |     17 |       1 |
================================================================================
Total tokens: 71

========== ERRORES LÉXICOS ==========
ERROR -> '@' en línea 17, col 1
ERROR -> '#' en línea 17, col 3
ERROR -> '$' en línea 17, col 5
Total errores: 3
```

---

## 🏗️ Arquitectura
```
Código fuente Java
       │
       ▼
  Definition.flex
       │
       ▼ JFlex
  Lexer.java (DFA)
       │
       ▼
  TablaSimbolos
       │
       ▼
  VentanaAnalizador (Swing)
```

---

## 🛠️ Tecnologías

| Tecnología | Versión | Uso |
|---|---|---|
| Java | 21 | Lenguaje principal |
| JFlex | 1.9.1 | Generador léxico |
| Maven | 3.x | Gestión de dependencias |
| Swing | JDK built-in | Interfaz gráfica |
| NetBeans | Latest | IDE |

---

## 📚 Referencias

- Aho, A., Lam, M., Sethi, R., & Ullman, J. (2006). *Compilers: Principles, Techniques, and Tools* (2nd ed.). Addison-Wesley.
- Hopcroft, J., Motwani, R., & Ullman, J. (2007). *Introduction to Automata Theory* (3rd ed.). Pearson.
- Klein, G. (2023). JFlex Documentation. https://jflex.de
- Oracle (2024). *Java SE 21 Language Specification*. https://docs.oracle.com/javase/specs/

---

## 👨‍💻 Autor

**Alejandro De Mendoza**  
Ingeniería Informática - Fundación Universitaria Internacional de La Rioja  
Procesadores de Lenguajes · 2026

---

<div align="center">
Desarrollado con ❤️ para el curso de Procesadores de Lenguajes
</div>
