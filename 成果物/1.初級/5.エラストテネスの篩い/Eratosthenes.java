import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.BitSet;

/**
 * 5.エラトステネスの篩い
 * 
 * @version 1.1.0, 2014/04/30
 * @author Takasaki Hiroki
 */

public class Eratosthenes {

	public static void main(String[] args) {

		int num = 0;
		try {
			num = inputMaxValueOfPrimeList();
		} catch (InvalidUserInputException e) {
			System.out.println(e.getMessage());
		}
		BitSet notPrimeList = createNotPrimeList(num);
		//0からリストが始まるため, lengthは+1する
		showPrimeList(notPrimeList, num + 1);
	}

	/**
	 * ユーザーがリストの最大値を入力するためメソッド
	 * @throws InvalidUserInputException 不正な数値または不正な文字が入力されました。
	 * @return 0以上の整数
	 */
	private static int inputMaxValueOfPrimeList() {
		System.out.println("素数リストを出力するプログラムです。");
		System.out.println("リストの最大値を整数で入力してください。");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int num;
		try {
			num = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new InvalidUserInputException("不正な値が入力されました。");
		}

		if (num < 0) {
			throw new InvalidUserInputException("自然数を入力してください。");
		}
		return num;
	}

	/**
	 * 素数リストを表示するメソッド
	 * @param notPrimeList 素数でない整数のリスト
	 * @param length notPrimeListの配列サイズ
	 */
	private static void showPrimeList(BitSet notPrimeList, int length) {
		for (int i = 0; i < length; i++) {
			if (!notPrimeList.get(i)) { // 素数でないの否定(=素数である)を表示
				System.out.print(i + " ");
			}
		}
	}

	/**
	 * 素数でない整数のリストを作成するメソッド
	 * @param num num以下の素数でないリストを作成する
	 * @return num以下の素数でない整数のリスト
	 */
	private static BitSet createNotPrimeList(int num) {

		//初めにすべてをtrue（＝すべて素数）にするのが嫌なので、
		//素数でないリストを作る
		// false -> 素数である, true -> 素数でない
		BitSet notPrimeList = new BitSet();
			
		final int MIN_PRIME = 2;

		//0,1は素数でない
		for(int i = 0; i <= num; i++){
			if(i == MIN_PRIME){
				break;
			}
			notPrimeList.set(i);
		} 

		int searchMaxValue = (int) Math.sqrt(num);
		int i,j;

		for (i = MIN_PRIME; i < searchMaxValue; i++) {
			for (j = MIN_PRIME; j <= num / i; j++) {
				// 素数でないものをtrueにする
				notPrimeList.set(i * j);
			}
		}
		return notPrimeList; 
	}

	/**
	 * ユーザーが不適切な数値を入力した場合に投げる例外
	 */
	public static class InvalidUserInputException extends RuntimeException {
		InvalidUserInputException(String message) {
			super(message);
		}
	}
}

