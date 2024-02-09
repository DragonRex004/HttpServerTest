package de.dragonrex.console;


import de.dragonrex.console.command.CommandMap;
import de.dragonrex.console.command.CommandResponse;
import de.dragonrex.console.command.ICommand;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

public class ConsoleReader {
    private final Logger logger;
    private final CommandMap commandMap;
    private final Console console;
    private final String prompt;

    private boolean isRunning;
    private boolean isDebugMode;

    public ConsoleReader(String prompt) {
        this.logger = new Logger();
        this.commandMap = new CommandMap();
        this.console = System.console();
        this.prompt = prompt;
        this.isDebugMode = false;
    }

    public ConsoleReader(String prompt, boolean isDebugMode) {
        this.logger = new Logger();
        this.commandMap = new CommandMap();
        this.console = System.console();
        this.prompt = prompt;
        this.isDebugMode = isDebugMode;
    }


    private void handleConsole() {
        while (this.isRunning) {
            String line = console.readLine(this.prompt);

            if(isDebugMode)
                this.logger.info("[Debug Mode] " + line, ConsoleColor.PURPLE);

            ArrayList<String> commandArgs = new ArrayList<>(List.of(line.split(" ")));
            String commandName = commandArgs.get(0);
            commandArgs.remove(0);
            if(isDebugMode) {
                this.logger.info("[Command Name] " + commandName, ConsoleColor.YELLOW);
                commandArgs.forEach(arg -> this.logger.info("[Command Arg] " + arg, ConsoleColor.CYAN));
            }

            if(this.commandMap.isRegistered(commandName)) {
                ICommand command = this.commandMap.getCommandMap().get(commandName);
                CommandResponse response = command.onExecute(commandName, commandArgs);
                if(isDebugMode) {
                    this.logger.info("[Debug Mode] " + command.name() + " is Executed");
                    this.logger.info("[Debug Mode] Command Response -> " + response.name());
                }
            } else if (commandName.isBlank()) {
                // empty statement is required
            } else {
                this.logger.error("-> The typed Command is not founded!");
            }
        }
    }
    public void start() {
        isRunning = true;
        handleConsole();
    }

    public Logger getLogger() {
        return logger;
    }

    public CommandMap getCommandMap() {
        return commandMap;
    }
}
