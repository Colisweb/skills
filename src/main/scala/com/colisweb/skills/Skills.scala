package com.colisweb.skills

import scala.annotation.tailrec

object Skills {

  def isThereAMatchBetween(l1: List[SkillConstraint], l2: List[SkillConstraint]): Boolean = {

    def ownedSkillMatch(skill: String, l: List[SkillConstraint]): Boolean = {
      val hasSameSkillOfTypeForbidden = l.contains(Forbidden(skill))

      !hasSameSkillOfTypeForbidden
    }

    def forbiddenSkillMatch(skill: String, l: List[SkillConstraint]): Boolean = {
      val hasSameSkillOfTypeOwned = l.contains(Owned(skill))
      val hasSameSkillOfTypeRequired = l.contains(Required(skill))

      !hasSameSkillOfTypeOwned && !hasSameSkillOfTypeRequired
    }

    def requiredSkillMatch(skill: String, l: List[SkillConstraint]): Boolean = {
      val hasSameSkillOfTypeOwned    = l.contains(Owned(skill))
      val hasSameSkillOfTypeRequired = l.contains(Required(skill))
      val hasSameSkillOfTypeForbidden = l.contains(Forbidden(skill))

      (hasSameSkillOfTypeOwned || hasSameSkillOfTypeRequired) && !hasSameSkillOfTypeForbidden
    }

    @tailrec
    def rec(a: List[SkillConstraint], b: List[SkillConstraint]): Boolean =
      a match {
        case Nil                                                     => true
        case Owned(skill) :: _ if !ownedSkillMatch(skill, b)         => false
        case Required(skill) :: _ if !requiredSkillMatch(skill, b)   => false
        case Forbidden(skill) :: _ if !forbiddenSkillMatch(skill, b) => false
        case _ :: t                                                  => rec(t, b)
      }

    rec(l1, l2) && rec(l2, l1)
  }

}
