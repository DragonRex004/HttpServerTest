package de.dragonrex.commands;

import de.dragonrex.console.command.CommandResponse;
import de.dragonrex.console.command.ICommand;

import java.util.ArrayList;

public class StopCommand implements ICommand {
    @Override
    public String name() {
        return "exit";
    }

    @Override
    public String desc() {
        return "This Command stops the Server";
    }

    @Override
    public CommandResponse onExecute(String name, ArrayList<String> args) {
        System.exit(0);
        return CommandResponse.SUCCESS;
    }
}
