package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:47
 *
 */
abstract class Value : Node()


class ObjectValue : Value() {

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


class ArrayValue : Value() {
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


class ValueValue(val value: Any?) : Value()