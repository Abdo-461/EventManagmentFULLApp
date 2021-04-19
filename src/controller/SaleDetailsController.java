package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.Sale;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class SaleDetailsController implements Initializable {

	/*FXML components on the scene - START
	 * 
	 * */
    @FXML
    private Button goBackBtn;

    @FXML
    private ImageView saleImage;

    @FXML
    private Label highestOffer;

    @FXML
    private Label minimumRaise;

    @FXML
    private Label askingPrice;

    @FXML
    private Button closePost;

    @FXML
    private Button deletePost;

    @FXML
    private Button savePost;
    
    @FXML
    private Label errorLabel;
    
    /*FXML components on the scene - END
	 * 
	 * */

    //get post id from select statement
    String salePostId;
    
    //elements to show on GUI
    int askingprice;
    int minimumraise;
    
    //Instance variable for table name and database name
    final String DB_NAME = "uniLinkDB";
    final String TABLE_NAME_Sales = "SALES";
    
    
	//Observable list to capture details of sale
	ObservableList<Sale> posts = FXCollections.observableArrayList();
	
	//list view to populate the list with observable list components
	@FXML
	ListView<Sale> postsList = new ListView<Sale>();
    
	  //function to close post by id
	  @SuppressWarnings("unused")
	void closeJobPost(String id) {	
	    //use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " UPDATE " +TABLE_NAME_Sales+  " SET STATUS = '"+ Status.CLOSE.toString() +"' " +
					" WHERE saleid LIKE '"+id+"' ";
			
			int result = stmt.executeUpdate(query);
			errorLabel.setText("Post Closed!");
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			errorLabel.setText(e.getMessage());
		}
	}
    
    //close post function on GUI
    @FXML
    void closePost(ActionEvent event) {
    	//close post by id
    	closeJobPost(salePostId);
    	//takes the user back to the main window
    	revert(event);
    }

	 //function to delete post by id
	 @SuppressWarnings("unused")
	void deleteSalePostById(String id) {
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = " DELETE FROM " +TABLE_NAME_Sales+ 
					" WHERE saleid LIKE '"+id+"' ";
			
			int result = stmt.executeUpdate(query);
			
			errorLabel.setText("Post Deleted!");
		
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}
		
	}
      
   //delete post function on GUI
    @FXML
    void deletePost(ActionEvent event) {

    	//deletes post by id
    	deleteSalePostById(salePostId);	
    	//goes back to the main menu after action is done
    	revert(event);
    }

    //function to go back to the previous page
    @FXML
    void revert(ActionEvent event) {
    	
    	//reference the log in window with FXML loader
    	FXMLLoader mainWindowRevert = new FXMLLoader(getClass().getResource("/view/MainWindowView.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) mainWindowRevert.load();
			//load the existing stage
	    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
	    	//create scene and load FXML page to scene
	    	Scene finalscene = new Scene(root);
	    	//load the scene onto the stage
	    	window.setScene(finalscene);
	    	//show the stage
	    	window.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void savePost(ActionEvent event) {

    }

    //functions to populate data from database into event observable list
    public void populateSaleDetails() {
    		
		try (Connection con = UniLinkDB.getConnection(DB_NAME);
				Statement stmt = con.createStatement();
		) {
			String query = "SELECT * FROM " + TABLE_NAME_Sales;
			
			try (ResultSet resultSet = stmt.executeQuery(query)) {
				while(resultSet.next()) {
				
					//capture current post id
					salePostId = resultSet.getString(1);
					
					 //capture extra details from sale table
					 askingprice = resultSet.getInt(6);
					 minimumraise = resultSet.getInt(7);
					 
					//constructor to capture extra details
					Sale salePost = new Sale(askingprice,minimumraise);
						
					//add details into observable test
					posts.add(salePost);						
				}
				
			} catch (SQLException e) {
				errorLabel.setText(e.getMessage());
			}
	
		} catch (Exception e) {
			errorLabel.setText(e.getMessage());
		}	
    }
    
    //function to post data from event observable list to list view to show on screen
    public void salePostsDetails() {
    
    	//get sale posts from database
    	populateSaleDetails();
    	//populate list view with observable list items
    	postsList.setItems(posts);
    	//show extra details on label
    	askingPrice.setText(""+askingprice);
    	minimumRaise.setText(""+minimumraise);
    }
    
    
    
    //when the page open, the info will be loaded on the page
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
		salePostsDetails();
	}

}

