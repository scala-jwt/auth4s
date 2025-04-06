package io.auth4s.testkit

import org.scalatest.*
import org.scalatest.matchers.should
import org.scalatest.wordspec.AsyncWordSpec

open class WordSpecBase extends AsyncWordSpec, should.Matchers, OptionValues, EitherValues
