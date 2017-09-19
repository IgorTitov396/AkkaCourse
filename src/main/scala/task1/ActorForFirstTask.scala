package task1

import akka.actor.Actor
import akka.event.Logging
import task1.messages.MessageForFirstTask

class ActorForFirstTask(var str: String = "") extends Actor {
  val log = Logging(context.system, this)
  override def receive = {
    case MessageForFirstTask(newStr) => {
      log.info(s"recived string: $newStr")
      str = newStr
    }
    case a => log.info(s"recived: $a")
  }
}

