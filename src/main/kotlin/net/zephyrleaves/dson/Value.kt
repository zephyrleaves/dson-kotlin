package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:47
 *
 */
abstract class Value : Node()

open class ObjectValue : Value() {

    fun obj(key: String, init: ObjectKey.() -> Unit) {
        val myKey = ObjectKey(key)
        myKey.init()
        children.add(myKey)
    }

    fun arr(key: String, init: ArrayKey.() -> Unit) {
        val myKey = ArrayKey(key)
        myKey.init()
        children.add(myKey)
    }

    fun v(key: String, value: () -> Any?) {
        val myKey = ValueKey(key, value())
        children.add(myKey)
    }

    fun v(key: String, value: Any?) {
        val myKey = ValueKey(key, value)
        children.add(myKey)
    }

    override fun data(): Map<String, Any?> {
        val result = linkedMapOf<String, Any?>()
        children.forEach {
            val node = it as Key
            result[node.key] = node.data()
        }
        return result
    }
}

class ArrayValue : Value() {
    fun obj(init: ObjectValue.() -> Unit) {
        val myKey = ObjectValue()
        myKey.init()
        children.add(myKey)
    }


    fun arr(init: ArrayValue.() -> Unit) {
        val myKey = ArrayValue()
        myKey.init()
        children.add(myKey)
    }

    fun v(value: () -> Any?) {
        val myKey = ValueValue(value())
        children.add(myKey)
    }

    fun v(value: Any?) {
        val myKey = ValueValue(value)
        children.add(myKey)
    }

    override fun data(): List<Any?> {
        val result = arrayListOf<Any?>()
        children.forEach {
            result.add(it.data())
        }
        return result
    }
}

class ValueValue(val value: Any?) : Value() {
    override fun data(): Any? {
        return value
    }
}
