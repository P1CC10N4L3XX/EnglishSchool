package it.uniroma2.view.utils;

public enum Color {
    RED,
    BLUE,
    GREEN;

    public static String fromColor(Color color){
        switch(color){
            case RED -> {return "\033[31m";}
            case BLUE -> {return "\033[34m";}
            case GREEN -> {return "\033[32m";}
        }
        return null;
    }


}
