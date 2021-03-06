import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {

    void testCase(String name) throws IOException {
        File pyFile = new File("templates/py/" + name + ".py");
        InputStream stream = new FileInputStream(pyFile);
        Translator t = new Translator();
        String res = t.generate(stream);
        Path cppFile = Path.of("templates/cpp/" + name + ".cpp");
        String sample = String.join("\n", Files.readAllLines(cppFile,
                StandardCharsets.UTF_8));
        assertEquals(sample, res);
    }

    @Test void test_01() throws IOException { testCase("01"); }
    @Test void test_02() throws IOException { testCase("02"); }
    @Test void test_03() throws IOException { testCase("03"); }
    @Test void test_bool() throws IOException { testCase("bool"); }
    @Test void test_if() throws IOException { testCase("if"); }
    @Test void test_fun() throws IOException { testCase("fun"); }
    @Test void test_hard() throws IOException { testCase("hard"); }
    @Test void test_fun_args() throws IOException { testCase("fun_args"); }
}