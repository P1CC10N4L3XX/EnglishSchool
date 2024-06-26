package it.uniroma2.database;

import it.uniroma2.database.ProcedureDAO;
import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseListProcedureDAO implements ProcedureDAO {

    private static final String COURSE_CODE = "Codice";
    private static final String COURSE_LEVEL = "Livello";
    private static final String COURSE_ACTIVATION_DATE = "DataDiAttivazione";
    private static final String COURSE_STUDENTS_NUMBER = "NumeroAllievi";

    @Override
    public List<Course> execute(Object... params) throws DAOException{
        List<Course> courseList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call course_list()}");
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            int courseCode, studentsNumber;
            Level courseLevel;
            Date courseActivationDate;
            while(resultSet.next()){
                courseCode = resultSet.getInt(COURSE_CODE);
                courseLevel = new Level(resultSet.getString(COURSE_LEVEL));
                courseActivationDate = resultSet.getDate(COURSE_ACTIVATION_DATE);
                studentsNumber = resultSet.getInt(COURSE_STUDENTS_NUMBER);
                courseList.add(new Course(courseCode,courseLevel,courseActivationDate,studentsNumber));
            }
        }catch (SQLException e) {
            throw new DAOException("Error course list: " + e.getMessage());
        }
        return courseList;
    }
}
