
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Lab8S extends Application {
	private static final String FILE_HEADER = "id,Assigments,Midterm,FinalExam";
	// Delimiter used in CSV file
	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = "\n";
	ObservableList<StudentRecord> marks;
	TableView<StudentRecord> table;
	// stores current files viewed file
	File currentFilesname;
	File saveAsFile;
	// create a File chooser 
    FileChooser fil_chooser = new FileChooser(); 
    FileChooser fil2_chooser = new FileChooser(); 
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle("Lab8");
		// this method will create the menu, Menuitems
		MenuBar menuBar = new MenuBar();
		// create actual menu
		Menu file = new Menu("File");
		// adding menu to menu bar
		menuBar.getMenus().add(file);
		// creating menu items
		MenuItem newOption = new MenuItem("New");
		MenuItem openOption = new MenuItem("Open");
		MenuItem saveOption = new MenuItem("Save");
		MenuItem saveAsOption = new MenuItem("Save As");
		MenuItem exitOption = new MenuItem("Exit");
		// adding items to menu
		file.getItems().addAll(newOption, openOption, saveAsOption, saveOption, exitOption);
		// set exit function
		exitOption.setOnAction(e -> System.exit(0));
		newOption.setOnAction(e->table.getItems().removeAll(marks));
		openOption.setOnAction(e->{currentFilesname=fil_chooser.showOpenDialog(primaryStage);
		saveOption.setOnAction(s->{save();});
		System.out.println(""+currentFilesname.getName());
		saveAsOption.setOnAction(r->{saveAsFile=fil2_chooser.showOpenDialog(primaryStage);
		saveAs();
			
		});
		try {
			table.setItems(getAllMarks());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		});
		// this code create an empty file
		currentFilesname = createFile();
		// this method will display the tableview
		displaytable();
		//save();
		
		
		
		VBox vBox = new VBox(menuBar, table);
		Scene scene = new Scene(vBox, 960, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void displaytable() throws IOException {
		// this code will display the tableview .
		// Studentid
		TableColumn<StudentRecord, String> StudentID = new TableColumn<>("SID");
		StudentID.setMinWidth(200);
		StudentID.setCellValueFactory(new PropertyValueFactory<>("SID"));

		// Midterm
		TableColumn<StudentRecord, String> Midterm = new TableColumn<>("Midterm");
		Midterm.setMinWidth(200);
		Midterm.setCellValueFactory(new PropertyValueFactory<>("Midterm"));
		// Midterm
		TableColumn<StudentRecord, String> Assignments = new TableColumn<>("Assginments");
		Assignments.setMinWidth(200);
		Assignments.setCellValueFactory(new PropertyValueFactory<>("Assginments"));
		// FinalExam
		TableColumn<StudentRecord, String> FinalExam = new TableColumn<>("FinalExam");
		FinalExam.setMinWidth(200);
		FinalExam.setCellValueFactory(new PropertyValueFactory<>("FianlExam"));

		// FinalExam
		TableColumn<StudentRecord, String> FinalMark = new TableColumn<>("FinalMark");
		FinalMark.setMinWidth(200);
		FinalMark.setCellValueFactory(new PropertyValueFactory<>("FinalMark"));

		// FinalExam
		TableColumn<StudentRecord, String> Grade = new TableColumn<>("Grade");
		Grade.setMinWidth(200);
		Grade.setCellValueFactory(new PropertyValueFactory<>("Grade"));

		table = new TableView<>();
		table.setItems(getAllMarks());
		table.getColumns().addAll(StudentID, Midterm, Assignments, FinalExam, FinalMark, Grade);

		// -------------------------------------
	}

	private MenuBar menuMethod() {
		// create menubar
		MenuBar menuBar = new MenuBar();
		// create actual menu
		Menu file = new Menu("File");
		// adding menu to menu bar
		menuBar.getMenus().add(file);
		// creating menu items
		MenuItem newOption = new MenuItem("New");
		MenuItem openOption = new MenuItem("Open");
		MenuItem saveOption = new MenuItem("Save");
		MenuItem saveAsOption = new MenuItem("Save As");
		MenuItem exitOption = new MenuItem("Exit");
		// adding items to menu
		file.getItems().addAll(newOption, openOption, saveAsOption, saveOption, exitOption);
		// set exit function
		exitOption.setOnAction(e -> System.exit(0));
		newOption.setOnAction(e->table.getItems().removeAll(marks));
		openOption.setOnAction(e->{});
		return menuBar;

	}

	public ObservableList<StudentRecord> getAllMarks() throws IOException {
		 marks = FXCollections.observableArrayList();
		 try {// this will start the value
			  
				Scanner inputStream = new Scanner(currentFilesname);
				while (inputStream.hasNext()) {
					String data = inputStream.next();
					String[] values = data.split(",");
					System.out.println(values[2] + "working");
					marks.add(new StudentRecord("" + values[0] + "", Double.parseDouble(values[1]),
							Double.parseDouble(values[2]), Double.parseDouble(values[3])));

				}
				inputStream.close();
			} catch (FileNotFoundException e) {
				System.out.print("Something went wrong with the file");
			}
		return marks;
	}

	private File createFile() throws IOException {
		// to save the file name

		currentFilesname = new File("testfile.csv");
		if (currentFilesname.createNewFile())
			System.out.print("fileName" + currentFilesname.getName());
		else {
			System.out.println("File Already Exist");
			System.out.print("fileName" + currentFilesname.getPath());
		}
		return currentFilesname;
	}

	private void save() {
		StudentRecord sturcd = new StudentRecord("", 0.0, 0.0, 0.0);
		List<List<String>> arrList = new ArrayList<>();
		for (int i = 0; i < table.getItems().size(); i++) {
			sturcd = table.getItems().get(i);
			arrList.add(new ArrayList<>());
			arrList.get(i).add(sturcd.getSID());
			arrList.get(i).add("" + sturcd.getAssginments());
			arrList.get(i).add("" + sturcd.getMidterm());
			arrList.get(i).add("" + sturcd.getFianlExam());
		}
		try (Writer writer = new BufferedWriter(new FileWriter("testfile.csv"))) {
			String record;
			String[] recordArr;
			for (int i = 0; i < arrList.size(); i++) {
				System.out.println(arrList.get(i).get(0));
				System.out.println(arrList.get(i).get(1));
				System.out.println(arrList.get(i).get(2));
				System.out.println(arrList.get(i).get(3));
				record = "" + arrList.get(i).get(0) + "," + arrList.get(i).get(1) + "," + arrList.get(i).get(2) + ","
						+ arrList.get(i).get(3);
				recordArr = record.split(",");
				writer.write(record);
				writer.write(NEW_LINE_SEPARATOR);
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}

	}

	private void saveAs() {
		StudentRecord sturcd = new StudentRecord("", 0.0, 0.0, 0.0);
		List<List<String>> arrList = new ArrayList<>();
		for (int i = 0; i < table.getItems().size(); i++) {
			sturcd = table.getItems().get(i);
			arrList.add(new ArrayList<>());
			arrList.get(i).add(sturcd.getSID());
			arrList.get(i).add("" + sturcd.getAssginments());
			arrList.get(i).add("" + sturcd.getMidterm());
			arrList.get(i).add("" + sturcd.getFianlExam());
		}
		try (Writer writer = new BufferedWriter(new FileWriter(saveAsFile))) {
			String record;
			String[] recordArr;
			System.out.println(currentFilesname.getName());
			for (int i = 0; i < arrList.size(); i++) {
				System.out.println(arrList.get(i).get(0));
				System.out.println(arrList.get(i).get(1));
				System.out.println(arrList.get(i).get(2));
				System.out.println(arrList.get(i).get(3));
				record = "" + arrList.get(i).get(0) + "," + arrList.get(i).get(1) + "," + arrList.get(i).get(2) + ","
						+ arrList.get(i).get(3);
				recordArr = record.split(",");
				writer.write(record);
				writer.write(NEW_LINE_SEPARATOR);
			}
			writer.close();
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		}

	}
}