package Test

import scala.concurrent.duration._
import scala.concurrent.{Await, Future, Promise}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class Test[T](a: T, toInt: T => Int) {
  var answer: Int = Int.MinValue
  var answer2: Int = Int.MinValue
  val fut: Future[Int] = Future {
    try {
      toInt(a)
    } catch {
      case _ => throw new Exception("Not Int")
    }
  } andThen {
    case Success(a) => answer = a
    case Failure(e) =>
  }

  val fut2 = fut.map(_ + 1)
  fut2.onComplete {
    case Success(a) => answer2 = a
    case Failure(e) =>
  }

  val fut3 = for {
    val1 <- fut
    val2 <- fut2
  } yield { val1 + val2}
}

object Test {
  def apply[T](a: T, toInt: (T) => Int): Test[T] = new Test[T](a, toInt)
}
