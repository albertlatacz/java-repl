package javarepl.parsing;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.atn.PredictionMode;

import static javarepl.parsing.Java7Parser.CompilationUnitContext;

class Java7ParsingPlayground {

    public static void main(String[] args) throws Exception {
        parseString("import java.util.*;\n" +
                "import java.math.*;\n" +
                "import static java.lang.Math.*;\n" +
                ";\n" +
                "public final class Evaluation$wl6fhv9e8nyx3tjz0k2o extends javarepl.EvaluationTemplate {\n" +
                "  public Evaluation$wl6fhv9e8nyx3tjz0k2o(javarepl.EvaluationContext context) { super(context); }\n" +
                "public int s = 12;\n" +
                "\n" +
                "\n" +
                "\n" +
                "  \n" +
                "  @MyAnnotation\n" +
                "  int aaa(java.lang.String<AAAA> i, Date b){}\n" +
                "  void aaa(String i, Date b){}\n" +
                "\n" +
                "  public void evaluate() throws Exception {\n" +
                "  }\n" +
                "}");
//
//        parseString("void ssss(){}");
    }


    public static void parseString(String f) throws Exception {
        try {
            Java7Lexer lexer = new Java7Lexer(null);
            lexer.setInputStream(new ANTLRInputStream(f));

            CommonTokenStream tokens = new CommonTokenStream(lexer);

            Java7Parser parser = new Java7Parser(tokens);

            parser.addParseListener(new Java7BaseListener() {
                @Override
                public void exitMemberDecl(Java7Parser.MemberDeclContext ctx) {
                    System.out.println("   >>>>>> " + ctx.getText());
                }
            });

//            parser.addErrorListener(new DiagnosticErrorListener());
            parser.getInterpreter().setPredictionMode(PredictionMode.SLL);
//            parser.getInterpreter().setPredictionMode(PredictionMode.LL);
//            parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);

            CompilationUnitContext tree = parser.compilationUnit();

//            tree.inspect(parser);
//            System.out.println(tree.toStringTree(parser));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

