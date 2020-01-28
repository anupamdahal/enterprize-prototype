import java.util.Scanner;
public class Netflix {

	public static void main(String[] args) {
		char flag='y',playChoice='n',logout='n',menu='n';
		int userChoice=0;
		String value=null;
		int num=0;
		String movie=null,filter=null;
		String username=null,password=null;
		boolean mainPage=false;
		boolean play=false;
		Admin admin=new Admin();
		AdminMedia adminM=new AdminMedia("netflix.txt");
		AccountMedia am=null;
		Media media=null;
		Scanner scan=new Scanner(System.in);
		System.out.println("Welcome to Netflix!\nEnter the number for the task you wish to do:");
		do{
			
			System.out.println("\n1. Create account\n2. Login");
			userChoice=scan.nextInt();
			switch(userChoice){
			case 1:				
				System.out.println("Enter new username:");
				username=scan.next();
				System.out.println("Enter new password:");
				password=scan.next();
				admin.createAccount(username, password);	
				menu='y';
				am=new AccountMedia(username,adminM.getAllMedia());
				flag='n';
				break;
			case 2:
				System.out.println("Enter username:");
				username=scan.next();
				System.out.println("Enter password:");
				password=scan.next();
				if(admin.login(username, password)==true){
					menu='y';
					am=new AccountMedia(username,adminM.getAllMedia());
					flag='n';
					
				}
				else{
					System.out.println("Incorrect username!\nDo you wish to return to the login page? Enter Y for yes or N for No");
					flag=scan.next().charAt(0);
					menu='n';
				}
				break;
			default:
				
				System.out.println("Incorrect input.\nDo you wish to return to the login page? Enter Y for yes or N for No");
				menu='n';
				flag=scan.next().charAt(0);
				break;
			}
	
		do{
				
				System.out.println("Select the number for the action you would like to execute: ");				
				System.out.println("1. Search for movie\n2. Filter movie list");
				if(!am.getRecommended().isEmpty()){
					System.out.println("3. Play from recommended list\nRecommended movies:\n");
					am.displayAllMedia();
				}
				userChoice=scan.nextInt();
			    switch(userChoice){
				case 1:
					System.out.println("Enter the name of the movie you wish to search:");
					Scanner search_movie = new Scanner(System.in);
					movie=search_movie.nextLine();
					media = am.search(movie);
					if(media!=null)
					{
						System.out.println("Would you like to play this movie? Enter Y for yes and N for no\n");
						playChoice=scan.next().charAt(0);
						if(playChoice=='Y'||playChoice=='y'){
							
							play=true;
						}
					}
					break;
				case 2:
					System.out.println("Enter the number of the field with which you wish to filter:\n1. year\n2. ratings\n3. IMDB");
					num=scan.nextInt();
					switch(num){
					case 1:
						filter="year";
					case 2:
						filter="ratings";
					case 3:
						filter="IMDb";	
					}
					
					System.out.println("Enter the value you wish to filter by: (eg. the 2015 for movies from the year 2015");
					value=scan.next();
					System.out.println("Enter the number of results you wish to view:");
					num=scan.nextInt();
					System.out.println("Movies filtered by "+filter+":");
					am.filter(filter, value+"/10", num);
					System.out.println("Enter the name of the movie you wish to play: ");
					Scanner filter_movie = new Scanner(System.in);
					movie=filter_movie.nextLine();
					media=am.allMedia.get(movie);
					play=true;								
					break;	
				case 3:				
					System.out.println("Enter the name of the movie you wish to play:\n ");
					Scanner recommended_movie = new Scanner(System.in);
					movie=recommended_movie.nextLine();
					media=am.allMedia.get(movie);
					play=true;
					break;
				default:
					System.out.println("Incorrect input");
					break;
				}
			    if(play==true)
			    {
			    	System.out.println(movie+" is playing");
			    	System.out.println(media.printDescription());
			    	
			    }
			    System.out.println("Do you wish to return to the main menu?  Enter Y for yes or N for No ");
			    menu=scan.next().charAt(0);
			
				
			}while(menu=='y'||menu=='Y');
		
		 	System.out.println("Do you wish to logout?  Enter Y for yes or N for No ");
		    logout=scan.next().charAt(0);
		    if(logout=='y'||logout=='Y'){
		    	System.out.println("Logging out");
		    	am.saveRecommendation(username);
		    	
		    }

		System.out.println("Do you wish to return to the login page? Enter Y for yes or N for No");
		flag=scan.next().charAt(0);
	
				
		
	}while(flag=='y'||flag=='Y');

}
}
