/**
 * Copyright (c) 2005-2012 by Appcelerator, Inc. All Rights Reserved.
 * Licensed under the terms of the Eclipse Public License (EPL).
 * Please see the license.txt included with this distribution for details.
 * Any modifications to this file must keep this entire header intact.
 */
package org.python.pydev.debug.ui;

import java.util.regex.Matcher;

import junit.framework.TestCase;

public class PythonConsoleLineTrackerTest extends TestCase {

    public void testFileMatch() throws Exception {
        // PythonConsoleLineTrackerTest fails because it depends on org.eclipse.debug.ui.console.IConsoleLineTracker
        // being able to be loaded. But IConsoleLineTracker is in a plug-in with an activator that in
        // turn relies on the workbench being loaded, leading to a test error. This isn't a problem
        // when run within Eclipse as a (plain) JUint test because the Activator is skipped.
        // Since the classes under test rely on IConsoleLineTracker, the test must be run as a
        // GUI enabled Plug-in test (i.e workbench started), however if you do that the test fails
        // because of interactions with other services in the workbench.
        //        if (PydevPlugin.getDefault() != null) {
        //            if (SharedCorePlugin.skipKnownFailures()) {
        //                return;
        //            }
        //        }
        Matcher matcher = PythonConsoleLineTracker.regularPythonlinePattern
                .matcher("File \"Y:\\test_python\\src\\mod1\\mod2\\test_it2.py\", line 45, in testAnotherCase");
        assertTrue(matcher.matches());
        String file = matcher.group(1);
        String fileName = matcher.group(2);
        String lineNumber = matcher.group(3);
        assertEquals("File", file);
        assertEquals("Y:\\test_python\\src\\mod1\\mod2\\test_it2.py", fileName);
        assertEquals("45", lineNumber);

        matcher = PythonConsoleLineTracker.regularPythonlinePattern
                .matcher("File \"/home/users/foo/test_it2.py\", line 45, in testAnotherCase");
        assertTrue(matcher.matches());
        fileName = matcher.group(2);
        lineNumber = matcher.group(3);
        assertEquals("/home/users/foo/test_it2.py", fileName);
        assertEquals("45", lineNumber);
    }

    public void testFileMatch2() {
        Matcher matcher = PythonConsoleLineTracker.insideQuotesMatcher1.matcher("sss\"yyy\"zzz");
        assertTrue(matcher.matches());
        assertEquals("yyy", matcher.group(1));
    }

    public void testFileMatch3() {
        Matcher matcher = PythonConsoleLineTracker.insideQuotesMatcher2.matcher("sss'yyy'zzz");
        assertTrue(matcher.matches());
        assertEquals("yyy", matcher.group(1));
    }

    public void testFileMatch4() {
        Matcher matcher = PythonConsoleLineTracker.insideQuotesMatcher2.matcher("sss'yyy:82'zzz");
        assertTrue(matcher.matches());
        assertEquals("yyy:82", matcher.group(1));
    }

}
