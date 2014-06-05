import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

/**
 *ヒストグラム
 *
 * @version 1.1.0, 2014/05/01
 * @author Takasaki Hiroki
 */

public class Histgram {
	
	/**
	 *引数で受け取った配列をヒストグラム形式で表示する
	 *@param args ヒストグラムに表示するデータ配列
	 */
	public static void main(String[] args) {
		List<Integer> data = new ArrayList<Integer>();

		//引数なしの場合は、使い方を説明する
		if(args.length == 0){
			System.out.println("グラフにプロットする値を引数に指定してください。");
			System.out.println("例）java Histogram 1 2 3 3 2 1");
			return;
		}

		try {
			for(int i = 0; i< args.length; i++){
				data.add(convertStringToNumber(args[i]));
			}

		} catch(NumberFormatException | NegativeNumberException e) {
			System.out.println(e.getMessage());
			return;
		}

		showHistogram(data);
	}

	/**
	 *文字から数字に変換するメソッド
	 *@param str 変換する文字列型の数値
	 *@throws NegativeNumberException 引数に指定できるのは正の数だけです。
	 *@throws NumberFormatException 引数に指定できるのは数値のみです。
	 *@return int型の数値
	 */
	private static int convertStringToNumber(String str) {
		int result = 0;
		try {
			result = Integer.parseInt(str);	
		} catch(NumberFormatException e) {
			throw new NumberFormatException("引数に指定できるのは数値のみです。");
		}

		if(result < 0) {
			throw new NegativeNumberException("引数に指定できるのは正の数だけです。");
		}
		return result;
	}

	/**
	 *入力されたデータをもとにヒストグラムを表示するメソッド
	 *@param data 各要素は0以上
	 */
	private static void showHistogram(List<Integer> data) {
		int max = Collections.max(data);

		for(int i = max; i > 0 ; i--) {
			for(Integer num : data) {
				if((int) num >= i){
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("");
		}
	}
}

class NegativeNumberException extends RuntimeException {
	/**
	* コンストラクタ
	* @param str エラーメッセージ
	*/
	public NegativeNumberException(String str) {
		super(str);
	}
}
