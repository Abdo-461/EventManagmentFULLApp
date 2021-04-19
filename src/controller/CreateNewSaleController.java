package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Sale;
import model.Status;
import uniLinkDatabase.UniLinkDB;

public class CreateNewSaleController implements Initializable {
	
	/*FXML components on the scene - START
	 * 
	 * */
	@FXML
    private Button createSaleBtn;
	
	@FXML
    private Button goBackBtn;

	@FXML
	private TextField saleTitle;
	
	@FXML
	private TextField saleDescription;
	
	@FXML
	private TextField saleAskingPrice;
	
	@FXML
	private TextField saleMinimumRaise;
	
	@FXML
	private Label invalidInput;
	
	@FXML
	private ImageView saleImage;
	
	/*FXML components on the scene - END
	 * 
	 * */

	//instance variables to capture user input 
    String id;
    String title;
    String description;
    String creatorid;
    String status;
    double askingPrice;
    double minimumRaise;
	
    //this function creates an event post - Button createSaleBtn
    @FXML
    void createSale(ActionEvent event) {
    	//this functions injects user input into the database
    	inserIntoDatabase();
    }
    
    //function to let user upload an image
    @FXML
    void uploadImage() {
    	
    	FileChooser Image = new FileChooser();
    	File selectedImage = Image.showOpenDialog(null);
    	Image image = new Image(selectedImage.toURI().toString());
    	
    	if(selectedImage !=null) {
    		saleImage.setImage(image);
    	}
    	
    }
     
    //this function inserts user input into the database
	@SuppressWarnings("unused")
	@FXML
    void inserIntoDatabase() {
    	
    	//to create new id starting from SAL00 and increments as a user creates a post
    	Sale sales = new Sale("SAL00");
    	
    	try {
    	 id = sales.getSPostID();
    	 title = saleTitle.getText();
    	 description = saleDescription.getText();
    	 creatorid = " ";
    	 status = Status.OPEN.toString();
    	 askingPrice = Integer.parseInt(saleAskingPrice.getText());
    	 minimumRaise = Integer.parseInt(saleMinimumRaise.getText());
    	}catch(Exception e) {
    		invalidInput.setText("Invalid Input, Please enter valid input!");	
    	}
    	
    	//Initialize database created
		final String DB_NAME = "uniLinkDB";
		//Initialize tables in database, one for each kind of posts and one for replies
		final String TABLE_NAME_SALE = "SALES";
		
		//use try-with-resources Statement
		try (Connection con = UniLinkDB.getConnection(DB_NAME);)  
			{
			@SuppressWarnings("unused")
			Sale sale = this.createSaleObj(id,title, description,creatorid,status,askingPrice,minimumRaise);
			String query = "INSERT INTO " + TABLE_NAME_SALE +
					" VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setString(1, id);
			stmt.setString(2, title);
			stmt.setString(3, description);
			stmt.setString(4, "In progress");
			stmt.setString(5, status);
			stmt.setDouble(6, askingPrice);
			stmt.setDouble(7,minimumRaise);
		
			int rs = stmt.executeUpdate();
			
			con.commit();
			con.close();
			
			//message to show when the record is created
			invalidInput.setText("Event recorded in database succesfully!");
		} catch (Exception e) {
			invalidInput.setText(e.getMessage());
		}
    }
    
    //this function creates an event object using the model Sale
    public Sale createSaleObj(String saleID ,String saleTitle, String saleDescription ,String creatorID, String status,double askingprice,double minimumRiase){
    	
    	//create an object of sale
    	Sale sale = new Sale();
    	//get elements of the post by the model functions
    	sale.setSPostID(saleID);
    	sale.setPostTitle(saleTitle);
    	sale.setPostDescription(saleDescription);
    	sale.setCreatorID(creatorID);
    	sale.setHighestoffer(askingprice);
    	sale.setMinimum(minimumRiase);
    	
    	return sale;
    }
    
    //this function for the back button to return to the main menu
    @FXML
    void revert(ActionEvent event) throws IOException {
    	
    	//create a reference to the FXML page
    	FXMLLoader mainWindowRevert = new FXMLLoader(getClass().getResource("/view/MainWindowView.fxml"));
    	//load the FXML page
    	Parent root = (Parent) mainWindowRevert.load();
    	//load the existing stage
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	//create new scene and load FXML page onto scene
    	Scene mainwindowscene = new Scene(root);
    	//put the scene onto the existing
    	window.setScene(mainwindowscene);
    	//show the scene on the stage
    	window.show();
    }
    
    //this function opens a stage to confirm to the user that the posts has been created
    @FXML
    void confirm(ActionEvent event) {
    	
    	//reference to the developer-info.fxml page
    	FXMLLoader confirmView = new FXMLLoader(getClass().getResource("../view/ConfirmCreate.FXML"));
    	//load the view
    	Pane root;
		try {
			root = (Pane) confirmView.load();
			//create a new stage for Developer-info.fxml
		    Stage ConfirmStage = new Stage();
			//set the scene in the new stage
		    ConfirmStage.setScene(new Scene(root));
		    //show stage
		    ConfirmStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
