/*
 * Created on 23/07/2005
 */
package com.python.pydev.analysis.messages;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.python.pydev.core.IToken;

import com.python.pydev.analysis.IAnalysisPreferences;



public class CompositeMessage extends AbstractMessage{

    public CompositeMessage(int type, IToken generator, IAnalysisPreferences prefs) {
        super(type, generator, prefs);
    }

    List<IMessage> msgs = new ArrayList<IMessage>();
    
    public void addMessage(IMessage msg){
        if(!msgs.contains(msg)){
            msgs.add(msg);
        }
    }

    public String getShortMessage() {
        StringBuffer buffer = new StringBuffer();
        
        Set<String> messages = new TreeSet<String>();
        for (Iterator<IMessage> iter = msgs.iterator(); iter.hasNext();) {
            IMessage msg = iter.next();
            messages.add(msg.getShortMessage().toString());
        }
        
        for (Iterator<String> iter = messages.iterator(); iter.hasNext();) {
            buffer.append(iter.next());
            if(iter.hasNext()){
                buffer.append(", ");
            }
        }

        return buffer.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof CompositeMessage)){
            return false;
        }
        CompositeMessage m = (CompositeMessage) obj;
        return m.getMessage().equals(getMessage());
    }

    @Override
    public int hashCode() {
        return getMessage().hashCode();
    }
}
