

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.Random;

import javax.swing.*;

import static com.sun.java.accessibility.util.AWTEventMonitor.addWindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class Lab10S extends Application  {

	 TextArea txtArea;
    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("SimpleBBS Server v1.0");
    	
       txtArea= new TextArea();
        Button button= new Button("Exit");
        button.setOnAction(e->{
        	System.exit(0);
        });
    	
    	//creating the hbox
        VBox hbox = new VBox(txtArea,button);
        
        //creating and assigning the scene.
        Scene scene = new Scene(hbox);
        primaryStage.setScene(scene);
        primaryStage.show();

        startChat();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
    
    /********************************************* SERVER CODE *********************************************/
	private ObjectOutputStream output;
	private ObjectInputStream input;
	private ServerSocket server;
	private String message = "";
	private Socket connection;
	// Function to start server and chat
	public void startChat(){
		// Create another thread for server to run on
		Runnable serverTask = new Runnable() {
			@Override
			public void run() {

				try {
					while (true) {
						// Establish server socket and max number of backlogged connected users
						server = new ServerSocket(3000, 50);
						try {
							// Wait for connection and run input and output stream functions to retrieve and send messages
							waitingToConnect();
							setupStreams();
							connectedChat();
						} catch (EOFException eofException) {
							// If server connection interrupted print error message
							showMessage("\n ERROR: Connection interrupted. ");
						} finally {
							// Close connection when done
							closeConnection();
						}
					}
				} catch (IOException ioException) {
					ioException.printStackTrace();
				}
			}
		};
		// Start server thread
		Thread serverThread = new Thread(serverTask);
		serverThread.start();
	}

	// Function to connect to server and client
	private void waitingToConnect() throws IOException{
	
		connection = server.accept();
	}
	// Function to setup streams
	private void setupStreams() throws IOException{
		// Setup input and output streams
		input = new ObjectInputStream(connection.getInputStream());
		output = new ObjectOutputStream(connection.getOutputStream());
		output.flush();
	}

	// Function to handle messages while chatting
	private void connectedChat() throws IOException{
		do{
			try{
				// Append incoming user's message
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
		try{
			// Close path to and from client, as well as connection
			output.close();
			input.close();
			connection.close();
		}catch(IOException ioException){
			ioException.printStackTrace();
		}
	}

	// Send message to the client
	private void sendMessage(String message){
		try{
			output.writeObject(message);
			output.flush();
		}catch(IOException ioException){
			txtArea.appendText("\n ERROR: message not sent, please retry.");
		}
	}

	// Update chat windows with message
	private void showMessage(final String text){
		SwingUtilities.invokeLater(
				new Runnable(){
					public void run(){
						// Show message in text box
						txtArea.appendText(text);
					}
				}
		);
	}

}

