package book.task2

package pong
//[...imports]
import akka.actor.{ActorSystem, Props}
import akka.pattern.ask        //Для того, чтобы юзать ask/? у актора
import akka.util.Timeout
import org.scalatest.{FunSpecLike, Matchers}

import scala.concurrent.Await
import scala.concurrent.duration._
class ScalaAskExamplesTest extends FunSpecLike with Matchers {
  val system = ActorSystem()
  implicit val timeout = Timeout(5 seconds)  //время в течение которого будет ждать сообщение актор, вызвав метод ask/?
  val pongActor = system.actorOf(Props(classOf[PongActor]))
  describe("Pong actor") {
    it("should respond with Pong") {
      val future = pongActor ? "Ping" //использует implicit timout // Запрашивает 3 ссылки: на актора, который прислал сообщение; на само сообщение // Имплисит timout. Возвращается Future[AnyRef] или эксепшен
      val result = Await.result(future.mapTo[String], 1 second) //Так как ответ Future[AnyRef] то мы его должны привести к типу. В данном случае String
      assert(result == "Pong")
    }
    it("should fail on unknown message") {
      val future = pongActor ? "unknown"
      intercept[Exception]{   //Ожидаем ошибку
        Await.result(future.mapTo[String], 1 second)
      }
    }
  }
}