/*
 *  Licensed to the Apache Software Foundation (ASF) under one
 *  or more contributor license agreements.  See the NOTICE file
 *  distributed with this work for additional information
 *  regarding copyright ownership.  The ASF licenses this file
 *  to you under the Apache License, Version 2.0 (the
 *  "License"); you may not use this file except in compliance
 *  with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.apache.groovy.groovysh.util

import org.apache.groovy.groovysh.Command
import org.apache.groovy.groovysh.Shell
import org.apache.groovy.groovysh.commands.AliasCommand
import org.apache.groovy.groovysh.commands.ClearCommand
import org.apache.groovy.groovysh.commands.DisplayCommand
import org.apache.groovy.groovysh.commands.DocCommand
import org.apache.groovy.groovysh.commands.EditCommand
import org.apache.groovy.groovysh.commands.ExitCommand
import org.apache.groovy.groovysh.commands.GrabCommand
import org.apache.groovy.groovysh.commands.HelpCommand
import org.apache.groovy.groovysh.commands.HistoryCommand
import org.apache.groovy.groovysh.commands.ImportCommand
import org.apache.groovy.groovysh.commands.InspectCommand
import org.apache.groovy.groovysh.commands.LoadCommand
import org.apache.groovy.groovysh.commands.PurgeCommand
import org.apache.groovy.groovysh.commands.RecordCommand
import org.apache.groovy.groovysh.commands.RegisterCommand
import org.apache.groovy.groovysh.commands.SaveCommand
import org.apache.groovy.groovysh.commands.SetCommand
import org.apache.groovy.groovysh.commands.ShowCommand

/**
 * Registers {@link Command} classes from an XML file like:
 * <commands>
 *  <command>org.apache.groovy.groovysh.commands.HelpCommand</command>
 * ...
 * </commands>
 */
class DefaultCommandsRegistrar {

    private final Shell shell

    DefaultCommandsRegistrar(final Shell shell) {
        assert shell != null

        this.shell = shell
    }

    void register() {
        def commands = [
            new HelpCommand(shell),
            new ExitCommand(shell),
            new ImportCommand(shell),
            new DisplayCommand(shell),
            new ClearCommand(shell),
            new ShowCommand(shell),
            new InspectCommand(shell),
            new PurgeCommand(shell),
            new EditCommand(shell),
            new LoadCommand(shell),
            new SaveCommand(shell),
            new RecordCommand(shell),
            new HistoryCommand(shell),
            new AliasCommand(shell),
            new SetCommand(shell),
            new GrabCommand(shell),
            new RegisterCommand(shell),
        ]

        if (!System.getProperty("groovysh.disableDocCommand")?.toBoolean()) {
            commands.add(new DocCommand(shell))
        }

        for (Command classname in commands) {
            shell.register(classname)
        }
    }
}
