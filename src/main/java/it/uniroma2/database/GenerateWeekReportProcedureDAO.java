package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.Course;
import it.uniroma2.models.Lesson;
import it.uniroma2.models.Level;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateWeekReportProcedureDAO implements ProcedureDAO{
    private static String COURSE_CODE = "CodiceCorso";
    private static String COURSE_LEVEL = "LivelloCorso";
    private static String LESSON_DAY = "Giorno";
    private static String START_TIME = "OraInizio";
    private static String END_TIME = "OraFine";

    @Override
    public List<Lesson> execute(Object... params) throws DAOException{
        String teacher = (String) params[0];
        List<Lesson> lessonList= new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_week_report(?)}");
            cs.setString(1,teacher);
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            int courseCode;
            Level courseLevel;
            String lessonDay;
            Time startTime;
            Time endTime;
            while(resultSet.next()){
                courseCode = resultSet.getInt(COURSE_CODE);
                courseLevel = new Level(resultSet.getString(COURSE_LEVEL));
                lessonDay = resultSet.getString(LESSON_DAY);
                startTime = resultSet.getTime(START_TIME);
                endTime = resultSet.getTime(END_TIME);
                lessonList.add(new Lesson(new Course(courseCode,courseLevel),lessonDay,startTime,endTime));
            }
        }catch(SQLException e){
            throw new DAOException("Error report: "+e.getMessage());
        }
        return lessonList;
    }
}
