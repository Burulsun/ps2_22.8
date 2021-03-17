package Random2D;



import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import java.security.SecureRandom;

public class RandomShapeController {

    @FXML Pane pane;

    private SecureRandom random = new SecureRandom();

    private int num;

    private int[] arr1;
    private int[] arr2;

    public void initialize() {
        num = random.nextInt(50) + 5;
        arr1 = new int[num];
        arr2 = new int[num];

        for (int i = 0; i < num; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(randomColor());
            rectangle.setStrokeWidth(random.nextInt(20));
            rectangle.setStroke(randomColor());
            rectangle.setWidth(random.nextInt(50));
            rectangle.setHeight(random.nextInt(80));
            rectangle.setX(random.nextInt(300)+50);
            rectangle.setY(random.nextInt(300)+50);


            pane.getChildren().add(rectangle);
            arr1[i] = 1 + random.nextInt(5);
            arr2[i] = 1 + random.nextInt(5);
        }

        Timeline timelineAnimation = new Timeline(

                new KeyFrame(Duration.millis(15), e -> moveShape())
        );

        timelineAnimation.setCycleCount(Timeline.INDEFINITE);
        timelineAnimation.play();
    }

    private void moveShape() {
        for (int i = 0; i < pane.getChildren().size(); i++) {
            Rectangle r = (Rectangle) pane.getChildren().get(i);
            r.setWidth(r.getWidth() + arr1[i]);
            r.setHeight(r.getHeight() + arr2[i]);
            if (r.getX() + r.getWidth() > pane.getWidth() || r.getX() + r.getHeight() < 0) arr1[i] = -arr1[i];
            if (r.getY() + r.getHeight() > pane.getHeight() || r.getY() + r.getHeight() < 0) arr2[i] = -arr2[i];

        }
    }
    private Color randomColor(){
        return Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                (double) random.nextInt(101) / 100);
    }
}
