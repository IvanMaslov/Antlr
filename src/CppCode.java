import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class CppCode {
    private final Set<String> variables = new HashSet<>();
    private final LinkedList<String> expressions = new LinkedList<>();
    private final List<CppCode> functions = new LinkedList<>();
    private final boolean isFunction;
    private final String functName;
    private final String functArg;

    public CppCode() {
        this.isFunction = false;
        this.functName = null;
        this.functArg = null;
    }

    public CppCode(String functName, String functArg) {
        this.isFunction = true;
        this.functName = functName;
        this.functArg = functArg;
    }

    public void merge(CppCode code) {
        variables.addAll(code.variables);
        expressions.addAll(code.expressions);
    }

    public void mergeTab(CppCode code) {
        variables.addAll(code.variables);
        for (var i : code.expressions) {
            expressions.add("\t" + i);
        }
    }

    public void addExpr(String s, Set<String> declVar) {
        variables.addAll(declVar);
        expressions.add(s);
    }

    public void addFunct(CppCode f) {
        if (!f.isFunction) throw new IllegalArgumentException();
        functions.add(f);
    }

    public String dropLast() {
        return expressions.pollLast();
    }

    @Override
    public String toString() {
        if (isFunction) {
            StringBuilder ans = new StringBuilder();
            ans
                    .append("int ")
                    .append(functName)
                    .append("(int ")
                    .append(functArg)
                    .append(")");
            ans.append("{\n");
            for (var i : variables) {
                if (i.equals(functArg)) continue;
                ans
                        .append("\tint ")
                        .append(i)
                        .append(";\n");
            }
            for (var i : expressions) {
                ans
                        .append(i)
                        .append("\n");
            }
            ans.append("}\n");
            return ans.toString();
        }
        StringBuilder ans = new StringBuilder("#include<iostream>\n");
        for (var i : variables) {
            ans.append("int ").append(i).append(";\n");
        }
        for (var i : functions) {
            ans.append(i.toString());
        }
        ans.append("int main(){\n");
        for (var i : expressions) {
            ans.append("\t").append(i).append("\n");
        }
        ans.append("}");
        return ans.toString();
    }
}
