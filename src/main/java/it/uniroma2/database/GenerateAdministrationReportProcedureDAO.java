package it.uniroma2.database;

import it.uniroma2.database.utils.ConnectionFactory;
import it.uniroma2.exceptions.*;
import it.uniroma2.models.Course;
import it.uniroma2.models.Level;
import it.uniroma2.models.ReportRowAdministration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenerateAdministrationReportProcedureDAO implements ProcedureDAO{
    private static String LESSON_DATE = "Data";
    private static String COURSE_CODE = "CodiceCorso";
    private static String COURSE_LEVEL = "LivelloCorso";
    private static String LESSON_DAY = "Giorno";
    private static String START_TIME = "OraInizio";
    private static String END_TIME = "OraFine";

    @Override
    public List<ReportRowAdministration> execute(Object... params) throws DAOException {
        String teacher = (String) params[0];
        int month = (int) params[1];
        List<ReportRowAdministration> reportRowAdministrationList = new ArrayList<>();
        try{
            Connection connection = ConnectionFactory.getInstance().getConnection();
            CallableStatement cs = connection.prepareCall("{call create_administration_report(?,?)}");
            cs.setString(1,teacher);
            cs.setInt(2,month);
            cs.execute();
            ResultSet resultSet = cs.getResultSet();
            Date lessonDate;
            int courseCode;
            Level courseLevel;
            String lessonDay;
            Time startTime;
            Time endTime;
            while(resultSet.next()){
                lessonDate = resultSet.getDate(LESSON_DATE);
                courseCode = resultSet.getInt(COURSE_CODE);
                courseLevel = new Level(resultSet.getString(COURSE_LEVEL));
                lessonDay = resultSet.getString(LESSON_DAY);
                startTime = resultSet.getTime(START_TIME);
                endTime = resultSet.getTime(END_TIME);
                reportRowAdministrationList.add(new ReportRowAdministration(lessonDate,lessonDay,new Course(courseCode,courseLevel),startTime,endTime));
            }
        }catch(SQLException e){
            throw new DAOException("Error report: "+e.getMessage());
        }
        return reportRowAdministrationList;
    }
}
