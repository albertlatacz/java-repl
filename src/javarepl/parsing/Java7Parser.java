package javarepl.parsing;// Generated from Java7.g4 by ANTLR 4.0

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.ATN;
import org.antlr.v4.runtime.atn.ATNSimulator;
import org.antlr.v4.runtime.atn.ParserATNSimulator;
import org.antlr.v4.runtime.atn.PredictionContextCache;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.util.List;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Java7Parser extends Parser {
    protected static final DFA[] _decisionToDFA;
    protected static final PredictionContextCache _sharedContextCache =
            new PredictionContextCache();
    public static final int
            T__88 = 1, T__87 = 2, T__86 = 3, T__85 = 4, T__84 = 5, T__83 = 6, T__82 = 7, T__81 = 8,
            T__80 = 9, T__79 = 10, T__78 = 11, T__77 = 12, T__76 = 13, T__75 = 14, T__74 = 15, T__73 = 16,
            T__72 = 17, T__71 = 18, T__70 = 19, T__69 = 20, T__68 = 21, T__67 = 22, T__66 = 23,
            T__65 = 24, T__64 = 25, T__63 = 26, T__62 = 27, T__61 = 28, T__60 = 29, T__59 = 30,
            T__58 = 31, T__57 = 32, T__56 = 33, T__55 = 34, T__54 = 35, T__53 = 36, T__52 = 37,
            T__51 = 38, T__50 = 39, T__49 = 40, T__48 = 41, T__47 = 42, T__46 = 43, T__45 = 44,
            T__44 = 45, T__43 = 46, T__42 = 47, T__41 = 48, T__40 = 49, T__39 = 50, T__38 = 51,
            T__37 = 52, T__36 = 53, T__35 = 54, T__34 = 55, T__33 = 56, T__32 = 57, T__31 = 58,
            T__30 = 59, T__29 = 60, T__28 = 61, T__27 = 62, T__26 = 63, T__25 = 64, T__24 = 65,
            T__23 = 66, T__22 = 67, T__21 = 68, T__20 = 69, T__19 = 70, T__18 = 71, T__17 = 72,
            T__16 = 73, T__15 = 74, T__14 = 75, T__13 = 76, T__12 = 77, T__11 = 78, T__10 = 79,
            T__9 = 80, T__8 = 81, T__7 = 82, T__6 = 83, T__5 = 84, T__4 = 85, T__3 = 86, T__2 = 87,
            T__1 = 88, T__0 = 89, HexLiteral = 90, DecimalLiteral = 91, OctalLiteral = 92, BinaryLiteral = 93,
            FloatingPointLiteral = 94, CharacterLiteral = 95, StringLiteral = 96, ENUM = 97,
            ASSERT = 98, Identifier = 99, WS = 100, COMMENT = 101, LINE_COMMENT = 102;
    public static final String[] tokenNames = {
            "<INVALID>", "'interface'", "'&'", "'*'", "'['", "'<'", "'--'", "'false'",
            "'continue'", "'!='", "'double'", "'abstract'", "'boolean'", "'}'", "'float'",
            "'char'", "'strictfp'", "'case'", "'super'", "'do'", "'%'", "'*='", "')'",
            "'throw'", "'@'", "'='", "'null'", "'throws'", "'|='", "'new'", "'class'",
            "'finally'", "'|'", "'transient'", "'!'", "'long'", "'short'", "']'",
            "'-='", "'public'", "'default'", "'synchronized'", "','", "'while'", "'-'",
            "'('", "':'", "'if'", "'&='", "'int'", "'private'", "'?'", "'try'", "'void'",
            "'package'", "'...'", "'{'", "'break'", "'native'", "'+='", "'extends'",
            "'^='", "'final'", "'else'", "'catch'", "'true'", "'static'", "'++'",
            "'import'", "'byte'", "'^'", "'.'", "'+'", "'protected'", "'for'", "'return'",
            "'volatile'", "';'", "'&&'", "'this'", "'||'", "'>'", "'implements'",
            "'%='", "'switch'", "'/='", "'/'", "'=='", "'~'", "'instanceof'", "HexLiteral",
            "DecimalLiteral", "OctalLiteral", "BinaryLiteral", "FloatingPointLiteral",
            "CharacterLiteral", "StringLiteral", "'enum'", "'assert'", "Identifier",
            "WS", "COMMENT", "LINE_COMMENT"
    };
    public static final int
            RULE_compilationUnit = 0, RULE_packageDeclaration = 1, RULE_importDeclaration = 2,
            RULE_typeDeclaration = 3, RULE_classOrInterfaceDeclaration = 4, RULE_classDeclaration = 5,
            RULE_normalClassDeclaration = 6, RULE_typeParameters = 7, RULE_typeParameter = 8,
            RULE_bound = 9, RULE_enumDeclaration = 10, RULE_enumConstants = 11, RULE_interfaceDeclaration = 12,
            RULE_normalInterfaceDeclaration = 13, RULE_classBody = 14, RULE_interfaceBody = 15,
            RULE_classBodyDeclaration = 16, RULE_memberDecl = 17, RULE_fieldDeclaration = 18,
            RULE_block = 19, RULE_blockStatement = 20, RULE_enumConstant = 21, RULE_typeList = 22,
            RULE_typeArguments = 23, RULE_typeArgument = 24, RULE_interfaceMemberDecl = 25,
            RULE_methodDeclaration = 26, RULE_constructorDeclaration = 27, RULE_variableModifier = 28,
            RULE_interfaceMethodOrFieldDecl = 29, RULE_interfaceMethodOrFieldRest = 30,
            RULE_interfaceMethodDeclaratorRest = 31, RULE_interfaceGenericMethodDecl = 32,
            RULE_voidInterfaceMethodDeclaratorRest = 33, RULE_constantDeclarator = 34,
            RULE_variableDeclarators = 35, RULE_variableDeclarator = 36, RULE_constantDeclaratorsRest = 37,
            RULE_constantDeclaratorRest = 38, RULE_variableDeclaratorId = 39, RULE_variableInitializer = 40,
            RULE_arrayInitializer = 41, RULE_modifier = 42, RULE_packageOrTypeName = 43,
            RULE_enumConstantName = 44, RULE_typeName = 45, RULE_typeRef = 46, RULE_classOrInterfaceType = 47,
            RULE_primitiveType = 48, RULE_qualifiedIdentifierList = 49, RULE_formalParameters = 50,
            RULE_formalParameterDeclarations = 51, RULE_formalParameterVariables = 52,
            RULE_methodBody = 53, RULE_explicitConstructorInvocation = 54, RULE_qualifiedIdentifier = 55,
            RULE_literal = 56, RULE_integerLiteral = 57, RULE_booleanLiteral = 58,
            RULE_annotation = 59, RULE_annotationName = 60, RULE_elementValuePairs = 61,
            RULE_elementValuePair = 62, RULE_elementValue = 63, RULE_elementValueArrayInitializer = 64,
            RULE_annotationTypeDeclaration = 65, RULE_annotationTypeElement = 66,
            RULE_annotationMethod = 67, RULE_defaultValue = 68, RULE_localVariableDeclaration = 69,
            RULE_statement = 70, RULE_tryStatement = 71, RULE_catchClause = 72, RULE_resources = 73,
            RULE_resource = 74, RULE_switchBlock = 75, RULE_switchBlockStatementGroup = 76,
            RULE_switchLabel = 77, RULE_forControl = 78, RULE_forInit = 79, RULE_enhancedForControl = 80,
            RULE_forUpdate = 81, RULE_parExpression = 82, RULE_expressionList = 83,
            RULE_statementExpression = 84, RULE_constantExpression = 85, RULE_expression = 86,
            RULE_primary = 87, RULE_creator = 88, RULE_createdName = 89, RULE_innerCreator = 90,
            RULE_explicitGenericInvocation = 91, RULE_arrayCreatorRest = 92, RULE_classCreatorRest = 93,
            RULE_nonWildcardTypeArguments = 94, RULE_arguments = 95;
    public static final String[] ruleNames = {
            "compilationUnit", "packageDeclaration", "importDeclaration", "typeDeclaration",
            "classOrInterfaceDeclaration", "classDeclaration", "normalClassDeclaration",
            "typeParameters", "typeParameter", "bound", "enumDeclaration", "enumConstants",
            "interfaceDeclaration", "normalInterfaceDeclaration", "classBody", "interfaceBody",
            "classBodyDeclaration", "memberDecl", "fieldDeclaration", "block", "blockStatement",
            "enumConstant", "typeList", "typeArguments", "typeArgument", "interfaceMemberDecl",
            "methodDeclaration", "constructorDeclaration", "variableModifier", "interfaceMethodOrFieldDecl",
            "interfaceMethodOrFieldRest", "interfaceMethodDeclaratorRest", "interfaceGenericMethodDecl",
            "voidInterfaceMethodDeclaratorRest", "constantDeclarator", "variableDeclarators",
            "variableDeclarator", "constantDeclaratorsRest", "constantDeclaratorRest",
            "variableDeclaratorId", "variableInitializer", "arrayInitializer", "modifier",
            "packageOrTypeName", "enumConstantName", "typeName", "typeRef", "classOrInterfaceType",
            "primitiveType", "qualifiedIdentifierList", "formalParameters", "formalParameterDeclarations",
            "formalParameterVariables", "methodBody", "explicitConstructorInvocation",
            "qualifiedIdentifier", "literal", "integerLiteral", "booleanLiteral",
            "annotation", "annotationName", "elementValuePairs", "elementValuePair",
            "elementValue", "elementValueArrayInitializer", "annotationTypeDeclaration",
            "annotationTypeElement", "annotationMethod", "defaultValue", "localVariableDeclaration",
            "statement", "tryStatement", "catchClause", "resources", "resource", "switchBlock",
            "switchBlockStatementGroup", "switchLabel", "forControl", "forInit", "enhancedForControl",
            "forUpdate", "parExpression", "expressionList", "statementExpression",
            "constantExpression", "expression", "primary", "creator", "createdName",
            "innerCreator", "explicitGenericInvocation", "arrayCreatorRest", "classCreatorRest",
            "nonWildcardTypeArguments", "arguments"
    };

    @Override
    public String getGrammarFileName() {
        return "Java7.g4";
    }

    @Override
    public String[] getTokenNames() {
        return tokenNames;
    }

    @Override
    public String[] getRuleNames() {
        return ruleNames;
    }

    @Override
    public ATN getATN() {
        return _ATN;
    }

    public Java7Parser(TokenStream input) {
        super(input);
        _interp = new ParserATNSimulator(this, _ATN, _decisionToDFA, _sharedContextCache);
    }

    public static class CompilationUnitContext extends ParserRuleContext {
        public PackageDeclarationContext packageDeclaration() {
            return getRuleContext(PackageDeclarationContext.class, 0);
        }

        public ImportDeclarationContext importDeclaration(int i) {
            return getRuleContext(ImportDeclarationContext.class, i);
        }

        public TerminalNode EOF() {
            return getToken(Java7Parser.EOF, 0);
        }

        public List<TypeDeclarationContext> typeDeclaration() {
            return getRuleContexts(TypeDeclarationContext.class);
        }

        public List<ImportDeclarationContext> importDeclaration() {
            return getRuleContexts(ImportDeclarationContext.class);
        }

        public TypeDeclarationContext typeDeclaration(int i) {
            return getRuleContext(TypeDeclarationContext.class, i);
        }

        public CompilationUnitContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_compilationUnit;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterCompilationUnit(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitCompilationUnit(this);
        }
    }

    public final CompilationUnitContext compilationUnit() throws RecognitionException {
        CompilationUnitContext _localctx = new CompilationUnitContext(_ctx, getState());
        enterRule(_localctx, 0, RULE_compilationUnit);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(193);
                switch (getInterpreter().adaptivePredict(_input, 0, _ctx)) {
                    case 1: {
                        setState(192);
                        packageDeclaration();
                    }
                    break;
                }
                setState(198);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 68) {
                    {
                        {
                            setState(195);
                            importDeclaration();
                        }
                    }
                    setState(200);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(204);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 11) | (1L << 16) | (1L << 24) | (1L << 30) | (1L << 33) | (1L << 39) | (1L << 41) | (1L << 50) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (66 - 66)) | (1L << (73 - 66)) | (1L << (76 - 66)) | (1L << (77 - 66)) | (1L << (ENUM - 66)))) != 0)) {
                    {
                        {
                            setState(201);
                            typeDeclaration();
                        }
                    }
                    setState(206);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(207);
                match(EOF);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PackageDeclarationContext extends ParserRuleContext {
        public List<AnnotationContext> annotation() {
            return getRuleContexts(AnnotationContext.class);
        }

        public AnnotationContext annotation(int i) {
            return getRuleContext(AnnotationContext.class, i);
        }

        public QualifiedIdentifierContext qualifiedIdentifier() {
            return getRuleContext(QualifiedIdentifierContext.class, 0);
        }

        public PackageDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_packageDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterPackageDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitPackageDeclaration(this);
        }
    }

    public final PackageDeclarationContext packageDeclaration() throws RecognitionException {
        PackageDeclarationContext _localctx = new PackageDeclarationContext(_ctx, getState());
        enterRule(_localctx, 2, RULE_packageDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(212);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24) {
                    {
                        {
                            setState(209);
                            annotation();
                        }
                    }
                    setState(214);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(215);
                match(54);
                setState(216);
                qualifiedIdentifier();
                setState(217);
                match(77);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ImportDeclarationContext extends ParserRuleContext {
        public QualifiedIdentifierContext qualifiedIdentifier() {
            return getRuleContext(QualifiedIdentifierContext.class, 0);
        }

        public ImportDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_importDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterImportDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitImportDeclaration(this);
        }
    }

    public final ImportDeclarationContext importDeclaration() throws RecognitionException {
        ImportDeclarationContext _localctx = new ImportDeclarationContext(_ctx, getState());
        enterRule(_localctx, 4, RULE_importDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(219);
                match(68);
                setState(221);
                _la = _input.LA(1);
                if (_la == 66) {
                    {
                        setState(220);
                        match(66);
                    }
                }

                setState(223);
                qualifiedIdentifier();
                setState(226);
                _la = _input.LA(1);
                if (_la == 71) {
                    {
                        setState(224);
                        match(71);
                        setState(225);
                        match(3);
                    }
                }

                setState(228);
                match(77);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeDeclarationContext extends ParserRuleContext {
        public ClassOrInterfaceDeclarationContext classOrInterfaceDeclaration() {
            return getRuleContext(ClassOrInterfaceDeclarationContext.class, 0);
        }

        public TypeDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeDeclaration(this);
        }
    }

    public final TypeDeclarationContext typeDeclaration() throws RecognitionException {
        TypeDeclarationContext _localctx = new TypeDeclarationContext(_ctx, getState());
        enterRule(_localctx, 6, RULE_typeDeclaration);
        try {
            setState(232);
            switch (_input.LA(1)) {
                case 1:
                case 11:
                case 16:
                case 24:
                case 30:
                case 33:
                case 39:
                case 41:
                case 50:
                case 58:
                case 62:
                case 66:
                case 73:
                case 76:
                case ENUM:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(230);
                    classOrInterfaceDeclaration();
                }
                break;
                case 77:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(231);
                    match(77);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassOrInterfaceDeclarationContext extends ParserRuleContext {
        public List<ModifierContext> modifier() {
            return getRuleContexts(ModifierContext.class);
        }

        public InterfaceDeclarationContext interfaceDeclaration() {
            return getRuleContext(InterfaceDeclarationContext.class, 0);
        }

        public ModifierContext modifier(int i) {
            return getRuleContext(ModifierContext.class, i);
        }

        public ClassDeclarationContext classDeclaration() {
            return getRuleContext(ClassDeclarationContext.class, 0);
        }

        public ClassOrInterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classOrInterfaceDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassOrInterfaceDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassOrInterfaceDeclaration(this);
        }
    }

    public final ClassOrInterfaceDeclarationContext classOrInterfaceDeclaration() throws RecognitionException {
        ClassOrInterfaceDeclarationContext _localctx = new ClassOrInterfaceDeclarationContext(_ctx, getState());
        enterRule(_localctx, 8, RULE_classOrInterfaceDeclaration);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(237);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        {
                            {
                                setState(234);
                                modifier();
                            }
                        }
                    }
                    setState(239);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 7, _ctx);
                }
                setState(242);
                switch (_input.LA(1)) {
                    case 30:
                    case ENUM: {
                        setState(240);
                        classDeclaration();
                    }
                    break;
                    case 1:
                    case 24: {
                        setState(241);
                        interfaceDeclaration();
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassDeclarationContext extends ParserRuleContext {
        public NormalClassDeclarationContext normalClassDeclaration() {
            return getRuleContext(NormalClassDeclarationContext.class, 0);
        }

        public EnumDeclarationContext enumDeclaration() {
            return getRuleContext(EnumDeclarationContext.class, 0);
        }

        public ClassDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassDeclaration(this);
        }
    }

    public final ClassDeclarationContext classDeclaration() throws RecognitionException {
        ClassDeclarationContext _localctx = new ClassDeclarationContext(_ctx, getState());
        enterRule(_localctx, 10, RULE_classDeclaration);
        try {
            setState(246);
            switch (_input.LA(1)) {
                case 30:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(244);
                    normalClassDeclaration();
                }
                break;
                case ENUM:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(245);
                    enumDeclaration();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NormalClassDeclarationContext extends ParserRuleContext {
        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public ClassBodyContext classBody() {
            return getRuleContext(ClassBodyContext.class, 0);
        }

        public TypeListContext typeList() {
            return getRuleContext(TypeListContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public NormalClassDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_normalClassDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterNormalClassDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitNormalClassDeclaration(this);
        }
    }

    public final NormalClassDeclarationContext normalClassDeclaration() throws RecognitionException {
        NormalClassDeclarationContext _localctx = new NormalClassDeclarationContext(_ctx, getState());
        enterRule(_localctx, 12, RULE_normalClassDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(248);
                match(30);
                setState(249);
                match(Identifier);
                setState(251);
                _la = _input.LA(1);
                if (_la == 5) {
                    {
                        setState(250);
                        typeParameters();
                    }
                }

                setState(255);
                _la = _input.LA(1);
                if (_la == 60) {
                    {
                        setState(253);
                        match(60);
                        setState(254);
                        typeRef();
                    }
                }

                setState(259);
                _la = _input.LA(1);
                if (_la == 82) {
                    {
                        setState(257);
                        match(82);
                        setState(258);
                        typeList();
                    }
                }

                setState(261);
                classBody();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeParametersContext extends ParserRuleContext {
        public List<TypeParameterContext> typeParameter() {
            return getRuleContexts(TypeParameterContext.class);
        }

        public TypeParameterContext typeParameter(int i) {
            return getRuleContext(TypeParameterContext.class, i);
        }

        public TypeParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeParameters;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeParameters(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeParameters(this);
        }
    }

    public final TypeParametersContext typeParameters() throws RecognitionException {
        TypeParametersContext _localctx = new TypeParametersContext(_ctx, getState());
        enterRule(_localctx, 14, RULE_typeParameters);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(263);
                match(5);
                setState(264);
                typeParameter();
                setState(269);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(265);
                            match(42);
                            setState(266);
                            typeParameter();
                        }
                    }
                    setState(271);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(272);
                match(81);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeParameterContext extends ParserRuleContext {
        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public BoundContext bound() {
            return getRuleContext(BoundContext.class, 0);
        }

        public TypeParameterContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeParameter;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeParameter(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeParameter(this);
        }
    }

    public final TypeParameterContext typeParameter() throws RecognitionException {
        TypeParameterContext _localctx = new TypeParameterContext(_ctx, getState());
        enterRule(_localctx, 16, RULE_typeParameter);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(274);
                match(Identifier);
                setState(277);
                _la = _input.LA(1);
                if (_la == 60) {
                    {
                        setState(275);
                        match(60);
                        setState(276);
                        bound();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BoundContext extends ParserRuleContext {
        public ClassOrInterfaceTypeContext classOrInterfaceType(int i) {
            return getRuleContext(ClassOrInterfaceTypeContext.class, i);
        }

        public List<ClassOrInterfaceTypeContext> classOrInterfaceType() {
            return getRuleContexts(ClassOrInterfaceTypeContext.class);
        }

        public BoundContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_bound;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterBound(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitBound(this);
        }
    }

    public final BoundContext bound() throws RecognitionException {
        BoundContext _localctx = new BoundContext(_ctx, getState());
        enterRule(_localctx, 18, RULE_bound);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(279);
                classOrInterfaceType();
                setState(284);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 2) {
                    {
                        {
                            setState(280);
                            match(2);
                            setState(281);
                            classOrInterfaceType();
                        }
                    }
                    setState(286);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnumDeclarationContext extends ParserRuleContext {
        public ClassBodyDeclarationContext classBodyDeclaration(int i) {
            return getRuleContext(ClassBodyDeclarationContext.class, i);
        }

        public EnumConstantsContext enumConstants() {
            return getRuleContext(EnumConstantsContext.class, 0);
        }

        public TerminalNode ENUM() {
            return getToken(Java7Parser.ENUM, 0);
        }

        public List<ClassBodyDeclarationContext> classBodyDeclaration() {
            return getRuleContexts(ClassBodyDeclarationContext.class);
        }

        public TypeListContext typeList() {
            return getRuleContext(TypeListContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public EnumDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterEnumDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitEnumDeclaration(this);
        }
    }

    public final EnumDeclarationContext enumDeclaration() throws RecognitionException {
        EnumDeclarationContext _localctx = new EnumDeclarationContext(_ctx, getState());
        enterRule(_localctx, 20, RULE_enumDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(287);
                match(ENUM);
                setState(288);
                match(Identifier);
                setState(291);
                _la = _input.LA(1);
                if (_la == 82) {
                    {
                        setState(289);
                        match(82);
                        setState(290);
                        typeList();
                    }
                }

                setState(293);
                match(56);
                setState(295);
                _la = _input.LA(1);
                if (_la == 24 || _la == 42 || _la == Identifier) {
                    {
                        setState(294);
                        enumConstants();
                    }
                }

                setState(304);
                _la = _input.LA(1);
                if (_la == 77) {
                    {
                        setState(297);
                        match(77);
                        setState(301);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                        while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 24) | (1L << 30) | (1L << 33) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 49) | (1L << 50) | (1L << 53) | (1L << 56) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (66 - 66)) | (1L << (69 - 66)) | (1L << (73 - 66)) | (1L << (76 - 66)) | (1L << (77 - 66)) | (1L << (ENUM - 66)) | (1L << (Identifier - 66)))) != 0)) {
                            {
                                {
                                    setState(298);
                                    classBodyDeclaration();
                                }
                            }
                            setState(303);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                        }
                    }
                }

                setState(306);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnumConstantsContext extends ParserRuleContext {
        public List<EnumConstantContext> enumConstant() {
            return getRuleContexts(EnumConstantContext.class);
        }

        public EnumConstantContext enumConstant(int i) {
            return getRuleContext(EnumConstantContext.class, i);
        }

        public EnumConstantsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumConstants;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterEnumConstants(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitEnumConstants(this);
        }
    }

    public final EnumConstantsContext enumConstants() throws RecognitionException {
        EnumConstantsContext _localctx = new EnumConstantsContext(_ctx, getState());
        enterRule(_localctx, 22, RULE_enumConstants);
        int _la;
        try {
            int _alt;
            setState(320);
            switch (_input.LA(1)) {
                case 24:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(308);
                    enumConstant();
                    setState(313);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(309);
                                    match(42);
                                    setState(310);
                                    enumConstant();
                                }
                            }
                        }
                        setState(315);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 20, _ctx);
                    }
                    setState(317);
                    _la = _input.LA(1);
                    if (_la == 42) {
                        {
                            setState(316);
                            match(42);
                        }
                    }

                }
                break;
                case 42:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(319);
                    match(42);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceDeclarationContext extends ParserRuleContext {
        public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
            return getRuleContext(AnnotationTypeDeclarationContext.class, 0);
        }

        public NormalInterfaceDeclarationContext normalInterfaceDeclaration() {
            return getRuleContext(NormalInterfaceDeclarationContext.class, 0);
        }

        public InterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceDeclaration(this);
        }
    }

    public final InterfaceDeclarationContext interfaceDeclaration() throws RecognitionException {
        InterfaceDeclarationContext _localctx = new InterfaceDeclarationContext(_ctx, getState());
        enterRule(_localctx, 24, RULE_interfaceDeclaration);
        try {
            setState(324);
            switch (_input.LA(1)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(322);
                    normalInterfaceDeclaration();
                }
                break;
                case 24:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(323);
                    annotationTypeDeclaration();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NormalInterfaceDeclarationContext extends ParserRuleContext {
        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public InterfaceBodyContext interfaceBody() {
            return getRuleContext(InterfaceBodyContext.class, 0);
        }

        public TypeListContext typeList() {
            return getRuleContext(TypeListContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public NormalInterfaceDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_normalInterfaceDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterNormalInterfaceDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitNormalInterfaceDeclaration(this);
        }
    }

    public final NormalInterfaceDeclarationContext normalInterfaceDeclaration() throws RecognitionException {
        NormalInterfaceDeclarationContext _localctx = new NormalInterfaceDeclarationContext(_ctx, getState());
        enterRule(_localctx, 26, RULE_normalInterfaceDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(326);
                match(1);
                setState(327);
                match(Identifier);
                setState(329);
                _la = _input.LA(1);
                if (_la == 5) {
                    {
                        setState(328);
                        typeParameters();
                    }
                }

                setState(333);
                _la = _input.LA(1);
                if (_la == 60) {
                    {
                        setState(331);
                        match(60);
                        setState(332);
                        typeList();
                    }
                }

                setState(335);
                interfaceBody();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassBodyContext extends ParserRuleContext {
        public ClassBodyDeclarationContext classBodyDeclaration(int i) {
            return getRuleContext(ClassBodyDeclarationContext.class, i);
        }

        public List<ClassBodyDeclarationContext> classBodyDeclaration() {
            return getRuleContexts(ClassBodyDeclarationContext.class);
        }

        public ClassBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classBody;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassBody(this);
        }
    }

    public final ClassBodyContext classBody() throws RecognitionException {
        ClassBodyContext _localctx = new ClassBodyContext(_ctx, getState());
        enterRule(_localctx, 28, RULE_classBody);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(337);
                match(56);
                setState(341);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 24) | (1L << 30) | (1L << 33) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 49) | (1L << 50) | (1L << 53) | (1L << 56) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (66 - 66)) | (1L << (69 - 66)) | (1L << (73 - 66)) | (1L << (76 - 66)) | (1L << (77 - 66)) | (1L << (ENUM - 66)) | (1L << (Identifier - 66)))) != 0)) {
                    {
                        {
                            setState(338);
                            classBodyDeclaration();
                        }
                    }
                    setState(343);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(344);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceBodyContext extends ParserRuleContext {
        public List<ModifierContext> modifier() {
            return getRuleContexts(ModifierContext.class);
        }

        public InterfaceMemberDeclContext interfaceMemberDecl(int i) {
            return getRuleContext(InterfaceMemberDeclContext.class, i);
        }

        public ModifierContext modifier(int i) {
            return getRuleContext(ModifierContext.class, i);
        }

        public List<InterfaceMemberDeclContext> interfaceMemberDecl() {
            return getRuleContexts(InterfaceMemberDeclContext.class);
        }

        public InterfaceBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceBody;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceBody(this);
        }
    }

    public final InterfaceBodyContext interfaceBody() throws RecognitionException {
        InterfaceBodyContext _localctx = new InterfaceBodyContext(_ctx, getState());
        enterRule(_localctx, 30, RULE_interfaceBody);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(346);
                match(56);
                setState(357);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 5) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 24) | (1L << 30) | (1L << 33) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 49) | (1L << 50) | (1L << 53) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (66 - 66)) | (1L << (69 - 66)) | (1L << (73 - 66)) | (1L << (76 - 66)) | (1L << (77 - 66)) | (1L << (ENUM - 66)) | (1L << (Identifier - 66)))) != 0)) {
                    {
                        setState(355);
                        switch (_input.LA(1)) {
                            case 1:
                            case 5:
                            case 10:
                            case 11:
                            case 12:
                            case 14:
                            case 15:
                            case 16:
                            case 24:
                            case 30:
                            case 33:
                            case 35:
                            case 36:
                            case 39:
                            case 41:
                            case 49:
                            case 50:
                            case 53:
                            case 58:
                            case 62:
                            case 66:
                            case 69:
                            case 73:
                            case 76:
                            case ENUM:
                            case Identifier: {
                                setState(350);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input, 27, _ctx);
                                while (_alt != 2 && _alt != -1) {
                                    if (_alt == 1) {
                                        {
                                            {
                                                setState(347);
                                                modifier();
                                            }
                                        }
                                    }
                                    setState(352);
                                    _errHandler.sync(this);
                                    _alt = getInterpreter().adaptivePredict(_input, 27, _ctx);
                                }
                                setState(353);
                                interfaceMemberDecl();
                            }
                            break;
                            case 77: {
                                setState(354);
                                match(77);
                            }
                            break;
                            default:
                                throw new NoViableAltException(this);
                        }
                    }
                    setState(359);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(360);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassBodyDeclarationContext extends ParserRuleContext {
        public List<ModifierContext> modifier() {
            return getRuleContexts(ModifierContext.class);
        }

        public MemberDeclContext memberDecl() {
            return getRuleContext(MemberDeclContext.class, 0);
        }

        public ModifierContext modifier(int i) {
            return getRuleContext(ModifierContext.class, i);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public ClassBodyDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classBodyDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassBodyDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassBodyDeclaration(this);
        }
    }

    public final ClassBodyDeclarationContext classBodyDeclaration() throws RecognitionException {
        ClassBodyDeclarationContext _localctx = new ClassBodyDeclarationContext(_ctx, getState());
        enterRule(_localctx, 32, RULE_classBodyDeclaration);
        int _la;
        try {
            int _alt;
            setState(374);
            switch (getInterpreter().adaptivePredict(_input, 32, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(362);
                    match(77);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(366);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(363);
                                    modifier();
                                }
                            }
                        }
                        setState(368);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 30, _ctx);
                    }
                    setState(369);
                    memberDecl();
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(371);
                    _la = _input.LA(1);
                    if (_la == 66) {
                        {
                            setState(370);
                            match(66);
                        }
                    }

                    setState(373);
                    block();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MemberDeclContext extends ParserRuleContext {
        public InterfaceDeclarationContext interfaceDeclaration() {
            return getRuleContext(InterfaceDeclarationContext.class, 0);
        }

        public ConstructorDeclarationContext constructorDeclaration() {
            return getRuleContext(ConstructorDeclarationContext.class, 0);
        }

        public FieldDeclarationContext fieldDeclaration() {
            return getRuleContext(FieldDeclarationContext.class, 0);
        }

        public ClassDeclarationContext classDeclaration() {
            return getRuleContext(ClassDeclarationContext.class, 0);
        }

        public MethodDeclarationContext methodDeclaration() {
            return getRuleContext(MethodDeclarationContext.class, 0);
        }

        public MemberDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_memberDecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterMemberDecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitMemberDecl(this);
        }
    }

    public final MemberDeclContext memberDecl() throws RecognitionException {
        MemberDeclContext _localctx = new MemberDeclContext(_ctx, getState());
        enterRule(_localctx, 34, RULE_memberDecl);
        try {
            setState(381);
            switch (getInterpreter().adaptivePredict(_input, 33, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(376);
                    methodDeclaration();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(377);
                    fieldDeclaration();
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(378);
                    constructorDeclaration();
                }
                break;

                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(379);
                    interfaceDeclaration();
                }
                break;

                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(380);
                    classDeclaration();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FieldDeclarationContext extends ParserRuleContext {
        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public VariableDeclaratorsContext variableDeclarators() {
            return getRuleContext(VariableDeclaratorsContext.class, 0);
        }

        public FieldDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_fieldDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterFieldDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitFieldDeclaration(this);
        }
    }

    public final FieldDeclarationContext fieldDeclaration() throws RecognitionException {
        FieldDeclarationContext _localctx = new FieldDeclarationContext(_ctx, getState());
        enterRule(_localctx, 36, RULE_fieldDeclaration);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(383);
                typeRef();
                setState(384);
                variableDeclarators();
                setState(385);
                match(77);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BlockContext extends ParserRuleContext {
        public BlockStatementContext blockStatement(int i) {
            return getRuleContext(BlockStatementContext.class, i);
        }

        public List<BlockStatementContext> blockStatement() {
            return getRuleContexts(BlockStatementContext.class);
        }

        public BlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_block;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterBlock(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitBlock(this);
        }
    }

    public final BlockContext block() throws RecognitionException {
        BlockContext _localctx = new BlockContext(_ctx, getState());
        enterRule(_localctx, 38, RULE_block);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(387);
                match(56);
                setState(391);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 6) | (1L << 7) | (1L << 8) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 18) | (1L << 19) | (1L << 23) | (1L << 24) | (1L << 26) | (1L << 29) | (1L << 30) | (1L << 33) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 43) | (1L << 44) | (1L << 45) | (1L << 47) | (1L << 49) | (1L << 50) | (1L << 52) | (1L << 53) | (1L << 56) | (1L << 57) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (66 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (73 - 65)) | (1L << (74 - 65)) | (1L << (75 - 65)) | (1L << (76 - 65)) | (1L << (77 - 65)) | (1L << (79 - 65)) | (1L << (84 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (ENUM - 65)) | (1L << (ASSERT - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        {
                            setState(388);
                            blockStatement();
                        }
                    }
                    setState(393);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(394);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BlockStatementContext extends ParserRuleContext {
        public StatementContext statement() {
            return getRuleContext(StatementContext.class, 0);
        }

        public ClassOrInterfaceDeclarationContext classOrInterfaceDeclaration() {
            return getRuleContext(ClassOrInterfaceDeclarationContext.class, 0);
        }

        public LocalVariableDeclarationContext localVariableDeclaration() {
            return getRuleContext(LocalVariableDeclarationContext.class, 0);
        }

        public BlockStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_blockStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterBlockStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitBlockStatement(this);
        }
    }

    public final BlockStatementContext blockStatement() throws RecognitionException {
        BlockStatementContext _localctx = new BlockStatementContext(_ctx, getState());
        enterRule(_localctx, 40, RULE_blockStatement);
        try {
            setState(401);
            switch (getInterpreter().adaptivePredict(_input, 35, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(396);
                    localVariableDeclaration();
                    setState(397);
                    match(77);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(399);
                    classOrInterfaceDeclaration();
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(400);
                    statement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnumConstantContext extends ParserRuleContext {
        public List<AnnotationContext> annotation() {
            return getRuleContexts(AnnotationContext.class);
        }

        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public AnnotationContext annotation(int i) {
            return getRuleContext(AnnotationContext.class, i);
        }

        public ClassBodyContext classBody() {
            return getRuleContext(ClassBodyContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public EnumConstantContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumConstant;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterEnumConstant(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitEnumConstant(this);
        }
    }

    public final EnumConstantContext enumConstant() throws RecognitionException {
        EnumConstantContext _localctx = new EnumConstantContext(_ctx, getState());
        enterRule(_localctx, 42, RULE_enumConstant);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(406);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24) {
                    {
                        {
                            setState(403);
                            annotation();
                        }
                    }
                    setState(408);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(409);
                match(Identifier);
                setState(411);
                _la = _input.LA(1);
                if (_la == 45) {
                    {
                        setState(410);
                        arguments();
                    }
                }

                setState(414);
                _la = _input.LA(1);
                if (_la == 56) {
                    {
                        setState(413);
                        classBody();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeListContext extends ParserRuleContext {
        public ClassOrInterfaceTypeContext classOrInterfaceType(int i) {
            return getRuleContext(ClassOrInterfaceTypeContext.class, i);
        }

        public List<ClassOrInterfaceTypeContext> classOrInterfaceType() {
            return getRuleContexts(ClassOrInterfaceTypeContext.class);
        }

        public TypeListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeList;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeList(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeList(this);
        }
    }

    public final TypeListContext typeList() throws RecognitionException {
        TypeListContext _localctx = new TypeListContext(_ctx, getState());
        enterRule(_localctx, 44, RULE_typeList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(416);
                classOrInterfaceType();
                setState(421);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(417);
                            match(42);
                            setState(418);
                            classOrInterfaceType();
                        }
                    }
                    setState(423);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeArgumentsContext extends ParserRuleContext {
        public TypeArgumentContext typeArgument(int i) {
            return getRuleContext(TypeArgumentContext.class, i);
        }

        public List<TypeArgumentContext> typeArgument() {
            return getRuleContexts(TypeArgumentContext.class);
        }

        public TypeArgumentsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeArguments;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeArguments(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeArguments(this);
        }
    }

    public final TypeArgumentsContext typeArguments() throws RecognitionException {
        TypeArgumentsContext _localctx = new TypeArgumentsContext(_ctx, getState());
        enterRule(_localctx, 46, RULE_typeArguments);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(424);
                match(5);
                setState(425);
                typeArgument();
                setState(430);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(426);
                            match(42);
                            setState(427);
                            typeArgument();
                        }
                    }
                    setState(432);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(433);
                match(81);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeArgumentContext extends ParserRuleContext {
        public ClassOrInterfaceTypeContext classOrInterfaceType() {
            return getRuleContext(ClassOrInterfaceTypeContext.class, 0);
        }

        public TypeArgumentContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeArgument;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeArgument(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeArgument(this);
        }
    }

    public final TypeArgumentContext typeArgument() throws RecognitionException {
        TypeArgumentContext _localctx = new TypeArgumentContext(_ctx, getState());
        enterRule(_localctx, 48, RULE_typeArgument);
        int _la;
        try {
            setState(448);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(435);
                    classOrInterfaceType();
                    setState(440);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == 4) {
                        {
                            {
                                setState(436);
                                match(4);
                                setState(437);
                                match(37);
                            }
                        }
                        setState(442);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                case 51:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(443);
                    match(51);
                    setState(446);
                    _la = _input.LA(1);
                    if (_la == 18 || _la == 60) {
                        {
                            setState(444);
                            _la = _input.LA(1);
                            if (!(_la == 18 || _la == 60)) {
                                _errHandler.recoverInline(this);
                            }
                            consume();
                            setState(445);
                            classOrInterfaceType();
                        }
                    }

                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceMemberDeclContext extends ParserRuleContext {
        public InterfaceDeclarationContext interfaceDeclaration() {
            return getRuleContext(InterfaceDeclarationContext.class, 0);
        }

        public InterfaceGenericMethodDeclContext interfaceGenericMethodDecl() {
            return getRuleContext(InterfaceGenericMethodDeclContext.class, 0);
        }

        public InterfaceMethodOrFieldDeclContext interfaceMethodOrFieldDecl() {
            return getRuleContext(InterfaceMethodOrFieldDeclContext.class, 0);
        }

        public ClassDeclarationContext classDeclaration() {
            return getRuleContext(ClassDeclarationContext.class, 0);
        }

        public VoidInterfaceMethodDeclaratorRestContext voidInterfaceMethodDeclaratorRest() {
            return getRuleContext(VoidInterfaceMethodDeclaratorRestContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public InterfaceMemberDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceMemberDecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceMemberDecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceMemberDecl(this);
        }
    }

    public final InterfaceMemberDeclContext interfaceMemberDecl() throws RecognitionException {
        InterfaceMemberDeclContext _localctx = new InterfaceMemberDeclContext(_ctx, getState());
        enterRule(_localctx, 50, RULE_interfaceMemberDecl);
        try {
            setState(457);
            switch (_input.LA(1)) {
                case 10:
                case 12:
                case 14:
                case 15:
                case 35:
                case 36:
                case 49:
                case 69:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(450);
                    interfaceMethodOrFieldDecl();
                }
                break;
                case 5:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(451);
                    interfaceGenericMethodDecl();
                }
                break;
                case 53:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(452);
                    match(53);
                    setState(453);
                    match(Identifier);
                    setState(454);
                    voidInterfaceMethodDeclaratorRest();
                }
                break;
                case 1:
                case 24:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(455);
                    interfaceDeclaration();
                }
                break;
                case 30:
                case ENUM:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(456);
                    classDeclaration();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MethodDeclarationContext extends ParserRuleContext {
        public QualifiedIdentifierListContext qualifiedIdentifierList() {
            return getRuleContext(QualifiedIdentifierListContext.class, 0);
        }

        public MethodBodyContext methodBody() {
            return getRuleContext(MethodBodyContext.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public FormalParametersContext formalParameters() {
            return getRuleContext(FormalParametersContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public MethodDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_methodDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterMethodDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitMethodDeclaration(this);
        }
    }

    public final MethodDeclarationContext methodDeclaration() throws RecognitionException {
        MethodDeclarationContext _localctx = new MethodDeclarationContext(_ctx, getState());
        enterRule(_localctx, 52, RULE_methodDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(460);
                _la = _input.LA(1);
                if (_la == 5) {
                    {
                        setState(459);
                        typeParameters();
                    }
                }

                setState(464);
                switch (_input.LA(1)) {
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 35:
                    case 36:
                    case 49:
                    case 69:
                    case Identifier: {
                        setState(462);
                        typeRef();
                    }
                    break;
                    case 53: {
                        setState(463);
                        match(53);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(466);
                match(Identifier);
                setState(467);
                formalParameters();
                setState(472);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 4) {
                    {
                        {
                            setState(468);
                            match(4);
                            setState(469);
                            match(37);
                        }
                    }
                    setState(474);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(477);
                _la = _input.LA(1);
                if (_la == 27) {
                    {
                        setState(475);
                        match(27);
                        setState(476);
                        qualifiedIdentifierList();
                    }
                }

                setState(481);
                switch (_input.LA(1)) {
                    case 56: {
                        setState(479);
                        methodBody();
                    }
                    break;
                    case 77: {
                        setState(480);
                        match(77);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ConstructorDeclarationContext extends ParserRuleContext {
        public BlockStatementContext blockStatement(int i) {
            return getRuleContext(BlockStatementContext.class, i);
        }

        public ExplicitConstructorInvocationContext explicitConstructorInvocation() {
            return getRuleContext(ExplicitConstructorInvocationContext.class, 0);
        }

        public QualifiedIdentifierListContext qualifiedIdentifierList() {
            return getRuleContext(QualifiedIdentifierListContext.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public FormalParametersContext formalParameters() {
            return getRuleContext(FormalParametersContext.class, 0);
        }

        public List<BlockStatementContext> blockStatement() {
            return getRuleContexts(BlockStatementContext.class);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ConstructorDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constructorDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterConstructorDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitConstructorDeclaration(this);
        }
    }

    public final ConstructorDeclarationContext constructorDeclaration() throws RecognitionException {
        ConstructorDeclarationContext _localctx = new ConstructorDeclarationContext(_ctx, getState());
        enterRule(_localctx, 54, RULE_constructorDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(484);
                _la = _input.LA(1);
                if (_la == 5) {
                    {
                        setState(483);
                        typeParameters();
                    }
                }

                setState(486);
                match(Identifier);
                setState(487);
                formalParameters();
                setState(490);
                _la = _input.LA(1);
                if (_la == 27) {
                    {
                        setState(488);
                        match(27);
                        setState(489);
                        qualifiedIdentifierList();
                    }
                }

                setState(492);
                match(56);
                setState(494);
                switch (getInterpreter().adaptivePredict(_input, 52, _ctx)) {
                    case 1: {
                        setState(493);
                        explicitConstructorInvocation();
                    }
                    break;
                }
                setState(499);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 6) | (1L << 7) | (1L << 8) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 18) | (1L << 19) | (1L << 23) | (1L << 24) | (1L << 26) | (1L << 29) | (1L << 30) | (1L << 33) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 43) | (1L << 44) | (1L << 45) | (1L << 47) | (1L << 49) | (1L << 50) | (1L << 52) | (1L << 53) | (1L << 56) | (1L << 57) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (66 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (73 - 65)) | (1L << (74 - 65)) | (1L << (75 - 65)) | (1L << (76 - 65)) | (1L << (77 - 65)) | (1L << (79 - 65)) | (1L << (84 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (ENUM - 65)) | (1L << (ASSERT - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        {
                            setState(496);
                            blockStatement();
                        }
                    }
                    setState(501);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(502);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableModifierContext extends ParserRuleContext {
        public AnnotationContext annotation() {
            return getRuleContext(AnnotationContext.class, 0);
        }

        public VariableModifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableModifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterVariableModifier(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitVariableModifier(this);
        }
    }

    public final VariableModifierContext variableModifier() throws RecognitionException {
        VariableModifierContext _localctx = new VariableModifierContext(_ctx, getState());
        enterRule(_localctx, 56, RULE_variableModifier);
        try {
            setState(506);
            switch (_input.LA(1)) {
                case 62:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(504);
                    match(62);
                }
                break;
                case 24:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(505);
                    annotation();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceMethodOrFieldDeclContext extends ParserRuleContext {
        public InterfaceMethodOrFieldRestContext interfaceMethodOrFieldRest() {
            return getRuleContext(InterfaceMethodOrFieldRestContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public InterfaceMethodOrFieldDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceMethodOrFieldDecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceMethodOrFieldDecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceMethodOrFieldDecl(this);
        }
    }

    public final InterfaceMethodOrFieldDeclContext interfaceMethodOrFieldDecl() throws RecognitionException {
        InterfaceMethodOrFieldDeclContext _localctx = new InterfaceMethodOrFieldDeclContext(_ctx, getState());
        enterRule(_localctx, 58, RULE_interfaceMethodOrFieldDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(508);
                typeRef();
                setState(509);
                match(Identifier);
                setState(510);
                interfaceMethodOrFieldRest();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceMethodOrFieldRestContext extends ParserRuleContext {
        public InterfaceMethodDeclaratorRestContext interfaceMethodDeclaratorRest() {
            return getRuleContext(InterfaceMethodDeclaratorRestContext.class, 0);
        }

        public ConstantDeclaratorsRestContext constantDeclaratorsRest() {
            return getRuleContext(ConstantDeclaratorsRestContext.class, 0);
        }

        public InterfaceMethodOrFieldRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceMethodOrFieldRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceMethodOrFieldRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceMethodOrFieldRest(this);
        }
    }

    public final InterfaceMethodOrFieldRestContext interfaceMethodOrFieldRest() throws RecognitionException {
        InterfaceMethodOrFieldRestContext _localctx = new InterfaceMethodOrFieldRestContext(_ctx, getState());
        enterRule(_localctx, 60, RULE_interfaceMethodOrFieldRest);
        try {
            setState(516);
            switch (_input.LA(1)) {
                case 4:
                case 25:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(512);
                    constantDeclaratorsRest();
                    setState(513);
                    match(77);
                }
                break;
                case 45:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(515);
                    interfaceMethodDeclaratorRest();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceMethodDeclaratorRestContext extends ParserRuleContext {
        public QualifiedIdentifierListContext qualifiedIdentifierList() {
            return getRuleContext(QualifiedIdentifierListContext.class, 0);
        }

        public FormalParametersContext formalParameters() {
            return getRuleContext(FormalParametersContext.class, 0);
        }

        public InterfaceMethodDeclaratorRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceMethodDeclaratorRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceMethodDeclaratorRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceMethodDeclaratorRest(this);
        }
    }

    public final InterfaceMethodDeclaratorRestContext interfaceMethodDeclaratorRest() throws RecognitionException {
        InterfaceMethodDeclaratorRestContext _localctx = new InterfaceMethodDeclaratorRestContext(_ctx, getState());
        enterRule(_localctx, 62, RULE_interfaceMethodDeclaratorRest);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(518);
                formalParameters();
                setState(523);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 4) {
                    {
                        {
                            setState(519);
                            match(4);
                            setState(520);
                            match(37);
                        }
                    }
                    setState(525);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(528);
                _la = _input.LA(1);
                if (_la == 27) {
                    {
                        setState(526);
                        match(27);
                        setState(527);
                        qualifiedIdentifierList();
                    }
                }

                setState(530);
                match(77);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InterfaceGenericMethodDeclContext extends ParserRuleContext {
        public InterfaceMethodDeclaratorRestContext interfaceMethodDeclaratorRest() {
            return getRuleContext(InterfaceMethodDeclaratorRestContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public TypeParametersContext typeParameters() {
            return getRuleContext(TypeParametersContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public InterfaceGenericMethodDeclContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_interfaceGenericMethodDecl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInterfaceGenericMethodDecl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInterfaceGenericMethodDecl(this);
        }
    }

    public final InterfaceGenericMethodDeclContext interfaceGenericMethodDecl() throws RecognitionException {
        InterfaceGenericMethodDeclContext _localctx = new InterfaceGenericMethodDeclContext(_ctx, getState());
        enterRule(_localctx, 64, RULE_interfaceGenericMethodDecl);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(532);
                typeParameters();
                setState(535);
                switch (_input.LA(1)) {
                    case 10:
                    case 12:
                    case 14:
                    case 15:
                    case 35:
                    case 36:
                    case 49:
                    case 69:
                    case Identifier: {
                        setState(533);
                        typeRef();
                    }
                    break;
                    case 53: {
                        setState(534);
                        match(53);
                    }
                    break;
                    default:
                        throw new NoViableAltException(this);
                }
                setState(537);
                match(Identifier);
                setState(538);
                interfaceMethodDeclaratorRest();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VoidInterfaceMethodDeclaratorRestContext extends ParserRuleContext {
        public QualifiedIdentifierListContext qualifiedIdentifierList() {
            return getRuleContext(QualifiedIdentifierListContext.class, 0);
        }

        public FormalParametersContext formalParameters() {
            return getRuleContext(FormalParametersContext.class, 0);
        }

        public VoidInterfaceMethodDeclaratorRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_voidInterfaceMethodDeclaratorRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener)
                ((Java7Listener) listener).enterVoidInterfaceMethodDeclaratorRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener)
                ((Java7Listener) listener).exitVoidInterfaceMethodDeclaratorRest(this);
        }
    }

    public final VoidInterfaceMethodDeclaratorRestContext voidInterfaceMethodDeclaratorRest() throws RecognitionException {
        VoidInterfaceMethodDeclaratorRestContext _localctx = new VoidInterfaceMethodDeclaratorRestContext(_ctx, getState());
        enterRule(_localctx, 66, RULE_voidInterfaceMethodDeclaratorRest);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(540);
                formalParameters();
                setState(543);
                _la = _input.LA(1);
                if (_la == 27) {
                    {
                        setState(541);
                        match(27);
                        setState(542);
                        qualifiedIdentifierList();
                    }
                }

                setState(545);
                match(77);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ConstantDeclaratorContext extends ParserRuleContext {
        public ConstantDeclaratorRestContext constantDeclaratorRest() {
            return getRuleContext(ConstantDeclaratorRestContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ConstantDeclaratorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constantDeclarator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterConstantDeclarator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitConstantDeclarator(this);
        }
    }

    public final ConstantDeclaratorContext constantDeclarator() throws RecognitionException {
        ConstantDeclaratorContext _localctx = new ConstantDeclaratorContext(_ctx, getState());
        enterRule(_localctx, 68, RULE_constantDeclarator);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(547);
                match(Identifier);
                setState(548);
                constantDeclaratorRest();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableDeclaratorsContext extends ParserRuleContext {
        public List<VariableDeclaratorContext> variableDeclarator() {
            return getRuleContexts(VariableDeclaratorContext.class);
        }

        public VariableDeclaratorContext variableDeclarator(int i) {
            return getRuleContext(VariableDeclaratorContext.class, i);
        }

        public VariableDeclaratorsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclarators;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterVariableDeclarators(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitVariableDeclarators(this);
        }
    }

    public final VariableDeclaratorsContext variableDeclarators() throws RecognitionException {
        VariableDeclaratorsContext _localctx = new VariableDeclaratorsContext(_ctx, getState());
        enterRule(_localctx, 70, RULE_variableDeclarators);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(550);
                variableDeclarator();
                setState(555);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(551);
                            match(42);
                            setState(552);
                            variableDeclarator();
                        }
                    }
                    setState(557);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableDeclaratorContext extends ParserRuleContext {
        public VariableDeclaratorIdContext variableDeclaratorId() {
            return getRuleContext(VariableDeclaratorIdContext.class, 0);
        }

        public VariableInitializerContext variableInitializer() {
            return getRuleContext(VariableInitializerContext.class, 0);
        }

        public VariableDeclaratorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclarator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterVariableDeclarator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitVariableDeclarator(this);
        }
    }

    public final VariableDeclaratorContext variableDeclarator() throws RecognitionException {
        VariableDeclaratorContext _localctx = new VariableDeclaratorContext(_ctx, getState());
        enterRule(_localctx, 72, RULE_variableDeclarator);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(558);
                variableDeclaratorId();
                setState(561);
                _la = _input.LA(1);
                if (_la == 25) {
                    {
                        setState(559);
                        match(25);
                        setState(560);
                        variableInitializer();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ConstantDeclaratorsRestContext extends ParserRuleContext {
        public List<ConstantDeclaratorContext> constantDeclarator() {
            return getRuleContexts(ConstantDeclaratorContext.class);
        }

        public ConstantDeclaratorRestContext constantDeclaratorRest() {
            return getRuleContext(ConstantDeclaratorRestContext.class, 0);
        }

        public ConstantDeclaratorContext constantDeclarator(int i) {
            return getRuleContext(ConstantDeclaratorContext.class, i);
        }

        public ConstantDeclaratorsRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constantDeclaratorsRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterConstantDeclaratorsRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitConstantDeclaratorsRest(this);
        }
    }

    public final ConstantDeclaratorsRestContext constantDeclaratorsRest() throws RecognitionException {
        ConstantDeclaratorsRestContext _localctx = new ConstantDeclaratorsRestContext(_ctx, getState());
        enterRule(_localctx, 74, RULE_constantDeclaratorsRest);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(563);
                constantDeclaratorRest();
                setState(568);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(564);
                            match(42);
                            setState(565);
                            constantDeclarator();
                        }
                    }
                    setState(570);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ConstantDeclaratorRestContext extends ParserRuleContext {
        public VariableInitializerContext variableInitializer() {
            return getRuleContext(VariableInitializerContext.class, 0);
        }

        public ConstantDeclaratorRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constantDeclaratorRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterConstantDeclaratorRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitConstantDeclaratorRest(this);
        }
    }

    public final ConstantDeclaratorRestContext constantDeclaratorRest() throws RecognitionException {
        ConstantDeclaratorRestContext _localctx = new ConstantDeclaratorRestContext(_ctx, getState());
        enterRule(_localctx, 76, RULE_constantDeclaratorRest);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(575);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 4) {
                    {
                        {
                            setState(571);
                            match(4);
                            setState(572);
                            match(37);
                        }
                    }
                    setState(577);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(578);
                match(25);
                setState(579);
                variableInitializer();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableDeclaratorIdContext extends ParserRuleContext {
        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public VariableDeclaratorIdContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableDeclaratorId;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterVariableDeclaratorId(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitVariableDeclaratorId(this);
        }
    }

    public final VariableDeclaratorIdContext variableDeclaratorId() throws RecognitionException {
        VariableDeclaratorIdContext _localctx = new VariableDeclaratorIdContext(_ctx, getState());
        enterRule(_localctx, 78, RULE_variableDeclaratorId);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(581);
                match(Identifier);
                setState(586);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 4) {
                    {
                        {
                            setState(582);
                            match(4);
                            setState(583);
                            match(37);
                        }
                    }
                    setState(588);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class VariableInitializerContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public ArrayInitializerContext arrayInitializer() {
            return getRuleContext(ArrayInitializerContext.class, 0);
        }

        public VariableInitializerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_variableInitializer;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterVariableInitializer(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitVariableInitializer(this);
        }
    }

    public final VariableInitializerContext variableInitializer() throws RecognitionException {
        VariableInitializerContext _localctx = new VariableInitializerContext(_ctx, getState());
        enterRule(_localctx, 80, RULE_variableInitializer);
        try {
            setState(591);
            switch (_input.LA(1)) {
                case 56:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(589);
                    arrayInitializer();
                }
                break;
                case 6:
                case 7:
                case 10:
                case 12:
                case 14:
                case 15:
                case 18:
                case 26:
                case 29:
                case 34:
                case 35:
                case 36:
                case 44:
                case 45:
                case 49:
                case 53:
                case 65:
                case 67:
                case 69:
                case 72:
                case 79:
                case 88:
                case HexLiteral:
                case DecimalLiteral:
                case OctalLiteral:
                case BinaryLiteral:
                case FloatingPointLiteral:
                case CharacterLiteral:
                case StringLiteral:
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(590);
                    expression(0);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ArrayInitializerContext extends ParserRuleContext {
        public VariableInitializerContext variableInitializer(int i) {
            return getRuleContext(VariableInitializerContext.class, i);
        }

        public List<VariableInitializerContext> variableInitializer() {
            return getRuleContexts(VariableInitializerContext.class);
        }

        public ArrayInitializerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayInitializer;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterArrayInitializer(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitArrayInitializer(this);
        }
    }

    public final ArrayInitializerContext arrayInitializer() throws RecognitionException {
        ArrayInitializerContext _localctx = new ArrayInitializerContext(_ctx, getState());
        enterRule(_localctx, 82, RULE_arrayInitializer);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(593);
                match(56);
                setState(605);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53) | (1L << 56))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        setState(594);
                        variableInitializer();
                        setState(599);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 66, _ctx);
                        while (_alt != 2 && _alt != -1) {
                            if (_alt == 1) {
                                {
                                    {
                                        setState(595);
                                        match(42);
                                        setState(596);
                                        variableInitializer();
                                    }
                                }
                            }
                            setState(601);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 66, _ctx);
                        }
                        setState(603);
                        _la = _input.LA(1);
                        if (_la == 42) {
                            {
                                setState(602);
                                match(42);
                            }
                        }

                    }
                }

                setState(607);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ModifierContext extends ParserRuleContext {
        public AnnotationContext annotation() {
            return getRuleContext(AnnotationContext.class, 0);
        }

        public ModifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_modifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterModifier(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitModifier(this);
        }
    }

    public final ModifierContext modifier() throws RecognitionException {
        ModifierContext _localctx = new ModifierContext(_ctx, getState());
        enterRule(_localctx, 84, RULE_modifier);
        try {
            setState(621);
            switch (_input.LA(1)) {
                case 24:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(609);
                    annotation();
                }
                break;
                case 39:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(610);
                    match(39);
                }
                break;
                case 73:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(611);
                    match(73);
                }
                break;
                case 50:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(612);
                    match(50);
                }
                break;
                case 66:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(613);
                    match(66);
                }
                break;
                case 11:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(614);
                    match(11);
                }
                break;
                case 62:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(615);
                    match(62);
                }
                break;
                case 58:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(616);
                    match(58);
                }
                break;
                case 41:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(617);
                    match(41);
                }
                break;
                case 33:
                    enterOuterAlt(_localctx, 10);
                {
                    setState(618);
                    match(33);
                }
                break;
                case 76:
                    enterOuterAlt(_localctx, 11);
                {
                    setState(619);
                    match(76);
                }
                break;
                case 16:
                    enterOuterAlt(_localctx, 12);
                {
                    setState(620);
                    match(16);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PackageOrTypeNameContext extends ParserRuleContext {
        public QualifiedIdentifierContext qualifiedIdentifier() {
            return getRuleContext(QualifiedIdentifierContext.class, 0);
        }

        public PackageOrTypeNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_packageOrTypeName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterPackageOrTypeName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitPackageOrTypeName(this);
        }
    }

    public final PackageOrTypeNameContext packageOrTypeName() throws RecognitionException {
        PackageOrTypeNameContext _localctx = new PackageOrTypeNameContext(_ctx, getState());
        enterRule(_localctx, 86, RULE_packageOrTypeName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(623);
                qualifiedIdentifier();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnumConstantNameContext extends ParserRuleContext {
        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public EnumConstantNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enumConstantName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterEnumConstantName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitEnumConstantName(this);
        }
    }

    public final EnumConstantNameContext enumConstantName() throws RecognitionException {
        EnumConstantNameContext _localctx = new EnumConstantNameContext(_ctx, getState());
        enterRule(_localctx, 88, RULE_enumConstantName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(625);
                match(Identifier);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeNameContext extends ParserRuleContext {
        public QualifiedIdentifierContext qualifiedIdentifier() {
            return getRuleContext(QualifiedIdentifierContext.class, 0);
        }

        public TypeNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeName(this);
        }
    }

    public final TypeNameContext typeName() throws RecognitionException {
        TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
        enterRule(_localctx, 90, RULE_typeName);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(627);
                qualifiedIdentifier();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TypeRefContext extends ParserRuleContext {
        public PrimitiveTypeContext primitiveType() {
            return getRuleContext(PrimitiveTypeContext.class, 0);
        }

        public ClassOrInterfaceTypeContext classOrInterfaceType() {
            return getRuleContext(ClassOrInterfaceTypeContext.class, 0);
        }

        public TypeRefContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_typeRef;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTypeRef(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTypeRef(this);
        }
    }

    public final TypeRefContext typeRef() throws RecognitionException {
        TypeRefContext _localctx = new TypeRefContext(_ctx, getState());
        enterRule(_localctx, 92, RULE_typeRef);
        try {
            int _alt;
            setState(645);
            switch (_input.LA(1)) {
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(629);
                    classOrInterfaceType();
                    setState(634);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 70, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(630);
                                    match(4);
                                    setState(631);
                                    match(37);
                                }
                            }
                        }
                        setState(636);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 70, _ctx);
                    }
                }
                break;
                case 10:
                case 12:
                case 14:
                case 15:
                case 35:
                case 36:
                case 49:
                case 69:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(637);
                    primitiveType();
                    setState(642);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 71, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(638);
                                    match(4);
                                    setState(639);
                                    match(37);
                                }
                            }
                        }
                        setState(644);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 71, _ctx);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassOrInterfaceTypeContext extends ParserRuleContext {
        public TypeArgumentsContext typeArguments(int i) {
            return getRuleContext(TypeArgumentsContext.class, i);
        }

        public TerminalNode Identifier(int i) {
            return getToken(Java7Parser.Identifier, i);
        }

        public List<TypeArgumentsContext> typeArguments() {
            return getRuleContexts(TypeArgumentsContext.class);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(Java7Parser.Identifier);
        }

        public ClassOrInterfaceTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classOrInterfaceType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassOrInterfaceType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassOrInterfaceType(this);
        }
    }

    public final ClassOrInterfaceTypeContext classOrInterfaceType() throws RecognitionException {
        ClassOrInterfaceTypeContext _localctx = new ClassOrInterfaceTypeContext(_ctx, getState());
        enterRule(_localctx, 94, RULE_classOrInterfaceType);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(647);
                match(Identifier);
                setState(649);
                switch (getInterpreter().adaptivePredict(_input, 73, _ctx)) {
                    case 1: {
                        setState(648);
                        typeArguments();
                    }
                    break;
                }
                setState(658);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 75, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        {
                            {
                                setState(651);
                                match(71);
                                setState(652);
                                match(Identifier);
                                setState(654);
                                switch (getInterpreter().adaptivePredict(_input, 74, _ctx)) {
                                    case 1: {
                                        setState(653);
                                        typeArguments();
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    setState(660);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 75, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class PrimitiveTypeContext extends ParserRuleContext {
        public PrimitiveTypeContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_primitiveType;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterPrimitiveType(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitPrimitiveType(this);
        }
    }

    public final PrimitiveTypeContext primitiveType() throws RecognitionException {
        PrimitiveTypeContext _localctx = new PrimitiveTypeContext(_ctx, getState());
        enterRule(_localctx, 96, RULE_primitiveType);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(661);
                _la = _input.LA(1);
                if (!(((((_la - 10)) & ~0x3f) == 0 && ((1L << (_la - 10)) & ((1L << (10 - 10)) | (1L << (12 - 10)) | (1L << (14 - 10)) | (1L << (15 - 10)) | (1L << (35 - 10)) | (1L << (36 - 10)) | (1L << (49 - 10)) | (1L << (69 - 10)))) != 0))) {
                    _errHandler.recoverInline(this);
                }
                consume();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class QualifiedIdentifierListContext extends ParserRuleContext {
        public QualifiedIdentifierContext qualifiedIdentifier(int i) {
            return getRuleContext(QualifiedIdentifierContext.class, i);
        }

        public List<QualifiedIdentifierContext> qualifiedIdentifier() {
            return getRuleContexts(QualifiedIdentifierContext.class);
        }

        public QualifiedIdentifierListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_qualifiedIdentifierList;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterQualifiedIdentifierList(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitQualifiedIdentifierList(this);
        }
    }

    public final QualifiedIdentifierListContext qualifiedIdentifierList() throws RecognitionException {
        QualifiedIdentifierListContext _localctx = new QualifiedIdentifierListContext(_ctx, getState());
        enterRule(_localctx, 98, RULE_qualifiedIdentifierList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(663);
                qualifiedIdentifier();
                setState(668);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(664);
                            match(42);
                            setState(665);
                            qualifiedIdentifier();
                        }
                    }
                    setState(670);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormalParametersContext extends ParserRuleContext {
        public FormalParameterDeclarationsContext formalParameterDeclarations() {
            return getRuleContext(FormalParameterDeclarationsContext.class, 0);
        }

        public FormalParametersContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formalParameters;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterFormalParameters(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitFormalParameters(this);
        }
    }

    public final FormalParametersContext formalParameters() throws RecognitionException {
        FormalParametersContext _localctx = new FormalParametersContext(_ctx, getState());
        enterRule(_localctx, 100, RULE_formalParameters);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(671);
                match(45);
                setState(673);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 24) | (1L << 35) | (1L << 36) | (1L << 49) | (1L << 62))) != 0) || _la == 69 || _la == Identifier) {
                    {
                        setState(672);
                        formalParameterDeclarations();
                    }
                }

                setState(675);
                match(22);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormalParameterDeclarationsContext extends ParserRuleContext {
        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public VariableModifierContext variableModifier(int i) {
            return getRuleContext(VariableModifierContext.class, i);
        }

        public List<VariableModifierContext> variableModifier() {
            return getRuleContexts(VariableModifierContext.class);
        }

        public FormalParameterVariablesContext formalParameterVariables() {
            return getRuleContext(FormalParameterVariablesContext.class, 0);
        }

        public FormalParameterDeclarationsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formalParameterDeclarations;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterFormalParameterDeclarations(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitFormalParameterDeclarations(this);
        }
    }

    public final FormalParameterDeclarationsContext formalParameterDeclarations() throws RecognitionException {
        FormalParameterDeclarationsContext _localctx = new FormalParameterDeclarationsContext(_ctx, getState());
        enterRule(_localctx, 102, RULE_formalParameterDeclarations);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(680);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24 || _la == 62) {
                    {
                        {
                            setState(677);
                            variableModifier();
                        }
                    }
                    setState(682);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(683);
                typeRef();
                setState(684);
                formalParameterVariables();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class FormalParameterVariablesContext extends ParserRuleContext {
        public VariableDeclaratorIdContext variableDeclaratorId() {
            return getRuleContext(VariableDeclaratorIdContext.class, 0);
        }

        public FormalParameterDeclarationsContext formalParameterDeclarations() {
            return getRuleContext(FormalParameterDeclarationsContext.class, 0);
        }

        public FormalParameterVariablesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_formalParameterVariables;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterFormalParameterVariables(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitFormalParameterVariables(this);
        }
    }

    public final FormalParameterVariablesContext formalParameterVariables() throws RecognitionException {
        FormalParameterVariablesContext _localctx = new FormalParameterVariablesContext(_ctx, getState());
        enterRule(_localctx, 104, RULE_formalParameterVariables);
        int _la;
        try {
            setState(693);
            switch (_input.LA(1)) {
                case 55:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(686);
                    match(55);
                    setState(687);
                    variableDeclaratorId();
                }
                break;
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(688);
                    variableDeclaratorId();
                    setState(691);
                    _la = _input.LA(1);
                    if (_la == 42) {
                        {
                            setState(689);
                            match(42);
                            setState(690);
                            formalParameterDeclarations();
                        }
                    }

                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class MethodBodyContext extends ParserRuleContext {
        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public MethodBodyContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_methodBody;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterMethodBody(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitMethodBody(this);
        }
    }

    public final MethodBodyContext methodBody() throws RecognitionException {
        MethodBodyContext _localctx = new MethodBodyContext(_ctx, getState());
        enterRule(_localctx, 106, RULE_methodBody);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(695);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExplicitConstructorInvocationContext extends ParserRuleContext {
        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public PrimaryContext primary() {
            return getRuleContext(PrimaryContext.class, 0);
        }

        public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
            return getRuleContext(NonWildcardTypeArgumentsContext.class, 0);
        }

        public ExplicitConstructorInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_explicitConstructorInvocation;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterExplicitConstructorInvocation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitExplicitConstructorInvocation(this);
        }
    }

    public final ExplicitConstructorInvocationContext explicitConstructorInvocation() throws RecognitionException {
        ExplicitConstructorInvocationContext _localctx = new ExplicitConstructorInvocationContext(_ctx, getState());
        enterRule(_localctx, 108, RULE_explicitConstructorInvocation);
        int _la;
        try {
            setState(713);
            switch (getInterpreter().adaptivePredict(_input, 83, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(698);
                    _la = _input.LA(1);
                    if (_la == 5) {
                        {
                            setState(697);
                            nonWildcardTypeArguments();
                        }
                    }

                    setState(700);
                    _la = _input.LA(1);
                    if (!(_la == 18 || _la == 79)) {
                        _errHandler.recoverInline(this);
                    }
                    consume();
                    setState(701);
                    arguments();
                    setState(702);
                    match(77);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(704);
                    primary();
                    setState(705);
                    match(71);
                    setState(707);
                    _la = _input.LA(1);
                    if (_la == 5) {
                        {
                            setState(706);
                            nonWildcardTypeArguments();
                        }
                    }

                    setState(709);
                    match(18);
                    setState(710);
                    arguments();
                    setState(711);
                    match(77);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class QualifiedIdentifierContext extends ParserRuleContext {
        public TerminalNode Identifier(int i) {
            return getToken(Java7Parser.Identifier, i);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(Java7Parser.Identifier);
        }

        public QualifiedIdentifierContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_qualifiedIdentifier;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterQualifiedIdentifier(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitQualifiedIdentifier(this);
        }
    }

    public final QualifiedIdentifierContext qualifiedIdentifier() throws RecognitionException {
        QualifiedIdentifierContext _localctx = new QualifiedIdentifierContext(_ctx, getState());
        enterRule(_localctx, 110, RULE_qualifiedIdentifier);
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(715);
                match(Identifier);
                setState(720);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 84, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        {
                            {
                                setState(716);
                                match(71);
                                setState(717);
                                match(Identifier);
                            }
                        }
                    }
                    setState(722);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 84, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LiteralContext extends ParserRuleContext {
        public TerminalNode StringLiteral() {
            return getToken(Java7Parser.StringLiteral, 0);
        }

        public BooleanLiteralContext booleanLiteral() {
            return getRuleContext(BooleanLiteralContext.class, 0);
        }

        public TerminalNode CharacterLiteral() {
            return getToken(Java7Parser.CharacterLiteral, 0);
        }

        public TerminalNode FloatingPointLiteral() {
            return getToken(Java7Parser.FloatingPointLiteral, 0);
        }

        public IntegerLiteralContext integerLiteral() {
            return getRuleContext(IntegerLiteralContext.class, 0);
        }

        public LiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_literal;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitLiteral(this);
        }
    }

    public final LiteralContext literal() throws RecognitionException {
        LiteralContext _localctx = new LiteralContext(_ctx, getState());
        enterRule(_localctx, 112, RULE_literal);
        try {
            setState(729);
            switch (_input.LA(1)) {
                case HexLiteral:
                case DecimalLiteral:
                case OctalLiteral:
                case BinaryLiteral:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(723);
                    integerLiteral();
                }
                break;
                case FloatingPointLiteral:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(724);
                    match(FloatingPointLiteral);
                }
                break;
                case CharacterLiteral:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(725);
                    match(CharacterLiteral);
                }
                break;
                case StringLiteral:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(726);
                    match(StringLiteral);
                }
                break;
                case 7:
                case 65:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(727);
                    booleanLiteral();
                }
                break;
                case 26:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(728);
                    match(26);
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class IntegerLiteralContext extends ParserRuleContext {
        public TerminalNode HexLiteral() {
            return getToken(Java7Parser.HexLiteral, 0);
        }

        public TerminalNode DecimalLiteral() {
            return getToken(Java7Parser.DecimalLiteral, 0);
        }

        public TerminalNode OctalLiteral() {
            return getToken(Java7Parser.OctalLiteral, 0);
        }

        public TerminalNode BinaryLiteral() {
            return getToken(Java7Parser.BinaryLiteral, 0);
        }

        public IntegerLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_integerLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterIntegerLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitIntegerLiteral(this);
        }
    }

    public final IntegerLiteralContext integerLiteral() throws RecognitionException {
        IntegerLiteralContext _localctx = new IntegerLiteralContext(_ctx, getState());
        enterRule(_localctx, 114, RULE_integerLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(731);
                _la = _input.LA(1);
                if (!(((((_la - 90)) & ~0x3f) == 0 && ((1L << (_la - 90)) & ((1L << (HexLiteral - 90)) | (1L << (DecimalLiteral - 90)) | (1L << (OctalLiteral - 90)) | (1L << (BinaryLiteral - 90)))) != 0))) {
                    _errHandler.recoverInline(this);
                }
                consume();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class BooleanLiteralContext extends ParserRuleContext {
        public BooleanLiteralContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_booleanLiteral;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterBooleanLiteral(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitBooleanLiteral(this);
        }
    }

    public final BooleanLiteralContext booleanLiteral() throws RecognitionException {
        BooleanLiteralContext _localctx = new BooleanLiteralContext(_ctx, getState());
        enterRule(_localctx, 116, RULE_booleanLiteral);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(733);
                _la = _input.LA(1);
                if (!(_la == 7 || _la == 65)) {
                    _errHandler.recoverInline(this);
                }
                consume();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnnotationContext extends ParserRuleContext {
        public AnnotationNameContext annotationName() {
            return getRuleContext(AnnotationNameContext.class, 0);
        }

        public ElementValueContext elementValue() {
            return getRuleContext(ElementValueContext.class, 0);
        }

        public ElementValuePairsContext elementValuePairs() {
            return getRuleContext(ElementValuePairsContext.class, 0);
        }

        public AnnotationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_annotation;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterAnnotation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitAnnotation(this);
        }
    }

    public final AnnotationContext annotation() throws RecognitionException {
        AnnotationContext _localctx = new AnnotationContext(_ctx, getState());
        enterRule(_localctx, 118, RULE_annotation);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(735);
                match(24);
                setState(736);
                annotationName();
                setState(743);
                _la = _input.LA(1);
                if (_la == 45) {
                    {
                        setState(737);
                        match(45);
                        setState(740);
                        switch (getInterpreter().adaptivePredict(_input, 86, _ctx)) {
                            case 1: {
                                setState(738);
                                elementValuePairs();
                            }
                            break;

                            case 2: {
                                setState(739);
                                elementValue();
                            }
                            break;
                        }
                        setState(742);
                        match(22);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnnotationNameContext extends ParserRuleContext {
        public TerminalNode Identifier(int i) {
            return getToken(Java7Parser.Identifier, i);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(Java7Parser.Identifier);
        }

        public AnnotationNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_annotationName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterAnnotationName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitAnnotationName(this);
        }
    }

    public final AnnotationNameContext annotationName() throws RecognitionException {
        AnnotationNameContext _localctx = new AnnotationNameContext(_ctx, getState());
        enterRule(_localctx, 120, RULE_annotationName);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(745);
                match(Identifier);
                setState(750);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 71) {
                    {
                        {
                            setState(746);
                            match(71);
                            setState(747);
                            match(Identifier);
                        }
                    }
                    setState(752);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ElementValuePairsContext extends ParserRuleContext {
        public ElementValuePairContext elementValuePair(int i) {
            return getRuleContext(ElementValuePairContext.class, i);
        }

        public List<ElementValuePairContext> elementValuePair() {
            return getRuleContexts(ElementValuePairContext.class);
        }

        public ElementValuePairsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elementValuePairs;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterElementValuePairs(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitElementValuePairs(this);
        }
    }

    public final ElementValuePairsContext elementValuePairs() throws RecognitionException {
        ElementValuePairsContext _localctx = new ElementValuePairsContext(_ctx, getState());
        enterRule(_localctx, 122, RULE_elementValuePairs);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(753);
                elementValuePair();
                setState(758);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(754);
                            match(42);
                            setState(755);
                            elementValuePair();
                        }
                    }
                    setState(760);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ElementValuePairContext extends ParserRuleContext {
        public ElementValueContext elementValue() {
            return getRuleContext(ElementValueContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ElementValuePairContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elementValuePair;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterElementValuePair(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitElementValuePair(this);
        }
    }

    public final ElementValuePairContext elementValuePair() throws RecognitionException {
        ElementValuePairContext _localctx = new ElementValuePairContext(_ctx, getState());
        enterRule(_localctx, 124, RULE_elementValuePair);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(761);
                match(Identifier);
                setState(762);
                match(25);
                setState(763);
                elementValue();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ElementValueContext extends ParserRuleContext {
        public ElementValueArrayInitializerContext elementValueArrayInitializer() {
            return getRuleContext(ElementValueArrayInitializerContext.class, 0);
        }

        public AnnotationContext annotation() {
            return getRuleContext(AnnotationContext.class, 0);
        }

        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public ElementValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elementValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterElementValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitElementValue(this);
        }
    }

    public final ElementValueContext elementValue() throws RecognitionException {
        ElementValueContext _localctx = new ElementValueContext(_ctx, getState());
        enterRule(_localctx, 126, RULE_elementValue);
        try {
            setState(768);
            switch (_input.LA(1)) {
                case 6:
                case 7:
                case 10:
                case 12:
                case 14:
                case 15:
                case 18:
                case 26:
                case 29:
                case 34:
                case 35:
                case 36:
                case 44:
                case 45:
                case 49:
                case 53:
                case 65:
                case 67:
                case 69:
                case 72:
                case 79:
                case 88:
                case HexLiteral:
                case DecimalLiteral:
                case OctalLiteral:
                case BinaryLiteral:
                case FloatingPointLiteral:
                case CharacterLiteral:
                case StringLiteral:
                case Identifier:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(765);
                    expression(0);
                }
                break;
                case 24:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(766);
                    annotation();
                }
                break;
                case 56:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(767);
                    elementValueArrayInitializer();
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ElementValueArrayInitializerContext extends ParserRuleContext {
        public List<ElementValueContext> elementValue() {
            return getRuleContexts(ElementValueContext.class);
        }

        public ElementValueContext elementValue(int i) {
            return getRuleContext(ElementValueContext.class, i);
        }

        public ElementValueArrayInitializerContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_elementValueArrayInitializer;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterElementValueArrayInitializer(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitElementValueArrayInitializer(this);
        }
    }

    public final ElementValueArrayInitializerContext elementValueArrayInitializer() throws RecognitionException {
        ElementValueArrayInitializerContext _localctx = new ElementValueArrayInitializerContext(_ctx, getState());
        enterRule(_localctx, 128, RULE_elementValueArrayInitializer);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(770);
                match(56);
                setState(779);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 24) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53) | (1L << 56))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        setState(771);
                        elementValue();
                        setState(776);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 91, _ctx);
                        while (_alt != 2 && _alt != -1) {
                            if (_alt == 1) {
                                {
                                    {
                                        setState(772);
                                        match(42);
                                        setState(773);
                                        elementValue();
                                    }
                                }
                            }
                            setState(778);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 91, _ctx);
                        }
                    }
                }

                setState(782);
                _la = _input.LA(1);
                if (_la == 42) {
                    {
                        setState(781);
                        match(42);
                    }
                }

                setState(784);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnnotationTypeDeclarationContext extends ParserRuleContext {
        public List<ModifierContext> modifier() {
            return getRuleContexts(ModifierContext.class);
        }

        public List<AnnotationTypeElementContext> annotationTypeElement() {
            return getRuleContexts(AnnotationTypeElementContext.class);
        }

        public ModifierContext modifier(int i) {
            return getRuleContext(ModifierContext.class, i);
        }

        public AnnotationTypeElementContext annotationTypeElement(int i) {
            return getRuleContext(AnnotationTypeElementContext.class, i);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public AnnotationTypeDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_annotationTypeDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterAnnotationTypeDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitAnnotationTypeDeclaration(this);
        }
    }

    public final AnnotationTypeDeclarationContext annotationTypeDeclaration() throws RecognitionException {
        AnnotationTypeDeclarationContext _localctx = new AnnotationTypeDeclarationContext(_ctx, getState());
        enterRule(_localctx, 130, RULE_annotationTypeDeclaration);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(786);
                match(24);
                setState(787);
                match(1);
                setState(788);
                match(Identifier);
                setState(789);
                match(56);
                setState(799);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 24) | (1L << 30) | (1L << 33) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 49) | (1L << 50) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 66)) & ~0x3f) == 0 && ((1L << (_la - 66)) & ((1L << (66 - 66)) | (1L << (69 - 66)) | (1L << (73 - 66)) | (1L << (76 - 66)) | (1L << (ENUM - 66)) | (1L << (Identifier - 66)))) != 0)) {
                    {
                        {
                            setState(793);
                            _errHandler.sync(this);
                            _alt = getInterpreter().adaptivePredict(_input, 94, _ctx);
                            while (_alt != 2 && _alt != -1) {
                                if (_alt == 1) {
                                    {
                                        {
                                            setState(790);
                                            modifier();
                                        }
                                    }
                                }
                                setState(795);
                                _errHandler.sync(this);
                                _alt = getInterpreter().adaptivePredict(_input, 94, _ctx);
                            }
                            setState(796);
                            annotationTypeElement();
                        }
                    }
                    setState(801);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(802);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnnotationTypeElementContext extends ParserRuleContext {
        public AnnotationTypeDeclarationContext annotationTypeDeclaration() {
            return getRuleContext(AnnotationTypeDeclarationContext.class, 0);
        }

        public AnnotationMethodContext annotationMethod() {
            return getRuleContext(AnnotationMethodContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public ClassDeclarationContext classDeclaration() {
            return getRuleContext(ClassDeclarationContext.class, 0);
        }

        public EnumDeclarationContext enumDeclaration() {
            return getRuleContext(EnumDeclarationContext.class, 0);
        }

        public NormalInterfaceDeclarationContext normalInterfaceDeclaration() {
            return getRuleContext(NormalInterfaceDeclarationContext.class, 0);
        }

        public VariableDeclaratorsContext variableDeclarators() {
            return getRuleContext(VariableDeclaratorsContext.class, 0);
        }

        public AnnotationTypeElementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_annotationTypeElement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterAnnotationTypeElement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitAnnotationTypeElement(this);
        }
    }

    public final AnnotationTypeElementContext annotationTypeElement() throws RecognitionException {
        AnnotationTypeElementContext _localctx = new AnnotationTypeElementContext(_ctx, getState());
        enterRule(_localctx, 132, RULE_annotationTypeElement);
        int _la;
        try {
            setState(827);
            switch (getInterpreter().adaptivePredict(_input, 101, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(804);
                    typeRef();
                    setState(807);
                    switch (getInterpreter().adaptivePredict(_input, 96, _ctx)) {
                        case 1: {
                            setState(805);
                            annotationMethod();
                        }
                        break;

                        case 2: {
                            setState(806);
                            variableDeclarators();
                        }
                        break;
                    }
                    setState(809);
                    match(77);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(811);
                    classDeclaration();
                    setState(813);
                    _la = _input.LA(1);
                    if (_la == 77) {
                        {
                            setState(812);
                            match(77);
                        }
                    }

                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(815);
                    normalInterfaceDeclaration();
                    setState(817);
                    _la = _input.LA(1);
                    if (_la == 77) {
                        {
                            setState(816);
                            match(77);
                        }
                    }

                }
                break;

                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(819);
                    enumDeclaration();
                    setState(821);
                    _la = _input.LA(1);
                    if (_la == 77) {
                        {
                            setState(820);
                            match(77);
                        }
                    }

                }
                break;

                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(823);
                    annotationTypeDeclaration();
                    setState(825);
                    _la = _input.LA(1);
                    if (_la == 77) {
                        {
                            setState(824);
                            match(77);
                        }
                    }

                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class AnnotationMethodContext extends ParserRuleContext {
        public DefaultValueContext defaultValue() {
            return getRuleContext(DefaultValueContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public AnnotationMethodContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_annotationMethod;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterAnnotationMethod(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitAnnotationMethod(this);
        }
    }

    public final AnnotationMethodContext annotationMethod() throws RecognitionException {
        AnnotationMethodContext _localctx = new AnnotationMethodContext(_ctx, getState());
        enterRule(_localctx, 134, RULE_annotationMethod);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(829);
                match(Identifier);
                setState(830);
                match(45);
                setState(831);
                match(22);
                setState(833);
                _la = _input.LA(1);
                if (_la == 40) {
                    {
                        setState(832);
                        defaultValue();
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class DefaultValueContext extends ParserRuleContext {
        public ElementValueContext elementValue() {
            return getRuleContext(ElementValueContext.class, 0);
        }

        public DefaultValueContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_defaultValue;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterDefaultValue(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitDefaultValue(this);
        }
    }

    public final DefaultValueContext defaultValue() throws RecognitionException {
        DefaultValueContext _localctx = new DefaultValueContext(_ctx, getState());
        enterRule(_localctx, 136, RULE_defaultValue);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(835);
                match(40);
                setState(836);
                elementValue();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class LocalVariableDeclarationContext extends ParserRuleContext {
        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public VariableDeclaratorsContext variableDeclarators() {
            return getRuleContext(VariableDeclaratorsContext.class, 0);
        }

        public VariableModifierContext variableModifier(int i) {
            return getRuleContext(VariableModifierContext.class, i);
        }

        public List<VariableModifierContext> variableModifier() {
            return getRuleContexts(VariableModifierContext.class);
        }

        public LocalVariableDeclarationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_localVariableDeclaration;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterLocalVariableDeclaration(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitLocalVariableDeclaration(this);
        }
    }

    public final LocalVariableDeclarationContext localVariableDeclaration() throws RecognitionException {
        LocalVariableDeclarationContext _localctx = new LocalVariableDeclarationContext(_ctx, getState());
        enterRule(_localctx, 138, RULE_localVariableDeclaration);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(841);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24 || _la == 62) {
                    {
                        {
                            setState(838);
                            variableModifier();
                        }
                    }
                    setState(843);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(844);
                typeRef();
                setState(845);
                variableDeclarators();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatementContext extends ParserRuleContext {
        public List<StatementContext> statement() {
            return getRuleContexts(StatementContext.class);
        }

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ParExpressionContext parExpression() {
            return getRuleContext(ParExpressionContext.class, 0);
        }

        public StatementContext statement(int i) {
            return getRuleContext(StatementContext.class, i);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public TryStatementContext tryStatement() {
            return getRuleContext(TryStatementContext.class, 0);
        }

        public StatementExpressionContext statementExpression() {
            return getRuleContext(StatementExpressionContext.class, 0);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public ForControlContext forControl() {
            return getRuleContext(ForControlContext.class, 0);
        }

        public TerminalNode ASSERT() {
            return getToken(Java7Parser.ASSERT, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public SwitchBlockContext switchBlock() {
            return getRuleContext(SwitchBlockContext.class, 0);
        }

        public StatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitStatement(this);
        }
    }

    public final StatementContext statement() throws RecognitionException {
        StatementContext _localctx = new StatementContext(_ctx, getState());
        enterRule(_localctx, 140, RULE_statement);
        int _la;
        try {
            setState(914);
            switch (getInterpreter().adaptivePredict(_input, 109, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(847);
                    block();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(848);
                    match(ASSERT);
                    setState(849);
                    expression(0);
                    setState(852);
                    _la = _input.LA(1);
                    if (_la == 46) {
                        {
                            setState(850);
                            match(46);
                            setState(851);
                            expression(0);
                        }
                    }

                    setState(854);
                    match(77);
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(856);
                    match(47);
                    setState(857);
                    parExpression();
                    setState(858);
                    statement();
                    setState(861);
                    switch (getInterpreter().adaptivePredict(_input, 105, _ctx)) {
                        case 1: {
                            setState(859);
                            match(63);
                            setState(860);
                            statement();
                        }
                        break;
                    }
                }
                break;

                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(863);
                    match(74);
                    setState(864);
                    match(45);
                    setState(865);
                    forControl();
                    setState(866);
                    match(22);
                    setState(867);
                    statement();
                }
                break;

                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(869);
                    match(43);
                    setState(870);
                    parExpression();
                    setState(871);
                    statement();
                }
                break;

                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(873);
                    match(19);
                    setState(874);
                    statement();
                    setState(875);
                    match(43);
                    setState(876);
                    parExpression();
                    setState(877);
                    match(77);
                }
                break;

                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(879);
                    tryStatement();
                }
                break;

                case 8:
                    enterOuterAlt(_localctx, 8);
                {
                    setState(880);
                    match(84);
                    setState(881);
                    parExpression();
                    setState(882);
                    switchBlock();
                }
                break;

                case 9:
                    enterOuterAlt(_localctx, 9);
                {
                    setState(884);
                    match(41);
                    setState(885);
                    parExpression();
                    setState(886);
                    block();
                }
                break;

                case 10:
                    enterOuterAlt(_localctx, 10);
                {
                    setState(888);
                    match(75);
                    setState(890);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                        {
                            setState(889);
                            expression(0);
                        }
                    }

                    setState(892);
                    match(77);
                }
                break;

                case 11:
                    enterOuterAlt(_localctx, 11);
                {
                    setState(893);
                    match(23);
                    setState(894);
                    expression(0);
                    setState(895);
                    match(77);
                }
                break;

                case 12:
                    enterOuterAlt(_localctx, 12);
                {
                    setState(897);
                    match(57);
                    setState(899);
                    _la = _input.LA(1);
                    if (_la == Identifier) {
                        {
                            setState(898);
                            match(Identifier);
                        }
                    }

                    setState(901);
                    match(77);
                }
                break;

                case 13:
                    enterOuterAlt(_localctx, 13);
                {
                    setState(902);
                    match(8);
                    setState(904);
                    _la = _input.LA(1);
                    if (_la == Identifier) {
                        {
                            setState(903);
                            match(Identifier);
                        }
                    }

                    setState(906);
                    match(77);
                }
                break;

                case 14:
                    enterOuterAlt(_localctx, 14);
                {
                    setState(907);
                    match(77);
                }
                break;

                case 15:
                    enterOuterAlt(_localctx, 15);
                {
                    setState(908);
                    statementExpression();
                    setState(909);
                    match(77);
                }
                break;

                case 16:
                    enterOuterAlt(_localctx, 16);
                {
                    setState(911);
                    match(Identifier);
                    setState(912);
                    match(46);
                    setState(913);
                    statement();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class TryStatementContext extends ParserRuleContext {
        public BlockContext block(int i) {
            return getRuleContext(BlockContext.class, i);
        }

        public ResourcesContext resources() {
            return getRuleContext(ResourcesContext.class, 0);
        }

        public List<CatchClauseContext> catchClause() {
            return getRuleContexts(CatchClauseContext.class);
        }

        public CatchClauseContext catchClause(int i) {
            return getRuleContext(CatchClauseContext.class, i);
        }

        public List<BlockContext> block() {
            return getRuleContexts(BlockContext.class);
        }

        public TryStatementContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_tryStatement;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterTryStatement(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitTryStatement(this);
        }
    }

    public final TryStatementContext tryStatement() throws RecognitionException {
        TryStatementContext _localctx = new TryStatementContext(_ctx, getState());
        enterRule(_localctx, 142, RULE_tryStatement);
        int _la;
        try {
            setState(946);
            switch (getInterpreter().adaptivePredict(_input, 115, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(916);
                    match(52);
                    setState(917);
                    match(45);
                    setState(918);
                    resources();
                    setState(919);
                    match(22);
                    setState(920);
                    block();
                    setState(924);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == 64) {
                        {
                            {
                                setState(921);
                                catchClause();
                            }
                        }
                        setState(926);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(929);
                    _la = _input.LA(1);
                    if (_la == 31) {
                        {
                            setState(927);
                            match(31);
                            setState(928);
                            block();
                        }
                    }

                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(931);
                    match(52);
                    setState(932);
                    block();
                    setState(944);
                    switch (_input.LA(1)) {
                        case 64: {
                            setState(934);
                            _errHandler.sync(this);
                            _la = _input.LA(1);
                            do {
                                {
                                    {
                                        setState(933);
                                        catchClause();
                                    }
                                }
                                setState(936);
                                _errHandler.sync(this);
                                _la = _input.LA(1);
                            } while (_la == 64);
                            setState(940);
                            _la = _input.LA(1);
                            if (_la == 31) {
                                {
                                    setState(938);
                                    match(31);
                                    setState(939);
                                    block();
                                }
                            }

                        }
                        break;
                        case 31: {
                            setState(942);
                            match(31);
                            setState(943);
                            block();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CatchClauseContext extends ParserRuleContext {
        public List<TypeNameContext> typeName() {
            return getRuleContexts(TypeNameContext.class);
        }

        public BlockContext block() {
            return getRuleContext(BlockContext.class, 0);
        }

        public TypeNameContext typeName(int i) {
            return getRuleContext(TypeNameContext.class, i);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public VariableModifierContext variableModifier(int i) {
            return getRuleContext(VariableModifierContext.class, i);
        }

        public List<VariableModifierContext> variableModifier() {
            return getRuleContexts(VariableModifierContext.class);
        }

        public CatchClauseContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_catchClause;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterCatchClause(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitCatchClause(this);
        }
    }

    public final CatchClauseContext catchClause() throws RecognitionException {
        CatchClauseContext _localctx = new CatchClauseContext(_ctx, getState());
        enterRule(_localctx, 144, RULE_catchClause);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(948);
                match(64);
                setState(949);
                match(45);
                setState(953);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24 || _la == 62) {
                    {
                        {
                            setState(950);
                            variableModifier();
                        }
                    }
                    setState(955);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(956);
                typeName();
                setState(961);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 32) {
                    {
                        {
                            setState(957);
                            match(32);
                            setState(958);
                            typeName();
                        }
                    }
                    setState(963);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(964);
                match(Identifier);
                setState(965);
                match(22);
                setState(966);
                block();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ResourcesContext extends ParserRuleContext {
        public ResourceContext resource(int i) {
            return getRuleContext(ResourceContext.class, i);
        }

        public List<ResourceContext> resource() {
            return getRuleContexts(ResourceContext.class);
        }

        public ResourcesContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_resources;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterResources(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitResources(this);
        }
    }

    public final ResourcesContext resources() throws RecognitionException {
        ResourcesContext _localctx = new ResourcesContext(_ctx, getState());
        enterRule(_localctx, 146, RULE_resources);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(968);
                resource();
                setState(973);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 118, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        {
                            {
                                setState(969);
                                match(77);
                                setState(970);
                                resource();
                            }
                        }
                    }
                    setState(975);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 118, _ctx);
                }
                setState(977);
                _la = _input.LA(1);
                if (_la == 77) {
                    {
                        setState(976);
                        match(77);
                    }
                }

            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ResourceContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public VariableDeclaratorIdContext variableDeclaratorId() {
            return getRuleContext(VariableDeclaratorIdContext.class, 0);
        }

        public ClassOrInterfaceTypeContext classOrInterfaceType() {
            return getRuleContext(ClassOrInterfaceTypeContext.class, 0);
        }

        public VariableModifierContext variableModifier(int i) {
            return getRuleContext(VariableModifierContext.class, i);
        }

        public List<VariableModifierContext> variableModifier() {
            return getRuleContexts(VariableModifierContext.class);
        }

        public ResourceContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_resource;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterResource(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitResource(this);
        }
    }

    public final ResourceContext resource() throws RecognitionException {
        ResourceContext _localctx = new ResourceContext(_ctx, getState());
        enterRule(_localctx, 148, RULE_resource);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(982);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24 || _la == 62) {
                    {
                        {
                            setState(979);
                            variableModifier();
                        }
                    }
                    setState(984);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(985);
                classOrInterfaceType();
                setState(986);
                variableDeclaratorId();
                setState(987);
                match(25);
                setState(988);
                expression(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SwitchBlockContext extends ParserRuleContext {
        public SwitchBlockStatementGroupContext switchBlockStatementGroup(int i) {
            return getRuleContext(SwitchBlockStatementGroupContext.class, i);
        }

        public List<SwitchLabelContext> switchLabel() {
            return getRuleContexts(SwitchLabelContext.class);
        }

        public SwitchLabelContext switchLabel(int i) {
            return getRuleContext(SwitchLabelContext.class, i);
        }

        public List<SwitchBlockStatementGroupContext> switchBlockStatementGroup() {
            return getRuleContexts(SwitchBlockStatementGroupContext.class);
        }

        public SwitchBlockContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_switchBlock;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterSwitchBlock(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitSwitchBlock(this);
        }
    }

    public final SwitchBlockContext switchBlock() throws RecognitionException {
        SwitchBlockContext _localctx = new SwitchBlockContext(_ctx, getState());
        enterRule(_localctx, 150, RULE_switchBlock);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(990);
                match(56);
                setState(994);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 121, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        {
                            {
                                setState(991);
                                switchBlockStatementGroup();
                            }
                        }
                    }
                    setState(996);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 121, _ctx);
                }
                setState(1000);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 17 || _la == 40) {
                    {
                        {
                            setState(997);
                            switchLabel();
                        }
                    }
                    setState(1002);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(1003);
                match(13);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SwitchBlockStatementGroupContext extends ParserRuleContext {
        public BlockStatementContext blockStatement(int i) {
            return getRuleContext(BlockStatementContext.class, i);
        }

        public List<SwitchLabelContext> switchLabel() {
            return getRuleContexts(SwitchLabelContext.class);
        }

        public List<BlockStatementContext> blockStatement() {
            return getRuleContexts(BlockStatementContext.class);
        }

        public SwitchLabelContext switchLabel(int i) {
            return getRuleContext(SwitchLabelContext.class, i);
        }

        public SwitchBlockStatementGroupContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_switchBlockStatementGroup;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterSwitchBlockStatementGroup(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitSwitchBlockStatementGroup(this);
        }
    }

    public final SwitchBlockStatementGroupContext switchBlockStatementGroup() throws RecognitionException {
        SwitchBlockStatementGroupContext _localctx = new SwitchBlockStatementGroupContext(_ctx, getState());
        enterRule(_localctx, 152, RULE_switchBlockStatementGroup);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1006);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 123, _ctx);
                do {
                    switch (_alt) {
                        case 1: {
                            {
                                setState(1005);
                                switchLabel();
                            }
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                    setState(1008);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 123, _ctx);
                } while (_alt != 2 && _alt != -1);
                setState(1013);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 1) | (1L << 6) | (1L << 7) | (1L << 8) | (1L << 10) | (1L << 11) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 16) | (1L << 18) | (1L << 19) | (1L << 23) | (1L << 24) | (1L << 26) | (1L << 29) | (1L << 30) | (1L << 33) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 39) | (1L << 41) | (1L << 43) | (1L << 44) | (1L << 45) | (1L << 47) | (1L << 49) | (1L << 50) | (1L << 52) | (1L << 53) | (1L << 56) | (1L << 57) | (1L << 58) | (1L << 62))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (66 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (73 - 65)) | (1L << (74 - 65)) | (1L << (75 - 65)) | (1L << (76 - 65)) | (1L << (77 - 65)) | (1L << (79 - 65)) | (1L << (84 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (ENUM - 65)) | (1L << (ASSERT - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        {
                            setState(1010);
                            blockStatement();
                        }
                    }
                    setState(1015);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class SwitchLabelContext extends ParserRuleContext {
        public ConstantExpressionContext constantExpression() {
            return getRuleContext(ConstantExpressionContext.class, 0);
        }

        public EnumConstantNameContext enumConstantName() {
            return getRuleContext(EnumConstantNameContext.class, 0);
        }

        public SwitchLabelContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_switchLabel;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterSwitchLabel(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitSwitchLabel(this);
        }
    }

    public final SwitchLabelContext switchLabel() throws RecognitionException {
        SwitchLabelContext _localctx = new SwitchLabelContext(_ctx, getState());
        enterRule(_localctx, 154, RULE_switchLabel);
        try {
            setState(1026);
            switch (getInterpreter().adaptivePredict(_input, 125, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1016);
                    match(17);
                    setState(1017);
                    constantExpression();
                    setState(1018);
                    match(46);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1020);
                    match(17);
                    setState(1021);
                    enumConstantName();
                    setState(1022);
                    match(46);
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1024);
                    match(40);
                    setState(1025);
                    match(46);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ForControlContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public EnhancedForControlContext enhancedForControl() {
            return getRuleContext(EnhancedForControlContext.class, 0);
        }

        public ForInitContext forInit() {
            return getRuleContext(ForInitContext.class, 0);
        }

        public ForUpdateContext forUpdate() {
            return getRuleContext(ForUpdateContext.class, 0);
        }

        public ForControlContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_forControl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterForControl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitForControl(this);
        }
    }

    public final ForControlContext forControl() throws RecognitionException {
        ForControlContext _localctx = new ForControlContext(_ctx, getState());
        enterRule(_localctx, 156, RULE_forControl);
        int _la;
        try {
            setState(1040);
            switch (getInterpreter().adaptivePredict(_input, 129, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1028);
                    enhancedForControl();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1030);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 24) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53) | (1L << 62))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                        {
                            setState(1029);
                            forInit();
                        }
                    }

                    setState(1032);
                    match(77);
                    setState(1034);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                        {
                            setState(1033);
                            expression(0);
                        }
                    }

                    setState(1036);
                    match(77);
                    setState(1038);
                    _la = _input.LA(1);
                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                        {
                            setState(1037);
                            forUpdate();
                        }
                    }

                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ForInitContext extends ParserRuleContext {
        public ExpressionListContext expressionList() {
            return getRuleContext(ExpressionListContext.class, 0);
        }

        public LocalVariableDeclarationContext localVariableDeclaration() {
            return getRuleContext(LocalVariableDeclarationContext.class, 0);
        }

        public ForInitContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_forInit;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterForInit(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitForInit(this);
        }
    }

    public final ForInitContext forInit() throws RecognitionException {
        ForInitContext _localctx = new ForInitContext(_ctx, getState());
        enterRule(_localctx, 158, RULE_forInit);
        try {
            setState(1044);
            switch (getInterpreter().adaptivePredict(_input, 130, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1042);
                    localVariableDeclaration();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1043);
                    expressionList();
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class EnhancedForControlContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public VariableModifierContext variableModifier(int i) {
            return getRuleContext(VariableModifierContext.class, i);
        }

        public List<VariableModifierContext> variableModifier() {
            return getRuleContexts(VariableModifierContext.class);
        }

        public EnhancedForControlContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_enhancedForControl;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterEnhancedForControl(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitEnhancedForControl(this);
        }
    }

    public final EnhancedForControlContext enhancedForControl() throws RecognitionException {
        EnhancedForControlContext _localctx = new EnhancedForControlContext(_ctx, getState());
        enterRule(_localctx, 160, RULE_enhancedForControl);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1049);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 24 || _la == 62) {
                    {
                        {
                            setState(1046);
                            variableModifier();
                        }
                    }
                    setState(1051);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
                setState(1052);
                typeRef();
                setState(1053);
                match(Identifier);
                setState(1054);
                match(46);
                setState(1055);
                expression(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ForUpdateContext extends ParserRuleContext {
        public ExpressionListContext expressionList() {
            return getRuleContext(ExpressionListContext.class, 0);
        }

        public ForUpdateContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_forUpdate;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterForUpdate(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitForUpdate(this);
        }
    }

    public final ForUpdateContext forUpdate() throws RecognitionException {
        ForUpdateContext _localctx = new ForUpdateContext(_ctx, getState());
        enterRule(_localctx, 162, RULE_forUpdate);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1057);
                expressionList();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ParExpressionContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public ParExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_parExpression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterParExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitParExpression(this);
        }
    }

    public final ParExpressionContext parExpression() throws RecognitionException {
        ParExpressionContext _localctx = new ParExpressionContext(_ctx, getState());
        enterRule(_localctx, 164, RULE_parExpression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1059);
                match(45);
                setState(1060);
                expression(0);
                setState(1061);
                match(22);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExpressionListContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ExpressionListContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_expressionList;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterExpressionList(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitExpressionList(this);
        }
    }

    public final ExpressionListContext expressionList() throws RecognitionException {
        ExpressionListContext _localctx = new ExpressionListContext(_ctx, getState());
        enterRule(_localctx, 166, RULE_expressionList);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1063);
                expression(0);
                setState(1068);
                _errHandler.sync(this);
                _la = _input.LA(1);
                while (_la == 42) {
                    {
                        {
                            setState(1064);
                            match(42);
                            setState(1065);
                            expression(0);
                        }
                    }
                    setState(1070);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class StatementExpressionContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public StatementExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_statementExpression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterStatementExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitStatementExpression(this);
        }
    }

    public final StatementExpressionContext statementExpression() throws RecognitionException {
        StatementExpressionContext _localctx = new StatementExpressionContext(_ctx, getState());
        enterRule(_localctx, 168, RULE_statementExpression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1071);
                expression(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ConstantExpressionContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public ConstantExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_constantExpression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterConstantExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitConstantExpression(this);
        }
    }

    public final ConstantExpressionContext constantExpression() throws RecognitionException {
        ConstantExpressionContext _localctx = new ConstantExpressionContext(_ctx, getState());
        enterRule(_localctx, 170, RULE_constantExpression);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1073);
                expression(0);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExpressionContext extends ParserRuleContext {
        public int _p;

        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public ExpressionListContext expressionList() {
            return getRuleContext(ExpressionListContext.class, 0);
        }

        public PrimaryContext primary() {
            return getRuleContext(PrimaryContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ExplicitGenericInvocationContext explicitGenericInvocation() {
            return getRuleContext(ExplicitGenericInvocationContext.class, 0);
        }

        public CreatorContext creator() {
            return getRuleContext(CreatorContext.class, 0);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        public ExpressionContext(ParserRuleContext parent, int invokingState, int _p) {
            super(parent, invokingState);
            this._p = _p;
        }

        @Override
        public int getRuleIndex() {
            return RULE_expression;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterExpression(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitExpression(this);
        }
    }

    public final ExpressionContext expression(int _p) throws RecognitionException {
        ParserRuleContext _parentctx = _ctx;
        int _parentState = getState();
        ExpressionContext _localctx = new ExpressionContext(_ctx, _parentState, _p);
        ExpressionContext _prevctx = _localctx;
        int _startState = 172;
        enterRecursionRule(_localctx, RULE_expression);
        int _la;
        try {
            int _alt;
            enterOuterAlt(_localctx, 1);
            {
                setState(1088);
                switch (getInterpreter().adaptivePredict(_input, 133, _ctx)) {
                    case 1: {
                        setState(1076);
                        _la = _input.LA(1);
                        if (!(_la == 6 || _la == 44 || _la == 67 || _la == 72)) {
                            _errHandler.recoverInline(this);
                        }
                        consume();
                        setState(1077);
                        expression(17);
                    }
                    break;

                    case 2: {
                        setState(1078);
                        _la = _input.LA(1);
                        if (!(_la == 34 || _la == 88)) {
                            _errHandler.recoverInline(this);
                        }
                        consume();
                        setState(1079);
                        expression(16);
                    }
                    break;

                    case 3: {
                        setState(1080);
                        match(45);
                        setState(1081);
                        typeRef();
                        setState(1082);
                        match(22);
                        setState(1083);
                        expression(15);
                    }
                    break;

                    case 4: {
                        setState(1085);
                        primary();
                    }
                    break;

                    case 5: {
                        setState(1086);
                        match(29);
                        setState(1087);
                        creator();
                    }
                    break;
                }
                _ctx.stop = _input.LT(-1);
                setState(1216);
                _errHandler.sync(this);
                _alt = getInterpreter().adaptivePredict(_input, 142, _ctx);
                while (_alt != 2 && _alt != -1) {
                    if (_alt == 1) {
                        if (_parseListeners != null) triggerExitRuleEvent();
                        _prevctx = _localctx;
                        {
                            setState(1214);
                            switch (getInterpreter().adaptivePredict(_input, 141, _ctx)) {
                                case 1: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1090);
                                    if (!(13 >= _localctx._p)) throw new FailedPredicateException(this, "13 >= $_p");
                                    setState(1091);
                                    _la = _input.LA(1);
                                    if (!(_la == 3 || _la == 20 || _la == 86)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(1092);
                                    expression(14);
                                }
                                break;

                                case 2: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1093);
                                    if (!(12 >= _localctx._p)) throw new FailedPredicateException(this, "12 >= $_p");
                                    setState(1094);
                                    _la = _input.LA(1);
                                    if (!(_la == 44 || _la == 72)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(1095);
                                    expression(13);
                                }
                                break;

                                case 3: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1096);
                                    if (!(11 >= _localctx._p)) throw new FailedPredicateException(this, "11 >= $_p");
                                    setState(1104);
                                    switch (getInterpreter().adaptivePredict(_input, 134, _ctx)) {
                                        case 1: {
                                            setState(1097);
                                            match(5);
                                            setState(1098);
                                            match(5);
                                        }
                                        break;

                                        case 2: {
                                            setState(1099);
                                            match(81);
                                            setState(1100);
                                            match(81);
                                            setState(1101);
                                            match(81);
                                        }
                                        break;

                                        case 3: {
                                            setState(1102);
                                            match(81);
                                            setState(1103);
                                            match(81);
                                        }
                                        break;
                                    }
                                    setState(1106);
                                    expression(12);
                                }
                                break;

                                case 4: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1107);
                                    if (!(10 >= _localctx._p)) throw new FailedPredicateException(this, "10 >= $_p");
                                    setState(1114);
                                    switch (getInterpreter().adaptivePredict(_input, 135, _ctx)) {
                                        case 1: {
                                            setState(1108);
                                            match(5);
                                            setState(1109);
                                            match(25);
                                        }
                                        break;

                                        case 2: {
                                            setState(1110);
                                            match(81);
                                            setState(1111);
                                            match(25);
                                        }
                                        break;

                                        case 3: {
                                            setState(1112);
                                            match(81);
                                        }
                                        break;

                                        case 4: {
                                            setState(1113);
                                            match(5);
                                        }
                                        break;
                                    }
                                    setState(1116);
                                    expression(11);
                                }
                                break;

                                case 5: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1117);
                                    if (!(8 >= _localctx._p)) throw new FailedPredicateException(this, "8 >= $_p");
                                    setState(1118);
                                    _la = _input.LA(1);
                                    if (!(_la == 9 || _la == 87)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                    setState(1119);
                                    expression(9);
                                }
                                break;

                                case 6: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1120);
                                    if (!(7 >= _localctx._p)) throw new FailedPredicateException(this, "7 >= $_p");
                                    setState(1121);
                                    match(2);
                                    setState(1122);
                                    expression(8);
                                }
                                break;

                                case 7: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1123);
                                    if (!(6 >= _localctx._p)) throw new FailedPredicateException(this, "6 >= $_p");
                                    setState(1124);
                                    match(70);
                                    setState(1125);
                                    expression(7);
                                }
                                break;

                                case 8: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1126);
                                    if (!(5 >= _localctx._p)) throw new FailedPredicateException(this, "5 >= $_p");
                                    setState(1127);
                                    match(32);
                                    setState(1128);
                                    expression(6);
                                }
                                break;

                                case 9: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1129);
                                    if (!(4 >= _localctx._p)) throw new FailedPredicateException(this, "4 >= $_p");
                                    setState(1130);
                                    match(78);
                                    setState(1131);
                                    expression(5);
                                }
                                break;

                                case 10: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1132);
                                    if (!(3 >= _localctx._p)) throw new FailedPredicateException(this, "3 >= $_p");
                                    setState(1133);
                                    match(80);
                                    setState(1134);
                                    expression(4);
                                }
                                break;

                                case 11: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1135);
                                    if (!(1 >= _localctx._p)) throw new FailedPredicateException(this, "1 >= $_p");
                                    setState(1155);
                                    switch (getInterpreter().adaptivePredict(_input, 136, _ctx)) {
                                        case 1: {
                                            setState(1136);
                                            match(61);
                                        }
                                        break;

                                        case 2: {
                                            setState(1137);
                                            match(59);
                                        }
                                        break;

                                        case 3: {
                                            setState(1138);
                                            match(38);
                                        }
                                        break;

                                        case 4: {
                                            setState(1139);
                                            match(21);
                                        }
                                        break;

                                        case 5: {
                                            setState(1140);
                                            match(85);
                                        }
                                        break;

                                        case 6: {
                                            setState(1141);
                                            match(48);
                                        }
                                        break;

                                        case 7: {
                                            setState(1142);
                                            match(28);
                                        }
                                        break;

                                        case 8: {
                                            setState(1143);
                                            match(25);
                                        }
                                        break;

                                        case 9: {
                                            setState(1144);
                                            match(81);
                                            setState(1145);
                                            match(81);
                                            setState(1146);
                                            match(25);
                                        }
                                        break;

                                        case 10: {
                                            setState(1147);
                                            match(81);
                                            setState(1148);
                                            match(81);
                                            setState(1149);
                                            match(81);
                                            setState(1150);
                                            match(25);
                                        }
                                        break;

                                        case 11: {
                                            setState(1151);
                                            match(5);
                                            setState(1152);
                                            match(5);
                                            setState(1153);
                                            match(25);
                                        }
                                        break;

                                        case 12: {
                                            setState(1154);
                                            match(83);
                                        }
                                        break;
                                    }
                                    setState(1157);
                                    expression(1);
                                }
                                break;

                                case 12: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1158);
                                    if (!(2 >= _localctx._p)) throw new FailedPredicateException(this, "2 >= $_p");
                                    setState(1159);
                                    match(51);
                                    setState(1160);
                                    expression(0);
                                    setState(1161);
                                    match(46);
                                    setState(1162);
                                    expression(3);
                                }
                                break;

                                case 13: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1164);
                                    if (!(26 >= _localctx._p)) throw new FailedPredicateException(this, "26 >= $_p");
                                    setState(1165);
                                    match(71);
                                    setState(1166);
                                    match(Identifier);
                                }
                                break;

                                case 14: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1167);
                                    if (!(25 >= _localctx._p)) throw new FailedPredicateException(this, "25 >= $_p");
                                    setState(1168);
                                    match(71);
                                    setState(1169);
                                    match(79);
                                }
                                break;

                                case 15: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1170);
                                    if (!(24 >= _localctx._p)) throw new FailedPredicateException(this, "24 >= $_p");
                                    setState(1171);
                                    match(71);
                                    setState(1172);
                                    match(18);
                                    setState(1173);
                                    match(45);
                                    setState(1175);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                                        {
                                            setState(1174);
                                            expressionList();
                                        }
                                    }

                                    setState(1177);
                                    match(22);
                                }
                                break;

                                case 16: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1178);
                                    if (!(23 >= _localctx._p)) throw new FailedPredicateException(this, "23 >= $_p");
                                    setState(1179);
                                    match(71);
                                    setState(1180);
                                    match(29);
                                    setState(1181);
                                    match(Identifier);
                                    setState(1182);
                                    match(45);
                                    setState(1184);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                                        {
                                            setState(1183);
                                            expressionList();
                                        }
                                    }

                                    setState(1186);
                                    match(22);
                                }
                                break;

                                case 17: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1187);
                                    if (!(22 >= _localctx._p)) throw new FailedPredicateException(this, "22 >= $_p");
                                    setState(1188);
                                    match(71);
                                    setState(1189);
                                    match(18);
                                    setState(1190);
                                    match(71);
                                    setState(1191);
                                    match(Identifier);
                                    setState(1193);
                                    switch (getInterpreter().adaptivePredict(_input, 139, _ctx)) {
                                        case 1: {
                                            setState(1192);
                                            arguments();
                                        }
                                        break;
                                    }
                                }
                                break;

                                case 18: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1195);
                                    if (!(21 >= _localctx._p)) throw new FailedPredicateException(this, "21 >= $_p");
                                    setState(1196);
                                    match(71);
                                    setState(1197);
                                    explicitGenericInvocation();
                                }
                                break;

                                case 19: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1198);
                                    if (!(20 >= _localctx._p)) throw new FailedPredicateException(this, "20 >= $_p");
                                    setState(1199);
                                    match(4);
                                    setState(1200);
                                    expression(0);
                                    setState(1201);
                                    match(37);
                                }
                                break;

                                case 20: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1203);
                                    if (!(19 >= _localctx._p)) throw new FailedPredicateException(this, "19 >= $_p");
                                    setState(1204);
                                    match(45);
                                    setState(1206);
                                    _la = _input.LA(1);
                                    if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                                        {
                                            setState(1205);
                                            expressionList();
                                        }
                                    }

                                    setState(1208);
                                    match(22);
                                }
                                break;

                                case 21: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1209);
                                    if (!(18 >= _localctx._p)) throw new FailedPredicateException(this, "18 >= $_p");
                                    setState(1210);
                                    _la = _input.LA(1);
                                    if (!(_la == 6 || _la == 67)) {
                                        _errHandler.recoverInline(this);
                                    }
                                    consume();
                                }
                                break;

                                case 22: {
                                    _localctx = new ExpressionContext(_parentctx, _parentState, _p);
                                    pushNewRecursionContext(_localctx, _startState, RULE_expression);
                                    setState(1211);
                                    if (!(9 >= _localctx._p)) throw new FailedPredicateException(this, "9 >= $_p");
                                    setState(1212);
                                    match(89);
                                    setState(1213);
                                    typeRef();
                                }
                                break;
                            }
                        }
                    }
                    setState(1218);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 142, _ctx);
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            unrollRecursionContexts(_parentctx);
        }
        return _localctx;
    }

    public static class PrimaryContext extends ParserRuleContext {
        public ExpressionContext expression() {
            return getRuleContext(ExpressionContext.class, 0);
        }

        public TypeRefContext typeRef() {
            return getRuleContext(TypeRefContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public LiteralContext literal() {
            return getRuleContext(LiteralContext.class, 0);
        }

        public PrimaryContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_primary;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterPrimary(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitPrimary(this);
        }
    }

    public final PrimaryContext primary() throws RecognitionException {
        PrimaryContext _localctx = new PrimaryContext(_ctx, getState());
        enterRule(_localctx, 174, RULE_primary);
        try {
            setState(1234);
            switch (getInterpreter().adaptivePredict(_input, 143, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1219);
                    match(45);
                    setState(1220);
                    expression(0);
                    setState(1221);
                    match(22);
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1223);
                    match(79);
                }
                break;

                case 3:
                    enterOuterAlt(_localctx, 3);
                {
                    setState(1224);
                    match(18);
                }
                break;

                case 4:
                    enterOuterAlt(_localctx, 4);
                {
                    setState(1225);
                    literal();
                }
                break;

                case 5:
                    enterOuterAlt(_localctx, 5);
                {
                    setState(1226);
                    match(Identifier);
                }
                break;

                case 6:
                    enterOuterAlt(_localctx, 6);
                {
                    setState(1227);
                    typeRef();
                    setState(1228);
                    match(71);
                    setState(1229);
                    match(30);
                }
                break;

                case 7:
                    enterOuterAlt(_localctx, 7);
                {
                    setState(1231);
                    match(53);
                    setState(1232);
                    match(71);
                    setState(1233);
                    match(30);
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CreatorContext extends ParserRuleContext {
        public CreatedNameContext createdName() {
            return getRuleContext(CreatedNameContext.class, 0);
        }

        public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
            return getRuleContext(NonWildcardTypeArgumentsContext.class, 0);
        }

        public ClassCreatorRestContext classCreatorRest() {
            return getRuleContext(ClassCreatorRestContext.class, 0);
        }

        public ArrayCreatorRestContext arrayCreatorRest() {
            return getRuleContext(ArrayCreatorRestContext.class, 0);
        }

        public CreatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_creator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterCreator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitCreator(this);
        }
    }

    public final CreatorContext creator() throws RecognitionException {
        CreatorContext _localctx = new CreatorContext(_ctx, getState());
        enterRule(_localctx, 176, RULE_creator);
        try {
            setState(1245);
            switch (_input.LA(1)) {
                case 5:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1236);
                    nonWildcardTypeArguments();
                    setState(1237);
                    createdName();
                    setState(1238);
                    classCreatorRest();
                }
                break;
                case 10:
                case 12:
                case 14:
                case 15:
                case 35:
                case 36:
                case 49:
                case 69:
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1240);
                    createdName();
                    setState(1243);
                    switch (_input.LA(1)) {
                        case 4: {
                            setState(1241);
                            arrayCreatorRest();
                        }
                        break;
                        case 45: {
                            setState(1242);
                            classCreatorRest();
                        }
                        break;
                        default:
                            throw new NoViableAltException(this);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class CreatedNameContext extends ParserRuleContext {
        public TypeArgumentsContext typeArguments(int i) {
            return getRuleContext(TypeArgumentsContext.class, i);
        }

        public TerminalNode Identifier(int i) {
            return getToken(Java7Parser.Identifier, i);
        }

        public PrimitiveTypeContext primitiveType() {
            return getRuleContext(PrimitiveTypeContext.class, 0);
        }

        public List<TypeArgumentsContext> typeArguments() {
            return getRuleContexts(TypeArgumentsContext.class);
        }

        public List<TerminalNode> Identifier() {
            return getTokens(Java7Parser.Identifier);
        }

        public CreatedNameContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_createdName;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterCreatedName(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitCreatedName(this);
        }
    }

    public final CreatedNameContext createdName() throws RecognitionException {
        CreatedNameContext _localctx = new CreatedNameContext(_ctx, getState());
        enterRule(_localctx, 178, RULE_createdName);
        int _la;
        try {
            setState(1266);
            switch (_input.LA(1)) {
                case 10:
                case 12:
                case 14:
                case 15:
                case 35:
                case 36:
                case 49:
                case 69:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1247);
                    primitiveType();
                }
                break;
                case Identifier:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1248);
                    match(Identifier);
                    setState(1252);
                    switch (getInterpreter().adaptivePredict(_input, 146, _ctx)) {
                        case 1: {
                            setState(1249);
                            typeArguments();
                        }
                        break;

                        case 2: {
                            setState(1250);
                            match(5);
                            setState(1251);
                            match(81);
                        }
                        break;
                    }
                    setState(1263);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == 71) {
                        {
                            {
                                setState(1254);
                                match(71);
                                setState(1255);
                                match(Identifier);
                                setState(1259);
                                switch (getInterpreter().adaptivePredict(_input, 147, _ctx)) {
                                    case 1: {
                                        setState(1256);
                                        typeArguments();
                                    }
                                    break;

                                    case 2: {
                                        setState(1257);
                                        match(5);
                                        setState(1258);
                                        match(81);
                                    }
                                    break;
                                }
                            }
                        }
                        setState(1265);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                }
                break;
                default:
                    throw new NoViableAltException(this);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class InnerCreatorContext extends ParserRuleContext {
        public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
            return getRuleContext(NonWildcardTypeArgumentsContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ClassCreatorRestContext classCreatorRest() {
            return getRuleContext(ClassCreatorRestContext.class, 0);
        }

        public InnerCreatorContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_innerCreator;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterInnerCreator(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitInnerCreator(this);
        }
    }

    public final InnerCreatorContext innerCreator() throws RecognitionException {
        InnerCreatorContext _localctx = new InnerCreatorContext(_ctx, getState());
        enterRule(_localctx, 180, RULE_innerCreator);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1271);
                switch (getInterpreter().adaptivePredict(_input, 150, _ctx)) {
                    case 1: {
                        setState(1268);
                        nonWildcardTypeArguments();
                    }
                    break;

                    case 2: {
                        setState(1269);
                        match(5);
                        setState(1270);
                        match(81);
                    }
                    break;
                }
                setState(1273);
                match(Identifier);
                setState(1274);
                classCreatorRest();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ExplicitGenericInvocationContext extends ParserRuleContext {
        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public NonWildcardTypeArgumentsContext nonWildcardTypeArguments() {
            return getRuleContext(NonWildcardTypeArgumentsContext.class, 0);
        }

        public TerminalNode Identifier() {
            return getToken(Java7Parser.Identifier, 0);
        }

        public ExplicitGenericInvocationContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_explicitGenericInvocation;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterExplicitGenericInvocation(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitExplicitGenericInvocation(this);
        }
    }

    public final ExplicitGenericInvocationContext explicitGenericInvocation() throws RecognitionException {
        ExplicitGenericInvocationContext _localctx = new ExplicitGenericInvocationContext(_ctx, getState());
        enterRule(_localctx, 182, RULE_explicitGenericInvocation);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1276);
                nonWildcardTypeArguments();
                setState(1277);
                match(Identifier);
                setState(1278);
                arguments();
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ArrayCreatorRestContext extends ParserRuleContext {
        public List<ExpressionContext> expression() {
            return getRuleContexts(ExpressionContext.class);
        }

        public ExpressionContext expression(int i) {
            return getRuleContext(ExpressionContext.class, i);
        }

        public ArrayInitializerContext arrayInitializer() {
            return getRuleContext(ArrayInitializerContext.class, 0);
        }

        public ArrayCreatorRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arrayCreatorRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterArrayCreatorRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitArrayCreatorRest(this);
        }
    }

    public final ArrayCreatorRestContext arrayCreatorRest() throws RecognitionException {
        ArrayCreatorRestContext _localctx = new ArrayCreatorRestContext(_ctx, getState());
        enterRule(_localctx, 184, RULE_arrayCreatorRest);
        int _la;
        try {
            int _alt;
            setState(1309);
            switch (getInterpreter().adaptivePredict(_input, 154, _ctx)) {
                case 1:
                    enterOuterAlt(_localctx, 1);
                {
                    setState(1280);
                    match(4);
                    setState(1281);
                    match(37);
                    setState(1286);
                    _errHandler.sync(this);
                    _la = _input.LA(1);
                    while (_la == 4) {
                        {
                            {
                                setState(1282);
                                match(4);
                                setState(1283);
                                match(37);
                            }
                        }
                        setState(1288);
                        _errHandler.sync(this);
                        _la = _input.LA(1);
                    }
                    setState(1289);
                    arrayInitializer();
                }
                break;

                case 2:
                    enterOuterAlt(_localctx, 2);
                {
                    setState(1290);
                    match(4);
                    setState(1291);
                    expression(0);
                    setState(1292);
                    match(37);
                    setState(1299);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 152, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(1293);
                                    match(4);
                                    setState(1294);
                                    expression(0);
                                    setState(1295);
                                    match(37);
                                }
                            }
                        }
                        setState(1301);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 152, _ctx);
                    }
                    setState(1306);
                    _errHandler.sync(this);
                    _alt = getInterpreter().adaptivePredict(_input, 153, _ctx);
                    while (_alt != 2 && _alt != -1) {
                        if (_alt == 1) {
                            {
                                {
                                    setState(1302);
                                    match(4);
                                    setState(1303);
                                    match(37);
                                }
                            }
                        }
                        setState(1308);
                        _errHandler.sync(this);
                        _alt = getInterpreter().adaptivePredict(_input, 153, _ctx);
                    }
                }
                break;
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ClassCreatorRestContext extends ParserRuleContext {
        public ArgumentsContext arguments() {
            return getRuleContext(ArgumentsContext.class, 0);
        }

        public ClassBodyContext classBody() {
            return getRuleContext(ClassBodyContext.class, 0);
        }

        public ClassCreatorRestContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_classCreatorRest;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterClassCreatorRest(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitClassCreatorRest(this);
        }
    }

    public final ClassCreatorRestContext classCreatorRest() throws RecognitionException {
        ClassCreatorRestContext _localctx = new ClassCreatorRestContext(_ctx, getState());
        enterRule(_localctx, 186, RULE_classCreatorRest);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1311);
                arguments();
                setState(1313);
                switch (getInterpreter().adaptivePredict(_input, 155, _ctx)) {
                    case 1: {
                        setState(1312);
                        classBody();
                    }
                    break;
                }
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class NonWildcardTypeArgumentsContext extends ParserRuleContext {
        public TypeListContext typeList() {
            return getRuleContext(TypeListContext.class, 0);
        }

        public NonWildcardTypeArgumentsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_nonWildcardTypeArguments;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterNonWildcardTypeArguments(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitNonWildcardTypeArguments(this);
        }
    }

    public final NonWildcardTypeArgumentsContext nonWildcardTypeArguments() throws RecognitionException {
        NonWildcardTypeArgumentsContext _localctx = new NonWildcardTypeArgumentsContext(_ctx, getState());
        enterRule(_localctx, 188, RULE_nonWildcardTypeArguments);
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1315);
                match(5);
                setState(1316);
                typeList();
                setState(1317);
                match(81);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public static class ArgumentsContext extends ParserRuleContext {
        public ExpressionListContext expressionList() {
            return getRuleContext(ExpressionListContext.class, 0);
        }

        public ArgumentsContext(ParserRuleContext parent, int invokingState) {
            super(parent, invokingState);
        }

        @Override
        public int getRuleIndex() {
            return RULE_arguments;
        }

        @Override
        public void enterRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).enterArguments(this);
        }

        @Override
        public void exitRule(ParseTreeListener listener) {
            if (listener instanceof Java7Listener) ((Java7Listener) listener).exitArguments(this);
        }
    }

    public final ArgumentsContext arguments() throws RecognitionException {
        ArgumentsContext _localctx = new ArgumentsContext(_ctx, getState());
        enterRule(_localctx, 190, RULE_arguments);
        int _la;
        try {
            enterOuterAlt(_localctx, 1);
            {
                setState(1319);
                match(45);
                setState(1321);
                _la = _input.LA(1);
                if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << 6) | (1L << 7) | (1L << 10) | (1L << 12) | (1L << 14) | (1L << 15) | (1L << 18) | (1L << 26) | (1L << 29) | (1L << 34) | (1L << 35) | (1L << 36) | (1L << 44) | (1L << 45) | (1L << 49) | (1L << 53))) != 0) || ((((_la - 65)) & ~0x3f) == 0 && ((1L << (_la - 65)) & ((1L << (65 - 65)) | (1L << (67 - 65)) | (1L << (69 - 65)) | (1L << (72 - 65)) | (1L << (79 - 65)) | (1L << (88 - 65)) | (1L << (HexLiteral - 65)) | (1L << (DecimalLiteral - 65)) | (1L << (OctalLiteral - 65)) | (1L << (BinaryLiteral - 65)) | (1L << (FloatingPointLiteral - 65)) | (1L << (CharacterLiteral - 65)) | (1L << (StringLiteral - 65)) | (1L << (Identifier - 65)))) != 0)) {
                    {
                        setState(1320);
                        expressionList();
                    }
                }

                setState(1323);
                match(22);
            }
        } catch (RecognitionException re) {
            _localctx.exception = re;
            _errHandler.reportError(this, re);
            _errHandler.recover(this, re);
        } finally {
            exitRule();
        }
        return _localctx;
    }

    public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
        switch (ruleIndex) {
            case 86:
                return expression_sempred((ExpressionContext) _localctx, predIndex);
        }
        return true;
    }

    private boolean expression_sempred(ExpressionContext _localctx, int predIndex) {
        switch (predIndex) {
            case 0:
                return 13 >= _localctx._p;

            case 1:
                return 12 >= _localctx._p;

            case 2:
                return 11 >= _localctx._p;

            case 3:
                return 10 >= _localctx._p;

            case 4:
                return 8 >= _localctx._p;

            case 5:
                return 7 >= _localctx._p;

            case 6:
                return 6 >= _localctx._p;

            case 7:
                return 5 >= _localctx._p;

            case 8:
                return 4 >= _localctx._p;

            case 9:
                return 3 >= _localctx._p;

            case 10:
                return 1 >= _localctx._p;

            case 11:
                return 2 >= _localctx._p;

            case 12:
                return 26 >= _localctx._p;

            case 13:
                return 25 >= _localctx._p;

            case 14:
                return 24 >= _localctx._p;

            case 15:
                return 23 >= _localctx._p;

            case 17:
                return 21 >= _localctx._p;

            case 16:
                return 22 >= _localctx._p;

            case 19:
                return 19 >= _localctx._p;

            case 18:
                return 20 >= _localctx._p;

            case 21:
                return 9 >= _localctx._p;

            case 20:
                return 18 >= _localctx._p;
        }
        return true;
    }

    public static final String _serializedATN =
            "\2\3h\u0530\4\2\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4" +
                    "\t\t\t\4\n\t\n\4\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20" +
                    "\4\21\t\21\4\22\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27" +
                    "\4\30\t\30\4\31\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36" +
                    "\4\37\t\37\4 \t \4!\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4" +
                    ")\t)\4*\t*\4+\t+\4,\t,\4-\t-\4.\t.\4/\t/\4\60\t\60\4\61\t\61\4\62\t\62" +
                    "\4\63\t\63\4\64\t\64\4\65\t\65\4\66\t\66\4\67\t\67\48\t8\49\t9\4:\t:\4" +
                    ";\t;\4<\t<\4=\t=\4>\t>\4?\t?\4@\t@\4A\tA\4B\tB\4C\tC\4D\tD\4E\tE\4F\t" +
                    "F\4G\tG\4H\tH\4I\tI\4J\tJ\4K\tK\4L\tL\4M\tM\4N\tN\4O\tO\4P\tP\4Q\tQ\4" +
                    "R\tR\4S\tS\4T\tT\4U\tU\4V\tV\4W\tW\4X\tX\4Y\tY\4Z\tZ\4[\t[\4\\\t\\\4]" +
                    "\t]\4^\t^\4_\t_\4`\t`\4a\ta\3\2\5\2\u00c4\n\2\3\2\7\2\u00c7\n\2\f\2\16" +
                    "\2\u00ca\13\2\3\2\7\2\u00cd\n\2\f\2\16\2\u00d0\13\2\3\2\3\2\3\3\7\3\u00d5" +
                    "\n\3\f\3\16\3\u00d8\13\3\3\3\3\3\3\3\3\3\3\4\3\4\5\4\u00e0\n\4\3\4\3\4" +
                    "\3\4\5\4\u00e5\n\4\3\4\3\4\3\5\3\5\5\5\u00eb\n\5\3\6\7\6\u00ee\n\6\f\6" +
                    "\16\6\u00f1\13\6\3\6\3\6\5\6\u00f5\n\6\3\7\3\7\5\7\u00f9\n\7\3\b\3\b\3" +
                    "\b\5\b\u00fe\n\b\3\b\3\b\5\b\u0102\n\b\3\b\3\b\5\b\u0106\n\b\3\b\3\b\3" +
                    "\t\3\t\3\t\3\t\7\t\u010e\n\t\f\t\16\t\u0111\13\t\3\t\3\t\3\n\3\n\3\n\5" +
                    "\n\u0118\n\n\3\13\3\13\3\13\7\13\u011d\n\13\f\13\16\13\u0120\13\13\3\f" +
                    "\3\f\3\f\3\f\5\f\u0126\n\f\3\f\3\f\5\f\u012a\n\f\3\f\3\f\7\f\u012e\n\f" +
                    "\f\f\16\f\u0131\13\f\5\f\u0133\n\f\3\f\3\f\3\r\3\r\3\r\7\r\u013a\n\r\f" +
                    "\r\16\r\u013d\13\r\3\r\5\r\u0140\n\r\3\r\5\r\u0143\n\r\3\16\3\16\5\16" +
                    "\u0147\n\16\3\17\3\17\3\17\5\17\u014c\n\17\3\17\3\17\5\17\u0150\n\17\3" +
                    "\17\3\17\3\20\3\20\7\20\u0156\n\20\f\20\16\20\u0159\13\20\3\20\3\20\3" +
                    "\21\3\21\7\21\u015f\n\21\f\21\16\21\u0162\13\21\3\21\3\21\7\21\u0166\n" +
                    "\21\f\21\16\21\u0169\13\21\3\21\3\21\3\22\3\22\7\22\u016f\n\22\f\22\16" +
                    "\22\u0172\13\22\3\22\3\22\5\22\u0176\n\22\3\22\5\22\u0179\n\22\3\23\3" +
                    "\23\3\23\3\23\3\23\5\23\u0180\n\23\3\24\3\24\3\24\3\24\3\25\3\25\7\25" +
                    "\u0188\n\25\f\25\16\25\u018b\13\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26" +
                    "\5\26\u0194\n\26\3\27\7\27\u0197\n\27\f\27\16\27\u019a\13\27\3\27\3\27" +
                    "\5\27\u019e\n\27\3\27\5\27\u01a1\n\27\3\30\3\30\3\30\7\30\u01a6\n\30\f" +
                    "\30\16\30\u01a9\13\30\3\31\3\31\3\31\3\31\7\31\u01af\n\31\f\31\16\31\u01b2" +
                    "\13\31\3\31\3\31\3\32\3\32\3\32\7\32\u01b9\n\32\f\32\16\32\u01bc\13\32" +
                    "\3\32\3\32\3\32\5\32\u01c1\n\32\5\32\u01c3\n\32\3\33\3\33\3\33\3\33\3" +
                    "\33\3\33\3\33\5\33\u01cc\n\33\3\34\5\34\u01cf\n\34\3\34\3\34\5\34\u01d3" +
                    "\n\34\3\34\3\34\3\34\3\34\7\34\u01d9\n\34\f\34\16\34\u01dc\13\34\3\34" +
                    "\3\34\5\34\u01e0\n\34\3\34\3\34\5\34\u01e4\n\34\3\35\5\35\u01e7\n\35\3" +
                    "\35\3\35\3\35\3\35\5\35\u01ed\n\35\3\35\3\35\5\35\u01f1\n\35\3\35\7\35" +
                    "\u01f4\n\35\f\35\16\35\u01f7\13\35\3\35\3\35\3\36\3\36\5\36\u01fd\n\36" +
                    "\3\37\3\37\3\37\3\37\3 \3 \3 \3 \5 \u0207\n \3!\3!\3!\7!\u020c\n!\f!\16" +
                    "!\u020f\13!\3!\3!\5!\u0213\n!\3!\3!\3\"\3\"\3\"\5\"\u021a\n\"\3\"\3\"" +
                    "\3\"\3#\3#\3#\5#\u0222\n#\3#\3#\3$\3$\3$\3%\3%\3%\7%\u022c\n%\f%\16%\u022f" +
                    "\13%\3&\3&\3&\5&\u0234\n&\3\'\3\'\3\'\7\'\u0239\n\'\f\'\16\'\u023c\13" +
                    "\'\3(\3(\7(\u0240\n(\f(\16(\u0243\13(\3(\3(\3(\3)\3)\3)\7)\u024b\n)\f" +
                    ")\16)\u024e\13)\3*\3*\5*\u0252\n*\3+\3+\3+\3+\7+\u0258\n+\f+\16+\u025b" +
                    "\13+\3+\5+\u025e\n+\5+\u0260\n+\3+\3+\3,\3,\3,\3,\3,\3,\3,\3,\3,\3,\3" +
                    ",\3,\5,\u0270\n,\3-\3-\3.\3.\3/\3/\3\60\3\60\3\60\7\60\u027b\n\60\f\60" +
                    "\16\60\u027e\13\60\3\60\3\60\3\60\7\60\u0283\n\60\f\60\16\60\u0286\13" +
                    "\60\5\60\u0288\n\60\3\61\3\61\5\61\u028c\n\61\3\61\3\61\3\61\5\61\u0291" +
                    "\n\61\7\61\u0293\n\61\f\61\16\61\u0296\13\61\3\62\3\62\3\63\3\63\3\63" +
                    "\7\63\u029d\n\63\f\63\16\63\u02a0\13\63\3\64\3\64\5\64\u02a4\n\64\3\64" +
                    "\3\64\3\65\7\65\u02a9\n\65\f\65\16\65\u02ac\13\65\3\65\3\65\3\65\3\66" +
                    "\3\66\3\66\3\66\3\66\5\66\u02b6\n\66\5\66\u02b8\n\66\3\67\3\67\38\58\u02bd" +
                    "\n8\38\38\38\38\38\38\38\58\u02c6\n8\38\38\38\38\58\u02cc\n8\39\39\39" +
                    "\79\u02d1\n9\f9\169\u02d4\139\3:\3:\3:\3:\3:\3:\5:\u02dc\n:\3;\3;\3<\3" +
                    "<\3=\3=\3=\3=\3=\5=\u02e7\n=\3=\5=\u02ea\n=\3>\3>\3>\7>\u02ef\n>\f>\16" +
                    ">\u02f2\13>\3?\3?\3?\7?\u02f7\n?\f?\16?\u02fa\13?\3@\3@\3@\3@\3A\3A\3" +
                    "A\5A\u0303\nA\3B\3B\3B\3B\7B\u0309\nB\fB\16B\u030c\13B\5B\u030e\nB\3B" +
                    "\5B\u0311\nB\3B\3B\3C\3C\3C\3C\3C\7C\u031a\nC\fC\16C\u031d\13C\3C\7C\u0320" +
                    "\nC\fC\16C\u0323\13C\3C\3C\3D\3D\3D\5D\u032a\nD\3D\3D\3D\3D\5D\u0330\n" +
                    "D\3D\3D\5D\u0334\nD\3D\3D\5D\u0338\nD\3D\3D\5D\u033c\nD\5D\u033e\nD\3" +
                    "E\3E\3E\3E\5E\u0344\nE\3F\3F\3F\3G\7G\u034a\nG\fG\16G\u034d\13G\3G\3G" +
                    "\3G\3H\3H\3H\3H\3H\5H\u0357\nH\3H\3H\3H\3H\3H\3H\3H\5H\u0360\nH\3H\3H" +
                    "\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H\3H" +
                    "\3H\3H\5H\u037d\nH\3H\3H\3H\3H\3H\3H\3H\5H\u0386\nH\3H\3H\3H\5H\u038b" +
                    "\nH\3H\3H\3H\3H\3H\3H\3H\3H\5H\u0395\nH\3I\3I\3I\3I\3I\3I\7I\u039d\nI" +
                    "\fI\16I\u03a0\13I\3I\3I\5I\u03a4\nI\3I\3I\3I\6I\u03a9\nI\rI\16I\u03aa" +
                    "\3I\3I\5I\u03af\nI\3I\3I\5I\u03b3\nI\5I\u03b5\nI\3J\3J\3J\7J\u03ba\nJ" +
                    "\fJ\16J\u03bd\13J\3J\3J\3J\7J\u03c2\nJ\fJ\16J\u03c5\13J\3J\3J\3J\3J\3" +
                    "K\3K\3K\7K\u03ce\nK\fK\16K\u03d1\13K\3K\5K\u03d4\nK\3L\7L\u03d7\nL\fL" +
                    "\16L\u03da\13L\3L\3L\3L\3L\3L\3M\3M\7M\u03e3\nM\fM\16M\u03e6\13M\3M\7" +
                    "M\u03e9\nM\fM\16M\u03ec\13M\3M\3M\3N\6N\u03f1\nN\rN\16N\u03f2\3N\7N\u03f6" +
                    "\nN\fN\16N\u03f9\13N\3O\3O\3O\3O\3O\3O\3O\3O\3O\3O\5O\u0405\nO\3P\3P\5" +
                    "P\u0409\nP\3P\3P\5P\u040d\nP\3P\3P\5P\u0411\nP\5P\u0413\nP\3Q\3Q\5Q\u0417" +
                    "\nQ\3R\7R\u041a\nR\fR\16R\u041d\13R\3R\3R\3R\3R\3R\3S\3S\3T\3T\3T\3T\3" +
                    "U\3U\3U\7U\u042d\nU\fU\16U\u0430\13U\3V\3V\3W\3W\3X\3X\3X\3X\3X\3X\3X" +
                    "\3X\3X\3X\3X\3X\3X\5X\u0443\nX\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X" +
                    "\3X\5X\u0453\nX\3X\3X\3X\3X\3X\3X\3X\3X\5X\u045d\nX\3X\3X\3X\3X\3X\3X" +
                    "\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X" +
                    "\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\5X\u0486\nX\3X\3X\3X\3X\3X\3X\3X\3X\3X" +
                    "\3X\3X\3X\3X\3X\3X\3X\3X\3X\5X\u049a\nX\3X\3X\3X\3X\3X\3X\3X\5X\u04a3" +
                    "\nX\3X\3X\3X\3X\3X\3X\3X\5X\u04ac\nX\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X\3X" +
                    "\5X\u04b9\nX\3X\3X\3X\3X\3X\3X\7X\u04c1\nX\fX\16X\u04c4\13X\3Y\3Y\3Y\3" +
                    "Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\3Y\5Y\u04d5\nY\3Z\3Z\3Z\3Z\3Z\3Z\3Z\5" +
                    "Z\u04de\nZ\5Z\u04e0\nZ\3[\3[\3[\3[\3[\5[\u04e7\n[\3[\3[\3[\3[\3[\5[\u04ee" +
                    "\n[\7[\u04f0\n[\f[\16[\u04f3\13[\5[\u04f5\n[\3\\\3\\\3\\\5\\\u04fa\n\\" +
                    "\3\\\3\\\3\\\3]\3]\3]\3]\3^\3^\3^\3^\7^\u0507\n^\f^\16^\u050a\13^\3^\3" +
                    "^\3^\3^\3^\3^\3^\3^\7^\u0514\n^\f^\16^\u0517\13^\3^\3^\7^\u051b\n^\f^" +
                    "\16^\u051e\13^\5^\u0520\n^\3_\3_\5_\u0524\n_\3`\3`\3`\3`\3a\3a\5a\u052c" +
                    "\na\3a\3a\3a\2b\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62" +
                    "\64\668:<>@BDFHJLNPRTVXZ\\^`bdfhjlnprtvxz|~\u0080\u0082\u0084\u0086\u0088" +
                    "\u008a\u008c\u008e\u0090\u0092\u0094\u0096\u0098\u009a\u009c\u009e\u00a0" +
                    "\u00a2\u00a4\u00a6\u00a8\u00aa\u00ac\u00ae\u00b0\u00b2\u00b4\u00b6\u00b8" +
                    "\u00ba\u00bc\u00be\u00c0\2\r\4\24\24>>\b\f\f\16\16\20\21%&\63\63GG\4\24" +
                    "\24QQ\3\\_\4\t\tCC\6\b\b..EEJJ\4$$ZZ\5\5\5\26\26XX\4..JJ\4\13\13YY\4\b" +
                    "\bEE\u05c2\2\u00c3\3\2\2\2\4\u00d6\3\2\2\2\6\u00dd\3\2\2\2\b\u00ea\3\2" +
                    "\2\2\n\u00ef\3\2\2\2\f\u00f8\3\2\2\2\16\u00fa\3\2\2\2\20\u0109\3\2\2\2" +
                    "\22\u0114\3\2\2\2\24\u0119\3\2\2\2\26\u0121\3\2\2\2\30\u0142\3\2\2\2\32" +
                    "\u0146\3\2\2\2\34\u0148\3\2\2\2\36\u0153\3\2\2\2 \u015c\3\2\2\2\"\u0178" +
                    "\3\2\2\2$\u017f\3\2\2\2&\u0181\3\2\2\2(\u0185\3\2\2\2*\u0193\3\2\2\2," +
                    "\u0198\3\2\2\2.\u01a2\3\2\2\2\60\u01aa\3\2\2\2\62\u01c2\3\2\2\2\64\u01cb" +
                    "\3\2\2\2\66\u01ce\3\2\2\28\u01e6\3\2\2\2:\u01fc\3\2\2\2<\u01fe\3\2\2\2" +
                    ">\u0206\3\2\2\2@\u0208\3\2\2\2B\u0216\3\2\2\2D\u021e\3\2\2\2F\u0225\3" +
                    "\2\2\2H\u0228\3\2\2\2J\u0230\3\2\2\2L\u0235\3\2\2\2N\u0241\3\2\2\2P\u0247" +
                    "\3\2\2\2R\u0251\3\2\2\2T\u0253\3\2\2\2V\u026f\3\2\2\2X\u0271\3\2\2\2Z" +
                    "\u0273\3\2\2\2\\\u0275\3\2\2\2^\u0287\3\2\2\2`\u0289\3\2\2\2b\u0297\3" +
                    "\2\2\2d\u0299\3\2\2\2f\u02a1\3\2\2\2h\u02aa\3\2\2\2j\u02b7\3\2\2\2l\u02b9" +
                    "\3\2\2\2n\u02cb\3\2\2\2p\u02cd\3\2\2\2r\u02db\3\2\2\2t\u02dd\3\2\2\2v" +
                    "\u02df\3\2\2\2x\u02e1\3\2\2\2z\u02eb\3\2\2\2|\u02f3\3\2\2\2~\u02fb\3\2" +
                    "\2\2\u0080\u0302\3\2\2\2\u0082\u0304\3\2\2\2\u0084\u0314\3\2\2\2\u0086" +
                    "\u033d\3\2\2\2\u0088\u033f\3\2\2\2\u008a\u0345\3\2\2\2\u008c\u034b\3\2" +
                    "\2\2\u008e\u0394\3\2\2\2\u0090\u03b4\3\2\2\2\u0092\u03b6\3\2\2\2\u0094" +
                    "\u03ca\3\2\2\2\u0096\u03d8\3\2\2\2\u0098\u03e0\3\2\2\2\u009a\u03f0\3\2" +
                    "\2\2\u009c\u0404\3\2\2\2\u009e\u0412\3\2\2\2\u00a0\u0416\3\2\2\2\u00a2" +
                    "\u041b\3\2\2\2\u00a4\u0423\3\2\2\2\u00a6\u0425\3\2\2\2\u00a8\u0429\3\2" +
                    "\2\2\u00aa\u0431\3\2\2\2\u00ac\u0433\3\2\2\2\u00ae\u0442\3\2\2\2\u00b0" +
                    "\u04d4\3\2\2\2\u00b2\u04df\3\2\2\2\u00b4\u04f4\3\2\2\2\u00b6\u04f9\3\2" +
                    "\2\2\u00b8\u04fe\3\2\2\2\u00ba\u051f\3\2\2\2\u00bc\u0521\3\2\2\2\u00be" +
                    "\u0525\3\2\2\2\u00c0\u0529\3\2\2\2\u00c2\u00c4\5\4\3\2\u00c3\u00c2\3\2" +
                    "\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c8\3\2\2\2\u00c5\u00c7\5\6\4\2\u00c6" +
                    "\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2" +
                    "\2\2\u00c9\u00ce\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cd\5\b\5\2\u00cc" +
                    "\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2" +
                    "\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7\1\2\2\u00d2" +
                    "\3\3\2\2\2\u00d3\u00d5\5x=\2\u00d4\u00d3\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6" +
                    "\u00d4\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2" +
                    "\2\2\u00d9\u00da\78\2\2\u00da\u00db\5p9\2\u00db\u00dc\7O\2\2\u00dc\5\3" +
                    "\2\2\2\u00dd\u00df\7F\2\2\u00de\u00e0\7D\2\2\u00df\u00de\3\2\2\2\u00df" +
                    "\u00e0\3\2\2\2\u00e0\u00e1\3\2\2\2\u00e1\u00e4\5p9\2\u00e2\u00e3\7I\2" +
                    "\2\u00e3\u00e5\7\5\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e6" +
                    "\3\2\2\2\u00e6\u00e7\7O\2\2\u00e7\7\3\2\2\2\u00e8\u00eb\5\n\6\2\u00e9" +
                    "\u00eb\7O\2\2\u00ea\u00e8\3\2\2\2\u00ea\u00e9\3\2\2\2\u00eb\t\3\2\2\2" +
                    "\u00ec\u00ee\5V,\2\u00ed\u00ec\3\2\2\2\u00ee\u00f1\3\2\2\2\u00ef\u00ed" +
                    "\3\2\2\2\u00ef\u00f0\3\2\2\2\u00f0\u00f4\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f2" +
                    "\u00f5\5\f\7\2\u00f3\u00f5\5\32\16\2\u00f4\u00f2\3\2\2\2\u00f4\u00f3\3" +
                    "\2\2\2\u00f5\13\3\2\2\2\u00f6\u00f9\5\16\b\2\u00f7\u00f9\5\26\f\2\u00f8" +
                    "\u00f6\3\2\2\2\u00f8\u00f7\3\2\2\2\u00f9\r\3\2\2\2\u00fa\u00fb\7 \2\2" +
                    "\u00fb\u00fd\7e\2\2\u00fc\u00fe\5\20\t\2\u00fd\u00fc\3\2\2\2\u00fd\u00fe" +
                    "\3\2\2\2\u00fe\u0101\3\2\2\2\u00ff\u0100\7>\2\2\u0100\u0102\5^\60\2\u0101" +
                    "\u00ff\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0105\3\2\2\2\u0103\u0104\7T" +
                    "\2\2\u0104\u0106\5.\30\2\u0105\u0103\3\2\2\2\u0105\u0106\3\2\2\2\u0106" +
                    "\u0107\3\2\2\2\u0107\u0108\5\36\20\2\u0108\17\3\2\2\2\u0109\u010a\7\7" +
                    "\2\2\u010a\u010f\5\22\n\2\u010b\u010c\7,\2\2\u010c\u010e\5\22\n\2\u010d" +
                    "\u010b\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f\u0110\3\2" +
                    "\2\2\u0110\u0112\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0113\7S\2\2\u0113" +
                    "\21\3\2\2\2\u0114\u0117\7e\2\2\u0115\u0116\7>\2\2\u0116\u0118\5\24\13" +
                    "\2\u0117\u0115\3\2\2\2\u0117\u0118\3\2\2\2\u0118\23\3\2\2\2\u0119\u011e" +
                    "\5`\61\2\u011a\u011b\7\4\2\2\u011b\u011d\5`\61\2\u011c\u011a\3\2\2\2\u011d" +
                    "\u0120\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f\3\2\2\2\u011f\25\3\2\2" +
                    "\2\u0120\u011e\3\2\2\2\u0121\u0122\7c\2\2\u0122\u0125\7e\2\2\u0123\u0124" +
                    "\7T\2\2\u0124\u0126\5.\30\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126" +
                    "\u0127\3\2\2\2\u0127\u0129\7:\2\2\u0128\u012a\5\30\r\2\u0129\u0128\3\2" +
                    "\2\2\u0129\u012a\3\2\2\2\u012a\u0132\3\2\2\2\u012b\u012f\7O\2\2\u012c" +
                    "\u012e\5\"\22\2\u012d\u012c\3\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3" +
                    "\2\2\2\u012f\u0130\3\2\2\2\u0130\u0133\3\2\2\2\u0131\u012f\3\2\2\2\u0132" +
                    "\u012b\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\7\17" +
                    "\2\2\u0135\27\3\2\2\2\u0136\u013b\5,\27\2\u0137\u0138\7,\2\2\u0138\u013a" +
                    "\5,\27\2\u0139\u0137\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b" +
                    "\u013c\3\2\2\2\u013c\u013f\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u0140\7," +
                    "\2\2\u013f\u013e\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0143\3\2\2\2\u0141" +
                    "\u0143\7,\2\2\u0142\u0136\3\2\2\2\u0142\u0141\3\2\2\2\u0143\31\3\2\2\2" +
                    "\u0144\u0147\5\34\17\2\u0145\u0147\5\u0084C\2\u0146\u0144\3\2\2\2\u0146" +
                    "\u0145\3\2\2\2\u0147\33\3\2\2\2\u0148\u0149\7\3\2\2\u0149\u014b\7e\2\2" +
                    "\u014a\u014c\5\20\t\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014f" +
                    "\3\2\2\2\u014d\u014e\7>\2\2\u014e\u0150\5.\30\2\u014f\u014d\3\2\2\2\u014f" +
                    "\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u0152\5 \21\2\u0152\35\3\2\2" +
                    "\2\u0153\u0157\7:\2\2\u0154\u0156\5\"\22\2\u0155\u0154\3\2\2\2\u0156\u0159" +
                    "\3\2\2\2\u0157\u0155\3\2\2\2\u0157\u0158\3\2\2\2\u0158\u015a\3\2\2\2\u0159" +
                    "\u0157\3\2\2\2\u015a\u015b\7\17\2\2\u015b\37\3\2\2\2\u015c\u0167\7:\2" +
                    "\2\u015d\u015f\5V,\2\u015e\u015d\3\2\2\2\u015f\u0162\3\2\2\2\u0160\u015e" +
                    "\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0163\3\2\2\2\u0162\u0160\3\2\2\2\u0163" +
                    "\u0166\5\64\33\2\u0164\u0166\7O\2\2\u0165\u0160\3\2\2\2\u0165\u0164\3" +
                    "\2\2\2\u0166\u0169\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2\2\2\u0168" +
                    "\u016a\3\2\2\2\u0169\u0167\3\2\2\2\u016a\u016b\7\17\2\2\u016b!\3\2\2\2" +
                    "\u016c\u0179\7O\2\2\u016d\u016f\5V,\2\u016e\u016d\3\2\2\2\u016f\u0172" +
                    "\3\2\2\2\u0170\u016e\3\2\2\2\u0170\u0171\3\2\2\2\u0171\u0173\3\2\2\2\u0172" +
                    "\u0170\3\2\2\2\u0173\u0179\5$\23\2\u0174\u0176\7D\2\2\u0175\u0174\3\2" +
                    "\2\2\u0175\u0176\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0179\5(\25\2\u0178" +
                    "\u016c\3\2\2\2\u0178\u0170\3\2\2\2\u0178\u0175\3\2\2\2\u0179#\3\2\2\2" +
                    "\u017a\u0180\5\66\34\2\u017b\u0180\5&\24\2\u017c\u0180\58\35\2\u017d\u0180" +
                    "\5\32\16\2\u017e\u0180\5\f\7\2\u017f\u017a\3\2\2\2\u017f\u017b\3\2\2\2" +
                    "\u017f\u017c\3\2\2\2\u017f\u017d\3\2\2\2\u017f\u017e\3\2\2\2\u0180%\3" +
                    "\2\2\2\u0181\u0182\5^\60\2\u0182\u0183\5H%\2\u0183\u0184\7O\2\2\u0184" +
                    "\'\3\2\2\2\u0185\u0189\7:\2\2\u0186\u0188\5*\26\2\u0187\u0186\3\2\2\2" +
                    "\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2\2\2\u018a\u018c" +
                    "\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u018d\7\17\2\2\u018d)\3\2\2\2\u018e" +
                    "\u018f\5\u008cG\2\u018f\u0190\7O\2\2\u0190\u0194\3\2\2\2\u0191\u0194\5" +
                    "\n\6\2\u0192\u0194\5\u008eH\2\u0193\u018e\3\2\2\2\u0193\u0191\3\2\2\2" +
                    "\u0193\u0192\3\2\2\2\u0194+\3\2\2\2\u0195\u0197\5x=\2\u0196\u0195\3\2" +
                    "\2\2\u0197\u019a\3\2\2\2\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199" +
                    "\u019b\3\2\2\2\u019a\u0198\3\2\2\2\u019b\u019d\7e\2\2\u019c\u019e\5\u00c0" +
                    "a\2\u019d\u019c\3\2\2\2\u019d\u019e\3\2\2\2\u019e\u01a0\3\2\2\2\u019f" +
                    "\u01a1\5\36\20\2\u01a0\u019f\3\2\2\2\u01a0\u01a1\3\2\2\2\u01a1-\3\2\2" +
                    "\2\u01a2\u01a7\5`\61\2\u01a3\u01a4\7,\2\2\u01a4\u01a6\5`\61\2\u01a5\u01a3" +
                    "\3\2\2\2\u01a6\u01a9\3\2\2\2\u01a7\u01a5\3\2\2\2\u01a7\u01a8\3\2\2\2\u01a8" +
                    "/\3\2\2\2\u01a9\u01a7\3\2\2\2\u01aa\u01ab\7\7\2\2\u01ab\u01b0\5\62\32" +
                    "\2\u01ac\u01ad\7,\2\2\u01ad\u01af\5\62\32\2\u01ae\u01ac\3\2\2\2\u01af" +
                    "\u01b2\3\2\2\2\u01b0\u01ae\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\3\2" +
                    "\2\2\u01b2\u01b0\3\2\2\2\u01b3\u01b4\7S\2\2\u01b4\61\3\2\2\2\u01b5\u01ba" +
                    "\5`\61\2\u01b6\u01b7\7\6\2\2\u01b7\u01b9\7\'\2\2\u01b8\u01b6\3\2\2\2\u01b9" +
                    "\u01bc\3\2\2\2\u01ba\u01b8\3\2\2\2\u01ba\u01bb\3\2\2\2\u01bb\u01c3\3\2" +
                    "\2\2\u01bc\u01ba\3\2\2\2\u01bd\u01c0\7\65\2\2\u01be\u01bf\t\2\2\2\u01bf" +
                    "\u01c1\5`\61\2\u01c0\u01be\3\2\2\2\u01c0\u01c1\3\2\2\2\u01c1\u01c3\3\2" +
                    "\2\2\u01c2\u01b5\3\2\2\2\u01c2\u01bd\3\2\2\2\u01c3\63\3\2\2\2\u01c4\u01cc" +
                    "\5<\37\2\u01c5\u01cc\5B\"\2\u01c6\u01c7\7\67\2\2\u01c7\u01c8\7e\2\2\u01c8" +
                    "\u01cc\5D#\2\u01c9\u01cc\5\32\16\2\u01ca\u01cc\5\f\7\2\u01cb\u01c4\3\2" +
                    "\2\2\u01cb\u01c5\3\2\2\2\u01cb\u01c6\3\2\2\2\u01cb\u01c9\3\2\2\2\u01cb" +
                    "\u01ca\3\2\2\2\u01cc\65\3\2\2\2\u01cd\u01cf\5\20\t\2\u01ce\u01cd\3\2\2" +
                    "\2\u01ce\u01cf\3\2\2\2\u01cf\u01d2\3\2\2\2\u01d0\u01d3\5^\60\2\u01d1\u01d3" +
                    "\7\67\2\2\u01d2\u01d0\3\2\2\2\u01d2\u01d1\3\2\2\2\u01d3\u01d4\3\2\2\2" +
                    "\u01d4\u01d5\7e\2\2\u01d5\u01da\5f\64\2\u01d6\u01d7\7\6\2\2\u01d7\u01d9" +
                    "\7\'\2\2\u01d8\u01d6\3\2\2\2\u01d9\u01dc\3\2\2\2\u01da\u01d8\3\2\2\2\u01da" +
                    "\u01db\3\2\2\2\u01db\u01df\3\2\2\2\u01dc\u01da\3\2\2\2\u01dd\u01de\7\35" +
                    "\2\2\u01de\u01e0\5d\63\2\u01df\u01dd\3\2\2\2\u01df\u01e0\3\2\2\2\u01e0" +
                    "\u01e3\3\2\2\2\u01e1\u01e4\5l\67\2\u01e2\u01e4\7O\2\2\u01e3\u01e1\3\2" +
                    "\2\2\u01e3\u01e2\3\2\2\2\u01e4\67\3\2\2\2\u01e5\u01e7\5\20\t\2\u01e6\u01e5" +
                    "\3\2\2\2\u01e6\u01e7\3\2\2\2\u01e7\u01e8\3\2\2\2\u01e8\u01e9\7e\2\2\u01e9" +
                    "\u01ec\5f\64\2\u01ea\u01eb\7\35\2\2\u01eb\u01ed\5d\63\2\u01ec\u01ea\3" +
                    "\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee\u01f0\7:\2\2\u01ef" +
                    "\u01f1\5n8\2\u01f0\u01ef\3\2\2\2\u01f0\u01f1\3\2\2\2\u01f1\u01f5\3\2\2" +
                    "\2\u01f2\u01f4\5*\26\2\u01f3\u01f2\3\2\2\2\u01f4\u01f7\3\2\2\2\u01f5\u01f3" +
                    "\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f8\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f8" +
                    "\u01f9\7\17\2\2\u01f99\3\2\2\2\u01fa\u01fd\7@\2\2\u01fb\u01fd\5x=\2\u01fc" +
                    "\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fd;\3\2\2\2\u01fe\u01ff\5^\60\2" +
                    "\u01ff\u0200\7e\2\2\u0200\u0201\5> \2\u0201=\3\2\2\2\u0202\u0203\5L\'" +
                    "\2\u0203\u0204\7O\2\2\u0204\u0207\3\2\2\2\u0205\u0207\5@!\2\u0206\u0202" +
                    "\3\2\2\2\u0206\u0205\3\2\2\2\u0207?\3\2\2\2\u0208\u020d\5f\64\2\u0209" +
                    "\u020a\7\6\2\2\u020a\u020c\7\'\2\2\u020b\u0209\3\2\2\2\u020c\u020f\3\2" +
                    "\2\2\u020d\u020b\3\2\2\2\u020d\u020e\3\2\2\2\u020e\u0212\3\2\2\2\u020f" +
                    "\u020d\3\2\2\2\u0210\u0211\7\35\2\2\u0211\u0213\5d\63\2\u0212\u0210\3" +
                    "\2\2\2\u0212\u0213\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0215\7O\2\2\u0215" +
                    "A\3\2\2\2\u0216\u0219\5\20\t\2\u0217\u021a\5^\60\2\u0218\u021a\7\67\2" +
                    "\2\u0219\u0217\3\2\2\2\u0219\u0218\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021c" +
                    "\7e\2\2\u021c\u021d\5@!\2\u021dC\3\2\2\2\u021e\u0221\5f\64\2\u021f\u0220" +
                    "\7\35\2\2\u0220\u0222\5d\63\2\u0221\u021f\3\2\2\2\u0221\u0222\3\2\2\2" +
                    "\u0222\u0223\3\2\2\2\u0223\u0224\7O\2\2\u0224E\3\2\2\2\u0225\u0226\7e" +
                    "\2\2\u0226\u0227\5N(\2\u0227G\3\2\2\2\u0228\u022d\5J&\2\u0229\u022a\7" +
                    ",\2\2\u022a\u022c\5J&\2\u022b\u0229\3\2\2\2\u022c\u022f\3\2\2\2\u022d" +
                    "\u022b\3\2\2\2\u022d\u022e\3\2\2\2\u022eI\3\2\2\2\u022f\u022d\3\2\2\2" +
                    "\u0230\u0233\5P)\2\u0231\u0232\7\33\2\2\u0232\u0234\5R*\2\u0233\u0231" +
                    "\3\2\2\2\u0233\u0234\3\2\2\2\u0234K\3\2\2\2\u0235\u023a\5N(\2\u0236\u0237" +
                    "\7,\2\2\u0237\u0239\5F$\2\u0238\u0236\3\2\2\2\u0239\u023c\3\2\2\2\u023a" +
                    "\u0238\3\2\2\2\u023a\u023b\3\2\2\2\u023bM\3\2\2\2\u023c\u023a\3\2\2\2" +
                    "\u023d\u023e\7\6\2\2\u023e\u0240\7\'\2\2\u023f\u023d\3\2\2\2\u0240\u0243" +
                    "\3\2\2\2\u0241\u023f\3\2\2\2\u0241\u0242\3\2\2\2\u0242\u0244\3\2\2\2\u0243" +
                    "\u0241\3\2\2\2\u0244\u0245\7\33\2\2\u0245\u0246\5R*\2\u0246O\3\2\2\2\u0247" +
                    "\u024c\7e\2\2\u0248\u0249\7\6\2\2\u0249\u024b\7\'\2\2\u024a\u0248\3\2" +
                    "\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2\2\2\u024c\u024d\3\2\2\2\u024d" +
                    "Q\3\2\2\2\u024e\u024c\3\2\2\2\u024f\u0252\5T+\2\u0250\u0252\5\u00aeX\2" +
                    "\u0251\u024f\3\2\2\2\u0251\u0250\3\2\2\2\u0252S\3\2\2\2\u0253\u025f\7" +
                    ":\2\2\u0254\u0259\5R*\2\u0255\u0256\7,\2\2\u0256\u0258\5R*\2\u0257\u0255" +
                    "\3\2\2\2\u0258\u025b\3\2\2\2\u0259\u0257\3\2\2\2\u0259\u025a\3\2\2\2\u025a" +
                    "\u025d\3\2\2\2\u025b\u0259\3\2\2\2\u025c\u025e\7,\2\2\u025d\u025c\3\2" +
                    "\2\2\u025d\u025e\3\2\2\2\u025e\u0260\3\2\2\2\u025f\u0254\3\2\2\2\u025f" +
                    "\u0260\3\2\2\2\u0260\u0261\3\2\2\2\u0261\u0262\7\17\2\2\u0262U\3\2\2\2" +
                    "\u0263\u0270\5x=\2\u0264\u0270\7)\2\2\u0265\u0270\7K\2\2\u0266\u0270\7" +
                    "\64\2\2\u0267\u0270\7D\2\2\u0268\u0270\7\r\2\2\u0269\u0270\7@\2\2\u026a" +
                    "\u0270\7<\2\2\u026b\u0270\7+\2\2\u026c\u0270\7#\2\2\u026d\u0270\7N\2\2" +
                    "\u026e\u0270\7\22\2\2\u026f\u0263\3\2\2\2\u026f\u0264\3\2\2\2\u026f\u0265" +
                    "\3\2\2\2\u026f\u0266\3\2\2\2\u026f\u0267\3\2\2\2\u026f\u0268\3\2\2\2\u026f" +
                    "\u0269\3\2\2\2\u026f\u026a\3\2\2\2\u026f\u026b\3\2\2\2\u026f\u026c\3\2" +
                    "\2\2\u026f\u026d\3\2\2\2\u026f\u026e\3\2\2\2\u0270W\3\2\2\2\u0271\u0272" +
                    "\5p9\2\u0272Y\3\2\2\2\u0273\u0274\7e\2\2\u0274[\3\2\2\2\u0275\u0276\5" +
                    "p9\2\u0276]\3\2\2\2\u0277\u027c\5`\61\2\u0278\u0279\7\6\2\2\u0279\u027b" +
                    "\7\'\2\2\u027a\u0278\3\2\2\2\u027b\u027e\3\2\2\2\u027c\u027a\3\2\2\2\u027c" +
                    "\u027d\3\2\2\2\u027d\u0288\3\2\2\2\u027e\u027c\3\2\2\2\u027f\u0284\5b" +
                    "\62\2\u0280\u0281\7\6\2\2\u0281\u0283\7\'\2\2\u0282\u0280\3\2\2\2\u0283" +
                    "\u0286\3\2\2\2\u0284\u0282\3\2\2\2\u0284\u0285\3\2\2\2\u0285\u0288\3\2" +
                    "\2\2\u0286\u0284\3\2\2\2\u0287\u0277\3\2\2\2\u0287\u027f\3\2\2\2\u0288" +
                    "_\3\2\2\2\u0289\u028b\7e\2\2\u028a\u028c\5\60\31\2\u028b\u028a\3\2\2\2" +
                    "\u028b\u028c\3\2\2\2\u028c\u0294\3\2\2\2\u028d\u028e\7I\2\2\u028e\u0290" +
                    "\7e\2\2\u028f\u0291\5\60\31\2\u0290\u028f\3\2\2\2\u0290\u0291\3\2\2\2" +
                    "\u0291\u0293\3\2\2\2\u0292\u028d\3\2\2\2\u0293\u0296\3\2\2\2\u0294\u0292" +
                    "\3\2\2\2\u0294\u0295\3\2\2\2\u0295a\3\2\2\2\u0296\u0294\3\2\2\2\u0297" +
                    "\u0298\t\3\2\2\u0298c\3\2\2\2\u0299\u029e\5p9\2\u029a\u029b\7,\2\2\u029b" +
                    "\u029d\5p9\2\u029c\u029a\3\2\2\2\u029d\u02a0\3\2\2\2\u029e\u029c\3\2\2" +
                    "\2\u029e\u029f\3\2\2\2\u029fe\3\2\2\2\u02a0\u029e\3\2\2\2\u02a1\u02a3" +
                    "\7/\2\2\u02a2\u02a4\5h\65\2\u02a3\u02a2\3\2\2\2\u02a3\u02a4\3\2\2\2\u02a4" +
                    "\u02a5\3\2\2\2\u02a5\u02a6\7\30\2\2\u02a6g\3\2\2\2\u02a7\u02a9\5:\36\2" +
                    "\u02a8\u02a7\3\2\2\2\u02a9\u02ac\3\2\2\2\u02aa\u02a8\3\2\2\2\u02aa\u02ab" +
                    "\3\2\2\2\u02ab\u02ad\3\2\2\2\u02ac\u02aa\3\2\2\2\u02ad\u02ae\5^\60\2\u02ae" +
                    "\u02af\5j\66\2\u02afi\3\2\2\2\u02b0\u02b1\79\2\2\u02b1\u02b8\5P)\2\u02b2" +
                    "\u02b5\5P)\2\u02b3\u02b4\7,\2\2\u02b4\u02b6\5h\65\2\u02b5\u02b3\3\2\2" +
                    "\2\u02b5\u02b6\3\2\2\2\u02b6\u02b8\3\2\2\2\u02b7\u02b0\3\2\2\2\u02b7\u02b2" +
                    "\3\2\2\2\u02b8k\3\2\2\2\u02b9\u02ba\5(\25\2\u02bam\3\2\2\2\u02bb\u02bd" +
                    "\5\u00be`\2\u02bc\u02bb\3\2\2\2\u02bc\u02bd\3\2\2\2\u02bd\u02be\3\2\2" +
                    "\2\u02be\u02bf\t\4\2\2\u02bf\u02c0\5\u00c0a\2\u02c0\u02c1\7O\2\2\u02c1" +
                    "\u02cc\3\2\2\2\u02c2\u02c3\5\u00b0Y\2\u02c3\u02c5\7I\2\2\u02c4\u02c6\5" +
                    "\u00be`\2\u02c5\u02c4\3\2\2\2\u02c5\u02c6\3\2\2\2\u02c6\u02c7\3\2\2\2" +
                    "\u02c7\u02c8\7\24\2\2\u02c8\u02c9\5\u00c0a\2\u02c9\u02ca\7O\2\2\u02ca" +
                    "\u02cc\3\2\2\2\u02cb\u02bc\3\2\2\2\u02cb\u02c2\3\2\2\2\u02cco\3\2\2\2" +
                    "\u02cd\u02d2\7e\2\2\u02ce\u02cf\7I\2\2\u02cf\u02d1\7e\2\2\u02d0\u02ce" +
                    "\3\2\2\2\u02d1\u02d4\3\2\2\2\u02d2\u02d0\3\2\2\2\u02d2\u02d3\3\2\2\2\u02d3" +
                    "q\3\2\2\2\u02d4\u02d2\3\2\2\2\u02d5\u02dc\5t;\2\u02d6\u02dc\7`\2\2\u02d7" +
                    "\u02dc\7a\2\2\u02d8\u02dc\7b\2\2\u02d9\u02dc\5v<\2\u02da\u02dc\7\34\2" +
                    "\2\u02db\u02d5\3\2\2\2\u02db\u02d6\3\2\2\2\u02db\u02d7\3\2\2\2\u02db\u02d8" +
                    "\3\2\2\2\u02db\u02d9\3\2\2\2\u02db\u02da\3\2\2\2\u02dcs\3\2\2\2\u02dd" +
                    "\u02de\t\5\2\2\u02deu\3\2\2\2\u02df\u02e0\t\6\2\2\u02e0w\3\2\2\2\u02e1" +
                    "\u02e2\7\32\2\2\u02e2\u02e9\5z>\2\u02e3\u02e6\7/\2\2\u02e4\u02e7\5|?\2" +
                    "\u02e5\u02e7\5\u0080A\2\u02e6\u02e4\3\2\2\2\u02e6\u02e5\3\2\2\2\u02e6" +
                    "\u02e7\3\2\2\2\u02e7\u02e8\3\2\2\2\u02e8\u02ea\7\30\2\2\u02e9\u02e3\3" +
                    "\2\2\2\u02e9\u02ea\3\2\2\2\u02eay\3\2\2\2\u02eb\u02f0\7e\2\2\u02ec\u02ed" +
                    "\7I\2\2\u02ed\u02ef\7e\2\2\u02ee\u02ec\3\2\2\2\u02ef\u02f2\3\2\2\2\u02f0" +
                    "\u02ee\3\2\2\2\u02f0\u02f1\3\2\2\2\u02f1{\3\2\2\2\u02f2\u02f0\3\2\2\2" +
                    "\u02f3\u02f8\5~@\2\u02f4\u02f5\7,\2\2\u02f5\u02f7\5~@\2\u02f6\u02f4\3" +
                    "\2\2\2\u02f7\u02fa\3\2\2\2\u02f8\u02f6\3\2\2\2\u02f8\u02f9\3\2\2\2\u02f9" +
                    "}\3\2\2\2\u02fa\u02f8\3\2\2\2\u02fb\u02fc\7e\2\2\u02fc\u02fd\7\33\2\2" +
                    "\u02fd\u02fe\5\u0080A\2\u02fe\177\3\2\2\2\u02ff\u0303\5\u00aeX\2\u0300" +
                    "\u0303\5x=\2\u0301\u0303\5\u0082B\2\u0302\u02ff\3\2\2\2\u0302\u0300\3" +
                    "\2\2\2\u0302\u0301\3\2\2\2\u0303\u0081\3\2\2\2\u0304\u030d\7:\2\2\u0305" +
                    "\u030a\5\u0080A\2\u0306\u0307\7,\2\2\u0307\u0309\5\u0080A\2\u0308\u0306" +
                    "\3\2\2\2\u0309\u030c\3\2\2\2\u030a\u0308\3\2\2\2\u030a\u030b\3\2\2\2\u030b" +
                    "\u030e\3\2\2\2\u030c\u030a\3\2\2\2\u030d\u0305\3\2\2\2\u030d\u030e\3\2" +
                    "\2\2\u030e\u0310\3\2\2\2\u030f\u0311\7,\2\2\u0310\u030f\3\2\2\2\u0310" +
                    "\u0311\3\2\2\2\u0311\u0312\3\2\2\2\u0312\u0313\7\17\2\2\u0313\u0083\3" +
                    "\2\2\2\u0314\u0315\7\32\2\2\u0315\u0316\7\3\2\2\u0316\u0317\7e\2\2\u0317" +
                    "\u0321\7:\2\2\u0318\u031a\5V,\2\u0319\u0318\3\2\2\2\u031a\u031d\3\2\2" +
                    "\2\u031b\u0319\3\2\2\2\u031b\u031c\3\2\2\2\u031c\u031e\3\2\2\2\u031d\u031b" +
                    "\3\2\2\2\u031e\u0320\5\u0086D\2\u031f\u031b\3\2\2\2\u0320\u0323\3\2\2" +
                    "\2\u0321\u031f\3\2\2\2\u0321\u0322\3\2\2\2\u0322\u0324\3\2\2\2\u0323\u0321" +
                    "\3\2\2\2\u0324\u0325\7\17\2\2\u0325\u0085\3\2\2\2\u0326\u0329\5^\60\2" +
                    "\u0327\u032a\5\u0088E\2\u0328\u032a\5H%\2\u0329\u0327\3\2\2\2\u0329\u0328" +
                    "\3\2\2\2\u032a\u032b\3\2\2\2\u032b\u032c\7O\2\2\u032c\u033e\3\2\2\2\u032d" +
                    "\u032f\5\f\7\2\u032e\u0330\7O\2\2\u032f\u032e\3\2\2\2\u032f\u0330\3\2" +
                    "\2\2\u0330\u033e\3\2\2\2\u0331\u0333\5\34\17\2\u0332\u0334\7O\2\2\u0333" +
                    "\u0332\3\2\2\2\u0333\u0334\3\2\2\2\u0334\u033e\3\2\2\2\u0335\u0337\5\26" +
                    "\f\2\u0336\u0338\7O\2\2\u0337\u0336\3\2\2\2\u0337\u0338\3\2\2\2\u0338" +
                    "\u033e\3\2\2\2\u0339\u033b\5\u0084C\2\u033a\u033c\7O\2\2\u033b\u033a\3" +
                    "\2\2\2\u033b\u033c\3\2\2\2\u033c\u033e\3\2\2\2\u033d\u0326\3\2\2\2\u033d" +
                    "\u032d\3\2\2\2\u033d\u0331\3\2\2\2\u033d\u0335\3\2\2\2\u033d\u0339\3\2" +
                    "\2\2\u033e\u0087\3\2\2\2\u033f\u0340\7e\2\2\u0340\u0341\7/\2\2\u0341\u0343" +
                    "\7\30\2\2\u0342\u0344\5\u008aF\2\u0343\u0342\3\2\2\2\u0343\u0344\3\2\2" +
                    "\2\u0344\u0089\3\2\2\2\u0345\u0346\7*\2\2\u0346\u0347\5\u0080A\2\u0347" +
                    "\u008b\3\2\2\2\u0348\u034a\5:\36\2\u0349\u0348\3\2\2\2\u034a\u034d\3\2" +
                    "\2\2\u034b\u0349\3\2\2\2\u034b\u034c\3\2\2\2\u034c\u034e\3\2\2\2\u034d" +
                    "\u034b\3\2\2\2\u034e\u034f\5^\60\2\u034f\u0350\5H%\2\u0350\u008d\3\2\2" +
                    "\2\u0351\u0395\5(\25\2\u0352\u0353\7d\2\2\u0353\u0356\5\u00aeX\2\u0354" +
                    "\u0355\7\60\2\2\u0355\u0357\5\u00aeX\2\u0356\u0354\3\2\2\2\u0356\u0357" +
                    "\3\2\2\2\u0357\u0358\3\2\2\2\u0358\u0359\7O\2\2\u0359\u0395\3\2\2\2\u035a" +
                    "\u035b\7\61\2\2\u035b\u035c\5\u00a6T\2\u035c\u035f\5\u008eH\2\u035d\u035e" +
                    "\7A\2\2\u035e\u0360\5\u008eH\2\u035f\u035d\3\2\2\2\u035f\u0360\3\2\2\2" +
                    "\u0360\u0395\3\2\2\2\u0361\u0362\7L\2\2\u0362\u0363\7/\2\2\u0363\u0364" +
                    "\5\u009eP\2\u0364\u0365\7\30\2\2\u0365\u0366\5\u008eH\2\u0366\u0395\3" +
                    "\2\2\2\u0367\u0368\7-\2\2\u0368\u0369\5\u00a6T\2\u0369\u036a\5\u008eH" +
                    "\2\u036a\u0395\3\2\2\2\u036b\u036c\7\25\2\2\u036c\u036d\5\u008eH\2\u036d" +
                    "\u036e\7-\2\2\u036e\u036f\5\u00a6T\2\u036f\u0370\7O\2\2\u0370\u0395\3" +
                    "\2\2\2\u0371\u0395\5\u0090I\2\u0372\u0373\7V\2\2\u0373\u0374\5\u00a6T" +
                    "\2\u0374\u0375\5\u0098M\2\u0375\u0395\3\2\2\2\u0376\u0377\7+\2\2\u0377" +
                    "\u0378\5\u00a6T\2\u0378\u0379\5(\25\2\u0379\u0395\3\2\2\2\u037a\u037c" +
                    "\7M\2\2\u037b\u037d\5\u00aeX\2\u037c\u037b\3\2\2\2\u037c\u037d\3\2\2\2" +
                    "\u037d\u037e\3\2\2\2\u037e\u0395\7O\2\2\u037f\u0380\7\31\2\2\u0380\u0381" +
                    "\5\u00aeX\2\u0381\u0382\7O\2\2\u0382\u0395\3\2\2\2\u0383\u0385\7;\2\2" +
                    "\u0384\u0386\7e\2\2\u0385\u0384\3\2\2\2\u0385\u0386\3\2\2\2\u0386\u0387" +
                    "\3\2\2\2\u0387\u0395\7O\2\2\u0388\u038a\7\n\2\2\u0389\u038b\7e\2\2\u038a" +
                    "\u0389\3\2\2\2\u038a\u038b\3\2\2\2\u038b\u038c\3\2\2\2\u038c\u0395\7O" +
                    "\2\2\u038d\u0395\7O\2\2\u038e\u038f\5\u00aaV\2\u038f\u0390\7O\2\2\u0390" +
                    "\u0395\3\2\2\2\u0391\u0392\7e\2\2\u0392\u0393\7\60\2\2\u0393\u0395\5\u008e" +
                    "H\2\u0394\u0351\3\2\2\2\u0394\u0352\3\2\2\2\u0394\u035a\3\2\2\2\u0394" +
                    "\u0361\3\2\2\2\u0394\u0367\3\2\2\2\u0394\u036b\3\2\2\2\u0394\u0371\3\2" +
                    "\2\2\u0394\u0372\3\2\2\2\u0394\u0376\3\2\2\2\u0394\u037a\3\2\2\2\u0394" +
                    "\u037f\3\2\2\2\u0394\u0383\3\2\2\2\u0394\u0388\3\2\2\2\u0394\u038d\3\2" +
                    "\2\2\u0394\u038e\3\2\2\2\u0394\u0391\3\2\2\2\u0395\u008f\3\2\2\2\u0396" +
                    "\u0397\7\66\2\2\u0397\u0398\7/\2\2\u0398\u0399\5\u0094K\2\u0399\u039a" +
                    "\7\30\2\2\u039a\u039e\5(\25\2\u039b\u039d\5\u0092J\2\u039c\u039b\3\2\2" +
                    "\2\u039d\u03a0\3\2\2\2\u039e\u039c\3\2\2\2\u039e\u039f\3\2\2\2\u039f\u03a3" +
                    "\3\2\2\2\u03a0\u039e\3\2\2\2\u03a1\u03a2\7!\2\2\u03a2\u03a4\5(\25\2\u03a3" +
                    "\u03a1\3\2\2\2\u03a3\u03a4\3\2\2\2\u03a4\u03b5\3\2\2\2\u03a5\u03a6\7\66" +
                    "\2\2\u03a6\u03b2\5(\25\2\u03a7\u03a9\5\u0092J\2\u03a8\u03a7\3\2\2\2\u03a9" +
                    "\u03aa\3\2\2\2\u03aa\u03a8\3\2\2\2\u03aa\u03ab\3\2\2\2\u03ab\u03ae\3\2" +
                    "\2\2\u03ac\u03ad\7!\2\2\u03ad\u03af\5(\25\2\u03ae\u03ac\3\2\2\2\u03ae" +
                    "\u03af\3\2\2\2\u03af\u03b3\3\2\2\2\u03b0\u03b1\7!\2\2\u03b1\u03b3\5(\25" +
                    "\2\u03b2\u03a8\3\2\2\2\u03b2\u03b0\3\2\2\2\u03b3\u03b5\3\2\2\2\u03b4\u0396" +
                    "\3\2\2\2\u03b4\u03a5\3\2\2\2\u03b5\u0091\3\2\2\2\u03b6\u03b7\7B\2\2\u03b7" +
                    "\u03bb\7/\2\2\u03b8\u03ba\5:\36\2\u03b9\u03b8\3\2\2\2\u03ba\u03bd\3\2" +
                    "\2\2\u03bb\u03b9\3\2\2\2\u03bb\u03bc\3\2\2\2\u03bc\u03be\3\2\2\2\u03bd" +
                    "\u03bb\3\2\2\2\u03be\u03c3\5\\/\2\u03bf\u03c0\7\"\2\2\u03c0\u03c2\5\\" +
                    "/\2\u03c1\u03bf\3\2\2\2\u03c2\u03c5\3\2\2\2\u03c3\u03c1\3\2\2\2\u03c3" +
                    "\u03c4\3\2\2\2\u03c4\u03c6\3\2\2\2\u03c5\u03c3\3\2\2\2\u03c6\u03c7\7e" +
                    "\2\2\u03c7\u03c8\7\30\2\2\u03c8\u03c9\5(\25\2\u03c9\u0093\3\2\2\2\u03ca" +
                    "\u03cf\5\u0096L\2\u03cb\u03cc\7O\2\2\u03cc\u03ce\5\u0096L\2\u03cd\u03cb" +
                    "\3\2\2\2\u03ce\u03d1\3\2\2\2\u03cf\u03cd\3\2\2\2\u03cf\u03d0\3\2\2\2\u03d0" +
                    "\u03d3\3\2\2\2\u03d1\u03cf\3\2\2\2\u03d2\u03d4\7O\2\2\u03d3\u03d2\3\2" +
                    "\2\2\u03d3\u03d4\3\2\2\2\u03d4\u0095\3\2\2\2\u03d5\u03d7\5:\36\2\u03d6" +
                    "\u03d5\3\2\2\2\u03d7\u03da\3\2\2\2\u03d8\u03d6\3\2\2\2\u03d8\u03d9\3\2" +
                    "\2\2\u03d9\u03db\3\2\2\2\u03da\u03d8\3\2\2\2\u03db\u03dc\5`\61\2\u03dc" +
                    "\u03dd\5P)\2\u03dd\u03de\7\33\2\2\u03de\u03df\5\u00aeX\2\u03df\u0097\3" +
                    "\2\2\2\u03e0\u03e4\7:\2\2\u03e1\u03e3\5\u009aN\2\u03e2\u03e1\3\2\2\2\u03e3" +
                    "\u03e6\3\2\2\2\u03e4\u03e2\3\2\2\2\u03e4\u03e5\3\2\2\2\u03e5\u03ea\3\2" +
                    "\2\2\u03e6\u03e4\3\2\2\2\u03e7\u03e9\5\u009cO\2\u03e8\u03e7\3\2\2\2\u03e9" +
                    "\u03ec\3\2\2\2\u03ea\u03e8\3\2\2\2\u03ea\u03eb\3\2\2\2\u03eb\u03ed\3\2" +
                    "\2\2\u03ec\u03ea\3\2\2\2\u03ed\u03ee\7\17\2\2\u03ee\u0099\3\2\2\2\u03ef" +
                    "\u03f1\5\u009cO\2\u03f0\u03ef\3\2\2\2\u03f1\u03f2\3\2\2\2\u03f2\u03f0" +
                    "\3\2\2\2\u03f2\u03f3\3\2\2\2\u03f3\u03f7\3\2\2\2\u03f4\u03f6\5*\26\2\u03f5" +
                    "\u03f4\3\2\2\2\u03f6\u03f9\3\2\2\2\u03f7\u03f5\3\2\2\2\u03f7\u03f8\3\2" +
                    "\2\2\u03f8\u009b\3\2\2\2\u03f9\u03f7\3\2\2\2\u03fa\u03fb\7\23\2\2\u03fb" +
                    "\u03fc\5\u00acW\2\u03fc\u03fd\7\60\2\2\u03fd\u0405\3\2\2\2\u03fe\u03ff" +
                    "\7\23\2\2\u03ff\u0400\5Z.\2\u0400\u0401\7\60\2\2\u0401\u0405\3\2\2\2\u0402" +
                    "\u0403\7*\2\2\u0403\u0405\7\60\2\2\u0404\u03fa\3\2\2\2\u0404\u03fe\3\2" +
                    "\2\2\u0404\u0402\3\2\2\2\u0405\u009d\3\2\2\2\u0406\u0413\5\u00a2R\2\u0407" +
                    "\u0409\5\u00a0Q\2\u0408\u0407\3\2\2\2\u0408\u0409\3\2\2\2\u0409\u040a" +
                    "\3\2\2\2\u040a\u040c\7O\2\2\u040b\u040d\5\u00aeX\2\u040c\u040b\3\2\2\2" +
                    "\u040c\u040d\3\2\2\2\u040d\u040e\3\2\2\2\u040e\u0410\7O\2\2\u040f\u0411" +
                    "\5\u00a4S\2\u0410\u040f\3\2\2\2\u0410\u0411\3\2\2\2\u0411\u0413\3\2\2" +
                    "\2\u0412\u0406\3\2\2\2\u0412\u0408\3\2\2\2\u0413\u009f\3\2\2\2\u0414\u0417" +
                    "\5\u008cG\2\u0415\u0417\5\u00a8U\2\u0416\u0414\3\2\2\2\u0416\u0415\3\2" +
                    "\2\2\u0417\u00a1\3\2\2\2\u0418\u041a\5:\36\2\u0419\u0418\3\2\2\2\u041a" +
                    "\u041d\3\2\2\2\u041b\u0419\3\2\2\2\u041b\u041c\3\2\2\2\u041c\u041e\3\2" +
                    "\2\2\u041d\u041b\3\2\2\2\u041e\u041f\5^\60\2\u041f\u0420\7e\2\2\u0420" +
                    "\u0421\7\60\2\2\u0421\u0422\5\u00aeX\2\u0422\u00a3\3\2\2\2\u0423\u0424" +
                    "\5\u00a8U\2\u0424\u00a5\3\2\2\2\u0425\u0426\7/\2\2\u0426\u0427\5\u00ae" +
                    "X\2\u0427\u0428\7\30\2\2\u0428\u00a7\3\2\2\2\u0429\u042e\5\u00aeX\2\u042a" +
                    "\u042b\7,\2\2\u042b\u042d\5\u00aeX\2\u042c\u042a\3\2\2\2\u042d\u0430\3" +
                    "\2\2\2\u042e\u042c\3\2\2\2\u042e\u042f\3\2\2\2\u042f\u00a9\3\2\2\2\u0430" +
                    "\u042e\3\2\2\2\u0431\u0432\5\u00aeX\2\u0432\u00ab\3\2\2\2\u0433\u0434" +
                    "\5\u00aeX\2\u0434\u00ad\3\2\2\2\u0435\u0436\bX\1\2\u0436\u0437\t\7\2\2" +
                    "\u0437\u0443\5\u00aeX\2\u0438\u0439\t\b\2\2\u0439\u0443\5\u00aeX\2\u043a" +
                    "\u043b\7/\2\2\u043b\u043c\5^\60\2\u043c\u043d\7\30\2\2\u043d\u043e\5\u00ae" +
                    "X\2\u043e\u0443\3\2\2\2\u043f\u0443\5\u00b0Y\2\u0440\u0441\7\37\2\2\u0441" +
                    "\u0443\5\u00b2Z\2\u0442\u0435\3\2\2\2\u0442\u0438\3\2\2\2\u0442\u043a" +
                    "\3\2\2\2\u0442\u043f\3\2\2\2\u0442\u0440\3\2\2\2\u0443\u04c2\3\2\2\2\u0444" +
                    "\u0445\6X\2\3\u0445\u0446\t\t\2\2\u0446\u04c1\5\u00aeX\2\u0447\u0448\6" +
                    "X\3\3\u0448\u0449\t\n\2\2\u0449\u04c1\5\u00aeX\2\u044a\u0452\6X\4\3\u044b" +
                    "\u044c\7\7\2\2\u044c\u0453\7\7\2\2\u044d\u044e\7S\2\2\u044e\u044f\7S\2" +
                    "\2\u044f\u0453\7S\2\2\u0450\u0451\7S\2\2\u0451\u0453\7S\2\2\u0452\u044b" +
                    "\3\2\2\2\u0452\u044d\3\2\2\2\u0452\u0450\3\2\2\2\u0453\u0454\3\2\2\2\u0454" +
                    "\u04c1\5\u00aeX\2\u0455\u045c\6X\5\3\u0456\u0457\7\7\2\2\u0457\u045d\7" +
                    "\33\2\2\u0458\u0459\7S\2\2\u0459\u045d\7\33\2\2\u045a\u045d\7S\2\2\u045b" +
                    "\u045d\7\7\2\2\u045c\u0456\3\2\2\2\u045c\u0458\3\2\2\2\u045c\u045a\3\2" +
                    "\2\2\u045c\u045b\3\2\2\2\u045d\u045e\3\2\2\2\u045e\u04c1\5\u00aeX\2\u045f" +
                    "\u0460\6X\6\3\u0460\u0461\t\13\2\2\u0461\u04c1\5\u00aeX\2\u0462\u0463" +
                    "\6X\7\3\u0463\u0464\7\4\2\2\u0464\u04c1\5\u00aeX\2\u0465\u0466\6X\b\3" +
                    "\u0466\u0467\7H\2\2\u0467\u04c1\5\u00aeX\2\u0468\u0469\6X\t\3\u0469\u046a" +
                    "\7\"\2\2\u046a\u04c1\5\u00aeX\2\u046b\u046c\6X\n\3\u046c\u046d\7P\2\2" +
                    "\u046d\u04c1\5\u00aeX\2\u046e\u046f\6X\13\3\u046f\u0470\7R\2\2\u0470\u04c1" +
                    "\5\u00aeX\2\u0471\u0485\6X\f\3\u0472\u0486\7?\2\2\u0473\u0486\7=\2\2\u0474" +
                    "\u0486\7(\2\2\u0475\u0486\7\27\2\2\u0476\u0486\7W\2\2\u0477\u0486\7\62" +
                    "\2\2\u0478\u0486\7\36\2\2\u0479\u0486\7\33\2\2\u047a\u047b\7S\2\2\u047b" +
                    "\u047c\7S\2\2\u047c\u0486\7\33\2\2\u047d\u047e\7S\2\2\u047e\u047f\7S\2" +
                    "\2\u047f\u0480\7S\2\2\u0480\u0486\7\33\2\2\u0481\u0482\7\7\2\2\u0482\u0483" +
                    "\7\7\2\2\u0483\u0486\7\33\2\2\u0484\u0486\7U\2\2\u0485\u0472\3\2\2\2\u0485" +
                    "\u0473\3\2\2\2\u0485\u0474\3\2\2\2\u0485\u0475\3\2\2\2\u0485\u0476\3\2" +
                    "\2\2\u0485\u0477\3\2\2\2\u0485\u0478\3\2\2\2\u0485\u0479\3\2\2\2\u0485" +
                    "\u047a\3\2\2\2\u0485\u047d\3\2\2\2\u0485\u0481\3\2\2\2\u0485\u0484\3\2" +
                    "\2\2\u0486\u0487\3\2\2\2\u0487\u04c1\5\u00aeX\2\u0488\u0489\6X\r\3\u0489" +
                    "\u048a\7\65\2\2\u048a\u048b\5\u00aeX\2\u048b\u048c\7\60\2\2\u048c\u048d" +
                    "\5\u00aeX\2\u048d\u04c1\3\2\2\2\u048e\u048f\6X\16\3\u048f\u0490\7I\2\2" +
                    "\u0490\u04c1\7e\2\2\u0491\u0492\6X\17\3\u0492\u0493\7I\2\2\u0493\u04c1" +
                    "\7Q\2\2\u0494\u0495\6X\20\3\u0495\u0496\7I\2\2\u0496\u0497\7\24\2\2\u0497" +
                    "\u0499\7/\2\2\u0498\u049a\5\u00a8U\2\u0499\u0498\3\2\2\2\u0499\u049a\3" +
                    "\2\2\2\u049a\u049b\3\2\2\2\u049b\u04c1\7\30\2\2\u049c\u049d\6X\21\3\u049d" +
                    "\u049e\7I\2\2\u049e\u049f\7\37\2\2\u049f\u04a0\7e\2\2\u04a0\u04a2\7/\2" +
                    "\2\u04a1\u04a3\5\u00a8U\2\u04a2\u04a1\3\2\2\2\u04a2\u04a3\3\2\2\2\u04a3" +
                    "\u04a4\3\2\2\2\u04a4\u04c1\7\30\2\2\u04a5\u04a6\6X\22\3\u04a6\u04a7\7" +
                    "I\2\2\u04a7\u04a8\7\24\2\2\u04a8\u04a9\7I\2\2\u04a9\u04ab\7e\2\2\u04aa" +
                    "\u04ac\5\u00c0a\2\u04ab\u04aa\3\2\2\2\u04ab\u04ac\3\2\2\2\u04ac\u04c1" +
                    "\3\2\2\2\u04ad\u04ae\6X\23\3\u04ae\u04af\7I\2\2\u04af\u04c1\5\u00b8]\2" +
                    "\u04b0\u04b1\6X\24\3\u04b1\u04b2\7\6\2\2\u04b2\u04b3\5\u00aeX\2\u04b3" +
                    "\u04b4\7\'\2\2\u04b4\u04c1\3\2\2\2\u04b5\u04b6\6X\25\3\u04b6\u04b8\7/" +
                    "\2\2\u04b7\u04b9\5\u00a8U\2\u04b8\u04b7\3\2\2\2\u04b8\u04b9\3\2\2\2\u04b9" +
                    "\u04ba\3\2\2\2\u04ba\u04c1\7\30\2\2\u04bb\u04bc\6X\26\3\u04bc\u04c1\t" +
                    "\f\2\2\u04bd\u04be\6X\27\3\u04be\u04bf\7[\2\2\u04bf\u04c1\5^\60\2\u04c0" +
                    "\u0444\3\2\2\2\u04c0\u0447\3\2\2\2\u04c0\u044a\3\2\2\2\u04c0\u0455\3\2" +
                    "\2\2\u04c0\u045f\3\2\2\2\u04c0\u0462\3\2\2\2\u04c0\u0465\3\2\2\2\u04c0" +
                    "\u0468\3\2\2\2\u04c0\u046b\3\2\2\2\u04c0\u046e\3\2\2\2\u04c0\u0471\3\2" +
                    "\2\2\u04c0\u0488\3\2\2\2\u04c0\u048e\3\2\2\2\u04c0\u0491\3\2\2\2\u04c0" +
                    "\u0494\3\2\2\2\u04c0\u049c\3\2\2\2\u04c0\u04a5\3\2\2\2\u04c0\u04ad\3\2" +
                    "\2\2\u04c0\u04b0\3\2\2\2\u04c0\u04b5\3\2\2\2\u04c0\u04bb\3\2\2\2\u04c0" +
                    "\u04bd\3\2\2\2\u04c1\u04c4\3\2\2\2\u04c2\u04c0\3\2\2\2\u04c2\u04c3\3\2" +
                    "\2\2\u04c3\u00af\3\2\2\2\u04c4\u04c2\3\2\2\2\u04c5\u04c6\7/\2\2\u04c6" +
                    "\u04c7\5\u00aeX\2\u04c7\u04c8\7\30\2\2\u04c8\u04d5\3\2\2\2\u04c9\u04d5" +
                    "\7Q\2\2\u04ca\u04d5\7\24\2\2\u04cb\u04d5\5r:\2\u04cc\u04d5\7e\2\2\u04cd" +
                    "\u04ce\5^\60\2\u04ce\u04cf\7I\2\2\u04cf\u04d0\7 \2\2\u04d0\u04d5\3\2\2" +
                    "\2\u04d1\u04d2\7\67\2\2\u04d2\u04d3\7I\2\2\u04d3\u04d5\7 \2\2\u04d4\u04c5" +
                    "\3\2\2\2\u04d4\u04c9\3\2\2\2\u04d4\u04ca\3\2\2\2\u04d4\u04cb\3\2\2\2\u04d4" +
                    "\u04cc\3\2\2\2\u04d4\u04cd\3\2\2\2\u04d4\u04d1\3\2\2\2\u04d5\u00b1\3\2" +
                    "\2\2\u04d6\u04d7\5\u00be`\2\u04d7\u04d8\5\u00b4[\2\u04d8\u04d9\5\u00bc" +
                    "_\2\u04d9\u04e0\3\2\2\2\u04da\u04dd\5\u00b4[\2\u04db\u04de\5\u00ba^\2" +
                    "\u04dc\u04de\5\u00bc_\2\u04dd\u04db\3\2\2\2\u04dd\u04dc\3\2\2\2\u04de" +
                    "\u04e0\3\2\2\2\u04df\u04d6\3\2\2\2\u04df\u04da\3\2\2\2\u04e0\u00b3\3\2" +
                    "\2\2\u04e1\u04f5\5b\62\2\u04e2\u04e6\7e\2\2\u04e3\u04e7\5\60\31\2\u04e4" +
                    "\u04e5\7\7\2\2\u04e5\u04e7\7S\2\2\u04e6\u04e3\3\2\2\2\u04e6\u04e4\3\2" +
                    "\2\2\u04e6\u04e7\3\2\2\2\u04e7\u04f1\3\2\2\2\u04e8\u04e9\7I\2\2\u04e9" +
                    "\u04ed\7e\2\2\u04ea\u04ee\5\60\31\2\u04eb\u04ec\7\7\2\2\u04ec\u04ee\7" +
                    "S\2\2\u04ed\u04ea\3\2\2\2\u04ed\u04eb\3\2\2\2\u04ed\u04ee\3\2\2\2\u04ee" +
                    "\u04f0\3\2\2\2\u04ef\u04e8\3\2\2\2\u04f0\u04f3\3\2\2\2\u04f1\u04ef\3\2" +
                    "\2\2\u04f1\u04f2\3\2\2\2\u04f2\u04f5\3\2\2\2\u04f3\u04f1\3\2\2\2\u04f4" +
                    "\u04e1\3\2\2\2\u04f4\u04e2\3\2\2\2\u04f5\u00b5\3\2\2\2\u04f6\u04fa\5\u00be" +
                    "`\2\u04f7\u04f8\7\7\2\2\u04f8\u04fa\7S\2\2\u04f9\u04f6\3\2\2\2\u04f9\u04f7" +
                    "\3\2\2\2\u04f9\u04fa\3\2\2\2\u04fa\u04fb\3\2\2\2\u04fb\u04fc\7e\2\2\u04fc" +
                    "\u04fd\5\u00bc_\2\u04fd\u00b7\3\2\2\2\u04fe\u04ff\5\u00be`\2\u04ff\u0500" +
                    "\7e\2\2\u0500\u0501\5\u00c0a\2\u0501\u00b9\3\2\2\2\u0502\u0503\7\6\2\2" +
                    "\u0503\u0508\7\'\2\2\u0504\u0505\7\6\2\2\u0505\u0507\7\'\2\2\u0506\u0504" +
                    "\3\2\2\2\u0507\u050a\3\2\2\2\u0508\u0506\3\2\2\2\u0508\u0509\3\2\2\2\u0509" +
                    "\u050b\3\2\2\2\u050a\u0508\3\2\2\2\u050b\u0520\5T+\2\u050c\u050d\7\6\2" +
                    "\2\u050d\u050e\5\u00aeX\2\u050e\u0515\7\'\2\2\u050f\u0510\7\6\2\2\u0510" +
                    "\u0511\5\u00aeX\2\u0511\u0512\7\'\2\2\u0512\u0514\3\2\2\2\u0513\u050f" +
                    "\3\2\2\2\u0514\u0517\3\2\2\2\u0515\u0513\3\2\2\2\u0515\u0516\3\2\2\2\u0516" +
                    "\u051c\3\2\2\2\u0517\u0515\3\2\2\2\u0518\u0519\7\6\2\2\u0519\u051b\7\'" +
                    "\2\2\u051a\u0518\3\2\2\2\u051b\u051e\3\2\2\2\u051c\u051a\3\2\2\2\u051c" +
                    "\u051d\3\2\2\2\u051d\u0520\3\2\2\2\u051e\u051c\3\2\2\2\u051f\u0502\3\2" +
                    "\2\2\u051f\u050c\3\2\2\2\u0520\u00bb\3\2\2\2\u0521\u0523\5\u00c0a\2\u0522" +
                    "\u0524\5\36\20\2\u0523\u0522\3\2\2\2\u0523\u0524\3\2\2\2\u0524\u00bd\3" +
                    "\2\2\2\u0525\u0526\7\7\2\2\u0526\u0527\5.\30\2\u0527\u0528\7S\2\2\u0528" +
                    "\u00bf\3\2\2\2\u0529\u052b\7/\2\2\u052a\u052c\5\u00a8U\2\u052b\u052a\3" +
                    "\2\2\2\u052b\u052c\3\2\2\2\u052c\u052d\3\2\2\2\u052d\u052e\7\30\2\2\u052e" +
                    "\u00c1\3\2\2\2\u009f\u00c3\u00c8\u00ce\u00d6\u00df\u00e4\u00ea\u00ef\u00f4" +
                    "\u00f8\u00fd\u0101\u0105\u010f\u0117\u011e\u0125\u0129\u012f\u0132\u013b" +
                    "\u013f\u0142\u0146\u014b\u014f\u0157\u0160\u0165\u0167\u0170\u0175\u0178" +
                    "\u017f\u0189\u0193\u0198\u019d\u01a0\u01a7\u01b0\u01ba\u01c0\u01c2\u01cb" +
                    "\u01ce\u01d2\u01da\u01df\u01e3\u01e6\u01ec\u01f0\u01f5\u01fc\u0206\u020d" +
                    "\u0212\u0219\u0221\u022d\u0233\u023a\u0241\u024c\u0251\u0259\u025d\u025f" +
                    "\u026f\u027c\u0284\u0287\u028b\u0290\u0294\u029e\u02a3\u02aa\u02b5\u02b7" +
                    "\u02bc\u02c5\u02cb\u02d2\u02db\u02e6\u02e9\u02f0\u02f8\u0302\u030a\u030d" +
                    "\u0310\u031b\u0321\u0329\u032f\u0333\u0337\u033b\u033d\u0343\u034b\u0356" +
                    "\u035f\u037c\u0385\u038a\u0394\u039e\u03a3\u03aa\u03ae\u03b2\u03b4\u03bb" +
                    "\u03c3\u03cf\u03d3\u03d8\u03e4\u03ea\u03f2\u03f7\u0404\u0408\u040c\u0410" +
                    "\u0412\u0416\u041b\u042e\u0442\u0452\u045c\u0485\u0499\u04a2\u04ab\u04b8" +
                    "\u04c0\u04c2\u04d4\u04dd\u04df\u04e6\u04ed\u04f1\u04f4\u04f9\u0508\u0515" +
                    "\u051c\u051f\u0523\u052b";
    public static final ATN _ATN =
            ATNSimulator.deserialize(_serializedATN.toCharArray());

    static {
        _decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
    }
}