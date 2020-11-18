import org.antlr.v4.runtime.RuleContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Reformer extends GrammarBaseListener {
    private CppCode result = new CppCode();

    public CppCode getCode() {
        return result;
    }

    @Override
    public void exitExp_input(GrammarParser.Exp_inputContext ctx) {
        String varName = ctx.VAR().getSymbol().getText();
        result.addExpr("scanf(\"%d\", " + varName + ");", Set.of(varName));
    }

    @Override
    public void exitExp_output(GrammarParser.Exp_outputContext ctx) {
        String varName = ctx.VAR().getSymbol().getText();
        result.addExpr("printf(\"%d\", " + varName + ");", Set.of(varName));
    }

    @Override
    public void exitExp_assgn(GrammarParser.Exp_assgnContext ctx) {
        String varName = ctx.VAR().getText();
        String expr = codeExp_notf;
        result.addExpr(varName + " = " + expr + ";", Set.of(varName));
    }

    private CppCode inOffset = null;

    @Override
    public void exitIf_statement_core(GrammarParser.If_statement_coreContext ctx) {
        result.addExpr("if (" + codeExp_bool.dropLast() + "){", Set.of());
        result.mergeTab(inOffset);
        inOffset = null;
        result.addExpr("}", Set.of());
    }

    @Override
    public void exitIf_statement_else(GrammarParser.If_statement_elseContext ctx) {
        result.addExpr("else{", Set.of());
        result.mergeTab(inOffset);
        inOffset = null;
        result.addExpr("}", Set.of());
    }

    @Override
    public void enterOffset_statemnts(GrammarParser.Offset_statemntsContext ctx) {
        inOffset = result;
        result = new CppCode();
    }

    @Override
    public void exitOffset_statemnts(GrammarParser.Offset_statemntsContext ctx) {
        CppCode tmp = result;
        result = inOffset;
        inOffset = tmp;
    }

    private CppCode currFunction = null;
    @Override
    public void enterFunct(GrammarParser.FunctContext ctx) {
        currFunction = result;
        String name = ctx.funct_name().getText();
        List<String> arg = ctx.funct_arg().stream()
                .map(RuleContext::getText)
                .collect(Collectors.toList());
        result = new CppCode(name, arg);
    }

    @Override
    public void exitFunct(GrammarParser.FunctContext ctx) {
        result.mergeTab(inOffset);
        currFunction.addFunct(result);
        result = currFunction;
        currFunction = null;
    }

    @Override
    public void exitExp_ret(GrammarParser.Exp_retContext ctx) {
        result.addExpr("return " + ctx.exp_arith().getText() + ";", Set.of());
    }

    @Override
    public void exitExpr(GrammarParser.ExprContext ctx) {
        if (ctx.exp_notf() != null) {
            String expr = codeExp_notf;
            result.addExpr(expr + ";", Set.of());
            return;
        }
        super.exitExpr(ctx);
    }

    private String codeExp_notf;

    @Override
    public void exitExp_notf(GrammarParser.Exp_notfContext ctx) {
        if (ctx.exp_bool() != null) {
            codeExp_notf = codeExp_bool.dropLast();
            return;
        }
        if (ctx.exp_arith() != null) {
            codeExp_notf = ctx.exp_arith().getText();
            return;
        }
    }

    private CppCode codeExp_bool = new CppCode();

    @Override
    public void exitExp_bool(GrammarParser.Exp_boolContext ctx) {
        if (ctx.exp_bool_false() != null) {
            codeExp_bool.addExpr("false", Set.of());
        }
        if (ctx.exp_bool_true() != null) {
            codeExp_bool.addExpr("true", Set.of());
        }
        if (ctx.exp_bool_var() != null) {
            codeExp_bool.addExpr(ctx.exp_bool_var().getText(), Set.of());
        }
        if (ctx.exp_bool_or() != null) {
            String b = codeExp_bool.dropLast();
            String a = codeExp_bool.dropLast();
            codeExp_bool.addExpr(a + " || " + b, Set.of());
        }
        if (ctx.exp_bool_and() != null) {
            String b = codeExp_bool.dropLast();
            String a = codeExp_bool.dropLast();
            codeExp_bool.addExpr(a + " && " + b, Set.of());
        }
        if (ctx.exp_bool_not() != null) {
            String a = codeExp_bool.dropLast();
            codeExp_bool.addExpr("! " + a, Set.of());
        }
        if (ctx.exp_bool_() != null) {
            String a = codeExp_bool.dropLast();
            codeExp_bool.addExpr("(" + a + ")", Set.of());
        }
        super.exitExp_bool(ctx);
    }
}
