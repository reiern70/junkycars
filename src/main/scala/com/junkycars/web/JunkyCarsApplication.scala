package com.junkycars.web

import java.util.{Date, Calendar}
import javax.inject.Inject

import com.junkcars.domain.repo.{IFuelTypesRepo, IUsersRepo, ICarAdvertsRepo}
import com.junkycars.domain.{CarAdvert, FuelType, User}
import de.agilecoders.wicket.core.Bootstrap
import de.agilecoders.wicket.core.settings.BootstrapSettings
import org.apache.wicket.injection.Injector
import org.apache.wicket.markup.html.WebPage
import org.apache.wicket.protocol.http.WebApplication
import org.apache.wicket.spring.injection.annot.SpringComponentInjector
import org.springframework.context.ApplicationContext
import org.springframework.web.context.support.WebApplicationContextUtils

import scala.util.Random

object JunkyCarsApplication {

  def getApp: JunkyCarsApplication = {
    WebApplication.get().asInstanceOf[JunkyCarsApplication]
  }

  private val carModels = Array("Peugeot", "Toyota", "Ford")

  private val years = 1990 to 2016

  val random = new Random()

  def getRandomModel = carModels(random.nextInt(carModels.length)) + " of year " + getRandomYear

  def getRandomYear = years(random.nextInt(years.size))

}

class JunkyCarsApplication extends WebApplication {

  import JunkyCarsApplication._

  @Inject
  var carAdvertsRepo: ICarAdvertsRepo = _
  @Inject
  var usersRepo: IUsersRepo = _
  @Inject
  var fuelTypesRepo: IFuelTypesRepo = _

  override def init(): Unit = {
    super.init()

    val bootstrapSettings: BootstrapSettings = new BootstrapSettings
    Bootstrap.install(this, bootstrapSettings)

    getMarkupSettings.setStripWicketTags(true)
    val springContext: ApplicationContext = getSpringContext
    getComponentInstantiationListeners.add(new SpringComponentInjector(this, springContext))
    Injector.get.inject(this)
    initMocks()
  }

  final def getSpringContext: ApplicationContext = {
    WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext)
  }

  private def initMocks(): Unit = {
    if(carAdvertsRepo.count() > 0) {
      return
    }
    createFuelTypes()
    createUsers()
    createAdverts()
  }

  private def createFuelTypes(): Unit = {
    val gasoline = new FuelType
    gasoline.setName("gasoline")
    gasoline.setDescription("gasoline")
    fuelTypesRepo.insert(gasoline)
  }

  private def createUsers(): Unit = {
    1 to 20 foreach(i => {
      val user = new User
      user.setName("User " + i)
      user.setName("LastName " + i)
      user.setLogin("user" + i)
      user.setEmail("user" + i + "@junkycars.com")
      user.setAddress("Bla Bla " + i)
      usersRepo.insert(user)
    })
  }

  def createAdverts(): Unit = {
    val users = usersRepo.findAll()
    val calendar = Calendar.getInstance()
    val fuelTypes = fuelTypesRepo.findAll()
    1 to 300 foreach( n => {
      val advert = new CarAdvert
      calendar.setTime(new Date())
      calendar.set(Calendar.YEAR, getRandomYear)
      advert.setDescription("Used car " + n)
      advert.setTitle(getRandomModel)
      advert.setUser(users.get(random.nextInt(users.size())))
      advert.setFuelType(fuelTypes.get(random.nextInt(fuelTypes.size())))
      advert.setFirstRegistration(calendar.getTime)
      advert.setMileage(random.nextInt(300000))
      advert.setPrice(random.nextInt(40000))
      advert.setNew(random.nextBoolean())
      carAdvertsRepo.insert(advert)
    })
  }

  override def getHomePage: Class[_ <: WebPage] = classOf[HomePage]
}
