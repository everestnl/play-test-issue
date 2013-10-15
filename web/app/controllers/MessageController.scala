package controllers

import services.Hello

import play.api.mvc.{Action, Controller}
import play.api.libs.json.Json
import play.api.Routes

case class Message(value: String)

object MessageController extends Controller {

  implicit val fooWrites = Json.writes[Message]

  def getMessage = Action {
  
    val lang =play.api.Play.current.configuration.getString("application.langs")
	
	//assertion fails even though application.conf _does_ define the property application.langs
	assert(lang!=None)
	
    Ok(Json.toJson(Message(Hello.toString)))
  }

  def javascriptRoutes = Action { implicit request =>
    Ok(Routes.javascriptRouter("jsRoutes")(routes.javascript.MessageController.getMessage)).as(JAVASCRIPT)
  }
}