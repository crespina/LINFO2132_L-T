package compiler.Generator;

import org.objectweb.asm.MethodVisitor;

import compiler.Semantic.SemanticException;


public interface GenVisitable {
    public void accept(GenVisitor visitor, MethodVisitor mv) throws SemanticException;
}
