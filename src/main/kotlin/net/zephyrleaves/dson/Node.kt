package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:45
 *
 */
@DslMarker
annotation class DsonMarker

@DsonMarker
abstract class Node {
    protected val children = arrayListOf<Node>()

    internal abstract fun data(): Any?
}
