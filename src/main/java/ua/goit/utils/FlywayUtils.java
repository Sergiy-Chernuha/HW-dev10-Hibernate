package ua.goit.utils;

import org.flywaydb.core.Flyway;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hibernate.cfg.AvailableSettings.*;

public class FlywayUtils {
    private static final FlywayUtils INSTANCE;
    private static final Properties properties;

    static {
        INSTANCE = new FlywayUtils();
        properties = new Properties();
        loadPropertiesFromHibernateFile();
    }

    private static void loadPropertiesFromHibernateFile() {
        try (InputStream inputStream = FlywayUtils.class
                .getClassLoader()
                .getResourceAsStream("hibernate.properties")) {
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static FlywayUtils getInstance() {
        return INSTANCE;
    }

    public void doMigrations() {
        Flyway flyway;
        String url = properties.getProperty(URL);
        String name = properties.getProperty(USER);
        String password = properties.getProperty(PASS);
        String drive = properties.getProperty(DRIVER);

        try {
            Class.forName(drive);
            flyway = Flyway.configure()
                    .dataSource(url, name, password)
                    .load();

            flyway.migrate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
