import java.io.*;
import java.util.Scanner; 
import java.util.HashMap;
import java.util.Map;

public class AdminMedia{

    protected File file;
    protected Map<String, Media> allMedia = new HashMap<>();

    public AdminMedia(String filename){
        this.init(filename);
    }

    protected void init(String filename){

        try {
            
            file = new File(filename);
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()){
                Media newMedia = new Media(sc.nextLine(), sc.nextLine());
                if (sc.hasNextLine()){
                    sc.nextLine();
                }

                allMedia.put(newMedia.getName(), newMedia);

            }

            sc.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        
    }

    public void displayAllMedia(){
        for (String name: allMedia.keySet()){
            System.out.println(name);
        }
    }

    public Map<String, Media> getAllMedia(){
        return allMedia;
    }
}