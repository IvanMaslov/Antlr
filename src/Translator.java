import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Translator {

    private String refactorTree(final ParseTree tree) {
        Reformer reformer = new Reformer();
        ParseTreeWalker.DEFAULT.walk(reformer, tree);
        return reformer.getCode().toString();
    }

    String generate(InputStream inputStream) throws IOException {
        CharStream stream = CharStreams.fromStream(inputStream);
        GrammarLexer lexer = new GrammarLexer(stream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(lexer);
        GrammarParser parser = new GrammarParser(commonTokenStream);

        ParseTree parseTree = parser.start();
        return refactorTree(parseTree);
    }

    public static void main(final String[] argv) throws IOException {
        Scanner t = new Scanner(System.in);
        StringBuilder input = new StringBuilder();
        while (t.hasNextLine()) {
            input.append(t.nextLine()).append("\n");
        }
        InputStream stream = new ByteArrayInputStream(input.toString().getBytes());
        System.out.print(new Translator().generate(stream));
    }
}
