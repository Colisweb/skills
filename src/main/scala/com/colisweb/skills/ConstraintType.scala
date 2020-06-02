package com.colisweb.skills

sealed trait ConstraintType {
  import ConstraintType._

  override def toString(): String =
    this match {
      case Owned     => OWNED
      case Required  => REQUIRED
      case Forbidden => FORBIDDEN
    }
}

final case object Owned     extends ConstraintType
final case object Required  extends ConstraintType
final case object Forbidden extends ConstraintType

object ConstraintType {

  val OWNED     = "owned"
  val REQUIRED  = "required"
  val FORBIDDEN = "forbidden"

  def fromString(string: String): Option[ConstraintType] = {
    string match {
      case OWNED     => Some(Owned)
      case REQUIRED  => Some(Required)
      case FORBIDDEN => Some(Forbidden)
      case _         => None
    }
  }
}
