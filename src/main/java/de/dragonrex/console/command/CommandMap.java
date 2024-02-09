package de.dragonrex.console.command;

import java.util.HashMap;
import java.util.Map;

public class CommandMap {
    private Map<String, ICommand> commandMap;

    public CommandMap() {
        this.commandMap = new HashMap<>();
    }

    public void register(ICommand command) {
        this.commandMap.put(command.name(), command);
    }

    public void register(ICommand... command) {
            for (ICommand value : command) {
                this.commandMap.put(value.name(), value);
            }
    }

    public void remove(String commandName, ICommand command) {
        this.commandMap.remove(commandName, command);
    }

    public void remove(String commandName) {
        this.commandMap.remove(commandName);
    }

    public void remove(String[] commandName, ICommand[] command) {
        for (String s : commandName) {
            for (ICommand value : command) {
                this.commandMap.remove(s, value);
            }
        }
    }

    public boolean isRegistered(String commandName) {
        return this.commandMap.containsKey(commandName);
    }

    public boolean isRegistered(ICommand command) {
        return this.commandMap.containsValue(command);
    }

    public Map<String, ICommand> getCommandMap() {
        return commandMap;
    }
}
