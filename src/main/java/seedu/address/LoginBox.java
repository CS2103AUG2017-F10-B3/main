package seedu.address;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


//@@author henryheyhey92
public class LoginBox {

    private static boolean answer;
    private static Stage window = new Stage();
    private static TextField nameInput = new TextField();
    private static TextField passwordInput = new TextField();

    public static boolean display(String title) {

        //create window
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        //create Grid
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setVgap(10);
        grid.setHgap(12);

        //create label for name and password
        Label nameLabel = new Label("User name:");
        GridPane.setConstraints(nameLabel, 1, 2);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 1, 3);

        // Name and Password input
        //TextField nameInput = new TextField();
        nameInput.setPromptText("Enter name");
        GridPane.setConstraints(nameInput, 2, 2);

        //TextField passwordInput = new TextField();
        passwordInput.setPromptText("Password");
        GridPane.setConstraints(passwordInput, 2, 3);

        //Create login buttons to access app
        Button yesButton = new Button("Login");
        GridPane.setConstraints(yesButton, 2, 4);

        window.setOnCloseRequest(e -> {
            e.consume();
            stop();
        });

        yesButton.setOnAction(LoginBox::handle);

        //Add buttons
        grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passwordInput, yesButton);
        Scene scene = new Scene(grid, 350, 200);
        window.setScene(scene);
        window.showAndWait();

        return answer;
        //Clicking will set answer and cloe window
    }

    private static void stop() {
        boolean answer = ConfirmBox.display("Exit Check Protocol","Confirm on exiting the program?");

        if(answer) {
            Platform.exit();
            System.exit(0);
        }
    }

    private static boolean isInt(TextField name, TextField pass){
        String name2 = name.getText();
        String pass2 = pass.getText();
        try{
            if(name2.compareTo("NUS")==0 ) {
                if (pass2.compareTo("1234") == 0)
                    return true;
            }else
                return false;
        }catch(NumberFormatException e){
           return false;
        }
        return false;
    }

    private static void handle(ActionEvent e) {
        if (isInt(nameInput, passwordInput)) {
            answer = true;
            window.close();
        }
    }
}//@@author



