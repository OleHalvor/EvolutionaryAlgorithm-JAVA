import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * Created by Olli on 24.02.2016.
 */
public class Controller {

    @FXML
    private Button btnStart;


    public Controller() {
    }

    @FXML
    private void initialize() {
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.main(null);
            }
        });
    }


}
