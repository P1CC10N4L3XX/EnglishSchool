package it.uniroma2.view.utils;

import it.uniroma2.exceptions.NotCompatibleOsException;

import java.io.File;
import java.io.IOException;

public class GraphicUtils {
    public static void showTitle(String title){
        final int SPACING=16;
        String cuteLine = "+" + "-".repeat(title.length() + SPACING) + "+";
        String cuteTitle = "|"+" ".repeat(SPACING/2)+title+" ".repeat(SPACING/2)+"|";
        System.out.println(cuteLine);
        System.out.println(cuteTitle);
        System.out.println(cuteLine);
    }

    public static void showSubTitle(String subTitle){
        final int SPACING=4;
        String cuteLine = "*".repeat(subTitle.length()+SPACING);
        String cuteSubtitle = "*"+" ".repeat(SPACING/2)+subTitle+" ".repeat(SPACING/2)+"*";
        System.out.println(cuteLine);
        System.out.println(cuteSubtitle);
        System.out.println(cuteLine);
    }

    public static void showSeparator(int k){
        System.out.println("*".repeat(k));
    }

    public static void showSpacing(int spacing){
        for(int i=0;i<spacing;i++){
            System.out.println();
        }
    }

    private static String osType(){
        return System.getProperty("os.name").toLowerCase();
    }
    public static void clear() throws NotCompatibleOsException {
        try{
            if(osType().contains("win")){
                new ProcessBuilder("cmd", "/c", "cls").start().waitFor();
            }else if(osType().contains("mac") || osType().contains("sunos") || osType().contains("nix") || osType().contains("nux") || osType().contains("aix")){
                new ProcessBuilder("clear").start().waitFor();
            }else{
                throw new NotCompatibleOsException("Operative System is not compatible with this application");
            }
        }catch(IOException | InterruptedException e){
            throw new RuntimeException(e);
        }
    }


    public static void showError(String message) {
        System.out.println("\033[0;31m"+message+"\\033[0m");
    }
}
