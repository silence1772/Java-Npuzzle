package application;

import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * 可移动游戏块类
 * 
 * @author wuzewei
 * @version 0.1
 */
public class Block extends Group {
	/** 游戏块背景 */
	private Rectangle background;
	/** 游戏块数字文本 */
	private Text number;
	/** 文本坐标偏移量 */
	private int bias = 12;
	/** 游戏块序号 */
	private int num;
	/** 大小 */
	private double size;
	/** 起始坐标X */
	private double layoutX;
	/** 起始坐标Y */
	private double layoutY;

	/**
	 * 构造函数，生成游戏块
	 * 
	 * @param layoutX
	 *            起始坐标X
	 * @param layoutY
	 *            起始坐标Y
	 * @param size
	 *            大小
	 * @param num
	 *            序号
	 */
	public Block(double layoutX, double layoutY, double size, String num) {
		this.num = Integer.parseInt(num);
		this.size = size;
		this.layoutX = layoutX;
		this.layoutY = layoutY;

		// 背景
		background = new Rectangle(layoutX, layoutY, size, size);
		background.getStyleClass().addAll("grid", "color-" + (int) (Math.random() * 9));
		this.getChildren().add(background);
		// 数字文本
		number = new Text(layoutX + size / 2 - bias, layoutY + size / 2 + bias, num);
		number.setFont(Font.loadFont(Block.class.getResource("Clear-Sans-Bold.ttf").toExternalForm(), 20.0));
		number.getStyleClass().addAll("text-color");
		this.getChildren().add(number);
		// 鼠标拖拽事件
		this.setOnMouseDragged(e -> {
			background.setX(e.getX() - this.size / 2);
			background.setY(e.getY() - this.size / 2);
			number.setX(e.getX() - bias);
			number.setY(e.getY() + bias);
		});
		// 鼠标释放事件，判断是否需要更新位置
		this.setOnMouseReleased(e -> {
			// 是否恢复原位置标记
			boolean flag = false;
			// 判断是否可以更新位置
			switch (canMove()) {
			case 0: // 上下左右均不为空，无法移动
				flag = true;
				break;
			case 1: // 上为空
				if (isInRange(e.getX(), e.getY(), 1)) { // 鼠标拖拽进入该空格内
					background.setX(this.layoutX);
					background.setY(this.layoutY - BlockList.gap - this.size);
					number.setX(this.layoutX + this.size / 2 - bias);
					number.setY(this.layoutY + this.size / 2 + bias - BlockList.gap - this.size);
					this.layoutY -= BlockList.gap + this.size;
					updateGridOrder(1); // 更新记录位置数组
				} else {
					flag = true;
				}
				break;
			case 2: // 下
				if (isInRange(e.getX(), e.getY(), 2)) {
					background.setX(this.layoutX);
					background.setY(this.layoutY + BlockList.gap + this.size);
					number.setX(this.layoutX + this.size / 2 - bias);
					number.setY(this.layoutY + this.size / 2 + bias + BlockList.gap + this.size);
					this.layoutY += BlockList.gap + this.size;
					updateGridOrder(2);
				} else {
					flag = true;
				}
				break;
			case 3: // 左
				if (isInRange(e.getX(), e.getY(), 3)) {
					background.setX(this.layoutX - BlockList.gap - this.size);
					background.setY(this.layoutY);
					number.setX(this.layoutX + this.size / 2 - bias - BlockList.gap - this.size);
					number.setY(this.layoutY + this.size / 2 + bias);
					this.layoutX -= BlockList.gap + this.size;
					updateGridOrder(3);
				} else {
					flag = true;
				}
				break;
			case 4: // 右
				if (isInRange(e.getX(), e.getY(), 4)) {
					background.setX(this.layoutX + BlockList.gap + this.size);
					background.setY(this.layoutY);
					number.setX(this.layoutX + this.size / 2 - bias + BlockList.gap + this.size);
					number.setY(this.layoutY + this.size / 2 + bias);
					this.layoutX += BlockList.gap + this.size;
					updateGridOrder(4);
				} else {
					flag = true;
				}
				break;
			default:
				break;
			}
			// 不能移动，恢复原位置
			if (flag) {
				background.setX(this.layoutX);
				background.setY(this.layoutY);
				number.setX(this.layoutX + this.size / 2 - bias);
				number.setY(this.layoutY + this.size / 2 + bias);
			}
			// 判断是否完成游戏
			if (isGameOver()) {
				GameManager.overGame.set(true);
			}
		});
		// 鼠标进入事件，光标变手型
		this.setOnMouseEntered(e -> {
			this.setCursor(Cursor.CLOSED_HAND);
		});
	}

	/**
	 * 判断该游戏块的上下左右是否为空格，如为空格则可向该方向移动
	 * 
	 * @return int 表示可移动的方向
	 */
	private int canMove() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (num == BlockList.gridOrder[i][j]) { // 找到该方块
					if ((i - 1) >= 0 && BlockList.gridOrder[i - 1][j] == 0) {
						return 1; // up
					} else if ((i + 1) <= 3 && BlockList.gridOrder[i + 1][j] == 0) {
						return 2; // down
					} else if ((j - 1) >= 0 && BlockList.gridOrder[i][j - 1] == 0) {
						return 3; // left
					} else if ((j + 1) <= 3 && BlockList.gridOrder[i][j + 1] == 0) {
						return 4; // right
					}
				}
			}
		}
		return 0;// can't move
	}

	/**
	 * 判断鼠标位置是否在该方块上下左右的空格内
	 * 
	 * @param cursorX
	 *            光标坐标X
	 * @param cursorY
	 *            光标坐标Y
	 * @param direction
	 *            方向
	 * @return boolean 表示光标是否在该方向的空格内
	 */
	private boolean isInRange(double cursorX, double cursorY, int direction) {
		// 该方向空格起始坐标X
		double emptyCellX;
		// 该方向空格起始坐标Y
		double emptyCellY;
		switch (direction) {
		case 1:
			emptyCellY = layoutY - BlockList.gap - size;
			if (layoutX < cursorX && cursorX < layoutX + size && emptyCellY < cursorY && cursorY < emptyCellY + size) {
				return true;
			}
			break;
		case 2:
			emptyCellY = layoutY + BlockList.gap + size;
			if (layoutX < cursorX && cursorX < layoutX + size && emptyCellY < cursorY && cursorY < emptyCellY + size) {
				return true;
			}
			break;
		case 3:
			emptyCellX = layoutX - BlockList.gap - size;
			if (emptyCellX < cursorX && cursorX < emptyCellX + size && layoutY < cursorY && cursorY < layoutY + size) {
				return true;
			}
			break;
		case 4:
			emptyCellX = layoutX + BlockList.gap + size;
			if (emptyCellX < cursorX && cursorX < emptyCellX + size && layoutY < cursorY && cursorY < layoutY + size) {
				return true;
			}
			break;
		default:
			break;
		}
		return false;
	}

	/**
	 * 更新记录位置数组
	 * 
	 * @param direction
	 *            表示当前块移动的方向
	 */
	private void updateGridOrder(int direction) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (num == BlockList.gridOrder[i][j]) {
					switch (direction) {
					case 1:
						BlockList.gridOrder[i][j] = BlockList.gridOrder[i - 1][j];
						BlockList.gridOrder[i - 1][j] = num;
						break;
					case 2:
						BlockList.gridOrder[i][j] = BlockList.gridOrder[i + 1][j];
						BlockList.gridOrder[i + 1][j] = num;
						break;
					case 3:
						BlockList.gridOrder[i][j] = BlockList.gridOrder[i][j - 1];
						BlockList.gridOrder[i][j - 1] = num;
						break;
					case 4:
						BlockList.gridOrder[i][j] = BlockList.gridOrder[i][j + 1];
						BlockList.gridOrder[i][j + 1] = num;
						break;
					default:
						break;
					}
					return;
				}
			}
		}
	}

	/**
	 * 判断当前是否达到完成游戏条件
	 * 
	 * @return boolean 是否完成游戏
	 */
	private boolean isGameOver() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (BlockList.gridOrder[i][j] != (i * 4 + j + 1) % 16) {
					return false;
				}
			}
		}
		return true;
		// 调试
		// if (BlockList.gridOrder[0][0] == 1)
		// return true;
		// return false;
	}
}
