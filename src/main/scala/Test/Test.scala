package Test

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class Test[T](a: T, toInt: T => Int) {
  var answer: Int = Int.MinValue
  val fut: Future[Int] = Future {
    try {
      toInt(a)
    } catch {
      case _ => throw new Exception("Not Int")
    }
  }
  fut.onComplete {
    case Success(a) => answer = a
    case Failure(e) =>
  }
}

object Test {
  def apply[T](a: T, toInt: (T) => Int): Test[T] = new Test[T](a, toInt)
}