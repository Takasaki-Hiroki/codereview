package intermediate;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Takasaki Hiroki
 * @version 1.1.0, 2014/05/09
 */
public class Intermediate2 {

	/**
	 * 連続する要素間で重複がある場合に重複を除去する
	 * @param src 重複除去する配列
	 * @return　連続した要素に関して、重複を除去した配列
	 * @throws NullPointerException srcがnullです
	 */
	public static int[] uniq(int[] src) {

		if (src == null) {
			throw new NullPointerException();
		}

		if (src.length <= 1) {
			return Arrays.copyOf(src, src.length);

		}

		/*
		重複除去アルゴリズム
		i
		-----------------------
	 	n1 n2 n3 ... nn
		-----------------------
		j
		
		1  ：i番目の要素を結果用リストに追加する
		2  ：j = iからスタートしてj++しながら、次の重複していない数字のところまでjをずらす
		3  ：i = jとし、1.の手順から繰り返し実行する
		3.1：ただし、jが末尾の場合は、そこで終了する 
		*/
		
		// 重複を除去した数値を格納するためのリスト
		List<Integer> uniqNumList = new LinkedList<Integer>();

		for (int i = 0; i < src.length;) {
			uniqNumList.add(src[i]);
			for (int j = i; j < src.length; j++) {
				if (src[i] != src[j]) {
					i = j;
					break;
				}
				
				if (j == src.length - 1) {
					i = src.length;
				}
			}
		}

		int resultLength = uniqNumList.size();
		int[] result = new int[resultLength];
		int i = 0;
		// 型変換(List<Integer> から int[])
		for (Integer elem : uniqNumList) {
			result[i] = elem.intValue();
			i++;
		}
		return result;

	}
}

