package it.uniroma2.view.utils;

import it.uniroma2.exceptions.NullListException;

import java.lang.reflect.Field;
import java.util.List;

public class TableCreator {
    private static final int COLUMN_WIDTH = 20;
    public static <T> void showTable(List<T> raws) throws NullListException {
        if (raws == null || raws.isEmpty()) {
            throw new NullListException();
        }
        Class<?> realClass = raws.get(0).getClass();
        Field[] fields = realClass.getDeclaredFields();
        printScheme(fields);
        for (T item : raws) {
            printEntry(item,fields);
        }
    }
    public static void showRow(Object object){
        Field[] fields = object.getClass().getDeclaredFields();
        printScheme(fields);
        printEntry(object,fields);
    }
    private static void printEntry(Object object,Field[] fields){
        for(Field field : fields){
            field.setAccessible(true);
            try{
                Object value = field.get(object);
                String stringValue = value != null ? value.toString() : "null";
                if(stringValue.length()>COLUMN_WIDTH){
                    System.out.print("|" + stringValue.substring(0,COLUMN_WIDTH-3)+"...");
                }else{
                    System.out.print("|" + stringValue + " ".repeat(COLUMN_WIDTH - stringValue.length()));
                }
            } catch (IllegalAccessException e) {
                System.out.print("|N/A" + " ".repeat(COLUMN_WIDTH - 4));
            }
        }
        System.out.println("|");
        for (Field field : fields) {
            System.out.print("+" + "-".repeat(COLUMN_WIDTH));
        }
        System.out.println("+");
    }
    private static void printScheme(Field[] fields){
        for(Field field : fields){
            System.out.print("+"+"-".repeat(COLUMN_WIDTH));
        }
        System.out.println("+");
        for(Field field : fields){
            System.out.print("|"+Color.fromColor(Color.BLUE)+field.getName()+"\033[0m"+" ".repeat(COLUMN_WIDTH-(field.getName().length())));
        }
        System.out.println("|");
        for(Field field : fields){
            System.out.print("+"+"-".repeat(COLUMN_WIDTH));
        }
        System.out.println("+");
    }
}
