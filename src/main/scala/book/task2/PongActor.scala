package book.task2

import akka.actor.{Actor, Status}
import akka.event.Logging

class PongActor extends Actor {
  val log = Logging(context.system, this)
  override def receive = {
    case "Ping" => {
      sender() ! "Pong"
      log.info("Got message \"Ping\"")
    }
    case _ => sender() ! Status.Failure(throw new Exception("Got not \"Pong\" message"))
  }
}
