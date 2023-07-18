package com.solvd.db.utils;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private static BasicDataSource dataSource;

    public static void loadPropertyConfigFile() {
        Properties property = new Properties();
        try (InputStream dbPropertiesStream = ConnectionPool.class.getClassLoader().getResourceAsStream("db.properties")) {
            if (dbPropertiesStream != null) {
                property.load(dbPropertiesStream);
            } else {
                logger.error("Unable to load the property file");
            }
            dataSource = new BasicDataSource();
            dataSource.setUrl(property.getProperty("db.url"));
            dataSource.setUsername(property.getProperty("db.user"));
            dataSource.setPassword(property.getProperty("db.password"));
            dataSource.setInitialSize(5);
            dataSource.setMaxTotal(15);
            dataSource.setMaxIdle(10);
            dataSource.setMaxWaitMillis(200);
        } catch (IOException e) {
            logger.error("Error loading the property file");
        }
    }
}
