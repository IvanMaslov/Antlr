import java.util.Set;

public class Reformer extends GrammarBaseListener {
    private final CppCode result = new CppCode();

    public CppCode getCode() {
        return result;
    }

    @Override
    public void enterExp_input(GrammarParser.Exp_inputContext ctx) {
        String varName = ctx.VAR().getSymbol().getText();
        result.addExpr("scanf(\"%d\", " + varName + ");", Set.of(varName));
    }

    @Override
    public void enterExp_output(GrammarParser.Exp_outputContext ctx) {
        String varName = ctx.VAR().getSymbol().getText();
        result.addExpr("printf(\"%d\", " + varName + ");", Set.of(varName));
    }

    @Override
    public void enterExp_assgn(GrammarParser.Exp_assgnContext ctx) {
        String varName = ctx.VAR().getText();
        String expr = ctx.exp_arith().getText();
        result.addExpr(varName + " = " + expr + ";", Set.of(varName));
    }

    @Override
    public void enterExpr(GrammarParser.ExprContext ctx) {
        if (ctx.exp_arith() != null) {
            String arithExpr = ctx.exp_arith().getText();
            result.addExpr(arithExpr + ";", Set.of());
            return;
        }
        super.enterExpr(ctx);
    }
}
