package com.colisweb.skills

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.matchers.should.Matchers
import ConstraintType._

final class ConstraintTypeSpec extends AnyFeatureSpec with Matchers {

  Scenario("ConstraintType to string") {
    val skillList = List(Owned, Required, Forbidden)
    skillList.map(_.toString()) shouldBe List(OWNED, REQUIRED, FORBIDDEN)
  }

  Scenario("String to ConstraintType") {
    val stringList = List(OWNED, REQUIRED, FORBIDDEN, "wrong constraint")
    stringList.map(s => ConstraintType.fromString(s)) shouldBe List(Some(Owned), Some(Required), Some(Forbidden), None)
  }

}
