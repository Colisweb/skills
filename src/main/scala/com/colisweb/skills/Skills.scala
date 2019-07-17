package com.colisweb.skills

object Skills {

  def isThereAMatchBetween(l1: List[SkillConstraint], l2: List[SkillConstraint]): Boolean =
    l1.forall(_.matches(l2)) && l2.forall(_.matches(l1))

}
