package net.zephyrleaves.dson

import org.testng.annotations.Test

/**
 * @author zephyrleaves
 * @since 2018/11/9 14:33
 */
class DSONKtTest {

    @Test
    fun testDson() {
        val a = false

        var d = dson {
            if (a) {
                obj("1") {
                }
            } else {
                array("2") {
                    value(null)
                    value(123)
                    value {
                        "123213"
                    }
                    value(321L.toString())
                    obj {
                        obj("1") {
                            value("1", "2")
                        }

                    }
                }
            }


            value("3") { "32" }
            value("3", "32")
        }

        println(d)
    }
}