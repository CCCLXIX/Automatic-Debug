import java.util.*;
import java.io.*;

	// PP = Private || Public
	// CIA = Class || Interface || Abstract
	// VN = Variable Name
	// IE = Implements || Exptends 

public class Reader implements Input{

	private String File_name;
	private ArrayList<String> toAnalizer;
	private ArrayList<String> toAnalizer2;




	public Reader (String file_name, ArrayList<String> input){
		File_name = file_name;
		if(input == null){
			toAnalizer = new ArrayList<String>();
			toAnalizer2 = new ArrayList<String>();
		} else{
			toAnalizer = input;
		}
		
	}

	public ArrayList<String> read() throws FileNotFoundException{
		Scanner sc = new Scanner(new File(File_name));
		process(sc);
		return toAnalizer;

	}

	public ArrayList<String> read(boolean r){
		String line;
		try(
			InputStream IS = new FileInputStream(File_name);
			InputStreamReader ISR = new InputStreamReader(IS);
			BufferedReader br = new BufferedReader(ISR);
			){
			while((line = br.readLine()) != null){
				toAnalizer2.add(line);
			}
		} catch(IOException e){
			
		}
		return toAnalizer2;
	}


	public ArrayList<String> toLibraryHandlerAnalizer(){
		System.out.println("Initializing LibraryHandlerAnalizer...");
		ArrayList<String> tempReturn = new ArrayList<String>();

		for(int i = 0; i<toAnalizer.size(); i ++){
			if(toAnalizer.get(i).equals("import")){
				tempReturn.add(toAnalizer.get(i+1 ));
			} else if(toAnalizer.get(i).equals("public") || toAnalizer.get(i).equals("private")){
				break;
			}
		}
		System.out.println("Libraries:");
		for(int i = 0; i<tempReturn.size();i++){
			System.out.println(tempReturn.get(i));
		}
		System.out.println("LibraryHandlerAnalizer [OK] \n");
		return tempReturn;
	}

	public Map<String, String> toClassHandlerAnalizer(){
		System.out.println("Initializing ClassHandlerAnalizer...");
		Map<String, String> tempReturn = new TreeMap<String, String>();
		boolean interfaceSentinel =false;
		for(int i = 0; i < toAnalizer.size(); i++){
			if(toAnalizer.get(i).equals("private") || toAnalizer.get(i).equals("public")){
				tempReturn.put("PP", toAnalizer.get(i));
				System.out.println("Public/Private: "+ tempReturn.get("PP"));
				toAnalizer.remove(i);
				i--;
				continue;
			}
			if(toAnalizer.get(i).equals("interface")){
				tempReturn.put("Interface", toAnalizer.get(i +1));
				toAnalizer.remove(i);
				toAnalizer.remove(i);
				System.out.println("Interface: "+ tempReturn.get("Interface"));
				i--;
				continue;
			}
			if(toAnalizer.get(i).equals("abstract")){
				tempReturn.put("Abstract", toAnalizer.get(i+1));
				toAnalizer.remove(i);
				toAnalizer.remove(i);
				System.out.println("Abstract: "+ tempReturn.get("Abstract"));
				i--;
				continue;
			}
			if(toAnalizer.get(i).equals("class")){
				tempReturn.put("Class",toAnalizer.get(i+1));
				toAnalizer.remove(i);
				toAnalizer.remove(i);
				System.out.println("Class: "+ tempReturn.get("Class"));
				i--;
				continue;
			}
			if(toAnalizer.get(i).equals("implements")){
				tempReturn.put("Implements",toAnalizer.get(i+1));
				toAnalizer.remove(i);
				toAnalizer.remove(i);
				System.out.println("Implements: "+ tempReturn.get("Implements"));
				i--;
				continue;
			}
			if(toAnalizer.get(i).equals("extends")){
				tempReturn.put("Extends",toAnalizer.get(i +1));
				toAnalizer.remove(i);
				toAnalizer.remove(i);
				System.out.println("Extends: "+ tempReturn.get("Extends"));
				i--;
				
				continue;
			}

			if(toAnalizer.get(i).equals("{")){
				toAnalizer.remove(0);

				
				toAnalizer.remove(toAnalizer.size() -1);
				break;
			}
			
		}
		System.out.println("ClassHandlerAnalizer [OK] \n");
		return tempReturn;
	}
	

	public ArrayList<String> cleanArrayList(char selection){
		if(selection == 'l'){
			for(int i = 0; i<toAnalizer.size(); i ++){
				if(toAnalizer.get(i).equals("import")){
					for(int x = 0; x <3; x++){
						toAnalizer.remove(i);
					}
					i--;
					
				} else if(toAnalizer.get(i).equals("public") || toAnalizer.get(i).equals("private")){
					break;
					}
				}
			}
			return toAnalizer;
		}
	
	public ArrayList<String> currentArray(){
		return toAnalizer;
	}


	private void process(Scanner sc){
		String temp = "";
		boolean sentinel = true;
		while(sc.hasNext()){
			temp = sc.next();
			for(int i = 0; i < temp.length(); i++){
				if(specialCase(temp, i)){
					wordScanner(temp);
					sentinel = false;
					break;
				} else{
					sentinel = true;
				}
			}
			if(sentinel){
				toAnalizer.add(temp);
			}
		}

		arrayCleaner();

	}

	private void wordScanner(String input){
		String toreturn = "";
		int[] tempLocation = new int[input.length()];
		int record =0;
		boolean wordFirst = false;

		if(!specialCase(input, 0)){
			wordFirst = true;
		}
		
		for(int i = 0; i< input.length(); i ++){
			if(!specialCase(input, i)){
				toreturn += input.charAt(i);
			} else if(!wordFirst){
				String temp = "" + input.charAt(i);
				toAnalizer.add(temp);
			}else{
				tempLocation[record] = i;
				record++;
			}
		}
		if(wordFirst){
			toAnalizer.add(toreturn);
			}
		for(int x = 0; x < record; x++){
			String temp = "" + input.charAt(tempLocation[x]);
			toAnalizer.add(temp);
			
		}
		if(!wordFirst){
			toAnalizer.add(toreturn);
			}
	}

	 private boolean specialCase(String temp, int i){
	 	return temp.charAt(i) == '(' || temp.charAt(i) == ')' || temp.charAt(i) == ';' || temp.charAt(i) == '{'
						|| temp.charAt(i) == '}';
	 }

	private void arrayCleaner(){
		for(int i = 0; i <toAnalizer.size(); i++){
			if(toAnalizer.get(i).equals("")){
				toAnalizer.remove(i);
			}
		}
	}


}