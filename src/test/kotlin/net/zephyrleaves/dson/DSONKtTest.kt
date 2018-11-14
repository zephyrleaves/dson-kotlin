package net.zephyrleaves.dson

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.testng.annotations.Test

/**
 * @author zephyrleaves
 * @since 2018/11/9 14:33
 */
class DSONKtTest {

    val mapper = jacksonObjectMapper()

    @Test
    fun testDson() {

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
                hashMapOf("1" to "11", "2" to "22") + payload["complex"] as Map<*, *>
                obj {
                    v("over", true)
                }
            }
            obj("filter") {
                payload.filterKeys { it -> it == "a" || it == "b" } +
                        (payload["complex"] as Map<*, *>).filterKeys { it -> it == "d" || it == "f" }
            }
        }

        println(mapper.writeValueAsString(d))
    }
}