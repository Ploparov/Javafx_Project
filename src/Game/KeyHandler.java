package Game;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyHandler {
    private boolean upPressed;
    private boolean downPressed;
    private boolean leftPressed;
    private boolean rightPressed;

    public void keyPressed(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W || code == KeyCode.UP) {
            System.out.println("w");
            setUpPressed(true);
        }
        if (code == KeyCode.S || code == KeyCode.DOWN) {
            System.out.println("s");
            setDownPressed(true);
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            System.out.println("a");
            setLeftPressed(true);
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            System.out.println("d");
            setRightPressed(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        KeyCode code = e.getCode();
        if (code == KeyCode.W || code == KeyCode.UP) {
            setUpPressed(false);
        }
        if (code == KeyCode.S || code == KeyCode.DOWN) {
            setDownPressed(false);
        }
        if (code == KeyCode.A || code == KeyCode.LEFT) {
            setLeftPressed(false);
        }
        if (code == KeyCode.D || code == KeyCode.RIGHT) {
            setRightPressed(false);
        }
    }

    public boolean isKeyPressed() {
        return (isUpPressed() || isDownPressed() || isLeftPressed() || isRightPressed());
    }

    public boolean isUpPressed() {
        return upPressed;
    }

    public void setUpPressed(boolean upPressed) {
        this.upPressed = upPressed;
    }

    public boolean isDownPressed() {
        return downPressed;
    }

    public void setDownPressed(boolean downPressed) {
        this.downPressed = downPressed;
    }

    public boolean isLeftPressed() {
        return leftPressed;
    }

    public void setLeftPressed(boolean leftPressed) {
        this.leftPressed = leftPressed;
    }

    public boolean isRightPressed() {
        return rightPressed;
    }

    public void setRightPressed(boolean rightPressed) {
        this.rightPressed = rightPressed;
    }
}