package com.junkycars.spring

import com.junkcars.domain.logic.ICarAdvertsLogic
import com.junkcars.domain.logic.impl.CarsAdvertsLogic
import com.mongodb.{Mongo, MongoClient, ReadPreference}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.data.mongodb.config.AbstractMongoConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Bootstrap spring.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@EnableMongoRepositories(basePackages=Array("com.junkcars.domain.repo"))
@ComponentScan(Array("com.junkcars.domain"))
class SpringBootstrap extends AbstractMongoConfiguration {

  override protected  def  getDatabaseName:  String = "junkycars"
  

  override def mongo(): Mongo = {
    val mongoClient = new MongoClient("localhost")
    mongoClient.setReadPreference(ReadPreference.secondaryPreferred())
    mongoClient
  }

  @Bean
  def getAdvertsLogic: ICarAdvertsLogic = new CarsAdvertsLogic
}
