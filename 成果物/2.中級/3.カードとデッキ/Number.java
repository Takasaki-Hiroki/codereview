package poker;

/**
 * トランプのカードの数字
 * @author Takasaki Hiroki
 * @version 1.0.2, 2014/05/19
 */
public enum Number {
	ACE(1),
	TWO(2),
	THREE(3),
	FOUR(4),
	FIVE(5),
	SIX(6),
	SEVEN(7),
	EIGHT(8),
	NINE(9),
	TEN(10),
	JACK(11),
	QUEEN(12),
	KING(13);
	
	/**
	 * トランプの数字
	 */
	private int num;
	
	/**
	 * コンストラクタ
	 * @param num トランプの数字
	 */
	Number(int num) {
		this.num = num;
	}
	
	/**
	 * トランプの数字を返す
	 * @return トランプの数字
	 */
	public int getNumber() {
		return this.num;
	}
	
	/**
	 * 指定された数字に対応するNumberオブジェクトを返す
	 * @param num 数字(1~13)
	 * @return Numberオブジェクト
	 * @throws IllegalArgumentException 1〜13までの数値を入力してください。
	 */
	public static Number create(int num) {
		switch(num) {
		case 1: return Number.ACE;
		case 2: return Number.TWO;
		case 3: return Number.THREE;
		case 4: return Number.FOUR;
		case 5: return Number.FIVE;
		case 6: return Number.SIX;
		case 7: return Number.SEVEN;
		case 8: return Number.EIGHT;
		case 9: return Number.NINE;
		case 10: return Number.TEN;
		case 11: return Number.JACK;
		case 12: return Number.QUEEN;
		case 13: return Number.KING;
		default : throw new IllegalArgumentException("1〜13までの数値を入力してください。");
		}
	}
}
