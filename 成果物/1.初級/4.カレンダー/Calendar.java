import java.util.Calendar;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * 4.カレンダー
 * 
 * @version 1.1.1, 2014/04/28
 * @author Takasaki Hiroki
 */

class Calendar_ {

	/**
	 * 表示するカレンダーの年月を指定してもらう 指定された年月のカレンダーを表示する
	 */
	public static void main(String[] args) {
		System.out.println("カレンダー出力します");

		int year = 0;
		int month = 0;
		try {
			year = inputYear();
			month = inputMonth();
		} catch (InvalidUserInputException | NumberFormatException | IOException e) {
			System.out.println(e.getMessage());
			return;
		}
		showCalendar(year, month);
	}

	/**
	 * ユーザーが年を入力するためメソッド
	 * @throws InvalidUserInputException 範囲外の数値(int)が入力されました。
	 * @throws NumberFormatException 数値の形式が正しくありません。
	 * @throws IOException 入出力処理に失敗しました。
	 * @return 年（1以上）
	 */
	private static int inputYear() throws InvalidUserInputException, NumberFormatException, IOException {
		System.out.println("年を入力してください（例：2014）");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int year;

		try {
			year = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new InvalidUserInputException("数値の形式が正しくありません。");
		}

		if (year < 0) {
			throw new InvalidUserInputException("年は１以上の自然数で入力してください。");
		}
		return year;
	}

	/**
	 * ユーザーが月を入力するためメソッド
	 * @throws InvalidUserInputException 範囲外の数値(int)が入力されました。
	 * @throws NumberFormatException 数値の形式が正しくありません。
	 * @throws IOException 入出力処理に失敗しました。
	 * @return 月(1〜12)
	 */
	private static int inputMonth() throws InvalidUserInputException, NumberFormatException, IOException {
		System.out.println("月を入力してください（例：4）");

		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int month;

		try {
			month = Integer.parseInt(reader.readLine());
		} catch (NumberFormatException | IOException e) {
			throw new InvalidUserInputException("数値の形式が正しくありません。");
		}

		if (month < 1 || month > 12) {
			throw new InvalidUserInputException("月は１〜１２の間で入力してください。");
		}
		return month;
	}

	/**
	 * 指定された年月のカレンダーを表示する
	 * @param year 0以上の整数
	 * @param month 1以上12以下の整数
	 */
	private static void showCalendar(int year, int month) {

		// 一日（ついたち）の曜日をもとに格納位置を計算する
		Calendar countUp = Calendar.getInstance();
		countUp.set(year, month - 1, 1);
		int startColumnPosition = countUp.get(Calendar.DAY_OF_WEEK) - 1;

		// その月の日数
		long days = countUp.getActualMaximum(Calendar.DAY_OF_MONTH);

		// 表示
		System.out.println("2014年" + month + "月のカレンダーを出力します");
		System.out.print(String.format("%d年 %d月 %n", year, month));
		System.out.println("日 月 火 水 木 金 土");

		// 前月の部分は空白を出力する
		for (int i = 0; i < startColumnPosition; i++) {
			System.out.print("   ");
		}

		// 日にちを出力する
		for (int day = 1; day <= days; day++) {
			System.out.print(String.format("%2d ", day));

			countUp.set(Calendar.DAY_OF_MONTH, day);

			// 土曜日ならば改行する
			if (Calendar.SATURDAY == countUp.get(Calendar.DAY_OF_WEEK)) {
				System.out.println();
			}
		}

		System.out.println();
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

