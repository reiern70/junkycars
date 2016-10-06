import javax.servlet.ServletContext

import com.junkycars.rest.EndPoint
import org.scalatra.LifeCycle

class ScalatraBootstrap extends LifeCycle {

  override def init(context: ServletContext) {

    context.mount(new EndPoint, "/")
  }
}
