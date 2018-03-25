package application;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 游戏界面UI类
 * 
 * @author wuzewei
 * @version 0.1
 */
public class GameUI extends Group {
	private Text text1;
	private Text text2;
	private Text text3;
	private Text text4;
	/** 时间格式字符串 */
	private Text times;
	/** 时间区域背景 */
	private Rectangle timesBackground;
	/** 刷新按钮背景 */
	private Rectangle refreshBackground;

	/**
	 * 构造函数，初始化各个UI组件
	 */
	public GameUI() {
		// 文本UI
		text1 = new Text(40, 100, "数字推盘");
		text2 = new Text(40, 140, "『移动方块使数字顺序排列』");
		text3 = new Text(155, 765, "『鼠标按住拖拽方块进行移动』");
		text1.getStyleClass().addAll("ui-color");
		text2.getStyleClass().addAll("ui-color", "font-size-20");
		text3.getStyleClass().addAll("ui-color", "font-size-20");

		// 时间UI
		timesBackground = new Rectangle(470, 60, 100, 74);
		text4 = new Text(505, 84, "用时");
		times = new Text(484, 116, "00:00");
		times.setFont(Font.loadFont(GameUI.class.getResource("Clear-Sans-Bold.ttf").toExternalForm(), 20.0));
		text4.getStyleClass().addAll("color-white", "font-size-16");
		timesBackground.getStyleClass().addAll("ui-background");
		times.getStyleClass().addAll("color-white", "font-size-24");

		// 刷新按钮
		refreshBackground = new Rectangle(520, 150, 40, 40);
		refreshBackground.getStyleClass().addAll("ui-background");
		Image image = new Image("application/refresh.png");// 按钮图片
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(30);
		imageView.setFitHeight(30);
		imageView.setLayoutX(525);
		imageView.setLayoutY(155);
		Group refresh = new Group();// 将背景与图片合成一个group，方便点击事件处理
		refresh.getChildren().addAll(refreshBackground, imageView);
		refresh.setOnMouseClicked(e -> {// 点击即刷新面板
			GameManager.refreshGame.set(true);
		});
		refresh.setOnMouseEntered(e -> {// 鼠标进入变成手型
			refresh.setCursor(Cursor.HAND);
		});

		this.getChildren().addAll(text1, text2, text3, timesBackground, text4, times, refresh);
	}

	/**
	 * 设置时间文本
	 * 
	 * @param timeStr
	 *            时间格式字符串
	 */
	public void setTimes(String timeStr) {
		times.setText(timeStr);
	}
}
