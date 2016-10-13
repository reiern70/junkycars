package com.junkcars.domain.logic.impl

import java.util
import javax.inject.Inject

import com.junkcars.domain.logic.ICarAdvertsLogic
import com.junkycars.domain.CarAdvert
import org.springframework.data.domain.Sort
import org.springframework.data.domain.Sort.{Direction, Order}
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.query.Query

object CarsAdvertsLogic {
  val sortableColumns = Array("title", "fuelType", "price", "firstRegistration", "user")
}

class CarsAdvertsLogic extends ICarAdvertsLogic {

  @Inject
  var template: MongoTemplate = _

  override def findAll(sort: String): util.List[CarAdvert] = {
    if(sort != null && CarsAdvertsLogic.sortableColumns.contains(sort)) {
      val query = new Query()
      query.`with`(new Sort(new Order(Direction.ASC, sort)))
      template.find[CarAdvert](query, classOf[CarAdvert])
    } else {
      val query = new Query()
      template.find[CarAdvert](query, classOf[CarAdvert])
    }
  }
}
