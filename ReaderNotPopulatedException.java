import java.util.*;

public class ReaderNotPopulatedException extends Exception{

	private String Report;
	public ReaderNotPopulatedException(String report){
		Report = report;
	}

	public String getReport(){
		return Report;
	}

}