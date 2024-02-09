package de.dragonrex;

import de.dragonrex.commands.StopCommand;
import de.dragonrex.console.ConsoleReader;
import de.dragonrex.console.command.CommandMap;
import de.dragonrex.template.TemplateEngine;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class HttpServerTest {
    private TemplateEngine templateEngine;
    private Javalin server;

    private ConsoleReader reader;
    public HttpServerTest() {
        this.server = Javalin.create();
        this.templateEngine = new TemplateEngine("pages");
        this.server.get("/", ctx -> {
            this.templateEngine.switchPage("index.html");
            ctx.html(this.templateEngine.getCurrentTemp().ctx());
            ctx.status(HttpStatus.ACCEPTED);
        });
        this.server.get("/home", ctx -> {
            this.templateEngine.switchPage("home.html");
            ctx.html(this.templateEngine.getCurrentTemp().ctx());
            ctx.status(HttpStatus.ACCEPTED);
        });
        this.server.get("/test", ctx -> {
            this.templateEngine.switchPage("test.html");
            ctx.html(this.templateEngine.getCurrentTemp().ctx());
            ctx.status(HttpStatus.ACCEPTED);
        });
        this.server.start(8080);
        this.reader = new ConsoleReader("[server] >");
        CommandMap map = this.reader.getCommandMap();
        map.register(new StopCommand());
        new Thread(() -> this.reader.start()).start();
        Runtime.getRuntime().addShutdownHook(new Thread(() -> this.server.stop()));
    }
    public static void main(String[] args) {
       new HttpServerTest();
    }
}