package net.zephyrleaves.dson

import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import javax.script.CompiledScript
import javax.script.ScriptEngineManager

/**
 * @author kevin.huang
 * @since 2018/11/8 18:28
 *
 */


fun obj(init: ObjectValue.() -> Unit): ObjectValue {
    val dson = ObjectValue()
    dson.init()
    return dson
}

fun arr(init: ArrayValue.() -> Unit): ArrayValue {
    val dson = ArrayValue()
    dson.init()
    return dson
}

inline fun <reified T> Any?.safeAs(): T? =
        if (this is T)
            this
        else
            null


object DSON {

    internal val ScriptEngine = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine

    fun from(v: ObjectValue): Map<String, Any?> {
        return v.data()
    }

    fun from(v: ArrayValue): List<Any?> {
        return v.data()
    }

    fun from(v: Any): Any {
        return when (v) {
            is ObjectValue -> from(v)
            is ArrayValue -> from(v)
            else -> throw Exception("Value can only be ObjectValue or ArrayValue!")
        }
    }

    fun compile(code: String): DSONCompileContext {
        return DSONCompileContext(ScriptEngine.compile(code))

    }
}


class DSONCompileContext(private val compiledScript: CompiledScript) {

    fun eval(bindings: Map<String, Any?>): Any {
        val b = DSON.ScriptEngine.createBindings()
        b.putAll(bindings)
        return compiledScript.eval(b)
    }
}






