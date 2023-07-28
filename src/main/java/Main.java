import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        String inputFilePath = "src/main/program.txt";
        String inputCode = readFromFile(inputFilePath);

        // Create an input stream from the input string
        CharStream inputStream = CharStreams.fromString(inputCode);

        // Create a lexer that feeds off of the input stream
        ElzeeLexer lexer = new ElzeeLexer(inputStream);

        // Create a buffer of tokens pulled from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create a parser that feeds off the tokens buffer
        ElzeeParser parser = new ElzeeParser(tokens);

        // Create the parse tree from the start rule
        ParseTree tree = parser.start();

        // Create your custom listener
        ElzeeListenerImpl listener = new ElzeeListenerImpl();

        // Walk the parse tree with your custom listener
        ParseTreeWalker.DEFAULT.walk(listener, tree);
    }

    private static String readFromFile(String filePath) {
        try {
            Path path = Paths.get(filePath);
            return Files.readString(path, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return "";
        }
    }


}
