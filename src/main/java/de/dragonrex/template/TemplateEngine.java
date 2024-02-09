package de.dragonrex.template;

import de.dragonrex.logger.ColorLogger;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

public class TemplateEngine {
    private HashMap<String, Template> registeredTemps;
    private Template currentTemp;

    private final ColorLogger logger = new ColorLogger();

    public TemplateEngine(String htmlDir) {
        try {
            this.registeredTemps = new HashMap<>();
            File dir = new File(htmlDir);
            if (!dir.exists())
                this.logger.logError("[HttpServerTest] -> no Html Dir found!");
            File[] htmlFiles = dir.listFiles();
            assert htmlFiles != null;
            for (File f : htmlFiles) {
                if (f.isDirectory() || !f.getName().endsWith(".html")) {
                    this.logger.logInfo("Html Error");
                    continue;
                }
                Template temp = new Template(
                        f.getName(),
                        f.getPath(), Files.readString(Path.of(f.getPath()))
                );
                this.registeredTemps.put(temp.name(), temp);
                this.logger.logInfo("[HttpServerTest] -> Html Page added: " + temp.name());
            }
            this.logger.logInfo("[HttpServerTest] -> Html Pages founded: " + this.registeredTemps.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setCurrentTemp(Template currentTemp) {
        this.currentTemp = currentTemp;
    }

    public void addPage(String htmlPath, String fileName) {
        try {
            File f = new File(htmlPath, fileName);
            if (!f.exists())
                this.logger.logError("[HttpServerTest] -> no Html Page found!");
            Template temp = new Template(
                    f.getName(),
                    f.getPath(), Files.readString(Path.of(f.getPath()))
            );
            this.registeredTemps.put(temp.name(), temp);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removePage(String page) {
        this.registeredTemps.remove(page);
        this.logger.logInfo("[HttpServerTest] -> Html Page removed!");
    }

    public Template switchPage(String pageName) {
        this.currentTemp = this.registeredTemps.get(pageName);
        this.logger.logInfo("[HttpServerTest] -> Html Page updated!");
        return this.currentTemp;
    }

    public Template getCurrentTemp() {
        return this.currentTemp;
    }
}
