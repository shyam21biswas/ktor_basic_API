package com.example.repo

import com.example.DAO.StudentDao
import com.example.StudentTable
import com.example.student
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.statements.InsertStatement

class studentrepository : StudentDao {
    override suspend fun insert( name: String, age: Long): student? {
        var statement: InsertStatement<Number>? = null
        DatabaseFactory.dbQuery {
             statement = StudentTable.insert { student ->
                student[StudentTable.name] = name
                student[StudentTable.age] = age

            }
        }
        return rowtostudent(statement?.resultedValues?.get(0))
    }


    override suspend fun update(userId: Long, name: String, age: Long): Int  =
        DatabaseFactory.dbQuery {
            StudentTable.update ({StudentTable.userId.eq(userId)}) { student ->
                student[StudentTable.name] = name
                student[StudentTable.age] = age
            }
        }

     override suspend fun deleteStudentById(userId: Long): Int =
         DatabaseFactory.dbQuery { StudentTable.deleteWhere { StudentTable.userId.eq(userId) } }






     override suspend fun getStudentById(userId: Long): student? =
         DatabaseFactory.dbQuery {
             StudentTable.select { StudentTable.userId eq (userId)}
                 .map{
                 rowtostudent(it)
             }.singleOrNull()
         }



    override suspend fun getAllStudents(): List<student>? =
        DatabaseFactory.dbQuery {
            //remove null value and give all data
        StudentTable.selectAll().mapNotNull {
            rowtostudent(it)

        }
    }

    private fun rowtostudent(row:ResultRow?): student? {
        if(row == null)
            return null
        return student(
            userId = row[StudentTable.userId],
            name = row[StudentTable.name],
            age = row[StudentTable.age]

        ) }

}