package javarepl.plugin;

/*
 * Copied from IntelliJ 12 sourcecode and heavily modified
 *
 * Copyright 2000-2010 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.openapi.editor.ex.EditorEx;
import com.intellij.openapi.util.Disposer;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author Gregory.Shrago
 */
public class JavaREPLLanguageConsoleView extends ConsoleViewImpl {
    @NotNull
    protected JavaREPLLanguageConsole myConsole;

    public JavaREPLLanguageConsoleView(@NotNull final JavaREPLLanguageConsole console) {
        super(console.getProject(), true);
        myConsole = console;
        Disposer.register(this, myConsole);
        Disposer.register(getProject(), this);
    }

    @NotNull
    public JavaREPLLanguageConsole getConsole() {
        return myConsole;
    }

    @Override
    protected EditorEx createRealEditor() {
        return myConsole.getHistoryViewer();
    }

    @Override
    protected void disposeEditor() {
    }

    @Override
    protected JComponent createCenterComponent() {
        return myConsole.getComponent();
    }

    @Override
    public JComponent getPreferredFocusableComponent() {
        return myConsole.getConsoleEditor().getContentComponent();
    }
}
