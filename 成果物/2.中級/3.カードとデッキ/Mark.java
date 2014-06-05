package poker;

/**
 * トランプのマーク
 * @author Takasaki Hiroki
 * @version 1.0.1, 2014/05/15
 */
public enum Mark {
	CLUB("クラブ", 1),
	DIAMOND("ダイヤ", 2),
	HEART("ハート", 3),
	SPADE("スペード", 4),
	JOKER("ジョーカー", 5);
	
	/**
	 * マーク名
	 */
	private String name;
	
	/**
	 * 内部処理用のマークの識別子
	 */
	private int id;
	
	/**
	 * コンストラクタ
	 * @param マーク
	 * @param マーク番号
	 */
	Mark(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	/**
	 * マーク名を返す
	 * @return マーク名
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * マーク番号を返す
	 * @return マーク番号
	 */
	public int getId() {
		return this.id;
	}
}
