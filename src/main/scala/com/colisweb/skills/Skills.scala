package com.colisweb.skills

object Skills {

  def isThereAMatchBetween(l1: Iterable[SkillConstraint], l2: Iterable[SkillConstraint]): Boolean =
    l1.forall(_.matches(l2.toSet)) && l2.forall(_.matches(l1.toSet))

}
