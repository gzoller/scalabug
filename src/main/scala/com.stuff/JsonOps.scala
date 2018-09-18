package com.stuff

trait JsonOps[J] {

  // A representation of an array's elements suitable to this serialized form.
  type ArrayElements

  /**
   * A representation of an object's fields suitable to this serialized form. For example, Json4s defines a JSON object
   * containing a [[List[(String, JValue)]]. Therefore, [[Json4sOps#ObjectFields]] is defined as [[List[(String, JValue)]].
   * Other libraries may define a JSON object as a [[Map]] instead of a [[List]].
   */
  type ObjectFields

  def foreachArrayElement(elements: ArrayElements, f: (Int, J) => Unit): Unit
  def applyArray(appendAllElements: (J => Unit) => Unit): J
  def unapplyArray(json: J): Option[ArrayElements]
  def applyBoolean(value: Boolean): J
  def unapplyBoolean(json: J): Option[Boolean]
  def applyString(string: String): J
  def unapplyString(json: J): Option[String]
}
