import java.io.*; 
import java.util.HashMap;
import java.util.Map;

public class AccountMedia extends AdminMedia{
    
    private FileWriter file;
    private Map<String, Media> recommended = new HashMap<>();

    public AccountMedia(String username){
        super(username);
    }

    public void saveRecommendation(String username){
        try {
            String[] temp = {""};
            recommended.forEach((k,v) -> temp[0] += k + "\n" + v.getDescription()+'\n'+'\n');

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
        if (allMedia.containsKey(N)){
            if(!recommended.containsKey(N)){
                recommended.put(N, allMedia.get(N));
            }
            return allMedia.get(N);
        }
        else{
            System.out.println(N + " not found\n");
            return null;
        }
    }

    public Map<String, Media> getRecommended(){
        return recommended;
    }
}