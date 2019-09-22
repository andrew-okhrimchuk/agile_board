package org.disturbed75.application.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.WriteResultChecking;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "org.disturbed75.application.DAO")
public class MongoConfig extends AbstractMongoConfiguration {

    @Override
    protected String getDatabaseName() {
        return "test";
    }


    @Override
    public Mongo mongo() {
        return new MongoClient("database", 27017);
    }

    @Override
    protected String getMappingBasePackage() {
        return "org.disturbed75.application";
    }
    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate myTemp = new MongoTemplate(mongoDbFactory(), mappingMongoConverter());
        myTemp.setWriteResultChecking(WriteResultChecking.EXCEPTION);
        return myTemp;
    }
}