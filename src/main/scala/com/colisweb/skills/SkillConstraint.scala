package com.colisweb.skills

sealed trait SkillConstraint {
  def matches(constraints: Set[SkillConstraint]): Boolean = true
}

final case class Owned(skill: String) extends SkillConstraint

final case class Required(skill: String) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    (constraints(Owned(skill)) || constraints(Required(skill))) && !constraints(Forbidden(skill))
}

final case class Forbidden(skill: String) extends SkillConstraint {
  override def matches(constraints: Set[SkillConstraint]): Boolean =
    !constraints(Owned(skill)) && !constraints(Required(skill))
}
