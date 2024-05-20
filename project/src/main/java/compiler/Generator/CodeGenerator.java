package compiler.Generator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import compiler.Parser.*;
import compiler.Semantic.*;
import org.objectweb.asm.*;
import static org.objectweb.asm.Opcodes.*;

public class CodeGenerator {

    public ArrayList<Statement> statements;
    GenVisitor symbolVisitor;
    public String className;

    private ClassWriter cw;

    private MethodVisitor mv;

    public CodeGenerator(ArrayList<Statement> statementList,  String className) throws SemanticException {
        this.statements = statementList;
        this.cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        this.symbolVisitor = new GeneratorVisitor(this.cw);
        this.className = className;
    }

    public void generateCode() {
        /* 
        cw.visit(V1_8, ACC_PUBLIC + ACC_SUPER, className, null, "java/lang/Object", null);
        mv = cw.visitMethod(ACC_STATIC, "<clinit>", "()V", null, null);

        for (Statement s : statements) {
            try {
				s.accept(symbolVisitor, mv);
                mv.visitInsn(RETURN);
                mv.visitMaxs(-1, -1);
                mv.visitEnd();
			} catch (SemanticException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }

        cw.visitEnd();
        */
        byte[] bytecode = cw.toByteArray();
        
        try (FileOutputStream outputStream = new FileOutputStream("./" + className + ".class")) {
            outputStream.write(bytecode);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
