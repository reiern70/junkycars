package com.junkycars.web

import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.request.mapper.parameter.PageParameters


class HomePage(pageParameters: PageParameters) extends WebPage(pageParameters) {

  override def onInitialize(): Unit = {
    super.onInitialize()

    add(new Label("version", getApplication.getFrameworkSettings.getVersion))
  }
}
