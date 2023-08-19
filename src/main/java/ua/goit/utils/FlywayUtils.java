package ua.goit.utils;

import org.flywaydb.core.Flyway;

import static ua.goit.utils.DBUtils.*;

public class FlywayUtils {
    private static final FlywayUtils INSTANCE;

    static {
        INSTANCE = new FlywayUtils();
    }

    public static FlywayUtils getInstance() {
        return INSTANCE;
    }

    public void doMigrations() {
        Flyway flyway;

        try {
            Class.forName(DB_DRIVER);
            flyway = Flyway.configure()
                    .locations(FLYWAY_LOCATION)
                    .dataSource(URL, USER_NAME, PASSWORD)
                    .load();

            flyway.migrate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
