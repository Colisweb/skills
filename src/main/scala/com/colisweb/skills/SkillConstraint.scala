package com.colisweb.skills

sealed trait SkillConstraint
final case class Owned(skill: String)     extends SkillConstraint
final case class Required(skill: String)  extends SkillConstraint
final case class Forbidden(skill: String) extends SkillConstraint
