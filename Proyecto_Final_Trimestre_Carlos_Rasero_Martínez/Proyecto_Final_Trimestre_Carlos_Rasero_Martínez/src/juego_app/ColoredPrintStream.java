package juego_app;

import java.io.OutputStream;
import java.io.PrintStream;

/**
 * La clase ColoredPrintStream extiende PrintStream para permitir la impresión de texto en colores.
 * Cada instancia de ColoredPrintStream imprimirá el texto en el color especificado.
 */
public class ColoredPrintStream extends PrintStream {
    /**
     * El color en el que se imprimirá el texto.
     */
    private String color;

    /**
     * Constructor de la clase ColoredPrintStream.
     *
     * @param out   El OutputStream al que se enviará el texto.
     * @param color El código de color ANSI que se aplicará al texto.
     */
    public ColoredPrintStream(OutputStream out, String color) {
        super(out);
        this.color = color;
    }

    /**
     * Imprime una línea de texto con el color especificado, seguido de un cambio de línea.
     *
     * @param x La cadena de texto a imprimir.
     */
    @Override
    public void println(String x) {
        super.println(color + x + ConsoleColors.RESET);
    }

    /**
     * Imprime una cadena de texto con el color especificado.
     *
     * @param x La cadena de texto a imprimir.
     */
    @Override
    public void print(String x) {
        super.print(color + x + ConsoleColors.RESET);
    }
}
