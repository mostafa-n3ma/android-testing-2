package com.example.android.architecture.blueprints.todoapp.tasks


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.architecture.blueprints.todoapp.data.Task
import com.example.android.architecture.blueprints.todoapp.data.source.FakeTestRepository
import com.example.android.architecture.blueprints.todoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.CoreMatchers.not
import org.hamcrest.Matchers.nullValue
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

//@RunWith(AndroidJUnit4::class)
class TasksViewModelTest{


    private lateinit var taskViewModel: TasksViewModel
    private lateinit var tasksRepository:FakeTestRepository

    @Before
    fun setTheViewModel(){
       tasksRepository=FakeTestRepository()
        val task1= Task("Title1","Description1")
        val task2= Task("Title2","Description2")
        val task3= Task("Title3","Description3")
        tasksRepository.addTasks(task1,task2,task3)

        taskViewModel=TasksViewModel(tasksRepository)

    }


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Test
    fun addNewTask_setNewTaskEvent(){

        taskViewModel.addNewTask()

        val value=taskViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(), not(nullValue()))
    }

    @Test
    fun setFilterAllTasks_TasksAddViewVisible(){

        taskViewModel.tasksAddViewVisible.getOrAwaitValue()

        taskViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        val value=taskViewModel.tasksAddViewVisible.value

        assertThat(value, `is`(true))

    }



    @Test
    fun newTestEvent_Zero_Zero(){

        taskViewModel.updateTestEvent(0)

        val value=taskViewModel.testEvent.getOrAwaitValue()
        assertThat(value, `is`(0))
    }

    @Test
    fun newTestEvent_One_One(){
        taskViewModel.updateTestEvent(1)
        val value=taskViewModel.testEvent.getOrAwaitValue()
        assertThat(value, `is`(1))
    }


}
