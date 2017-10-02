package book.task2

import akka.actor.{ActorSystem, Props}
import book.task2.DB.DBImpl


object Main extends App {
  val system = ActorSystem("akkademy")
  system.actorOf(Props[DBImpl], name = "my_bd")
  while (scala.io.StdIn.readLine() != "exit") {
    Thread.sleep(100)
  }
  System.exit(0)
}