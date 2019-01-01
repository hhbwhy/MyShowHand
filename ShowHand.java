package collection;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;

public class ShowHand {
	//定义游戏支持多少玩家
	private final int PLAY_NUM = 5;
	//定义扑克牌的所有花色和数值
	private String[] types = {"方块", "草花", "红心", "黑桃"};
	private String[] values = {"2","3","4","5","6"
			,"7","8","9","10","J","Q","K","A"};
	//cards是一局游戏中剩下的扑克牌
	private List<String> cards = new LinkedList<String>();
	//定义所有玩家
	private String[] players = new String[PLAY_NUM];
	//所有玩家手上的扑克牌
	private List<String>[] playersCards = new List[PLAY_NUM];
	
	/**
	 * 初始化扑克牌，放入52张扑克牌
	 * 并且使用shuffle方法将他们随机排序
	 * @param 
	 */
	/**
	 * 
	 */
	public void initCards() {
		for(String typ : types) {
			for(String val : values) {
				cards.add(typ+val);
			}
		}
//		System.out.println(cards);
		Collections.shuffle(cards);
//		System.out.println(cards);
	}
	/**
	 * 初始化玩家，为每个用户分配用户名
	 */
	public void initPlayer(String... names) {
		if(names.length>PLAY_NUM ||names.length<2) {
			System.out.println("玩家数量不对");
			return;
		}else {
			//初始化玩家名
			for(int i =0; i<names.length; i++) {
				players[i] = names[i];
			}
		}
	}
	/**
	 * 初始化玩家手上的牌，开始游戏时每个玩家手上为空
	 * 程序使用一个长度为0的LinkedList来表示
	 */
	public void initPlayerCards() {
		for (int i = 0; i<players.length; i++) {
			if (players[i] != null && !players[i].equals("")) {
				playersCards[i] = new LinkedList<String>();
			}
		}
		
	}
	/**
	 * 派扑克牌
	 * @param first 最先派给谁
	 */
	public void deliverCard(String first) {
		//调用ArrayUtils工具类的search方法
		//查询出指定元素在数组中的索引
		int firstPos = ArrayUtils.indexOf(players, first);
		//依次给位于该指定玩家后的每个玩家派扑克牌
		for (int i = firstPos; i<PLAY_NUM; i++) {
			if (players[i] != null) {
				playersCards[i].add(cards.get(0));
				cards.remove(0);
			}
		}
		//依次给位于该指定玩家之前的每个玩家派扑克牌
		for (int i = 0; i < firstPos; i++) {
			if (players[i] != null) {
				playersCards[i].add(cards.get(0));
				cards.remove(0);
			}
		}
	}
	/**
	 * 输出玩家手中的扑克牌
	 * 实现该方法时，应该控制每个玩家看不到别人的第一张牌，但此处没有增加此功能
	 */
	public void showPlayerCards() {
		for (int i = 0; i < PLAY_NUM; i++) {
			//当玩家不为空时
			if(players[i] != null) {
				System.out.print(players[i] + ": ");
				//遍历输出玩家手上的牌
				for (String card : playersCards[i]) {
					System.out.print(card + "\t");
				}
			}
			System.out.print("\n");
		}
	}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShowHand sh = new ShowHand();
		sh.initPlayer("电脑玩家", "孙悟空");
		sh.initCards();
		sh.initPlayerCards();
		
		//下面从孙悟空开始派牌
		sh.deliverCard("孙悟空");
		sh.showPlayerCards();
		
		
		//再次从电脑玩家开始派牌
		sh.deliverCard("电脑玩家");
		sh.showPlayerCards();

	}

}
