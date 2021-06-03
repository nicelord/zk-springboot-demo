package com.example.zkspringbootweb.migration;

import com.example.zkspringbootweb.ZkSpringbootWebApplication;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.EbeanServerFactory;
import io.ebean.annotation.Platform;
import io.ebean.config.DatabaseConfig;
import io.ebean.config.ServerConfig;
import io.ebean.config.dbplatform.DatabasePlatform;
import io.ebean.dbmigration.DbMigration;
import io.ebean.migration.MigrationConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ZkSpringbootWebApplication.class)
public class ApplyDbMigration {

    @Autowired
    Database database;

    @Test
    public void runMigration() {

    }
}
