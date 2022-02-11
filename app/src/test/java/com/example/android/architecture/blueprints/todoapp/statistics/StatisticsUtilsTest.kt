package com.example.android.architecture.blueprints.todoapp.statistics

import com.example.android.architecture.blueprints.todoapp.data.Task
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Test

class StatisticsUtilsTest{

    @Test
    fun getActivatedAndCompletedStats_noCompleted_returnsZeroHundred(){
        //GIVEN a list of tasks with a single,active task
        val tasks= listOf<Task>(
            Task("title","description",isCompleted = false)
        )
        //WHEN you call getActiveAndCompletedStats()
        val result= getActiveAndCompletedStats(tasks)
        //THEN there are 0% completed and 100% activated
        assertThat(result.completedTasksPercent,`is`(0f))
        assertThat(result.activeTasksPercent,`is`(100f))
    }



    @Test
    fun getActivatedAndCompletedStats_both_returnsZeroHundred(){
        val tasks= listOf<Task>(
            Task("title","description",isCompleted = true),
            Task("title","description",isCompleted = true),
            Task("title","description",isCompleted = false),
            Task("title","description",isCompleted = false),
            Task("title","description",isCompleted = false),
        )
        val result= getActiveAndCompletedStats(tasks)
        assertEquals(40f,result.completedTasksPercent)
        assertEquals(60f,result.activeTasksPercent)
    }

    @Test
    fun getActivatedAndCompleteStats_empty_returnsZeros(){
        val tasks= emptyList<Task>()
        val result= getActiveAndCompletedStats(tasks)
        assertEquals(0f,result.activeTasksPercent)
        assertEquals(0f,result.completedTasksPercent)
    }

    @Test
    fun getActivatedAndCompletedStats_null_returnsZeros(){
        val tasks=null
        val result= getActiveAndCompletedStats(tasks)
        assertEquals(0f,result.completedTasksPercent)
        assertEquals(0f,result.activeTasksPercent)
    }
}