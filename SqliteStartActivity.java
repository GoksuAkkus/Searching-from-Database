package com.example.cuyanik.sqlitedatabaseapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class SqliteStartActivity extends AppCompatActivity {

    ArrayList<Employee> employees = new ArrayList<>();

    EmployeeAdaptor employeeAdaptor;

    EmployeeDBContext employeeDBContext;

    EditText etName, etSurname, etAge, etSalary;
    Spinner spGender;

    Button btnAdd, btnDelete, btnUpdate;

    ListView lvEmployees;
    int selectedEmployeePosition = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite_start);

        this.initialize();

    }

    private void initialize() {

        etName = (EditText) findViewById(R.id.etName);
        etSurname = (EditText) findViewById(R.id.etSurname);
        etAge = (EditText) findViewById(R.id.etAge);
        etSalary = (EditText) findViewById(R.id.etSalary);
        spGender = (Spinner) findViewById(R.id.spGender);

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        lvEmployees = (ListView) findViewById(R.id.lvEmployees);
        lvEmployees.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                selectedEmployeePosition = position;
                updateBoxesBySelectedEmployee();
            }
        });

        this.employeeDBContext = new EmployeeDBContext(getApplicationContext());

        this.updateListViewContent();


    }

    private void updateListViewContent(){
        employees = employeeDBContext.GetAllEmployees();

        employeeAdaptor = new EmployeeAdaptor(this,employees);

        lvEmployees.setAdapter(employeeAdaptor);
    }

    public void BtnAddClick(View view) {

        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        Integer age = Integer.parseInt(etAge.getText().toString());
        Double salary = Double.parseDouble(etSalary.getText().toString());
        String gender = spGender.getSelectedItem().toString();

        Employee newEmployee = new Employee(name,surname,age,salary,gender);
        employeeDBContext.AddEmployee(newEmployee);
        //employees.add(new Employee(name,surname,age,salary,gender));
        this.updateListViewContent();
    }

    public void updateBoxesBySelectedEmployee(){
        Employee employee = employees.get(selectedEmployeePosition);
        etName.setText(employee.getName());
        etSurname.setText(employee.getSurname());
        etAge.setText(employee.getAge().toString());
        etSalary.setText(employee.getSalary().toString());

        if(employee.getGender().equals("Male")){
            spGender.setSelection(0);
        }else{
            spGender.setSelection(1);
        }

    }

    public void BtnDeleteClick(View view) {
        Employee employee = employees.get(selectedEmployeePosition);
        employeeDBContext.DeleteEmployee(employee);
        //employees.remove(employee);
        this.updateListViewContent();
    }

    public void BtnUpdateClick(View view) {
        String name = etName.getText().toString();
        String surname = etSurname.getText().toString();
        Integer age = Integer.parseInt(etAge.getText().toString());
        Double salary = Double.parseDouble(etSalary.getText().toString());
        String gender = spGender.getSelectedItem().toString();


        Employee employee = employees.get(selectedEmployeePosition);
        employee.setName(name);
        employee.setSurname(surname);
        employee.setAge(age);
        employee.setSalary(salary);
        employee.setGender(gender);

        employeeDBContext.UpdateEmployee(employee);

        this.updateListViewContent();
    }

    public void BtnClearTable(View view) {
        employeeDBContext.RemoveAllEmployees();
        this.updateListViewContent();
    }
}
