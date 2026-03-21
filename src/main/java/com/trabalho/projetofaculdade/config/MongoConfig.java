package com.trabalho.projetofaculdade.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {
        "com.trabalho.projetofaculdade.repository.Book",
        "com.trabalho.projetofaculdade.repository.User"
})
@EnableMongoRepositories(basePackages = {
        "com.trabalho.projetofaculdade.repository.Log"
})
public class MongoConfig {
}
