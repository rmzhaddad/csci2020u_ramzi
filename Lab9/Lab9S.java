
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Lab9S extends Application {
	XYChart.Series series2;
	XYChart.Series series1;

	@Override
	public void start(Stage stage) throws IOException {
		final LineChart<String, Number> lineChart = drawLineChart(stage);

		drawLinePlot();

		Scene scene = new Scene(lineChart, 800, 600);
		lineChart.getData().addAll(series1, series2);

		stage.setScene(scene);
		stage.show();
	}

	private void drawLinePlot() throws IOException {
		series1 = new XYChart.Series();
		series1.setName("Stock1");
		series2 = new XYChart.Series();
		series2.setName("Stock2");
		drowLine(series1, "AAPL");
		drowLine(series2, "GOOG");
	}

	private void drowLine(XYChart.Series series1, String stockName) throws IOException {
		try {// 6IY0N1XACF3L1P1F

			// creates url object
			URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + stockName
					+ "&outputsize=full&apikey=6IY0N1XACF3L1P1F&datatype=csv");
			// this will create url connection
			URLConnection urlConn = url.openConnection();
			// stream readers
			InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
			BufferedReader buff = new BufferedReader(inStream);
			// reading data
			String line = buff.readLine();
			line = buff.readLine();
			while (line != null) {
				String[] fields = line.split(",", -1);
				System.out.println(line);
				// ---------insert data in rows
				series1.getData().add(new XYChart.Data(fields[0], Float.parseFloat(fields[4])));
				// ---------
				line = buff.readLine();
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private LineChart<String, Number> drawLineChart(Stage stage) {
		stage.setTitle("Stock Compare");
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Date");
		final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
		lineChart.setTitle("");
		// end of linechart create
		return lineChart;
	}

	public static void main(String[] args) {
		launch(args);
	}
}