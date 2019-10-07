package org.disturbed75.application.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;



@Configuration
@EnableMongoRepositories(basePackages = "org.disturbed75.application.DAO")
@PropertySource("classpath:mongo.properties")
public class MongoConfig extends AbstractMongoConfiguration {

    @Autowired
    private Environment env;

    @Override
    protected String getDatabaseName() {
        return env.getProperty("mongodb.db");
    }


    @Override
    public MongoClient mongoClient() {
        return new MongoClient(env.getProperty("mongodb.host"), Integer.parseInt(env.getProperty("mongodb.port")));
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.disturbed75.application.entity";
    }

}