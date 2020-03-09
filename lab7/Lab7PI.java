

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Side;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
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
	        String currentname="";

	        try (BufferedReader br = new BufferedReader(new FileReader("data.csv"))) {

	            while ((line = br.readLine()) != null) {

	                // use comma as separator
	                String[] country = line.split(cvsSplitBy);

	               
	             
	                currentname=country[5];
	                if(currentname.equals(warningTypes[0]))
	                	values[0]++;
	                if(currentname.equals(warningTypes[1]))
	                	values[1]++;
	                if(currentname.equals(warningTypes[2]))
	                	values[2]++;
	                if(currentname.equals(warningTypes[3]))
	                	values[3]++;
	            	   
	            	   
	            }
	            
	            
	            
	            ObservableList<PieChart.Data> pieChartData =
	                    FXCollections.observableArrayList(

	            		);
	            for(int x=0;x<4;x++) 
	                pieChartData.add(new PieChart.Data(""+warningTypes[x]+"", values[x]));
	                
	                 PieChart chart = new PieChart(pieChartData);
	                 chart.setTitle("Lab7");
	                 chart.setLegendSide(Side.LEFT);
	                 Scene scene=new Scene(chart);
	                Stage stage=new Stage();
	                stage.setScene(scene);
	                stage.show();

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }	
	  public static void main(String[] args) {
	        Application.launch(args);
	    }

}
