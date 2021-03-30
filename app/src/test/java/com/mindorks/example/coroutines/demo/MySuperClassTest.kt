package com.mindorks.example.coroutines.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindorks.example.coroutines.democoroutine.MySuperClass
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

class MySuperClassTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    private val testCoroutineScope = TestCoroutineScope(testCoroutineDispatcher)

    private val mySuperClass =
        MySuperClass(
            testCoroutineScope,
            testCoroutineDispatcher
        )

    @Test
    fun testGetResult() {
    }

    @Test
    fun testGetANumber() = runBlocking {
        assertEquals(2, mySuperClass.getANumber())
    }

    @Test
    fun `Case two with runBlocking`() {
        runBlocking {
            mySuperClass.addTwoNumbers()
        }
        assertEquals(6,mySuperClass.result.value)

    }

    @Test
    fun testAddTwoNumbers() {
        testCoroutineDispatcher.runBlockingTest {
            mySuperClass.addTwoNumbers()
        }
        assertEquals(6,mySuperClass.result.value)
    }
}