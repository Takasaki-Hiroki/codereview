package poker;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

/**
 * Cardクラスのテスト
 * @author Takasaki Hiroki
 * @version 1.1.0, 2014/05/20 
 */
public class CardTest {
	@Test
	public void getMarkでそのカードのマークが取得できること() {
		Card card = Card.newInstance(Mark.CLUB, 1);
		Mark actual = card.getMark();
		Mark matcher = Mark.CLUB;
		
		assertThat(actual, is(matcher));
	}
	
	@Test
	public void getNumberでそのカードの番号が取得できること() {
		Card card = Card.newInstance(Mark.CLUB, 1);
		Number actual = card.getNumber();
		Number matcher = Number.ACE;
		
		assertThat(actual, is(matcher));
	}
	
	@Test
	public void getIdでそのカードのidが取得できること() {
		Card card1 = Card.newInstance(Mark.CLUB, 1);
		int actual1 = card1.getId();
		int matcher1 = 1;
		assertThat(actual1, is(matcher1));
		
		Card card2 = Card.newInstanceForJoker();
		int actual2 = card2.getId();
		int matcher2 = 53;
		assertThat(actual2, is(matcher2));
		
	}
	
	@Test
	public void isJokerでジョーカーであるかを取得できること() {
		Card card1 = Card.newInstanceForJoker();
		boolean actual1 = card1.isJoker();
		assertTrue(actual1);
		
		Card card2 = Card.newInstance(Mark.CLUB, 1);
		boolean actual2 = card2.isJoker();
		assertFalse(actual2);
	}

	@Test
	public void マークを正しい順序で判定できること() {
		Card club1 = Card.newInstance(Mark.CLUB, 1);
		Card spade1 = Card.newInstance(Mark.SPADE, 1);
		
		//クラブ1とスペード1ではクラブ1が小さい
		boolean actual1 = (club1.compareTo(spade1) < 0);
		assertTrue(actual1);
		
		//クラブ1とクラブ1では同じ
		boolean actual2 = (club1.compareTo(club1) == 0);
		assertTrue(actual2);
		
		//スペード1とクラブ1はスペード1が大きい
		boolean actual3 = (spade1.compareTo(club1) > 0);
		assertTrue(actual3);
	}
	
	@Test
	public void カードがソートできること() {
		Card card1 = Card.newInstance(Mark.CLUB, 1);
		Card card2 = Card.newInstance(Mark.CLUB, 2);
		Card card3 = Card.newInstance(Mark.CLUB, 3);
		Card card4 = Card.newInstance(Mark.CLUB, 4);
	
		List<Card> matcher = new LinkedList<Card>();
		matcher.add(card1);
		matcher.add(card2);
		matcher.add(card3);
		matcher.add(card4);
		
		
		List<Card> actual = new LinkedList<Card>();
		actual.add(card1);
		actual.add(card3);
		actual.add(card2);
		actual.add(card4);
		
		Collections.sort(actual);
		
		assertThat(actual, is(matcher));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 不適切なカードを生成できないこと1() {
		Card.newInstance(Mark.JOKER, 1);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void 不適切なカードを生成できないこと2() {
		Card.newInstance(Mark.CLUB, 15);
	}
}
