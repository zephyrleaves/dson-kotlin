package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:50
 *
 */

abstract class Key(private val key: String) : Node()

class ObjectKey(key: String) : Key(key), ObjectTrait

class ArrayKey(key: String) : Key(key), ArrayTrait

class ValueKey(key: String, val value: Any?) : Key(key)
