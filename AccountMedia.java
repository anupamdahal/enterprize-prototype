import java.io.*; 
import java.util.HashMap;
import java.util.Map;

public class AccountMedia extends AdminMedia{
    
    private FileWriter file;
    private Map<String, Media> adminMedia = new HashMap<>();
    // private Map<String, Media> myMedia = new HashMap<>();

    public AccountMedia(String username, Map<String, Media> adminmedialist){
        super(username + ".txt");
        this.adminMedia = adminmedialist;
        
    }

    public void saveRecommendation(String username){
        try {
            String[] temp = {""};
            allMedia.forEach((k,v) -> temp[0] += v.getInfo() +'\n'+'\n');

            System.out.println(temp[0]);
            
            file = new FileWriter(username);
            file.write(temp[0]);
            file.close();

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }
   
    public Media search(String N){
         
        if (adminMedia.containsKey(N)){
            if(!allMedia.containsKey(N)){
                allMedia.put(N, adminMedia.get(N));
            }
            return allMedia.get(N);
        }
        else{
            System.out.println(N + " not found\n");
            return null;
        }
    }

    public Map<String, Media> getRecommended(){
        return allMedia;
    }

    public void filter(String field, String value, int i){

        int count = 0;

        if (field.equals("year")){

            for (Media a: adminMedia.values()){
                // System.out.println(a.getName());
                if (a.getDescription()[0].equals(value)){
                    System.out.println(a.getName());
                    count++;
                    if (count>i){
                        return;
                    }
                }
            }
        }
        else if (field.equals("ratings")){
            for (Media a: adminMedia.values()){
                if (a.getDescription()[1].equals(value)){
                    System.out.println(a.getName());
                    count++;
                    if (count>i){
                        return;
                    }
                }
                
            }
        }
        else if(field.equals("IMDb")){
            for (Media a: adminMedia.values()){
                if (a.getDescription()[2].equals(value)){
                    System.out.println(a.getName());
                    count++;
                    if (count>i){
                        System.out.println(count);
                        return;
                    }
                }
            }
        }
        else{
            System.out.println(field + " attribute not found.");
        }
    }
}