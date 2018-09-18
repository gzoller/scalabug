package com.stuff

trait JValue

case class JArray(x: List[JValue]) extends JValue
case class JBool(x: Boolean) extends JValue
case class JString(x: String) extends JValue