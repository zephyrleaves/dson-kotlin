package net.zephyrleaves.dson

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.jetbrains.kotlin.script.jsr223.KotlinJsr223JvmLocalScriptEngine
import org.testng.annotations.Test
import javax.script.ScriptEngineManager

/**
 * @author zephyrleaves
 * @since 2018/11/9 14:33
 */
class DSONKtTest {

    val mapper = jacksonObjectMapper().enable(SerializationFeature.INDENT_OUTPUT)
    val data = """{
               "a": "a",
               "b": "b",
               "c": "c",
               "complex": {
                 "a": 1,
                 "b": 2,
                 "c": 3,
                 "d": true,
                 "e": false,
                 "f": null
               },
               "array": [
                 1,
                 2,
                 3,
                 4,
                5
               ]
             }"""
    val outerPayload = mapper.readValue<Map<String, Any?>>(data)


    @Test
    fun testDson() {


        val d = obj {
            val payload = outerPayload
            v("default", payload)
            arr("my_array") {
                (payload["array"] as List<Int>).forEach {
                    v(it + 1)
                }
            }
            v("sum", (payload["array"] as List<Int>).sum())
            v("test big_data") {
                hashMapOf("1" to "11", "2" to "22") + payload["complex"] as Map<*, *>
            }
            arr("all_array") {
                v(100)
                v(101)
                v(102)
                (payload["array"] as List<Int>).forEach { v ->
                    v(v + 1000)
                }
                v(2000)
                for (v in payload["array"] as List<Int>) {
                    v(v + 2000)
                }
                v(3000)
                v { hashMapOf("1" to "11", "2" to "22") + payload["complex"] as Map<*, *> }
                obj {
                    v("over", true)
                }
            }
            v("filter") {
                payload.filterKeys { it == "a" || it == "b" } +
                        (payload["complex"] as Map<*, *>).filterKeys { it -> it == "d" || it == "f" }
            }
            obj("filter2") {
                v("a", payload["a"])
                v("b", payload["b"])
                v("d", (payload["complex"] as Map<*, *>)["d"])
                v("f", (payload["complex"] as Map<*, *>)["f"])
            }
        }

        val from = DSON.from(d)
        println(mapper.writeValueAsString(from))
    }

    @Test
    fun testScript() {
        val resource = this::class.java.classLoader.getResourceAsStream("simple.txt")
        val se = ScriptEngineManager().getEngineByExtension("kts") as KotlinJsr223JvmLocalScriptEngine
        val bindings = se.createBindings()
        bindings["outerPayload"] = outerPayload
        val compiler = se.compile(resource.bufferedReader(Charsets.UTF_8))
        val obj = compiler.eval(bindings)
        println(DSON.from(obj))
    }
}


