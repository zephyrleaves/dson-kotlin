package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/8 18:28
 *
 */


fun obj(init: ObjectValue.() -> Unit): ObjectValue {
    val dson = ObjectValue()
    dson.init()
    return dson
}

fun arr(init: ArrayValue.() -> Unit): ArrayValue {
    val dson = ArrayValue()
    dson.init()
    return dson
}


object DSON {

    fun from(v: ObjectValue): Map<String, Any?> {
        return v.data()
    }

    fun from(v: ArrayValue): List<Any?> {
        return v.data()
    }

    fun from(v: Any): Any {
        return when (v) {
            is ObjectValue -> from(v)
            is ArrayValue -> from(v)
            else -> throw Exception("v only is ObjectValue or ArrayValue")
        }
    }
}






