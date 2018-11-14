package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/8 18:28
 *
 */


fun obj(init: ObjectValue.() -> Unit): Node {
    val dson = ObjectValue()
    dson.init()
    return dson
}

fun arr(init: ArrayValue.() -> Unit): Node {
    val dson = ArrayValue()
    dson.init()
    return dson
}


class DSON {

//    fun
}






