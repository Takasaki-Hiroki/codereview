package poker;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * トランプのカードが積まれているdeck
 * @author Takasaki Hiroki
 * @version 1.1.0, 2014/05/20
 *
 */
public class Deck {
	/**
	 * deckに積まれているカードのリスト(indexが若い方が下側)
	 */
	private LinkedList<Card> cards;
	
	/**
	 * deckに積まれているカード枚数 
	 */
	private int currentCardsNumber;
	
	/**
	 * Jokerなしの52枚のカードを生成する
	 */
	public Deck() {
		this(0);
	}
	
	/**
	 * 指定した枚数のJokerを持つデッキを生成する
	 * @param numJoker Jokerの枚数
	 * @throws IllegalArgumentException 0未満の整数を受け取ったとき
	 */
	public Deck(int numJoker) {
		//負の枚数のジョーカーを作ろうとしたとき
		if(numJoker < 0) {
			throw new IllegalArgumentException("不適切な数字が入力されました。");
		}
		
		this.cards = new LinkedList<Card>();
		
		//JOKER以外のカードを生成し、deckに積む
		for(Mark mark: Mark.values()) {
			if(mark == Mark.JOKER) { 
				continue;
			}
			for(int i = 1; i <= Card.NUM_PER_MARK; i++) {
				cards.push(Card.newInstance(mark, i));
			}
		}
		
		//JOKERを最後にdeckに積む
		for(int i = 0; i < numJoker; i++) {
			cards.push(Card.newInstanceForJoker());
		}
		
		//シャッフルする
		Collections.shuffle(this.cards);
		
		//カード枚数をセットする
		this.currentCardsNumber = this.cards.size();
	}
	
	/**
	 * 現在のdeckに残っているカード枚数を返す
	 * @return　カード枚数
	 */
	public int getCurrentCardsNumber() {
		return this.currentCardsNumber;
	}
	
	/**
	 * 
	 * @param num deckからカードを引く枚数
	 * @throws IllegalArgumentException deckから不適切な枚数を引いたとき
	 * @return 引いたカードのリスト(indexが小さいほうが先に引いたもの)
	 */
	public List<Card> draw(int num) {
		LinkedList<Card> drawnCards = new LinkedList<Card>();

		if(num <= 0) {
			throw new IllegalArgumentException("不適切な数字が入力されました。");
		}
		
		if(this.currentCardsNumber < num) {
			throw new NoSuchElementException("残りのカード枚数が不足しています。");
		}
		
		for(int i = 0; i < num; i++){
			drawnCards.push(this.cards.pop());
		}

		this.currentCardsNumber = this.cards.size();
		
		return drawnCards;
	}
}
