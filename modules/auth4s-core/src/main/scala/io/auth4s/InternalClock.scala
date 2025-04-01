package io.auth4s

import cats.Applicative
import cats.syntax.all.*

private[auth4s] trait InternalClock[F[_]] {
  def now: F[Long]
}

object InternalClock {
  def default[F[_] : Applicative]: InternalClock[F] = new InternalClock[F] {
    override def now: F[Long] = System.currentTimeMillis().pure
  }
}
