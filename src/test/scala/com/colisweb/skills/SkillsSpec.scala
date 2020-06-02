package com.colisweb.skills

import com.colisweb.Approbation
import com.github.writethemfirst.approvals.utils.FunctionUtils
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor3

import scala.collection.JavaConverters._
import eu.timepit.refined.auto._
import eu.timepit.refined.types.string.NonEmptyString

final class SkillsSpec extends Approbation {

  import Skills._
  import SkillsSpec._

  Feature("isThereAMatchBetween") {
    Scenario("Result") { _ =>
      forAll(data) { (l1, l2, `match`) =>
        isThereAMatchBetween(l1, l2) should (equal(isThereAMatchBetween(l2, l1)) and equal(`match`))
      }
    }

    Scenario("Approbation") { approver =>
      val possibleConstraints = List[Set[SkillConstraint]](
        Set(OwnedSkill(freshSkill)),
        Set(RequiredSkill(hotSkill)),
        Set(ForbiddenSkill(freshSkill)),
        Set(RequiredSkill(freshSkill)),
        Set(RequiredSkill(freshSkill), RequiredSkill(hotSkill)),
        Set(OwnedSkill(freshSkill), OwnedSkill(hotSkill)),
        Set(OwnedSkill(hotSkill), RequiredSkill(freshSkill)),
        Set.empty
      )

      val combinations = FunctionUtils.applyCombinations[Set[SkillConstraint], Set[SkillConstraint]](
        possibleConstraints.asJava,
        possibleConstraints.asJava,
        Skills.isThereAMatchBetween
      )
      approver.verify(combinations)
    }
  }

  Scenario("skillConstraint should match") { _ =>
    val skillList: List[SkillConstraint] =
      List(SkillConstraint("owned skill", Owned), SkillConstraint("forbidden skill", Forbidden))
    skillList.head.tag.value shouldBe "owned skill"
    skillList.tail.head.`type` shouldBe Forbidden

  }

}

object SkillsSpec {

  val freshSkill: NonEmptyString     = "fresh"
  val hotSkill: NonEmptyString       = "hot"
  val eatableSkill: NonEmptyString   = "eatable"
  val drinkableSkill: NonEmptyString = "drinkable"
  val flyingSkill: NonEmptyString    = "flying"
  val rollingSkill: NonEmptyString   = "rolling"

  val data: TableFor3[List[SkillConstraint], List[SkillConstraint], Boolean] =
    Table(
      ("l1", "l2", "match"),
      (List(OwnedSkill(freshSkill)), List(RequiredSkill(hotSkill)), false),
      (Nil, Nil, true),
      (List(RequiredSkill(freshSkill)), Nil, false),
      (List(ForbiddenSkill(freshSkill)), Nil, true),
      (List(RequiredSkill(freshSkill)), List(OwnedSkill(freshSkill)), true),
      (
        List(RequiredSkill(freshSkill), RequiredSkill(hotSkill)),
        List(OwnedSkill(freshSkill), OwnedSkill(hotSkill)),
        true
      ),
      (List(RequiredSkill(freshSkill), RequiredSkill(hotSkill)), List(OwnedSkill(freshSkill)), false),
      (List(RequiredSkill(freshSkill)), List(RequiredSkill(freshSkill)), true),
      (List(RequiredSkill(freshSkill)), List(RequiredSkill(freshSkill), RequiredSkill(hotSkill)), false),
      (
        List(OwnedSkill(hotSkill), RequiredSkill(freshSkill)),
        List(RequiredSkill(freshSkill), RequiredSkill(hotSkill)),
        true
      ),
      (List(ForbiddenSkill(freshSkill)), List(OwnedSkill(freshSkill)), false),
      (List(ForbiddenSkill(freshSkill), ForbiddenSkill(hotSkill)), List(OwnedSkill(freshSkill)), false),
      (
        List(ForbiddenSkill(freshSkill), ForbiddenSkill(drinkableSkill)),
        List(OwnedSkill(hotSkill), OwnedSkill(eatableSkill)),
        true
      ),
      (List(ForbiddenSkill(freshSkill)), List(ForbiddenSkill(freshSkill)), true),
      (
        List(RequiredSkill(hotSkill), RequiredSkill(eatableSkill), ForbiddenSkill(freshSkill)),
        List(OwnedSkill(hotSkill), OwnedSkill(eatableSkill), OwnedSkill(freshSkill)),
        false
      ),
      (List(RequiredSkill(hotSkill), ForbiddenSkill(freshSkill)), List(OwnedSkill(drinkableSkill)), false),
      (
        List(
          OwnedSkill(rollingSkill),
          RequiredSkill(hotSkill),
          RequiredSkill(drinkableSkill),
          ForbiddenSkill(freshSkill)
        ),
        List(
          OwnedSkill(drinkableSkill),
          RequiredSkill(rollingSkill),
          RequiredSkill(hotSkill),
          ForbiddenSkill(flyingSkill)
        ),
        true
      )
    )

}
