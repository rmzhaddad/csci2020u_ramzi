
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    Stage window;
    TableView<StudentRecord> table;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("LAB5");

        //Studentid
        TableColumn<StudentRecord, String> StudentID = new TableColumn<>("SID");
        StudentID.setMinWidth(200);
        StudentID.setCellValueFactory(new PropertyValueFactory<>("SID"));
        
      //Midterm
        TableColumn<StudentRecord, String> Midterm = new TableColumn<>("Midterm");
        Midterm.setMinWidth(200);
        Midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
        //Midterm
        TableColumn<StudentRecord, String> Assignments = new TableColumn<>("Assginments");
        Assignments.setMinWidth(200);
        Assignments.setCellValueFactory(new PropertyValueFactory<>("Assginments"));
      //FinalExam
        TableColumn<StudentRecord, String> FinalExam = new TableColumn<>("FinalExam");
        FinalExam.setMinWidth(200);
        FinalExam.setCellValueFactory(new PropertyValueFactory<>("FianlExam"));
        
      //FinalExam
        TableColumn<StudentRecord, String> FinalMark = new TableColumn<>("FinalMark");
        FinalMark.setMinWidth(200);
        FinalMark.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));
        
        //FinalExam
        TableColumn<StudentRecord, String> Grade = new TableColumn<>("Grade");
        Grade.setMinWidth(200);
        Grade.setCellValueFactory(new PropertyValueFactory<>("Grade"));
        
        
        

        table = new TableView<>();
        table.setItems(getAllMarks());
        table.getColumns().addAll(StudentID,Midterm,Assignments,FinalExam,FinalMark,Grade);

        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);

        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.show();
    }


    
  
    	public ObservableList<StudentRecord> getAllMarks() {
    	ObservableList<StudentRecord> marks =
    	FXCollections.observableArrayList();

    	
    	// Student ID, Assignments, Midterm, Final exam
    	marks.add(new StudentRecord("100100100", 75.0, 68.0, 54.25));
    	marks.add(new StudentRecord("100100101", 70.0, 69.25, 51.5));
    	marks.add(new StudentRecord("100100102", 100.0, 97.0, 92.5));
    	marks.add(new StudentRecord("100100103", 90.0, 88.5, 68.75));
    	marks.add(new StudentRecord("100100104", 72.25, 74.75, 58.25));
    	marks.add(new StudentRecord("100100105", 85.0, 56.0, 62.5));
    	marks.add(new StudentRecord("100100106", 70.0, 66.5, 61.75));
    	marks.add(new StudentRecord("100100107", 55.0, 47.0, 50.5));
    	marks.add(new StudentRecord("100100108", 40.0, 32.5, 27.75));
    	marks.add(new StudentRecord("100100109", 82.5, 77.0, 74.25));
    	return marks;
    	}
    	

    	


}

   
