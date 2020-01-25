public class Media{

    private String name;
    private String[] description;

    public Media (String n, String[] d){
        this.name = n;
        this.description = d;
    }

    public String getName(){
        return this.name;
    }

    public String[] getDescription(){
        return this.description;
    }

    public String printDescription(){
        return "Year" + this.description[0]+ " Rating" + this.description[1]+ " IMDb" + this.description[2];
    }

    public String getInfo(){
        if (description.length>2)
            return this.name + '\n' + this.description[0]+ "\t" + this.description[1] + "\t" +this.description[2];
        else{
            return null;
        }
    }

    public String printInfo(){
        System.out.println(this.getName() + "\n" + this.printDescription());
        return this.getName() + "\n" + this.printDescription();
    }
}