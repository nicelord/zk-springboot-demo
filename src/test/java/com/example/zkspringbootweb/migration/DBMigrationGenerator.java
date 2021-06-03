package com.example.zkspringbootweb.migration;



import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.ServerConfig;
import io.ebean.dbmigration.DbMigration;

import java.io.IOException;

public class DBMigrationGenerator {

    public static void main(String[] args) throws IOException {

//        String path = "db-migration-service/src/main/resources";

//        System.out.println(path);

//        System.setProperty("ddl.migration.version", "1.39");

//        System.setProperty("ddl.migration.name", "add_user_role");

        System.setProperty("ddl.migration.pendingDropsFor", "1.4");

//        ObjectMapper objectMapper = new ObjectMapper();
//

        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/zk-springboot-demo");
        config.setUsername("root");
        config.setPassword("");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");

        DatabaseConfig databaseConfig = new DatabaseConfig();
        databaseConfig.setDataSource(new HikariDataSource(config));

//        databaseConfig.setObjectMapper(objectMapper);

        DbMigration dbMigration = DbMigration.create();
        dbMigration.setPlatform(Platform.MYSQL55);
        dbMigration.setStrictMode(false);
//        dbMigration.setPathToResources(path);
        dbMigration.setApplyPrefix("V");
        dbMigration.setServerConfig(databaseConfig);
        dbMigration.generateMigration();

    }
}
