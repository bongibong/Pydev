/*
 * Created on Jan 14, 2006
 */
package org.python.pydev.core;

import org.python.pydev.core.structure.CompletionRecursionException;





public interface ICompletionState {

    String getActivationToken();

    IPythonNature getNature();

    ICompletionState getCopy();

    void setActivationToken(String string);

    void setBuiltinsGotten(boolean b);

    void raiseNFindTokensOnImportedModsCalled(IModule mod, String tok) throws CompletionRecursionException;
    
    /**
     * @param i: starting at 0
     */
    void setCol(int i);

    /**
     * @param i: starting at 0
     */
    void setLine(int i);

    void setLocalImportsGotten(boolean b);

    boolean getLocalImportsGotten();

    /**
     * @return the line for the request (starting at 0)
     */
    int getLine();

    /**
     * @return the col for the request (starting at 0)
     */
    int getCol();

    void checkDefinitionMemory(IModule module, IDefinition definition) throws CompletionRecursionException;

    void checkWildImportInMemory(IModule current, IModule mod) throws CompletionRecursionException;
    
    public void checkResolveImportMemory(IModule module, String value) throws CompletionRecursionException;

    boolean getBuiltinsGotten();

    void checkMemory(IModule module, String base) throws CompletionRecursionException;

    void checkFindMemory(IModule module, String value) throws CompletionRecursionException;

    void checkFindDefinitionMemory(IModule mod, String tok) throws CompletionRecursionException;
    
    void checkFindModuleCompletionsMemory(IModule mod, String tok) throws CompletionRecursionException;
    
    void checkFindResolveImportMemory(IToken tok) throws CompletionRecursionException;
    
    boolean getIsInCalltip();

    public static final int LOOKING_FOR_INSTANCE_UNDEFINED=0;
    public static final int LOOKING_FOR_INSTANCED_VARIABLE=1;
    public static final int LOOKING_FOR_UNBOUND_VARIABLE=2;
    public static final int LOOKING_FOR_CLASSMETHOD_VARIABLE=3;
    public static final int LOOKING_FOR_ASSIGN = 4;
    
    /**
     * Identifies if we should be looking for an instance (in which case, self should not
     * be added to the parameters -- otherwise, it should)
     */
    void setLookingFor(int b);

    ICompletionState getCopyWithActTok(String value);

    String getQualifier();

    int isLookingFor();

    void setIsInCalltip(boolean isInCalltip);

    ICompletionState getCopyForResolveImportWithActTok(String representation);

    void pushFindResolveImportMemoryCtx();

    void popFindResolveImportMemoryCtx();


}