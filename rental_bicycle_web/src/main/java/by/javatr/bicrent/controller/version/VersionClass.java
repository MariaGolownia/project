package by.javatr.bicrent.controller.version;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class VersionClass {
    private Properties prop;

    public String getVer() {
        return prop.getProperty("version");
    }

    private String getEntryById(String id) {
        return prop.getProperty(id);
    }

    public VersionClass() {
        InputStream resourceAsStream =
                this.getClass().getResourceAsStream("/version.properties");
        this.prop = new Properties();
        try {
            this.prop.load(resourceAsStream);
        } catch (IOException e) {
            // FIXME: This should be done by using a logging framework like log4j etc.
            e.printStackTrace();
        }

        //System.out.println("Version: " + getEntryById("version"));
    }

    public static void main(String[] args) {
        @SuppressWarnings("unused")
        VersionClass tvc = new VersionClass();
    }
}