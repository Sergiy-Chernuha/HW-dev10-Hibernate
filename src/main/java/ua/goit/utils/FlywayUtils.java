package ua.goit.utils;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static ua.goit.utils.DBUtils.*;

public class FlywayUtils {
//    private static final Logger LOGGER= LoggerFactory.getLogger(FlywayUtils.class);
    private static final FlywayUtils INSTANCE;

    static {
        INSTANCE = new FlywayUtils();
    }

    public static FlywayUtils getInstance() {
        return INSTANCE;
    }

    public void doMigrations(){
        Flyway flyway;

        try {
            Class.forName(DB_DRIVER);
            flyway = Flyway.configure()
                    .locations(FLYWAY_LOCATION)
                    .dataSource(URL, USER_NAME, PASSWORD)
                    .load();

            flyway.migrate();
        } catch (Exception e) {
//            LOGGER.error(e.getMessage());
        }
    }
}
