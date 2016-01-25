package Controllers
import app.redis.RedisTest
import play.api.mvc.{AnyContent, Action, Controller}
import scala.concurrent.ExecutionContext.Implicits.global

import scala.concurrent.Future

trait TestController extends Controller {

  def test(): Action[AnyContent]= Action {
    implicit request =>
      //val x: RedisTest
      val xx = new RedisTest
      val x = xx.check("hello")
      Ok("hello = " + x)
  }
}

object TestController extends TestController



