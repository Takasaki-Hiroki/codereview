import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.io.IOException;

/**
 * じゃんけん
 * 
 * @version 1.3.0, 2014/04/25
 * @author Takasaki Hiroki
 */

public class RPS {
	/**
	 * じゃんけんの手を表す
	 */
	private enum Hand {
		ROCK("グー", 1), SCISSORS("チョキ", 2), PAPER("パー", 3);

		private String name;
		private int value;

		Hand(String name, int value) {
			this.name = name;
			this.value = value;
		}

		/**
		 * getter じゃんけんの手を日本語で返す
		 * 
		 * @return "グー" "チョキ" "パー"のいずれかを返す
		 */
		public String getName() {
			return name;
		}

		/**
		 * getter じゃんけんの手を数字で返す
		 * 
		 * @return "1: グー、2: チョキ、3: パー"
		 */
		public int getValue() {
			return value;
		}

		/**
		 * 出した手（数字）をHandに変換するメソッド
		 * 
		 * @param rps
		 *            1:グー 2:チョキ 3:パー
		 * @return ROCKまたは、PAPERまたは、SCISSORS
		 * @throws IllegalArgumentException
		 *             想定外の引数を受け取りました
		 */
		public static Hand convertNumberToHand(int rps)
				throws IllegalArgumentException {
			for (Hand hand : Hand.values()) {
				if (rps == hand.getValue()) {
					return hand;
				}
			}
			throw new IllegalArgumentException("想定外の引数を受け取りました");
		}

	}

	/**
	 * 勝敗を表す
	 */
	private static enum Result {
		WIN("あなたの勝ちです！"), LOSE("あなたの負けです！"), DRAW("あいこでした！");

		private String name;

		Result(String name) {
			this.name = name;
		}

		/**
		 * getter 勝敗のメッセージを返す
		 * 
		 * @return "あなたの勝ちです！" or "あなたの負けです！" or "あいこでした！"
		 */
		public String getName() {
			return name;
		}
	}

	/**
	 * あいこの場合は繰り返す 不適切な入力を受け取った場合は、再度入力してもらう
	 */
	public static void main(String[] args) {
		System.out.println("じゃんけんをしましょう！");
		System.out.println("1: グー、2: チョキ、3: パー");
		System.out.println("じゃーんけーん・・");

		boolean isFirstTime = true;
		Result result = null;

		while (isFirstTime || result == Result.DRAW) {

			Hand userHand = null;
			Hand cpuHand = null;
			try {
				// ユーザーが標準入力で出す手を選択する
				System.out.print("出す手を入力 => ");
				userHand = inputRPS();

				if (!isFirstTime) {
					System.out.println("しょ！");
				} else {
					System.out.println("ぽん！");
					isFirstTime = false;
				}

				// コンピュータがランダムで出す手を選択する
				cpuHand = getCpuHand();

				// 勝敗の判定
				result = judge(userHand, cpuHand);

				// 今回のプログラムでは例外はすべて放置する
			} catch (IOException e) {
				return;
			} catch (InvalidUserInputException e) {
				continue;
			} catch (NumberFormatException e) {
				continue;
			} catch (IllegalArgumentException e) {
				continue;
			}
			System.out.println("あなた：" + userHand.getName() + "、コンピュータ："
					+ cpuHand.getName());

			if (result == Result.DRAW) {
				System.out.println("あーいこーで・・");
			}

		}
		// 結果の表示
		System.out.println(result.getName());
	}

	/**
	 * じゃんけんの勝敗を判定するメソッド(user1視点)
	 * 
	 * @param user1
	 *            user1の出す手 1:グー 2:チョキ 3:パー
	 * @param user2
	 *            user2の出す手 1:グー 2:チョキ 3:パー
	 * @throws IllegalArgumentException
	 *             想定外の引数を受け取りました
	 * @return WINまたは、LOSEまたは、DRAW
	 */
	private static Result judge(Hand user1, Hand user2)
			throws IllegalArgumentException {
		switch (user1) {
		// ユーザーがグーを出した場合
		case ROCK:
			switch (user2) {
			case ROCK:
				return Result.DRAW;
			case PAPER:
				return Result.LOSE;
			case SCISSORS:
				return Result.WIN;
			}

			// ユーザーがパーを出した場合
		case PAPER:
			switch (user2) {
			case ROCK:
				return Result.WIN;
			case PAPER:
				return Result.DRAW;
			case SCISSORS:
				return Result.LOSE;
			}

			// ユーザーがチョキを出した場合
		case SCISSORS:
			switch (user2) {
			case ROCK:
				return Result.LOSE;
			case PAPER:
				return Result.WIN;
			case SCISSORS:
				return Result.DRAW;
			}
		default:
			throw new IllegalArgumentException("想定外の引数を受け取りました。");
		}
	}

	/**
	 * ユーザーがじゃんけんで出す手を入力するためのメソッド
	 * 
	 * @return ROCKまたは、PAPERまたは、SCISSORS
	 * @throws InvalidUserInputException
	 *             ユーザーの入力値が適切値でない
	 * @throws IOException
	 *             入力時に例外が発生した
	 * @throws NumberFormatException
	 *             ユーザーの入力値が文字であった
	 */
	public static Hand inputRPS() throws InvalidUserInputException,
			IOException, NumberFormatException {
		int userInput = 0;

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					System.in));
			userInput = Integer.parseInt(reader.readLine());
			return Hand.convertNumberToHand(userInput);

		} catch (NumberFormatException e) {
			throw e;
		} catch (IllegalArgumentException e) {
			throw new InvalidUserInputException("1~3以外の数字が入力されました");
		}
	}

	/**
	 * じゃんけんでランダムに手を選ぶメソッド
	 * 
	 * @return ROCKまたは、PAPERまたは、SCISSORS
	 * @throws IllegalArgumentException
	 *             想定外の引数を受け取りました
	 */
	public static Hand getCpuHand() throws IllegalArgumentException {
		Random random = new Random();
		int num = (random.nextInt(3) + 1);
		try {
			return Hand.convertNumberToHand(num);
		} catch (IllegalArgumentException e) {
			throw e;
		}
	}

	/**
	 * ユーザーが不適切な値（1~3）以外の数値を入力した場合に投げる例外
	 */
	public static class InvalidUserInputException extends RuntimeException {
		InvalidUserInputException(String message) {
			super(message);
		}
	}
}

