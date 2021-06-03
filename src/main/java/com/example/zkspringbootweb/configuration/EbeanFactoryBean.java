package com.example.zkspringbootweb.configuration;

import io.ebean.Database;
import io.ebean.DatabaseFactory;
import io.ebean.config.DatabaseConfig;
import io.ebean.datasource.DataSourceConfig;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EbeanFactoryBean implements FactoryBean<Database> {

    private EbeanCurrentUser currentUser;

    @Value("${ebean.datasource.databaseDriver}")
    private String driver;
    @Value("${ebean.datasource.username}")
    private String username;
    @Value("${ebean.datasource.password}")
    private String password;
    @Value("${ebean.datasource.databaseUrl}")
    private String url;


    public EbeanFactoryBean(EbeanCurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public Database getObject() {

        DataSourceConfig mysql = new DataSourceConfig();
        mysql.setDriver(driver);
        mysql.setUsername(username);
        mysql.setPassword(password);
        mysql.setUrl(url);


        DatabaseConfig config = new DatabaseConfig();
        config.setName("db");
        config.setDataSourceConfig(mysql);
        config.setCurrentUserProvider(currentUser);
        config.loadFromProperties();

        Database database = DatabaseFactory.create(config);
        return database;
    }

    @Override
    public Class<?> getObjectType() {
        return Database.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
