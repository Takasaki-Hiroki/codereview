package poker;


import poker.Number;
/**
 * トランプのカード
 * @author Takasaki Hiroki
 * @version 1.1.0, 2014/05/20
 */
public class Card implements Comparable<Card> {
	
	/**
	 * 内部処理用のトランプの識別子
	 * 順序比較(強さ)に利用する。大きい方が強い。
	 */
	private int id;
	
	/**
	 * トランプのマーク
	 */
	private Mark mark;
	
	/**
	 * トランプの数字(1~13) なお、Jokerは1
	 */
	private Number number;
	
	/**
	 * Jokerであるかどうかを意味する
	 */
	private boolean isJoker;
	
	/**
	 * マーク毎のトランプの数(Joker除く)
	 */
	public static final int NUM_PER_MARK = 13;
	
	/**
	 * コンストラクタ staticファクトリーメソッド
	 * @param mark トランプのマーク
	 * @param number トランプの数字
	 */
	private Card(Mark mark, int number) {		
		this.mark = mark;
		this.number = Number.create(number);
		this.id = (mark.getId() - 1)* NUM_PER_MARK + number;
		
		if(mark == Mark.JOKER) {
			this.isJoker = true;
		}
	}
	
	/**
	 * ジョーカーを作るメソッド
	 * @return ジョーカー
	 */
	public static Card newInstanceForJoker() {
		return new Card(Mark.JOKER, 1);
	}
	
	/**
	 * ジョーカー以外のトランプを作るメソッド
	 * @param mark トランプのマーク
	 * @param number トランプの数字
	 * @throws IllegalArgumentException ジョーカーを生成しようとしたとき
	 * @return トランプのカード(Joker以外)
	 */
	public static Card newInstance(Mark mark, int number) {
		if(mark == Mark.JOKER) {
			throw new IllegalArgumentException("Jokerは生成できません。");
		}
		return new Card(mark, number);
	}
	
	/**
	 * トランプのマークを返す
	 * @return トランプのマーク
	 */
	public Mark getMark() {
		return this.mark;
	}
	
	/**
	 * トランプの数字を返す
	 * @return トランプの数字
	 */
	public Number getNumber() {
		return this.number;
	}
	
	/**
	 * トランプの識別子(強さ)を返す
	 * @return トランプの識別子(強さ)
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * ジョーカーであるかを返す
	 * @return ジョーカーならばtrue　そうでなければfalse
	 */
	public boolean isJoker() {
		return this.isJoker;
	}

	
	/**
	 * 強さを比較する
	 * @param card 比較対象のカード
	 * @return 比較対象のカードのほうが強い場合は負の数を、弱い場合は正の数を、同じ場合は0を返す
	 */
	public int compareTo(Card card) {
		return this.id - card.id;
	}
}
