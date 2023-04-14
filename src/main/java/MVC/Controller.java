package MVC;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import server.Server;
import server.ServerLauncher;
import server.models.Course;
import server.models.RegistrationForm;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    private Model model = new Model();
    private ArrayList<Course> cours = new ArrayList<>();
    private VBox selectedVBox;
    @FXML
    ComboBox<String> sessionBox;
    @FXML
    VBox displayCours;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        model.connectServer();
    }
    /**
     * Gestion du click d'un VBox qui correspond a un cours
     * @param event Source du click
     */
    @FXML
    public void handleCoursClicked(MouseEvent event){
        VBox clickedCours = (VBox) event.getSource();

        if (selectedVBox != null) {
            // Reset the background color of the previously selected VBox to white
            selectedVBox.setStyle("-fx-background-color: #FFFFFF");
        }

        // Set the background color of the clicked VBox to blue
        clickedCours.setStyle("-fx-background-color: #AEC4FF");
        selectedVBox = clickedCours; // Update the selected VBox
    }

    /**
     * Charge et affiche les cours dans le GUI
     */
    @FXML
    public void loadCours(){
        cours = model.loadCours(sessionBox.getValue());
        displayCours.getChildren().clear(); // Clear the existing VBox children
        for (Course course: cours){
            Text text = new Text(course.getCode() +"\t\t\t" + course.getName());
            VBox vBox = new VBox(text);
            vBox.setOnMouseClicked(this::handleCoursClicked); // Set the click event handler
            displayCours.getChildren().add(vBox);
        }
    }
    @FXML TextField prenomTxtField;
    @FXML TextField nomTextField;
    @FXML TextField emailTextField;
    @FXML TextField matrTextField;
    @FXML
    public void inscription(){

        String prenom = prenomTxtField.getText();
        String nom = nomTextField.getText();
        String email = emailTextField.getText();
        int matricule = Integer.valueOf(matrTextField.getText());
        String session = sessionBox.getValue();


    }
}
