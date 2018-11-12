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

class DSON : Node() {
    fun obj(key: String, init: ObjectKey.() -> Unit) {
        val myKey = ObjectKey(key)
        myKey.init()
        children.add(myKey)
    }


    fun array(key: String, init: ArrayKey.() -> Unit) {
        val myKey = ArrayKey(key)
        myKey.init()
        children.add(myKey)
    }

    fun value(key: String, value: () -> Any?) {
        val myKey = ValueKey(key, value())
        children.add(myKey)
    }

    fun value(key: String, value: Any?) {
        val myKey = ValueKey(key, value)
        children.add(myKey)
    }


}






