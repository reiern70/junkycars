package com.junkycars.rest

import java.util.Date

import com.junkycars.domain.CarAdvert
import com.junkycars.web.JunkyCarsApplication
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{CorsSupport, ScalatraFilter}
import scala.collection.JavaConversions._

case class ServiceError(error: String)

case class Advert(id: String, title: String, description: String, fuelType: String, price: Integer,
                  isNew: Boolean, mileage: Integer, firstRegistration: Date, user: String)

case class SiteUser(id: String, name: String, lastName: String)

class ServicesEndPoint extends ScalatraFilter with  CorsSupport with JacksonJsonSupport {

  before() {
    contentType = formats("json")
  }

  get("/cars") {
    JunkyCarsApplication.getApp.advertsLogic.findAll(null).map(a => Advert(a.getUuid, a.getTitle,
      a.getDescription, a.getFuelType, a.getPrice, a.getNew, a.getMileage, a.getFirstRegistration, a.getUser))
  }

  get("/cars/:id") {
    val id: String  = params.getOrElse("id", null)
    val a = JunkyCarsApplication.getApp.carAdvertsRepo.loadByUUId(id)
    if(a != null) {
      Advert(a.getUuid, a.getTitle,
        a.getDescription, a.getFuelType, a.getPrice, a.getNew, a.getMileage, a.getFirstRegistration, a.getUser)
    } else {
      ServiceError("Could not find element with id " + id)
    }
  }

  get("/cars/sorted/:sort") {
    val sort: String  = params.getOrElse("sort", null)
   JunkyCarsApplication.getApp.advertsLogic.findAll(sort).map(a => Advert(a.getUuid, a.getTitle,
     a.getDescription, a.getFuelType, a.getPrice, a.getNew, a.getMileage, a.getFirstRegistration, a.getUser))
  }

  post("/cars") {
    val toCreate = parsedBody.extract[Advert]
    val entity = new CarAdvert
    if(toCreate != null) {
      try {
        entity.setTitle(toCreate.title)
        entity.setDescription(toCreate.description)
        entity.setFirstRegistration(toCreate.firstRegistration)
        entity.setPrice(toCreate.price)
        entity.setNew(toCreate.isNew)
        entity.setFuelType(toCreate.fuelType)
        entity.setUser(toCreate.user)
        JunkyCarsApplication.getApp.carAdvertsRepo.save(entity)
      } catch {
        case e: Exception =>
          ServiceError("Unable to persist entity " +  e.getMessage)
      }
    } else {
      ServiceError("Wrong type of entity")
    }
  }

  delete("/cars/:id") {
    val id: String  = params.getOrElse("id", null)
    val a = JunkyCarsApplication.getApp.carAdvertsRepo.loadByUUId(id)
    if(a != null) {
      Advert(a.getUuid, a.getTitle,
        a.getDescription, a.getFuelType, a.getPrice, a.getNew, a.getMileage, a.getFirstRegistration, a.getUser)
    } else {
      ServiceError("Could not find element with id " + id)
    }
  }

  // users

  get("/users") {
    JunkyCarsApplication.getApp.usersRepo.findAll().map(u => new SiteUser(u.getUuid, u.getName, u.getLastName))
  }

  override protected implicit def jsonFormats: Formats = DefaultFormats
}
