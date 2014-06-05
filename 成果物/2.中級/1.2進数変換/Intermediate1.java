package intermediate;

import java.util.LinkedList;
import java.util.List;


/**
 * 2進数変換
 * @version 1.3.0, 2014/05/08
 * @author Takasaki Hiroki
 */
public class Intermediate1 {
	/**
	 * 10進数の正の整数を2進数に変換し、文字列として返すメソッド
	 * @param src 10進数の0以上の整数
	 * @return 2進数を表す文字列
	 */
	public static String toBinaryString(int src) {		
		//２進数に変換
		final int BASE = 2; //基数
		return toNAryStriing(src, BASE);
	}
	
	
	/**
	 * 10進数の正の整数をN進数に変換し、文字列として返すメソッド
	 * @param src 10進数の0以上の整数
	 * @param base N進数のN
	 * @return N進数を表す文字列
	 */
	public static String toNAryString(int src, int base) {
		List<Integer> dest = convertDecimalToNAry(src, base);
		
		//Stringに型変換
		StringBuilder sb = new StringBuilder();
		for(Integer elem : dest) {
			sb.append(elem.toString());
		}
		return sb.toString();
	}
	
	/**
	 * 10進数をN進数に変換するメソッド
	 * @param src 変換する10進数
	 * @param base 基数
	 * @return N進数を表すリスト
	 * @throws IllegalArgumentException 負の整数を受け取りました
	 */
	public static List<Integer> convertDecimalToNAry(int src, int base) {
		if(src < 0) {
			throw new IllegalArgumentException(); 
		}

		List<Integer> dest = new LinkedList<Integer>();
		return convertRecursivelyDecimalToNAry(src, base, dest);
	}
	
	/**
	 * 再帰処理用の10進数をN進数に変換するメソッド
	 * 引数にlistを渡して毎回書き換えるので、privateで設定
	 * @param src 変換する10進数
	 * @param base 基数
	 * @param list 途中結果保存用
	 * @return N進数を表すリスト
	 */
	private static List<Integer> convertRecursivelyDecimalToNAry(int src, int base, List<Integer> list) {
		list.add(0, src % base);

		if(src < base) {
			return list;
		} 
		return convertRecursivelyDecimalToNAry(src / base, base, list);
	}
}

