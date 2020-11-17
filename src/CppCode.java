import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class CppCode {
    private final Set<String> variables = new HashSet<>();
    private final LinkedList<String> expressions = new LinkedList<>();

    public void merge(CppCode code) {
        variables.addAll(code.variables);
        expressions.addAll(code.expressions);
    }

    public void addExpr(String s, Set<String> declVar) {
        variables.addAll(declVar);
        expressions.add(s);
    }

    public String dropLast() {
        return expressions.pollLast();
    }

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder("#include<iostream>\n");
        for (var i : variables) {
            ans.append("int ").append(i).append(";\n");
        }
        ans.append("int main(){\n");
        for (var i : expressions) {
            ans.append("\t").append(i).append("\n");
        }
        ans.append("}");
        return ans.toString();
    }
}
