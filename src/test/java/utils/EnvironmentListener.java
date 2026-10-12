package utils;

import org.testng.ISuite;
import org.testng.ISuiteListener;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/** После прогона записывает target/allure-results/environment.properties (блок Environment в Allure). */
public class EnvironmentListener implements ISuiteListener {
    private static final Map<String, String> BROWSERS = new ConcurrentHashMap<>();

    public static void register(String browserName, String version) {
        BROWSERS.put(browserName, version);
    }

    @Override
    public void onFinish(ISuite suite) {
        Properties p = new Properties();
        p.setProperty("os.name", System.getProperty("os.name") + " " + System.getProperty("os.version"));
        p.setProperty("jdk.version", System.getProperty("java.version"));
        p.setProperty("selenium", "4.25.0");
        p.setProperty("testng", "7.10.2");
        p.setProperty("allure", "2.25.0");
        p.setProperty("driver.manager", "SeleniumManager");
        for (Map.Entry<String, String> e : BROWSERS.entrySet()) {
            p.setProperty("browser." + e.getKey(), e.getValue());
        }
        Path dir = Paths.get("target", "allure-results");
        try {
            Files.createDirectories(dir);
            try (OutputStream out = Files.newOutputStream(dir.resolve("environment.properties"))) {
                p.store(out, "Allure environment");
            }
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
