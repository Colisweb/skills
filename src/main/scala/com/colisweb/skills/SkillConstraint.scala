package com.colisweb.skills

import eu.timepit.refined.types.string.NonEmptyString

sealed trait SkillConstraint {
  def tag: NonEmptyString
  def `type`: ConstraintType
  def matches(constraints: Set[SkillConstraint]): Boolean = true
}

final case class OwnedSkill(override val tag: NonEmptyString) extends SkillConstraint {
  override def `type`: ConstraintType = Owned
}

final case class RequiredSkill(override val tag: NonEmptyString) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    (constraints(OwnedSkill(tag)) || constraints(RequiredSkill(tag))) && !constraints(ForbiddenSkill(tag))

  override def `type`: ConstraintType = Required
}

final case class ForbiddenSkill(override val tag: NonEmptyString) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    !constraints(OwnedSkill(tag)) && !constraints(RequiredSkill(tag))

  override def `type`: ConstraintType = Forbidden
}
