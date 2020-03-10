package com.colisweb

import com.github.writethemfirst.approvals.approvers.Approver
import org.scalatest.{Outcome, fixture}
import pprint.PPrinter
import pprint.PPrinter.BlackWhite
import org.scalatest.flatspec
import org.scalatest.matchers.should.Matchers

class Approbation extends flatspec.FixtureAnyFlatSpec with Matchers {

  private val approvals  = (new Approver).testing(getClass)
  val prettify: PPrinter = BlackWhite.copy(defaultHeight = Int.MaxValue)

  override type FixtureParam = Approver

  // see https://stackoverflow.com/questions/14831246/access-scalatest-test-name-from-inside-test
  // and http://www.scalatest.org/user_guide/sharing_fixtures
  override def withFixture(test: OneArgTest): Outcome = {
    val approver = approvals.writeTo(test.name)
    withFixture(test.toNoArgTest(approver))
  }

}
