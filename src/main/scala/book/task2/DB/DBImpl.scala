package book.task2.DB

import akka.actor.{Actor, Props, Status}
import akka.event.Logging
import book.task2.messages.{GetRequest, KeyNotFoundException, SetRequest}

class DBImpl extends Actor {
  val map = scala.collection.mutable.Map[String, Any]()
  val log = Logging(context.system, this)
  log.info("Server is up. Actor name of server: %s".format(self.path.name))
  override def receive = {
    case SetRequest(key, value) =>
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value)
      sender() ! Status.Success
    case GetRequest(key) =>
      log.info("received GetRequest - key: {}", key)
      val response: Option[Any] = map.get(key)
      response match {
        case Some(x) => sender() ! x
        case None => sender() ! Status.Failure(new
            KeyNotFoundException(key))
      }
    case o => Status.Failure(new ClassNotFoundException)
  }
}