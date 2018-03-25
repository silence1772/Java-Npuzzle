package application;

import java.util.Random;

import javafx.scene.Group;

/**
 * 游戏块集合列表类，包含所有可移动的方块
 * 
 * @author wuzewei
 * @version 0.1
 */
public class BlockList extends Group {
	/** 起始坐标X */
	private static double layoutX;
	/** 起始坐标Y */
	private static double layoutY;
	/** 大小 */
	private static double size;
	/** 方块间隔 */
	public static double gap;
	/** 记录方块位置数组 */
	public static int[][] gridOrder = { { 0, 1, 2, 3 }, { 4, 5, 6, 7 }, { 8, 9, 10, 11 }, { 12, 13, 14, 15 } };

	/**
	 * 无参构造函数
	 */
	public BlockList() {
		this(40, 10, 0, 0);
	}

	/**
	 * 有参构造函数
	 * 
	 * @param layoutX
	 *            起始坐标X
	 * @param layoutY
	 *            起始坐标Y
	 * @param size
	 *            大小
	 * @param gap
	 *            间隔
	 */
	public BlockList(double layoutX, double layoutY, double size, double gap) {
		BlockList.layoutX = layoutX;
		BlockList.layoutY = layoutY;
		BlockList.size = size;
		BlockList.gap = gap;

		init();
	}

	/**
	 * 初始化记录位置数组，随机打乱
	 */
	private void init() {
		Random random = new Random();
		for (int i = 16; i > 0; i--) { // 最后一个与随机位置交换
			int index = random.nextInt(i);
			int num = gridOrder[index / 4][index % 4];
			gridOrder[index / 4][index % 4] = gridOrder[(i - 1) / 4][(i - 1) % 4];
			gridOrder[(i - 1) / 4][(i - 1) % 4] = num;
			if (num == 0) // 0为空位，不生成方格
				continue;
			// 同时生成对应方块
			this.getChildren().add(createBlock((i - 1) / 4, (i - 1) % 4, num));
		}
//		调试
//		for (int i = 15; i > 0; i--) {
//			this.getChildren().add(createBlock((i - 1) / 4, (i - 1) % 4, i));
//		}
//		
//		for (int i = 0; i < 4; i++) {
//			for (int j = 0; j < 4; j++) {
//				BlockList.gridOrder[i][j] = (i * 4 + j + 1) % 16;
//			}
//		}
	}

	/**
	 * 生成游戏方块
	 * 
	 * @param row
	 *            在网格中的行数
	 * @param col
	 *            在网格中的列数
	 * @param number
	 *            方块对应数字
	 * @return 相应游戏方块
	 */
	private Block createBlock(int row, int col, int number) {
		Block grid = new Block(layoutX + (col + 1) * gap + col * size, layoutY + (row + 1) * gap + row * size, size,
				String.valueOf(number));

		return grid;
	}

	/**
	 * 刷新游戏，即把所有游戏方块移除后再重新初始化
	 */
	public void refreshGame() {
		for (int i = 0; i < 15; i++) {
			this.getChildren().remove(0);
		}

		init();
	}
}
