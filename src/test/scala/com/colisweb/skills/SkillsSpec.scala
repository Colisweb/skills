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

  "skills" should "test" in { _ =>
    forAll(data) { (l1, l2, `match`) =>
      isThereAMatchBetween(l1, l2) should (equal(isThereAMatchBetween(l2, l1)) and equal(`match`))
    }
  }

  "skillConstraint" should "match" in { _ =>
    val skillList: List[SkillConstraint] = List(SkillOwned("owned skill"), SkillForbidden("forbidden skill"))
    skillList.head.tag.toString() shouldBe "owned skill"
    skillList.tail.head.`type` shouldBe Forbidden

  }

  "skills" should "match" in { approver =>
    val possibleConstraints = List[Set[SkillConstraint]](
      Set(SkillOwned(freshSkill)),
      Set(SkillRequired(hotSkill)),
      Set(SkillForbidden(freshSkill)),
      Set(SkillRequired(freshSkill)),
      Set(SkillRequired(freshSkill), SkillRequired(hotSkill)),
      Set(SkillOwned(freshSkill), SkillOwned(hotSkill)),
      Set(SkillOwned(hotSkill), SkillRequired(freshSkill)),
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
      (List(SkillOwned(freshSkill)), List(SkillRequired(hotSkill)), false),
      (Nil, Nil, true),
      (List(SkillRequired(freshSkill)), Nil, false),
      (List(SkillForbidden(freshSkill)), Nil, true),
      (List(SkillRequired(freshSkill)), List(SkillOwned(freshSkill)), true),
      (
        List(SkillRequired(freshSkill), SkillRequired(hotSkill)),
        List(SkillOwned(freshSkill), SkillOwned(hotSkill)),
        true
      ),
      (List(SkillRequired(freshSkill), SkillRequired(hotSkill)), List(SkillOwned(freshSkill)), false),
      (List(SkillRequired(freshSkill)), List(SkillRequired(freshSkill)), true),
      (List(SkillRequired(freshSkill)), List(SkillRequired(freshSkill), SkillRequired(hotSkill)), false),
      (
        List(SkillOwned(hotSkill), SkillRequired(freshSkill)),
        List(SkillRequired(freshSkill), SkillRequired(hotSkill)),
        true
      ),
      (List(SkillForbidden(freshSkill)), List(SkillOwned(freshSkill)), false),
      (List(SkillForbidden(freshSkill), SkillForbidden(hotSkill)), List(SkillOwned(freshSkill)), false),
      (
        List(SkillForbidden(freshSkill), SkillForbidden(drinkableSkill)),
        List(SkillOwned(hotSkill), SkillOwned(eatableSkill)),
        true
      ),
      (List(SkillForbidden(freshSkill)), List(SkillForbidden(freshSkill)), true),
      (
        List(SkillRequired(hotSkill), SkillRequired(eatableSkill), SkillForbidden(freshSkill)),
        List(SkillOwned(hotSkill), SkillOwned(eatableSkill), SkillOwned(freshSkill)),
        false
      ),
      (List(SkillRequired(hotSkill), SkillForbidden(freshSkill)), List(SkillOwned(drinkableSkill)), false),
      (
        List(
          SkillOwned(rollingSkill),
          SkillRequired(hotSkill),
          SkillRequired(drinkableSkill),
          SkillForbidden(freshSkill)
        ),
        List(
          SkillOwned(drinkableSkill),
          SkillRequired(rollingSkill),
          SkillRequired(hotSkill),
          SkillForbidden(flyingSkill)
        ),
        true
      )
    )

}
