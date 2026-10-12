package utils;

import java.time.Duration;

public final class Config {
    private Config() {}

    public static final String BASE_URL = "https://demoqa.com";
    public static final Duration TIMEOUT = Duration.ofSeconds(10);
}
