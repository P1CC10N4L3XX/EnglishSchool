package it.uniroma2.models;

public enum Role {
    AMMINISTRAZIONE(1),
    SEGRETERIA(2),
    INSEGNANTE(3),
    LOGIN(5);

    private final int id;
    Role(int id){
        this.id = id;
    }
    public static Role fromInt(int id){
        for(Role type : values()){
            if(type.getId() == id){
                return type;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }
}
