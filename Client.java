import java.util.*;
import java.io.*;
public class Client{


	public static void main(String[] args) {
		 ArrayList<String> readArrayList = new ArrayList<String>();

		Reader trial2 = new Reader("Dummy.java", null);
		


		

		/*

		try{
			readArrayList =  trial2.read();
			
			} catch(FileNotFoundException e){
			System.out.println("Bruh");
		}
		*/
		readArrayList = trial2.read(true);

		for(int i = 0; i <readArrayList.size(); i++){
			System.out.println(readArrayList.get(i));
		}

		

		//writeReport(readArrayList);
		
	}

	private static void writeReport( ArrayList<String> input){

		Writer trial = new Writer("Report.txt");
		Analizer analizer = new Analizer(input);
		boolean updatedList = false;

		analizer.analizePopulate();
		try{
		analizer.importHandler();
		} catch(ReaderNotPopulatedException e){
			System.out.println(e.getReport());
		}


		
		try{
			updatedList = analizer.updateList(true, false);
			analizer.classHandler();
		} catch(ReaderNotPopulatedException e){
			System.out.println(e.getReport());
			e.printStackTrace();
		}

		try{
			trial.write("*** Report *** \n\nNumber of statements to be processed: "+analizer.Input.size()+"\n\nLibraries: ");
			for(int i  = 0; i < analizer.libraries.length; i ++){
			trial.write("    "+analizer.libraries[i]);
			}
			trial.write("\nObject Characteristics: ");
			trial.write("    Private/Public: "+ analizer.objectCharacteristics.get("PP"));
			trial.write("    Class: "+ analizer.objectCharacteristics.get("Class"));
			trial.write("    Interface: "+ analizer.objectCharacteristics.get("Interface"));
			trial.write("    Abstract: "+ analizer.objectCharacteristics.get("Abstract"));
			trial.write("    Extends: "+ analizer.objectCharacteristics.get("Extends"));
			trial.write("    Implements: "+ analizer.objectCharacteristics.get("Implements"));
			trial.write("\n\nList: ");	
		} catch(IOException IO){
			System.out.println(":not");
		}
		
		
		if(updatedList){
			for(int i = 0; i <analizer.Input.size(); i++){
				try{
					trial.write("    "+analizer.Input.get(i));
				} catch(IOException e){
					System.out.println("Not..");
				}
			}
		}		
	}
}