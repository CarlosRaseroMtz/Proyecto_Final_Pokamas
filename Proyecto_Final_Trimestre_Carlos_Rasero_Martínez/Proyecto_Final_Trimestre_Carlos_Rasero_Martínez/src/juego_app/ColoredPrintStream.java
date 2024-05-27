package juego_app;

import java.io.OutputStream;
import java.io.PrintStream;

public class ColoredPrintStream extends PrintStream {
    private String color;

    public ColoredPrintStream(OutputStream out, String color) {
        super(out);
        this.color = color;
    }

    @Override
    public void println(String x) {
        super.println(color + x + ConsoleColors.RESET);
    }

    @Override
    public void print(String x) {
        super.print(color + x + ConsoleColors.RESET);
    }
}

