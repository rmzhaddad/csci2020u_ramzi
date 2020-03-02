
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.chart.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;
 
public class LAB6P2 extends Application {
 
    @Override public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Lab6p2");
        stage.setWidth(500);
        stage.setHeight(500);
        
// gathering data for the pie chart        
        String[] ageGroups = {
        		"18-25", "26-35", "36-45", "46-55", "56-65", "65+"
        		};
        		 int[] purchasesByAgeGroup = {
        		648, 1021, 2453, 3173, 1868, 2247
        		};
        		 Color[] pieColours = {
        		Color.AQUA, Color.GOLD, Color.DARKORANGE,
        		Color.DARKSALMON, Color.LAWNGREEN, Color.PLUM
        		};
        
        
        
        
        
        
//create pieChart and fill it with data
        ObservableList<PieChart.Data> pieChartData =
                FXCollections.observableArrayList(
                new PieChart.Data(""+ageGroups[0]+"", purchasesByAgeGroup[0]),
                new PieChart.Data(""+ageGroups[1]+"", purchasesByAgeGroup[1]),
                new PieChart.Data(""+ageGroups[2]+"", purchasesByAgeGroup[2]),
                new PieChart.Data(""+ageGroups[3]+"", purchasesByAgeGroup[3]),
                new PieChart.Data(""+ageGroups[4]+"", purchasesByAgeGroup[4]),
                new PieChart.Data(""+ageGroups[5]+"", purchasesByAgeGroup[5])
        		);
         PieChart chart = new PieChart(pieChartData);
         chart.setTitle("Lab6 P2");
         chart.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());

        ((Group) scene.getRoot()).getChildren().add(chart);
        stage.setScene(scene);
        stage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}