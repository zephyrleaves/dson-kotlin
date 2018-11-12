package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/8 18:28
 *
 */


fun dson(init: DSON.() -> Unit): DSON {
    val dson = DSON()
    dson.init()
    return dson
}

class DSON : Node(), ObjectTrait {

//    fun
}






