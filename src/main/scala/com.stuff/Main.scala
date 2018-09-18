package com.stuff

import JsonValue._

object Json4sOps extends JsonOps[JValue] {
  override type ArrayElements = List[JValue]
  override type ObjectFields = List[(String, JValue)]
  0
  override def foreachArrayElement(elements: List[JValue], f: (Int, JValue) => Unit): Unit = {
    for ((element, index) <- elements.zipWithIndex) {
      f(index, element)
    }
  }
  override def applyArray(appendAllElements: (JValue => Unit) => Unit): JValue = {
    val elementsBuilder = List.newBuilder[JValue]

    appendAllElements { element =>
      elementsBuilder += element
    }

    JArray(elementsBuilder.result())
  }
  override def unapplyArray(json: JValue): Option[List[JValue]] =
    json match {
      case JArray(elements) => Some(elements)
      case _                => None
    }

  override def applyBoolean(value: Boolean): JValue =
    JBool(value)

  override def unapplyBoolean(json: JValue): Option[Boolean] =
    json match {
      case JBool(value) => Some(value)
      case _            => None
    }

  override def applyString(string: String): JValue =
    JString(string)

  override def unapplyString(json: JValue): Option[String] =
    json match {
      case JString(value) => Some(value)
      case _              => None
    }
}

object JsonRenderer {
  def renderCompact[J](json: J)(implicit ops: JsonOps[J]): String = {
    val builder = new StringBuilder

    def helper(j: J): Unit = {
      j match {
        case JsonArray(x) =>
          builder.append('[')
          ops.foreachArrayElement(x.asInstanceOf[ops.ArrayElements], { (index, element) =>
            if (index > 0) {
              builder.append(",")
            }
            helper(element)
          })
          builder.append(']')

        case JsonBoolean(booleanValue) =>
          builder.append(if (booleanValue) "true" else "false")

        case JsonString(string) =>
          builder.append(string)
      }
    }

    helper(json)
    builder.result()
  }
}

object Main extends App {
  val jv: JValue = JArray(List(JBool(true), JBool(false), JBool(true)))
  println(JsonRenderer.renderCompact(jv)(Json4sOps))
}