import java.io.*;
import java.util.Scanner;
public class Admin {
	private String username;
	private String password;
	private File file;
	public Admin(){
		username=null;
		password=null;
		
		
	}
	public void createAccount(String username, String password){
		
		try{
			
            BufferedWriter out = new BufferedWriter( 
                   new FileWriter("accounts.txt", true)); 
            out.write(username+"\t"+password+"\n"); 
            FileOutputStream output = new FileOutputStream(username+".txt");
            output.close();
            out.close(); 
		}
		catch(IOException e){
			e.printStackTrace();
	}
		
	
	}
	public boolean login(String username, String password){
		this.username=username;
		this.password=password;
		
		file=new File("accounts.txt");
		boolean flag=false;
		
		try{
			
			Scanner scan = new Scanner(file); 
			  
		    while(scan.hasNextLine()) {
		    	if(scan.nextLine().equals(username+"\t"+password)){
		    		flag=true;
		    				    	}
		    	
		    }
		    scan.close();	      
			}
		catch(IOException e){
			e.printStackTrace();
	}
		
		return flag;

	}
	
	

}
