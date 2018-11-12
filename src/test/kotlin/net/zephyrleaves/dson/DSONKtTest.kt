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


        val d = dson {
            val payload = outerPayload
            value("default", payload)
            arr("my_array") {
                val tmp1 = payload["array"] as List<*>
                for (v in tmp1) {
                    value(v as Int + 1)
                }
            }
            value("sum", (payload["array"] as List<Int>).sum())
            value("test big_data") {
                hashMapOf("1" to "11", "2" to "22") + payload["complex"] as Map<*, *>
            }
            arr("all_array") {
                value(100)
                value(101)
                value(102)
                val tmp1 = payload["array"] as List<Int>
                for (v in tmp1) {
                    value(v + 1000)
                }
                value(2000)
                for (v in tmp1) {
                    value(v + 2000)
                }
                value(3000)
                hashMapOf("1" to "11", "2" to "22") + payload["complex"] as Map<*, *>
                obj {
                    value("over", true)
                }
            }
            obj("filter") {
                payload.filterKeys {
                    it == "a" || it == "b"
                } + (payload["complex"] as Map<*, *>).filterKeys {
                    it == "d" || it == "f"
                }
            }
        }

        println(mapper.writeValueAsString(d))
    }
}