package com.colisweb.skills

import eu.timepit.refined.types.string.NonEmptyString

sealed trait SkillConstraint {
  def tag: NonEmptyString
  def `type`: ConstraintType
  def matches(constraints: Set[SkillConstraint]): Boolean = true
}

final case class SkillOwned(override val tag: NonEmptyString) extends SkillConstraint {
  override def `type`: ConstraintType = Owned
}

final case class SkillRequired(override val tag: NonEmptyString) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    (constraints(SkillOwned(tag)) || constraints(SkillRequired(tag))) && !constraints(SkillForbidden(tag))

  override def `type`: ConstraintType = Required
}

final case class SkillForbidden(override val tag: NonEmptyString) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    !constraints(SkillOwned(tag)) && !constraints(SkillRequired(tag))

  override def `type`: ConstraintType = Forbidden
}
