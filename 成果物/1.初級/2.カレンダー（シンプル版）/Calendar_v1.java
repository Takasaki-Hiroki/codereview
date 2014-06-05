import java.util.Calendar;

/**
 *カレンダー（シンプル版）
 *
 * @version 1.5.0, 2014/04/24
 * @author Takasaki Hiroki
 */

public class Calendar_v1 {
	public static void main(String[] args) {
		Calendar cal = createCalendar();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH);

		showCalendar(year, month + 1);
	}

	/**
	 *表示するカレンダーの年月を生成するメソッド
	 *@return Calendar型のインスタンス
	 */
	private static Calendar createCalendar() {
		Calendar cal = Calendar.getInstance();
		return cal;
	}

	/**
	 *指定された年月のカレンダーを表示する
	 *@param cal 表示するカレンダーの年月
	 */
	private static void showCalendar(int year, int month) {
	
		//一日（ついたち）の曜日をもとに格納位置を計算する
		Calendar countUp = Calendar.getInstance();
		countUp.set(year, month - 1, 1);
		int startColumnPosition = countUp.get(Calendar.DAY_OF_WEEK) - 1;
		
		//その月の日数
		long days = countUp.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//表示
		System.out.println("2014年" + month + "月のカレンダーを出力します");
		System.out.print(String.format("%d年 %d月 %n", year, month));
		System.out.println("日 月 火 水 木 金 土");

		//前月の部分は空白を出力する
		for(int i = 0; i < startColumnPosition; i++) {
			System.out.print("   ");
		}

		//日にちを出力する
		for(int day = 1; day <= days; day++) {
			System.out.print(String.format("%2d ", day));
			
			countUp.set(Calendar.DAY_OF_MONTH, day);
			
			//土曜日ならば改行する
			if(Calendar.SATURDAY == countUp.get(Calendar.DAY_OF_WEEK)) {
				System.out.println();
			}
		}

		System.out.println();
	}
}
