public class Media{

    private String name;
    private String description;

    public Media (String n, String d){
        this.name = n;
        this.description = d;
    }

    public String getName(){
        return this.name;
    }

    public String getDescription(){
        return this.description;
    }

    public String getInfo(){
        return this.getName() + " " + this.getDescription();
    }
}