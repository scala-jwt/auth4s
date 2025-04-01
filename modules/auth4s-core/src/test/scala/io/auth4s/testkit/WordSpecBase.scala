package io.auth4s.testkit

import org.scalatest.matchers.should
import org.scalatest.wordspec.AsyncWordSpec
import org.scalatest.{EitherValues, OptionValues}

import java.time.Instant

open class WordSpecBase extends AsyncWordSpec, should.Matchers, OptionValues, EitherValues
