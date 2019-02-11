package com.example.cuyanik.sqlitedatabaseapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.Telephony;

import java.util.ArrayList;

/**
 * Created by CUyanik on 9.12.2016.
 */

public class EmployeeDBContext extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "employee.db";

    private static final String TABLE_NAME = "EMPLOYEE";
    private static final String COLUMN_NAME_ID = "ID";
    private static final String COLUMN_NAME_NAME = "NAME";
    private static final String COLUMN_NAME_SURNAME = "SURNAME";
    private static final String COLUMN_NAME_AGE = "AGE";
    private static final String COLUMN_NAME_SALARY = "SALARY";
    private static final String COLUMN_NAME_GENDER = "GENDER";

    public EmployeeDBContext(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

//        CREATE TABLE IF NOT EXISTS EMPLOYEE (
//                ID      INTEGER PRIMARY KEY AUTOINCREMENT,
//                NAME    TEXT,
//                SURNAME TEXT,
//                AGE     INTEGER,
//                SALARY  DOUBLE,
//                GENDER  TEXT
//        );

        String create_table_command = " CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
                                      COLUMN_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                      COLUMN_NAME_NAME + " TEXT, " +
                                      COLUMN_NAME_SURNAME + " TEXT, " +
                                      COLUMN_NAME_AGE  +  " INTEGER, " +
                                      COLUMN_NAME_SALARY + " DOUBLE, " +
                                      COLUMN_NAME_GENDER + " TEXT " +
                                      " ); ";

        db.execSQL(create_table_command);
    }


    public void AddEmployee(Employee newEmployee){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, newEmployee.getName() );
        values.put(COLUMN_NAME_SURNAME, newEmployee.getSurname() );
        values.put(COLUMN_NAME_AGE, newEmployee.getAge());
        values.put(COLUMN_NAME_SALARY, newEmployee.getSalary());
        values.put(COLUMN_NAME_GENDER, newEmployee.getGender() );

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void DeleteEmployee(Employee deleteEmploye){

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME, COLUMN_NAME_ID + " = ? ", new String[] { deleteEmploye.getId().toString() } );
        db.close();
    }

    public void UpdateEmployee(Employee updateEmployee){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_NAME, updateEmployee.getName() );
        values.put(COLUMN_NAME_SURNAME, updateEmployee.getSurname() );
        values.put(COLUMN_NAME_AGE, updateEmployee.getAge());
        values.put(COLUMN_NAME_SALARY, updateEmployee.getSalary());
        values.put(COLUMN_NAME_GENDER, updateEmployee.getGender() );

        db.update(TABLE_NAME,values, COLUMN_NAME_ID + " = ? ", new String[] { updateEmployee.getId().toString() });

        db.close();
    }

    public Employee GetEmployee(Long id){
        //SELECT * FROM TABLE_NAME WHERE ID = id
        String select_command = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_ID + " = " + id.toString();

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor =  db.rawQuery(select_command,null);

        Employee employee = null;

        if(cursor.moveToFirst()){
            Long employeID  = cursor.getLong( cursor.getColumnIndex(COLUMN_NAME_ID) );
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SURNAME));
            Integer age = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_AGE));
            Double salary = cursor.getDouble(cursor.getColumnIndex(COLUMN_NAME_SALARY));
            String gender = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENDER));

            employee = new Employee(employeID,name,surname,age,salary,gender);
        }

        cursor.close();
        db.close();


        return employee;


    }

    public ArrayList<Employee> GetAllEmployees(){

        //SELECT * FROM TABLE_NAME
        String select_all_command = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(select_all_command,null);

        ArrayList<Employee> employeeList = new ArrayList<>();

        while(cursor.moveToNext()){

            Long employeID  = cursor.getLong( cursor.getColumnIndex(COLUMN_NAME_ID) );
            String name = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME));
            String surname = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_SURNAME));
            Integer age = cursor.getInt(cursor.getColumnIndex(COLUMN_NAME_AGE));
            Double salary = cursor.getDouble(cursor.getColumnIndex(COLUMN_NAME_SALARY));
            String gender = cursor.getString(cursor.getColumnIndex(COLUMN_NAME_GENDER));

            Employee employee = new Employee(employeID,name,surname,age,salary,gender);

            employeeList.add(employee);
        }

        cursor.close();
        db.close();

        return employeeList;
    }

    public void RemoveAllEmployees(){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_NAME,null,null);

        db.close();
    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
