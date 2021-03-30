package com.mindorks.example.coroutines.demo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mindorks.example.coroutines.democoroutine.MySuperClass
import com.mindorks.example.coroutines.utils.getOrAwaitValue
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
    fun testAddTwoNumbers() {
        testCoroutineDispatcher.runBlockingTest {
            mySuperClass.addTwoNumbers()

            mySuperClass.result.observeForever {
                assertEquals(6, it)
            }
        }
    }
}