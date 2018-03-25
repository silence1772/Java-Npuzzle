package application;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * 游戏控制类，集成游戏的各个部分
 * 
 * @author wuzewei
 * @version 0.1
 */
public class GameManager extends Group {
	/** 窗口的宽属性 */
	private final IntegerProperty width = new SimpleIntegerProperty(600);
	/** 窗口的高属性 */
	private final IntegerProperty height = new SimpleIntegerProperty(800);
	/** 游戏界面的背景 */
	private Rectangle background;
	/** 游戏主操作面板 */
	private GameBoard gameBoard;
	/** 游戏UI */
	private GameUI gameUI;
	/** 计时模块 */
	private Timer timer;
	/** 游戏结束画面 */
	private GameOver gameOver;
	/** 游戏刷新标识 */
	public static BooleanProperty refreshGame = new SimpleBooleanProperty(false);
	/** 游戏结束标识 */
	public static BooleanProperty overGame = new SimpleBooleanProperty(false);

	/**
	 * 构造函数，初始化各个变量
	 */
	public GameManager() {
		// 新建游戏背景
		background = new Rectangle(0, 0, width.doubleValue(), height.doubleValue());
		background.getStyleClass().addAll("root-background");
		this.getChildren().add(background);

		// 新建游戏主操作面板
		gameBoard = new GameBoard(width.doubleValue() * 0.05, height.doubleValue() * 0.25, width.doubleValue() * 0.9,
				width.doubleValue() * 0.9);
		this.getChildren().add(gameBoard);

		// 新建游戏UI，同时赋予计时功能
		gameUI = new GameUI();
		timer = new Timer(gameUI);
		timer.start();
		this.getChildren().add(gameUI);

		// 新建游戏结束画面，但不添加进场景
		gameOver = new GameOver();

		// 游戏刷新标识监听器，一旦改变即刷新游戏操作面板
		refreshGame.addListener((ov, oldb, newb) -> {
			if (newb) {
				gameBoard.refreshGame();
				timer.reset();
				refreshGame.set(false);
			}
		});

		// 游戏结束标识监听器，控制游戏结束画面显示
		overGame.addListener((ov, oldb, newb) -> {
			if (newb) {
				gameOver.setTime(timer.getTimes());
				this.getChildren().add(gameOver);
				timer.suspend();
			} else { // 关闭结束画面会改变游戏刷新标识
				this.getChildren().remove(gameOver);
				timer.resume();
				refreshGame.set(true);
			}
		});
	}
}
