
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class LAB6P1 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("BarChart Experiments");

// required data from documantation
        double[] avgHousingPricesByYear = {
        		247381.0,264171.4,287715.3,294736.1,
        		308431.4,322635.9,340253.0,363153.7
        		};
         double[] avgCommercialPricesByYear = {
        		1121585.3,1219479.5,1246354.2,1295364.8,
        		1335932.6,1472362.0,1583521.9,1613246.3
        		};

        
        
        
// names for the x and the y axis
        CategoryAxis xAxis    = new CategoryAxis();
        xAxis.setLabel("Series1");

        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Series2");
// creates the bar chart
        BarChart     barChart = new BarChart(xAxis, yAxis);
//creates series one for the bar.
       XYChart.Series dataSeries1 = new XYChart.Series();
       dataSeries1.setName("AVGHousing");
// change the color of the series.
       
       barChart.getStylesheets().add(getClass().getResource("chart.css").toExternalForm());
// Hides the legend
       barChart.setLegendVisible(false);
       
//fill series one of data
       for(int x=0;x<avgHousingPricesByYear.length;x++)
       {
    	   dataSeries1.getData().add(new XYChart.Data(""+x+"", avgHousingPricesByYear[x]));
       }
        barChart.getData().add(dataSeries1);
        

        
        

//creates second series for the bar.
        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("AVGCommerical");

        for(int x=0;x<avgCommercialPricesByYear.length;x++)
        {
     	   dataSeries2.getData().add(new XYChart.Data(""+x+"", avgCommercialPricesByYear[x]));
        }

        barChart.getData().add(dataSeries2);
        
        
//create the vbox and display the charts
        VBox vbox = new VBox(barChart);

        Scene scene = new Scene(vbox, 400, 200);

        primaryStage.setScene(scene);
        primaryStage.setHeight(300);
        primaryStage.setWidth(1200);

        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}