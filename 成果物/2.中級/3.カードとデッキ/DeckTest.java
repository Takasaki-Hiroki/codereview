package poker;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Deckクラスのテスト
 * @author Takasaki Hiroki
 * @version 1.1.0, 2014/05/20
 */
public class DeckTest {

	@Test
	public void getNumCardsで現在のカード枚数を取得できること() {
		//ジョーカーが2枚のとき
		Deck deck1 = new Deck(2);
		
		deck1.draw(3);
		int actual1 = deck1.getCurrentCardsNumber();
		
		int matcher1 = 51;
		assertThat(actual1, is(matcher1));

		//ジョーカーが0枚のとき
		Deck deck2 = new Deck();
		deck2.draw(5);
		int actual2 = deck2.getCurrentCardsNumber();

		int matcher2 = 47;
		assertThat(actual2, is(matcher2));
	}

	@Test
	public void deck生成時に52枚とジョーカ分のカードを保持していること() {
		//ジョーカーが0枚のとき
		Deck deck1 = new Deck();
		int actual1 = deck1.getCurrentCardsNumber(); 
		
		int matcher1 = 52;
		assertThat(actual1, is(matcher1));
		
		//ジョーカーが2枚のとき
		Deck deck2 = new Deck(2);
		int actual2 = deck2.getCurrentCardsNumber();
		
		int matcher2 = 54;
		assertThat(actual2, is(matcher2));
	}
	
	@Test
	public void deckから指定枚数のカードを引けること() {
		//ジョーカーが0枚のとき
		Deck deck = new Deck(0);
		int actual = deck.draw(5).size();
		
		int matcher = 5;
		assertThat(actual, is(matcher));
	}
		
	
	//id順に並んだデッキをシャッフルするので、
	//id順に並んでいなかったらパスすることとする。
	@Test
	public void デッキがランダムになっていること() {
		Deck deck = new Deck(0);
		List<Card> cards = new LinkedList<Card>();
		cards = deck.draw(52);
		
		int[] actual = new int[52];
		int i = 0;
		for(Card card : cards) {
			actual[i] = card.getId();
			i++;
		}
		
		int[] matcher = new int[52];
		for(int j = 1; j <= 52; j++) {
			matcher[j-1] = j;
		}
		assertThat(actual, is(not(matcher)));
	}
	
	@Test(expected=NoSuchElementException.class)
	public void 残り枚数以上のカードを引けないこと() {
		Deck deck = new Deck(0);
		deck.draw(100);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void ゼロ以下の整数の枚数分カードを引けないこと() {
		Deck deck = new Deck(0);
		deck.draw(0);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 負の数の枚数分ジョーカがあるデッキを作れないこと() {
		Deck deck = new Deck(-1);
	}

}
