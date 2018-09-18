package com.stuff

object JsonValue {

  object JsonArray {

    @inline final def apply[J](build: (J => Unit) => Unit)(implicit ops: JsonOps[J]): J =
      ops.applyArray(build)

    @inline final def unapply[J](json: J)(implicit ops: JsonOps[J]): Option[ops.ArrayElements] =
      ops.unapplyArray(json)

  }

  object JsonBoolean {

    @inline final def apply[J](value: Boolean)(implicit ops: JsonOps[J]): J =
      ops.applyBoolean(value)

    @inline final def unapply[J](json: J)(implicit ops: JsonOps[J]): Option[Boolean] =
      ops.unapplyBoolean(json)

  }

  object JsonString {

    @inline final def apply[J](value: String)(implicit ops: JsonOps[J]): J =
      ops.applyString(value)

    @inline final def unapply[J](json: J)(implicit ops: JsonOps[J]): Option[String] =
      ops.unapplyString(json)

  }
}
