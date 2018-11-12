package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:50
 *
 */

abstract class Key(private val key: String) : Node()

class ObjectKey(key: String) : Key(key) {

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

class ArrayKey(key: String) : Key(key) {

    fun obj(init: ObjectValue.() -> Unit) {
        val myKey = ObjectValue()
        myKey.init()
        children.add(myKey)
    }


    fun array(init: ArrayValue.() -> Unit) {
        val myKey = ArrayValue()
        myKey.init()
        children.add(myKey)
    }

    fun value(value: () -> Any?) {
        val myKey = ValueValue(value())
        children.add(myKey)
    }

    fun value(value: Any?) {
        val myKey = ValueValue(value)
        children.add(myKey)
    }


}


class ValueKey(key: String, val value: Any?) : Key(key)
