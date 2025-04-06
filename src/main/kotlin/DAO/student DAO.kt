package com.example.DAO

import com.example.StudentTable
import com.example.student

interface StudentDao {
    suspend fun insert( name: String, age: Long): student?
    //gernate sudent list
    suspend fun getAllStudents(): List<student>?
    //jp bhi id ush student
    suspend fun getStudentById(userId: Long): student?

    suspend fun update(userId: Long, name: String, age: Long) : Int
    // the last  : k baad  give return type  1 - done and  0  not
    suspend fun deleteStudentById(userId: Long) : Int


}