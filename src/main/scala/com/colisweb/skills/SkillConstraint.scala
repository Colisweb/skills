package com.colisweb.skills

sealed trait SkillConstraint {
  def matches(l: List[SkillConstraint]): Boolean
}

final case class Owned(skill: String) extends SkillConstraint {
  def matches(l: List[SkillConstraint]): Boolean =
    !l.contains(Forbidden(skill))
}

final case class Required(skill: String) extends SkillConstraint {
  def matches(l: List[SkillConstraint]): Boolean =
    (l.contains(Owned(skill)) || l.contains(Required(skill))) && !l.contains(Forbidden(skill))
}

final case class Forbidden(skill: String) extends SkillConstraint {
  def matches(l: List[SkillConstraint]): Boolean =
    !l.contains(Owned(skill)) && !l.contains(Required(skill))
}
