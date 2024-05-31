package it.uniroma2.view.utils;

import it.uniroma2.exceptions.NotCompatibleOsException;

import java.io.IOException;

public class GraphicUtils {
    public static void showTitle(String title){
        final int SPACING=16;
        String cuteLine = "+" + "-".repeat(title.length() + SPACING) + "+";
        String cuteTitle = "+"+" ".repeat(SPACING/2)+title+" ".repeat(SPACING/2)+"+";
        System.out.println(cuteLine);
        System.out.println(cuteTitle);
        System.out.println(cuteLine);
    }
    public static void showSpacing(int spacing){
        for(int i=0;i<spacing;i++){
            System.out.println();
        }
    }

    private static String osType(){
        return System.getProperty("os.name").toLowerCase();
    }
    private static void executeCommand(String command) throws IOException,InterruptedException{
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process = processBuilder.inheritIO().start();
        process.waitFor();
    }

    public static void clear() throws IOException,InterruptedException,NotCompatibleOsException {
        String command;
        if(osType().contains("win")){
            command = "cls";
        }else if(osType().contains("mac") || osType().contains("sunos") || osType().contains("nix") || osType().contains("nux") || osType().contains("aix")){
            command = "clear";
        }else{
            throw new NotCompatibleOsException("Operative System is not compatible with this application");
        }
        executeCommand(command);
    }
}
