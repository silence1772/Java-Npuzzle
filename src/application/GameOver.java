package application;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * 游戏结束界面
 * 
 * @author wuzewei
 * @version 0.1
 */
public class GameOver extends Group {
	private Rectangle background1;
	private Rectangle background2;
	private Rectangle button;
	private Text text1;
	private Text text2;
	private Text time;
	private Text text4;

	/**
	 * 构造函数
	 */
	public GameOver() {
		background1 = new Rectangle(0, 0, 600, 800);
		background1.getStyleClass().addAll("gameover-back1");

		background2 = new Rectangle(140, 200, 320, 270);
		background2.getStyleClass().addAll("gameover-back2");

		text1 = new Text(180, 260, "恭喜你！完成挑战");
		text1.getStyleClass().addAll("text-color", "font-size-28");

		text2 = new Text(180, 320, "用时：");
		text2.getStyleClass().addAll("text-color", "font-size-20");

		time = new Text(240, 320, "00:00");
		time.getStyleClass().addAll("text-color", "font-size-20");

		button = new Rectangle(240, 370, 120, 50);
		button.getStyleClass().addAll("gameover-button");

		text4 = new Text(260, 400, "再来一局");
		text4.getStyleClass().addAll("text-color", "font-size-20");

		Group buttons = new Group(button, text4);
		buttons.setOnMouseEntered(e -> {
			buttons.setCursor(Cursor.HAND);
		});
		buttons.setOnMouseClicked(e -> {
			GameManager.overGame.set(false);
		});

		this.getChildren().addAll(background1, background2, text1, text2, time, buttons);
	}

	/**
	 * 设置显示时间
	 * 
	 * @param timestr
	 *            时间字符串
	 */
	public void setTime(String timestr) {
		time.setText(timestr);
	}
}
