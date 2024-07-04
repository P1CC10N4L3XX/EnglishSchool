package it.uniroma2.view;

import it.uniroma2.beans.LessonBean;
import it.uniroma2.beans.ReportRowTeacherBean;
import it.uniroma2.exceptions.NotCompatibleOsException;
import it.uniroma2.exceptions.NullListException;
import it.uniroma2.view.utils.Color;
import it.uniroma2.view.utils.GraphicUtils;
import it.uniroma2.view.utils.TableCreator;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class TeacherView {
    public static int getCommand() throws NotCompatibleOsException, IOException, InputMismatchException {
        GraphicUtils.clear();
        GraphicUtils.showTitle("TeacherView", Color.RED);
        GraphicUtils.showSpacing(1);
        System.out.println("1)Genera report");
        System.out.println("2)logout");
        System.out.println("3)Esci dal programma");
        Scanner input = new Scanner(System.in);
        int command;
        System.out.print("Inserire la scelta: ");
        command = input.nextInt();
        return command;
    }
    public static void viewWeekReport(List<ReportRowTeacherBean> reportRowTeacherBeanList, String teacher) {
        GraphicUtils.showSpacing(1);
        GraphicUtils.showSeparator(40);
        GraphicUtils.showSubTitle("Report settimanale");
        GraphicUtils.showSpacing(1);
        System.out.println("Report settimanale generato per l'insegnante con codice fiscale: "+teacher);
        GraphicUtils.showSpacing(1);
        try {
            TableCreator.showTable(reportRowTeacherBeanList);
        } catch (NullListException e) {
            GraphicUtils.showError("Non esiste alcuna lezione a cui l'insegnante sia stato assegnato !!");
            GraphicUtils.showSpacing(1);
        }
    }
}
