package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.DAOException;
import it.uniroma2.exceptions.DataTooLongException;
import it.uniroma2.exceptions.DuplicatedEntryDAOException;
import it.uniroma2.exceptions.LessonTimeException;
import it.uniroma2.models.Assignation;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;
import it.uniroma2.models.Teacher;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class AssignationListProcedureDAO implements ProcedureDAO{
    private static final String COURSE_CODE = "CodiceCorso";
    private static final String COURSE_LEVEL = "LivelloCorso";
    private static final String ASSIGNATION_TEACHER = "Insegnante";
    @Override
    public List<Assignation> execute(Object... params) throws DAOException {
        List<Assignation> assignationList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call assignation_list()}");
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            String teacherCf, courseLevel;
            int courseCode;
            while(resultSet.next()){
                teacherCf = resultSet.getString(ASSIGNATION_TEACHER);
                courseCode = resultSet.getInt(COURSE_CODE);
                courseLevel = resultSet.getString(COURSE_LEVEL);
                assignationList.add(new Assignation(new Teacher(teacherCf),new Course(courseCode,new Level(courseLevel))));
            }
        } catch (SQLException e) {
            throw new DAOException("Assignation list error: " + e.getMessage());
        }
        return assignationList;
    }
}
