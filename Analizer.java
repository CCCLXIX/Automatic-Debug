import java.util.*;
import java.io.*;
public class Analizer {

	// PP = Private || Public
	// IA = Interface || Abstract
	// ON = Object Name
	// C = Class
	// IE = Implements || Extends 


	public ArrayList<String> Input;
	public String[] libraries;
	private Reader incoming;
	private boolean POPULATED = false;
	public Map<String,String> objectCharacteristics;

	public Analizer(ArrayList<String> input){
		Input = input;
	}

	public void analizePopulate(){
		incoming = new Reader(null, Input);

		objectCharacteristics = new TreeMap<String, String>();
		POPULATED = true;
	}

	public boolean updateList(boolean libraries, boolean methods) throws ReaderNotPopulatedException{
		if(libraries){
			if(POPULATED){
				Input =  incoming.cleanArrayList('l');
				return true;
			}else{
				throw new ReaderNotPopulatedException("Reader Class not populated **updateList**");
			}
			
		}
		return false;
	}
	public void importHandler() throws ReaderNotPopulatedException{

		if(POPULATED){
			ArrayList<String> temp = incoming.toLibraryHandlerAnalizer();
			libraries = new String[temp.size()];

			for(int i = 0; i<temp.size();i++){
				libraries[i] = temp.get(i);
			}
		}else{
			throw new ReaderNotPopulatedException("Reader Class not populated **importHandler**");
		}
	}

	public void classHandler() throws ReaderNotPopulatedException{

		if(POPULATED){
			objectCharacteristics = incoming.toClassHandlerAnalizer();
			
		} else{
			throw new ReaderNotPopulatedException("Reader Class not populated **classHandler**");
		}
	}

	public String toProcess(){
		String toReturn = "Pending... "+ incoming.currentArray().size();
		return toReturn;
	}




}