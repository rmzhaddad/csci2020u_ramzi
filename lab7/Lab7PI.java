package Lab7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

public class Lab7PI extends Application {

	// create a list of warning
	String[] warningTypes=new String[] {"FLASH FLOOD","SEVERE THUNDERSTORM","SPECIAL MARINE","TORNADO"};
	//create  list for value it will a int array
	int[] values=new int[4];

	
	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		
		//this code will read the data from the file and update the values accordingly
		  String csvFile = "data.csv";
	        String line = "";
	        String cvsSplitBy = ",";

	        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);

	                System.out.println("Country [code= " + country[4] + " , name=" + country[5] + "]");

	            }

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }	
	  public static void main(String[] args) {
	        Application.launch(args);
	    }

}
