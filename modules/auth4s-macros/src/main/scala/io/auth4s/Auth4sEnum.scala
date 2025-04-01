package io.auth4s

import scala.deriving.Mirror

trait Auth4sEnum[E] {
  def values: Map[E, String]
}

object Auth4sEnum {

  inline def derived[E: Mirror.SumOf]: String = ${ Auth4sEnumMacro.derived[E] }
}
