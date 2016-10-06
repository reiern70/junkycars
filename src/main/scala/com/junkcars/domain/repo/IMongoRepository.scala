package com.junkcars.domain.repo

import com.junkycars.domain.MongoBase
import org.springframework.data.mongodb.repository.{MongoRepository, Query}

/**
 * IMongoDao
 */
trait IMongoRepository[T<:MongoBase] extends MongoRepository[T, String] {
  
  @Query("{'uuid' : ?0 }")
  def loadByUUId(uuid: String): T


}
