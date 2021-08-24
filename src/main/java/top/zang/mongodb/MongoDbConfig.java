package top.zang.mongodb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
class MongoDbConfig {
  @Value("${spring.data.mongoTemplate.uri}")
  private String uri;

  @Bean(name = {"mongoTemplate"})
  MongoTemplate mongoTemplate() throws Exception {
    MongoTemplate mongoTemplate = new MongoTemplate((MongoDatabaseFactory)new SimpleMongoClientDatabaseFactory(this.uri));
    return mongoTemplate;
  }

}
