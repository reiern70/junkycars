package com.junkycars.web

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.protocol.http.WebApplication


class JunkyCarsApplication extends WebApplication {

  override def init(): Unit = {
    super.init()
  }

  override def getHomePage: Class[_ <: WebPage] = classOf[HomePage]
}
