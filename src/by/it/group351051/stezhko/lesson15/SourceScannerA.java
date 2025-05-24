package by.it.group351051.stezhko.lesson15;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SourceScannerA {
    public static void main(String[] args) {
        Path src = Path.of(System.getProperty("user.dir"));
        if (!"src".equals(src.getFileName().toString()))
            src = src.resolve("src");
        if (!Files.isDirectory(src)) {
            System.err.println("Directory not found: " + src);
            return;
        }
        try (var walk = Files.walk(src)) {
            final Path fullPath = src;
            walk.filter(pth -> pth.toString().endsWith(".java"))
                    .forEach(pth -> {
                        try {
                            String content = Files.readString(pth);
                            if (!content.contains("@Test") && !content.contains("org.junit.Test"))
                                System.out.println(fullPath.relativize(pth).toString());
                        } catch (IOException e) {}
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
