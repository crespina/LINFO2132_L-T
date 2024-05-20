package compiler.Generator;

import java.util.ArrayList;
import java.util.HashMap;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import static org.objectweb.asm.Opcodes.*;

import compiler.Parser.*;
import compiler.Parser.Number;
import compiler.Semantic.SemanticException;
import compiler.Semantic.SymbolTable;

public class GeneratorVisitor implements GenVisitor{

    private ClassWriter cw;
    
    private MethodVisitor mv;
    public SymbolTable st;

    HashMap<String,Integer> variableMap = new HashMap<String,Integer>();

    public GeneratorVisitor(ClassWriter cw){
        this.cw = cw;
    }

    @Override
    public void visit(ArrayAccess arrayAccess, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(ArrayInit ai, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Bool b, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Comment c, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(ForLoop fl, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(FunctionCall fc, MethodVisitor mv) throws SemanticException {
        if(fc.getFunctionName().startsWith("write")){
            String funcName = "";
            String funcDesc = "";

            if(fc.getFunctionName().endsWith("ln")){ funcName = "println"; funcDesc = "(Ljava/lang/String;)V"; }
            if(fc.getFunctionName().endsWith("ite")){ funcName = "print"; funcDesc = "(Ljava/lang/String;)V"; }
            if(fc.getFunctionName().endsWith("Int")){ funcName = "println"; funcDesc = "(I)V"; }
            if(fc.getFunctionName().endsWith("Float")){ funcName = "println"; funcDesc = "(F)V"; }

            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "out",
                    "Ljava/io/PrintStream;");

            /* Need param typebut param is statement and not parram
            if (fc.getFunctionName().equals("writeFloat") &&  fc.getParams().get(0) .exprType.type.equals("int") && functionTable.get(s.identifier).get(0).type.type.equals("real")) {
                mv.visitInsn(I2F);
            }
            */
            mv.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", funcName,
                    funcDesc, false);
            return;
        }
        if(fc.getFunctionName().startsWith("read")){
            String funcName = "";
            String funcDesc = "";

            if(fc.getFunctionName().endsWith("Int")){ funcName = "nextInt"; funcDesc = "()I"; }
            if(fc.getFunctionName().endsWith("Real")){ funcName = "nextFloat"; funcDesc = "()F"; }
            if(fc.getFunctionName().endsWith("String")){ funcName = "next"; funcDesc = "()Ljava/lang/String;"; }


            mv.visitTypeInsn(NEW, "java/util/Scanner");
            mv.visitInsn(DUP);
            mv.visitFieldInsn(GETSTATIC, "java/lang/System", "in", "Ljava/io/InputStream;");
            mv.visitMethodInsn(INVOKESPECIAL, "java/util/Scanner", "<init>", "(Ljava/io/InputStream;)V", false);

            mv.visitMethodInsn(INVOKEVIRTUAL, "java/util/Scanner", funcName, funcDesc, false);



            return;
        }
    }

    @Override
    public void visit(IfCond ic, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Method m, MethodVisitor mv) throws SemanticException {
        String params = "(";
        ArrayList<Param> parameters = m.getParameters();
        for (Param p : parameters) {
            params += p.getType().getIdentifier();
        }
        params += ")";
        params += m.getReturnType();
        mv = cw.visitMethod(ACC_PUBLIC+ACC_STATIC, m.getIdentifier(),params,null,null);

        // give the right names to the parameters
        for (int i = 0; i < parameters.size(); i++) {
            mv.visitParameter(parameters.get(i).getName(), i);
        }

        mv.visitCode();
        for (Statement s : m.getBody()) {
            // Recursivement
        }

        mv.visitEnd();
        //int nxtId = sit.nxtAvailableIndex();
        mv.visitMaxs(-1, -1);
    }

    @Override
    public void visit(Number n, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Operand od, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Operation op, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Operator or, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Param p, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(ReturnStatement rs, MethodVisitor mv) throws SemanticException {
		// On recupere l'id du return et le nom de la methode a qui il appartient
		int returnId = rs.getId();
        // Name de la methode 
		String methodName = st.getReturn(returnId);
		// On recupere la valeur de retour de la methode
		ArrayList<Param> methodTypes = st.get(methodName);
		Statement s = rs.getReturnStatement();
        Type returnType = methodTypes.get(methodTypes.size()-1).getType();
        mv.visitInsn(IRETURN);
    }

    @Override
    public void visit(StringStmt ss, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Structure s, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(StructureAccess sa, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(StructureInstanciation si, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Type t, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(Variable v, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

    @Override
    public void visit(VariableCreation vc, MethodVisitor mv) throws SemanticException {
        mv.visitLdcInsn(vc.getType());

        // Store the value in the variable
        var slot = variableMap.get(vc.getIdentifier());
        mv.visitVarInsn(Opcodes.ISTORE, slot);
    }

    @Override
    public void visit(WhileLoop wl, MethodVisitor mv) throws SemanticException {
        Label startLabel = new Label();
        Label endLabel = new Label();

        mv.visitLabel(startLabel);

        mv.visitJumpInsn(IFEQ, endLabel);

        for (Statement s : wl.getBody()) {
            // Recursivement
        }

        mv.visitJumpInsn(GOTO,startLabel);
        mv.visitLabel(endLabel);
    }

    @Override
    public void visit(UnaryOperation unaryOperation, MethodVisitor mv) throws SemanticException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'visit'");
    }

}
