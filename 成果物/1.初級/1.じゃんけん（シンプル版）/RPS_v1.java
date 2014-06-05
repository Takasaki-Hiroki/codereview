import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.io.IOException;

/**
 *じゃんけん（シンプル版）
 *
 * @version 1.2.1, 2014/04/18
 * @author Takasaki Hiroki
 */

public class RPS_v1 {
	/**
	 *じゃんけんの手を表す
	 */
	private enum Hand {
		ROCK("グー",1),
		SCISSORS("チョキ",2),
		PAPER("パー",3);


		private String name;
		private int value;

		Hand(String name,int value) {
			this.name = name;
			this.value = value;
		}
		
		/**
		 *getter じゃんけんの手を日本語で返す
		 * @return "グー" "チョキ" "パー"のいずれかを返す
		 */
		public String getName() {
			return name;
		}

		/**
		 *getter じゃんけんの手を数字で返す
		 *@return "1: グー、2: チョキ、3: パー"
		 */
		public int getValue() {
			return value;
		}

		
		/**
	 	*出した手（数字）をHandに変換するメソッド
		*@param rps 1:グー 2:チョキ 3:パー
		*@return ROCKまたは、PAPERまたは、SCISSORS
		*/
		public static Hand convertNumberToHand(int rps) throws IllegalArgumentException {
			Hand result = Hand.ROCK;

			if(rps == Hand.ROCK.getValue()){
				result = Hand.ROCK;
			} else if(rps == Hand.PAPER.getValue()){
				result = Hand.PAPER;
			} else if(rps == Hand.SCISSORS.getValue()){
				result = Hand.SCISSORS;
			} else {
				throw new IllegalArgumentException("想定外の使い方をしています。");
			}
			return result;
		}

		/**
		*ユーザーがじゃんけんで出す手を入力するためのメソッド
		*@return ROCKまたは、PAPERまたは、SCISSORS
		*/
		public static Hand inputRPS() throws IOException, IllegalArgumentException{
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			int userInput;

			try {
				userInput = Integer.parseInt(reader.readLine());

				if((Hand.ROCK.getValue() != userInput) && (Hand.PAPER.getValue() != userInput) && (Hand.SCISSORS.getValue()) != userInput) {
					throw new IllegalArgumentException("想定外の使い方をしています。");
				}
				return Hand.convertNumberToHand(userInput);
			} catch (IOException e){
				throw e;
			} catch (IllegalArgumentException e) {
				throw e;
			}
		}

			
		/**
		*じゃんけんでランダムに手を選ぶメソッド
		*@return ROCKまたは、PAPERまたは、SCISSORS
		*/
		public static Hand throwRandomRPS() throws IllegalArgumentException{
			Random random = new Random();
			int num = (random.nextInt(3) + 1);
			try {
				return Hand.convertNumberToHand(num);
			}	catch(IllegalArgumentException e) {
				throw e;
			}
		}
	}

	/**
	 *勝敗を表す
	 */
	private static enum Result {
		WIN("あなたの勝ちです！"),
		LOSE("あなたの負けです！"),
		DRAW("あいこでした！");

		private String name;

		Result(String name) {
			this.name = name;
		}

		public String getName() {
			return name;
		}
	}
  
	/**
	 *メインメソッド
	 */
	public static void main(String[] args) {
		System.out.println("じゃんけんをしましょう！");
		System.out.println("1: グー、2: チョキ、3: パー");
		System.out.println("じゃーんけーん・・");
		System.out.print("出す手を入力 => ");
		
		try {
			//ユーザーが標準入力で出す手を選択する
			Hand userHand = Hand.inputRPS();
			//コンピュータがランダムで出す手を選択する
			Hand cpuHand = Hand.throwRandomRPS();

			//勝敗の判定
			Result result = judge(userHand,cpuHand);

			System.out.println("ぽん！");
			System.out.println("あなた："  + userHand.getName() + "、コンピュータ：" + cpuHand.getName()); 
		
			//結果の表示
			System.out.println(result.getName());
		
		} catch(IOException e) {
			System.out.println("1～3で入力してください！");
			return;
		}	catch(IllegalArgumentException e) {
			//ここを通ることはありえないけど、念のための記述
			System.out.println(e);
			return;
		}
	}



	/**
	 *じゃんけんの勝敗を判定するメソッド(user1視点)
	 *@param user1 user1の出す手 1:グー 2:チョキ 3:パー
	 *@param user2 user2の出す手 1:グー 2:チョキ 3:パー
	 *@return WINまたは、LOSEまたは、DRAW
	 */
	private static Result judge(Hand user1,Hand user2) throws IllegalArgumentException {
		Result result = Result.WIN;
		switch(user1) {
			//ユーザーがグーを出した場合
			case ROCK:
				switch(user2) {
					case ROCK:
						result = Result.DRAW;
						break;
					case PAPER:
						result = Result.LOSE;
						break;
					case SCISSORS:
						result = Result.WIN;
						break;
					}
				break;
			
			//ユーザーがパーを出した場合
			case PAPER:
				switch(user2) {
					case ROCK:
						result = Result.WIN;
						break;
					case PAPER:
						result = Result.DRAW;
						break;
					case SCISSORS:
						result = Result.LOSE;
						break;
					}
				break;
				
			//ユーザーがチョキを出した場合
			case SCISSORS:
				switch(user2) {
					case ROCK:
						result = Result.LOSE;
						break;
					case PAPER:
						result = Result.WIN;
						break;
					case SCISSORS:
						result = Result.DRAW;
						break;
					}
				break;
			default:
				throw new IllegalArgumentException("想定外の使われ方をしています。");
			}
			return result;
	}
}
