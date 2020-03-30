

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

import com.sun.xml.internal.bind.v2.runtime.Name;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Lab10 extends Application  {


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SimpleBBS Client v1.0");
    	//Generate  a random value 
    	
        GridPane one=new GridPane();
        GridPane two=new GridPane();
        Button bOne =new Button("Send");
        Button bTwo =new Button("Exit");
        Label lOne=new Label("Username: ");
        Label LTwo=new Label("Message: ");
        TextField name=new TextField();
        TextField message=new TextField();
        one.add(lOne, 0, 0);
        one.add(name, 1, 0);
        two.add(LTwo, 0, 0);
        two.add(message, 1, 0);
       bTwo.setOnAction(r->System.exit(0));
        bOne.setOnAction(e->{
        	sendMessage(""+name.getText()+": "+message.getText());
        });
        
        startChat();
        
        VBox hbox=new VBox();
        hbox.getChildren().addAll(one,two,bOne,bTwo);
        //creating and assigning the scene.
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    
    /********************************************* SERVER CODE *********************************************/
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private String message = "";
	private String serverIP = "127.0.0.1";
	private Socket connection;
	// Function to connect to server and start chat
	public void startChat(){
		// Create another thread for server to run on
		Runnable serverTask = new Runnable() {
			@Override
			public void run() {
				try {
					// Establish connection and run input and output stream functions: retrieves and send messages
					connectToAgent();
					setupStreams();
					connectedChat();
				} catch (EOFException eofException) {
					// If server connection interrupted print error message
					showMessage("\n ERROR: Connection interrupted.");
				} catch (IOException ioException) {
					ioException.printStackTrace();
				} finally {
					// Close connection when done
					closeConnection();
				}
			}
		};
		// Start server thread
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}

	// Function to connect to Agent on server
	private void connectToAgent() throws IOException{
		showMessage(" Attempting to connect to an Agent... \n");
		// Get server connection
		connection = new Socket(InetAddress.getByName(serverIP), 3000);
		showMessage("\n Now connected to: " + connection.getInetAddress().getHostName());
	}

	// Function to setup streams
	private void setupStreams() throws IOException{
		// Setup input and output streams
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
		input = new ObjectInputStream(connection.getInputStream());
		// Print confirmation message
		showMessage("\n Agent is ready to chat. \n");
	}

	// Function to handle messages while chatting
	private void connectedChat() throws IOException{
		do{
			try{
				// Append user's message
				message = (String) input.readObject();
				showMessage(message);
			}catch(ClassNotFoundException classNotFoundException){
				// If message sent is not valid, print error message
				showMessage("ERROR: User has sent an invalid object!");
			}
		}while(!message.equals("END CHAT"));
	}

	// Function to close the connections
	public void closeConnection(){
		showMessage("\n Agent has left the chat.\n Connection terminated. \n");
		try{
			// Close path to and from Agent
			output.close();
			input.close();
			connection.close(); //close connection
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	// Send message to the Agent
	private void sendMessage(String message){
		try{
			output.writeObject(message);
			output.flush();
		}catch(IOException ioException){
			
		}
	}

	// Update chat window with message
	private void showMessage(final String message){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						// Show message in text box
						
					}
				}
		);
	}

}

