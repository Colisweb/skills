package com.colisweb.skills

sealed trait ConstraintType

final case object Owned     extends ConstraintType
final case object Required  extends ConstraintType
final case object Forbidden extends ConstraintType
