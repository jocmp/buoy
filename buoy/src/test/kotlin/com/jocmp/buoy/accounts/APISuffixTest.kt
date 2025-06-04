package com.jocmp.buoy.accounts

import kotlin.test.Test
import kotlin.test.assertEquals

class APISuffixTest {
    @Test
    fun `without the api path`() {
        val input = "https://example.com/"
        val result = apiSuffix(input)

        assertEquals(
            expected = "https://example.com/api/",
            actual = result
        )
    }

    @Test
    fun `with the API path`() {
        val input = "https://example.com/api/"
        val result = apiSuffix(input)

        assertEquals(
            expected = "https://example.com/api/",
            actual = result
        )
    }
}
