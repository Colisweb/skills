package com.colisweb.skills

import eu.timepit.refined.types.string.NonEmptyString

object Skills {

  def isThereAMatchBetween(l1: Iterable[SkillConstraint], l2: Iterable[SkillConstraint]): Boolean =
    l1.forall(_.matches(l2.toSet)) && l2.forall(_.matches(l1.toSet))

  def SkillConstraint(tag: NonEmptyString, `type`: ConstraintType): SkillConstraint = {
    `type` match {
      case Owned     => OwnedSkill(tag)
      case Required  => RequiredSkill(tag)
      case Forbidden => ForbiddenSkill(tag)
    }
  }

}
