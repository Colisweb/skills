package com.colisweb.skills

import com.colisweb.Approbation
import com.github.writethemfirst.approvals.utils.FunctionUtils
import org.scalatest.prop.TableDrivenPropertyChecks._
import org.scalatest.prop.TableFor3
import scala.collection.JavaConverters._

final class SkillsSpec extends Approbation {

  import Skills._
  import SkillsSpec._

  "skills" should "test" in { _ =>
    forAll(data) { (l1, l2, `match`) =>
      isThereAMatchBetween(l1, l2) should (equal(isThereAMatchBetween(l2, l1)) and equal(`match`))
    }
  }

  "skills" should "match" in { approver =>
    val possibleConstraints = List[Set[SkillConstraint]](
      Set(Owned(freshSkill)),
      Set(Required(hotSkill)),
      Set(Forbidden(freshSkill)),
      Set(Required(freshSkill)),
      Set(Required(freshSkill), Required(hotSkill)),
      Set(Owned(freshSkill), Owned(hotSkill)),
      Set(Owned(hotSkill), Required(freshSkill)),
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

  val freshSkill     = "fresh"
  val hotSkill       = "hot"
  val eatableSkill   = "eatable"
  val drinkableSkill = "drinkable"
  val flyingSkill    = "flying"
  val rollingSkill   = "rolling"

  val data: TableFor3[List[SkillConstraint], List[SkillConstraint], Boolean] =
    Table(
      ("l1", "l2", "match"),
      (List(Owned(freshSkill)), List(Required(hotSkill)), false),
      (Nil, Nil, true),
      (List(Required(freshSkill)), Nil, false),
      (List(Forbidden(freshSkill)), Nil, true),
      (List(Required(freshSkill)), List(Owned(freshSkill)), true),
      (List(Required(freshSkill), Required(hotSkill)), List(Owned(freshSkill), Owned(hotSkill)), true),
      (List(Required(freshSkill), Required(hotSkill)), List(Owned(freshSkill)), false),
      (List(Required(freshSkill)), List(Required(freshSkill)), true),
      (List(Required(freshSkill)), List(Required(freshSkill), Required(hotSkill)), false),
      (List(Owned(hotSkill), Required(freshSkill)), List(Required(freshSkill), Required(hotSkill)), true),
      (List(Forbidden(freshSkill)), List(Owned(freshSkill)), false),
      (List(Forbidden(freshSkill), Forbidden(hotSkill)), List(Owned(freshSkill)), false),
      (List(Forbidden(freshSkill), Forbidden(drinkableSkill)), List(Owned(hotSkill), Owned(eatableSkill)), true),
      (List(Forbidden(freshSkill)), List(Forbidden(freshSkill)), true),
      (
        List(Required(hotSkill), Required(eatableSkill), Forbidden(freshSkill)),
        List(Owned(hotSkill), Owned(eatableSkill), Owned(freshSkill)),
        false
      ),
      (List(Required(hotSkill), Forbidden(freshSkill)), List(Owned(drinkableSkill)), false),
      (
        List(Owned(rollingSkill), Required(hotSkill), Required(drinkableSkill), Forbidden(freshSkill)),
        List(Owned(drinkableSkill), Required(rollingSkill), Required(hotSkill), Forbidden(flyingSkill)),
        true
      )
    )

}
