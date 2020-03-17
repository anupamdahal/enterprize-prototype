import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*
This class representes a mean by which a user creates and runs a simulation
@author Anupam Dahal, Torunima Taposhi
 */

public class Menu {
	 private ArrayList <String> DeathReports;
	 private boolean plentiful,dangerous;
	 private int years;

	 /**
	  * Constructs a menu to work with
	  */
	 Menu(){
		 this.plentiful=true;
		 this.dangerous=true;
		 this.years=0;
		 this.DeathReports=new ArrayList<String>();
	 }

	 /**
	  * Takes input for the conditions and parameters for environment and simulation
	  */
	 public void SetSimulationParameters(){
			Scanner scan1=new Scanner(System.in);
			char choice=' ';
			System.out.println("You must enter the initial conditions for the simulation:");
			System.out.println("Enter the number of years of simulation you want to observe:");
			this.years=scan1.nextInt();
			System.out.println("Will the environment be plentiful? Enter y/n:");
			choice=scan1.next().charAt(0);
			if(choice=='y'||choice=='Y'){
				this.plentiful=true;

			}
			else if(choice=='n'||choice=='N'){
				this.plentiful=false;

			}
			System.out.println("Will the environment be dangerous? Enter y/n:");
			choice=scan1.next().charAt(0);
			if(choice=='y'||choice=='Y'){
				this.dangerous=true;

			}
			else if(choice=='n'||choice=='N'){
				this.dangerous=false;

			}

	 }

	/**
	 * Main Method
	 * @param args
	 */
	public static void main(String[] args){
		Scanner scan2=new Scanner(System.in);
		Menu menu=new Menu();
		char flag='y';
		int userChoice1=0;
		int userChoice2=0;
		//boolean secondMenu=false;
		do{
			/**
			 * user is asked what they would like to do
			 */
			System.out.println("Select the action you wish to execute:");
			System.out.println("1. Simulate Evolution\n2. View Death Report\n3. Exit Menu");
			userChoice1=scan2.nextInt();
			switch(userChoice1){
			/**
			 * Evolution Simulation
			 */
			case 1:
				menu.SetSimulationParameters();
				menu.RunSimulation();
				break;
			/**
		     * Death report is shown
			 */
			case 2:
				menu.ReadDeathReport();
				//Application.launch(args);
				break;
			case 3:
				flag='n';
			default:
				System.out.println("Incorrect input");
				break;
			}
		/**
		 * user may return to the login page
		 */
		System.out.println("Select the action you wish to execute:");
		System.out.println("1. Return to main menu\n2.Exit program");
		userChoice2=scan2.nextInt();
		if(userChoice2==1){
			flag='y';
		}
		else if(userChoice2==2){
			flag='n';
		}



	}while(flag=='y'||flag=='Y');
	}

	/**
	 * Feeds inputs form users to initialize the simulation
	 */
	public void RunSimulation(){
		Environment environment=new Environment(this.plentiful,this.dangerous);
		Simulation simulation=new Simulation(this.years,environment);
		simulation.RunNewSimulation();
		DeathReports.add(simulation.SummarizeDeaths());


	}

	/**
	 * Prints DeathReports to screen when the user asks for it
	 */
	public void ReadDeathReport(){
		for(String report:DeathReports){
			System.out.println(report);
		}

	}


}
