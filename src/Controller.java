import javafx.beans.property.IntegerProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;

/**
 * Created by Olli on 24.02.2016.
 */
public class Controller {

    @FXML
    private Button btnStart;
    @FXML
    private CheckBox chkOneMax;
    @FXML
    private CheckBox chkLolz;
    @FXML
    private CheckBox chkSurprising;
    @FXML
    private CheckBox chkAdultFull;
    @FXML
    private CheckBox chkAdultOver;
    @FXML
    private CheckBox chkAdultMixing;
    @FXML
    private CheckBox chkFitProp;
    @FXML
    private CheckBox chkSigma;
    @FXML
    private CheckBox chkTurnament;
    @FXML
    private CheckBox chkMyOwn;
    @FXML
    private TextField txtProbSize;



    public Controller() {
    }

    @FXML
    private void initialize() {
        Main main = new Main();
        btnStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    main.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Main main = new Main();
                main.setGenome_length(Integer.parseInt(txtProbSize.getText()));
                main.start();
            }
        });
        chkOneMax.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setProblem(0);
                chkOneMax.setSelected(true);
                chkLolz.setSelected(false);
                chkSurprising.setSelected(false);
            }
        });
        chkLolz.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setProblem(1);
                chkOneMax.setSelected(false);
                chkLolz.setSelected(true);
                chkSurprising.setSelected(false);
            }
        });
        chkSurprising.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setProblem(3);
                chkOneMax.setSelected(false);
                chkLolz.setSelected(false);
                chkSurprising.setSelected(true);
            }
        });
        chkAdultFull.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setAdult_selection(0);
                chkAdultFull.setSelected(true);
                chkAdultMixing.setSelected(false);
                chkAdultOver.setSelected(false);
            }
        });
        chkAdultOver.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setAdult_selection(1);
                chkAdultFull.setSelected(false);
                chkAdultMixing.setSelected(false);
                chkAdultOver.setSelected(true);
            }
        });
        chkAdultMixing.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setAdult_selection(3);
                chkAdultFull.setSelected(false);
                chkAdultMixing.setSelected(true);
                chkAdultOver.setSelected(false);
            }
        });
        chkFitProp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setParent_selection(0);
                chkFitProp.setSelected(true);
                chkSigma.setSelected(false);
                chkTurnament.setSelected(false);
                chkMyOwn.setSelected(false);
            }
        });
        chkSigma.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setParent_selection(1);
                chkFitProp.setSelected(false);
                chkSigma.setSelected(true);
                chkTurnament.setSelected(false);
                chkMyOwn.setSelected(false);
            }
        });
        chkTurnament.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setParent_selection(2);
                chkFitProp.setSelected(false);
                chkSigma.setSelected(false);
                chkTurnament.setSelected(true);
                chkMyOwn.setSelected(false);
            }
        });
        chkMyOwn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Main.setParent_selection(3);
                chkFitProp.setSelected(false);
                chkSigma.setSelected(false);
                chkTurnament.setSelected(false);
                chkMyOwn.setSelected(true);
            }
        });
    }


}
