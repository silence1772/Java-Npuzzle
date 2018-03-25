package application;

import javafx.scene.Group;
import javafx.scene.shape.Rectangle;

/**
 * 游戏主操作面板
 * 
 * @author wuzewei
 * @version 0.1
 */
public class GameBoard extends Group {
	/** 面板背景 */
	private Rectangle background = new Rectangle();
	/** 面板起始坐标X */
	private double layoutX = 0;
	/** 面板起始坐标Y */
	private double layoutY = 0;
	/** 面板宽度 */
	private double width = 400;
	/** 面板高度 */
	private double height = 300;
	/** 游戏块占宽度比例 */
	private double theta = 0.22;
	/** 游戏块间隔 */
	private double gap;
	/** 游戏块大小 */
	private double cellSize;
	/** 游戏块集合列表 */
	private BlockList blockList;

	/**
	 * 无参构造函数
	 */
	public GameBoard() {
		init();
	}

	/**
	 * 有参构造函数
	 * 
	 * @param layoutx
	 *            起始坐标X
	 * @param layouty
	 *            起始坐标Y
	 * @param width
	 *            宽度
	 * @param height
	 *            高度
	 */
	public GameBoard(double layoutx, double layouty, double width, double height) {
		this.layoutX = layoutx;
		this.layoutY = layouty;
		this.width = width;
		this.height = height;
		init();
	}

	/**
	 * 初始化方法
	 */
	private void init() {
		//计算游戏块间隔与宽度
		gap = width * (1 - theta * 4) / 5;
		cellSize = width * theta;
		
		//设置背景
		background.setLayoutX(layoutX);
		background.setLayoutY(layoutY);
		background.setWidth(width);
		background.setHeight(height);
		background.getStyleClass().addAll("game-background");
		this.getChildren().add(background);
		
		//创建游戏块方格背景
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				this.getChildren().add(createGrid(i, j));
			}
		}

		// 创建游戏集合列表
		blockList = new BlockList(layoutX, layoutY, cellSize, gap);
		this.getChildren().add(blockList);
	}

	/**
	 * 生成游戏块的背景方格
	 * 
	 * @param i
	 *            游戏块在网格中的列数
	 * @param j
	 *            游戏块在网格中的行数
	 * @return 背景方格
	 */
	private Rectangle createGrid(int i, int j) {
		// 计算位置生成背景方格
		Rectangle grid = new Rectangle(layoutX + (i + 1) * gap + i * cellSize, layoutY + (j + 1) * gap + j * cellSize,
				cellSize, cellSize);
		grid.getStyleClass().addAll("grid-background");
		return grid;
	}

	/**
	 * 刷新游戏
	 */
	public void refreshGame() {
		blockList.refreshGame();
	}
}
