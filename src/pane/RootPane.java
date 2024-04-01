package pane;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import utils.Goto;

public class RootPane extends StackPane {
    private static RootPane instance;

    private RootPane() {
        setBackground(Background.fill(Color.WHITE));
        Goto.setPane(this);
        Goto.MainMenuPage();
    }

    public static RootPane getRootPane() {
        if (instance == null)
            instance = new RootPane();
        return instance;
    }
}
