package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:50
 *
 */

abstract class Key(internal val key: String) : Node()

class ObjectKey(key: String) : Key(key) {
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

class ArrayKey(key: String) : Key(key) {
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

class ValueKey(key: String, val value: Any?) : Key(key) {
    override fun data(): Any? {
        return value
    }
}


