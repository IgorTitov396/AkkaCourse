package Test

import scala.concurrent.duration._
import akka.actor.ActorSystem
import akka.event.Logging
import akka.event.jul.Logger
import org.scalatest.{FunSpecLike, Matchers}
import org.slf4j.LoggerFactory

import scala.concurrent.Await
import scala.concurrent.ExecutionContext.Implicits.global


class TestSpec extends FunSpecLike with Matchers {
  describe("Test future"){
    it("Spec for class Test #1") {
      val obj = Test[Int](3, (_: Int).toInt)
      Thread.sleep(1000)
      assert(obj.answer == 3)
    }
    it("Spec for class Test #2") {
      val obj = Test[String]("34", (_).toInt)
      Thread.sleep(1000)
      assert(obj.answer == 34)
    }
    it("Spec for class Test #3") {
      val err = intercept[Throwable] {
        val obj = Test[String]("asdf", (_).toInt)
        Await.result(obj.fut, 1 second)
      }
      assert(err.getMessage === "Not Int")
    }
  }
}
