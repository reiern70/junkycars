import javax.servlet.ServletContext

import com.junkycars.rest.ServicesEndPoint
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {

    context.mount(new ServicesEndPoint, "/")
  }
}
