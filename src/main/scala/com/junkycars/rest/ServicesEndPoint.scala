package com.junkycars.rest

import com.junkycars.web.JunkyCarsApplication
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json.JacksonJsonSupport
import org.scalatra.{CorsSupport, ScalatraFilter}

class ServicesEndPoint extends ScalatraFilter with  CorsSupport with JacksonJsonSupport {

  before() {
    contentType = formats("json")
  }

  get("/cars") {
   JunkyCarsApplication.getApp.carAdvertsRepo.findAll()
  }

  override protected implicit def jsonFormats: Formats = DefaultFormats
}
