package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/12 15:02
 *
 */
interface ObjectTrait {
    val children: ArrayList<Node>

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

    fun value(key: String, value: () -> Any?) {
        val myKey = ValueKey(key, value())
        children.add(myKey)
    }

    fun value(key: String, value: Any?) {
        val myKey = ValueKey(key, value)
        children.add(myKey)
    }
}


interface ArrayTrait {
    val children: ArrayList<Node>

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

    fun value(value: () -> Any?) {
        val myKey = ValueValue(value())
        children.add(myKey)
    }

    fun value(value: Any?) {
        val myKey = ValueValue(value)
        children.add(myKey)
    }

}

