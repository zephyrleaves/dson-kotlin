package net.zephyrleaves.dson

/**
 * @author kevin.huang
 * @since 2018/11/9 10:47
 *
 */
abstract class Value : Node()


class ObjectValue : Value(), ObjectTrait


class ArrayValue : Value(), ArrayTrait


class ValueValue(val value: Any?) : Value()
