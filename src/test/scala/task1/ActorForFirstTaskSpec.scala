package task1

import akka.actor.ActorSystem
import akka.testkit.TestActorRef
import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import task1.messages.MessageForFirstTask

class ActorForFirstTaskSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("\nActorForFirstTask") {
    describe("given StringRequest") {
      it("should save last string") {
        val actorRef = TestActorRef(new ActorForFirstTask())
        actorRef ! MessageForFirstTask("Test 1")
        val actorThatReciveMessage = actorRef.underlyingActor
        actorThatReciveMessage.str should equal("Test 1")
        actorRef ! MessageForFirstTask("Test 2")
        actorThatReciveMessage.str should(equal("Test 2"))
        actorRef ! "str"
        actorThatReciveMessage.str should(equal("Test 2"))
      }
    }
  }
}
x