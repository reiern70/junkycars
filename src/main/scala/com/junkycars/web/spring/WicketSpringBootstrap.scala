package com.junkycars.web.spring

import com.junkycars.spring.SpringBootstrap
import com.junkycars.web.JunkyCarsApplication
import org.apache.wicket.protocol.http.WebApplication
import org.springframework.context.annotation.{Bean, Configuration, Import}
import org.springframework.transaction.annotation.EnableTransactionManagement

/**
 * Wicket spring configuration!
 */

@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
@Import(Array(classOf[SpringBootstrap]))
class WicketSpringBootstrap {
  /**
   * webApplication
    *
    * @return WebApplication
   */
  @Bean
  def webApplication: WebApplication = {
    new JunkyCarsApplication
  }
}
