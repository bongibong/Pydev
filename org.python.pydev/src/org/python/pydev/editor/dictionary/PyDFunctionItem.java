/*
 * Author: atotic
 * Created on Mar 29, 2004
 * License: Common Public License v1.0
 */
package org.python.pydev.editor.dictionary;

import org.python.parser.ast.FunctionDef;
import org.eclipse.core.runtime.IStatus;
import org.python.pydev.plugin.PydevPlugin;

/**
 *
 * TODO Comment this class
 */
public class PyDFunctionItem extends PyDictionaryItem {

	FunctionDef node;
	
	public PyDFunctionItem(PyDictionaryItem parent, FunctionDef node) {
		super(parent);
		this.node = node;
		PopulateDictionary populator = new PopulateDictionary(this, subItems);
		try {
			PopulateDictionary.FunctionDefTraverse(populator, node);
			//node.traverse(populator);
		} catch (Exception e) {
			PydevPlugin.log(IStatus.ERROR, "Unexpected error populating ClassDef", e);
			e.printStackTrace();
		}
	}

	public String toString() {
		String dic = subItems.toString();
		String dicString = dic.length() == 0 ? " []" : "\n[ "+dic+" ]\n";
		return node.name + " FUNCTION DICTIONARY" + dicString;
	}

}
