package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 * 应用程序主类
 * 
 * @author wuzewei
 * @version 0.1
 */
public class Main extends Application {
	private GameManager gameManager;

	/**
	 * 初始化，加载字体文件
	 */
	@Override
	public void init() throws Exception {
		Font.loadFont(Main.class.getResource("Clear-Sans-Bold.ttf").toExternalForm(), 10.0);
	}

	/**
	 * 开始方法，设置窗体及其属性，添加游戏场景
	 * 
	 * @param primatyStage
	 *            主场景
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			primaryStage.setTitle("魔板游戏");

			gameManager = new GameManager();
			StackPane root = new StackPane(gameManager);

			Scene scene = new Scene(root);
			scene.getStylesheets().add(Main.class.getResource("main.css").toExternalForm());

			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(800);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 启动程序
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
