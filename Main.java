package highAndLow;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.regex.Pattern;

// System.out.println が長いためコモンクラスを作成しています．
import common.Common;

public class Main {

	public static void main(String[] args) {
		HighAndLow hal = new HighAndLow();
		hal.startGame();
	}
	
}

class HighAndLow{
	// 最初にHighAndLowを選んでもらための基準となる数値
	private int firstnum;
	
	// 基準となる数値に対してHighAndLowの判定となる数値
	private int secondnum;
	
	// ユーザが選択する数値
	private String userAnswer;
	
	// 数値チェックのための正規表現
	private Pattern pat = Pattern.compile("[1-3]");

	// HighAndLowクラスのInitializeメソッド
	public HighAndLow() {
	}

	// ゲームスタートメソッド
	// firstnum, secondnum の初期値を決定する
	public void startGame() {
		Random rnd = new Random();
		// 乱数にて firstnum, secondnum に一桁の数字を格納
		this.firstnum = rnd.nextInt(9) + 1;
		this.secondnum = rnd.nextInt(9) + 1;
		
		// firstnum に格納された数値と選択しを出力
		Common.println("最初に選ばれた数値は 『 " + this.firstnum + " 』でした．\n" + "さて，次に選ばれた数値はHighかLowかを当ててみよう！\n"
				+ "選択肢 : 1. High or 2. Low or 3.Draw");
		
		// ユーザからの入力を受け付けるメソッドを呼び出す
		inputUserAnswer();
		
		// 判定のための secondnum を出力しユーザに掲示
		Common.println("次に選ばれた数値は 『 " + this.secondnum + " 』 でした．");
		
		// ユーザの選択に対して正解か残念かの回答を行うためのフラグ立て
		boolean flg = checkHighLow();
		
		// フラグから，正解・残念の回答を行う
		if (flg) {
			Common.println("正解！");
		} else {
			Common.println("残念！");
		}

	}

	// ユーザからの入力を受け付ける処理
	public void inputUserAnswer() {
		// 入力を受け付けるインスタンスを作成
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		
		// 入力を受けるString型の変数の定義
		String userStr = "";
		
		Common.printf("Your Answer : ");
		try {
			// ユーザが入力した値を変数に格納
			userStr = br.readLine();
			
			// 入力された値のチェック
			// 数値１〜３の時，
			// １なら"High"，２なら"Low"，3なら"Draw"をユーザの選択として格納
			if (pat.matcher(userStr).matches()) {
				int num = Integer.valueOf(userStr);
				if (num == 1) {
					this.userAnswer = "High";
				} else if (num == 2) {
					this.userAnswer = "Low";
				} else if (num == 3) {
					this.userAnswer = "Draw";
				}
			// 最初の文字が大文字である場合も小文字である場合も定めた文字列を返す
			} else if (userStr.equals("High") || userStr.equals("Low") || userStr.equals("Draw")) {
				this.userAnswer = userStr;
			} else if (userStr.equals("high") || userStr.equals("low") || userStr.equals("draw")) {
				// BigStart(String) - 小文字スタートの定めた文字列を大文字スタートに変更する．
				this.userAnswer = BigStart(userStr);
			// ランダムに選択肢を選択する
			} else if (userStr.equals("Dice") || userStr.equals("dice")){
				this.userAnswer = RandomAns();
			// それ以外の場合は，再帰を使って処理を繰り返す．
			} else {
				Common.println("入力された値が不正です．選択にある数字もしくは文字を入力してください．");
				inputUserAnswer();
			}
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	// ユーザが選んだ選択が正しいかどうかの判定をする．
	public boolean checkHighLow() {
		if (userAnswer.equals("High")) {
			return (this.secondnum > this.firstnum);
		} else if (userAnswer.equals("Low")) {
			return (this.secondnum < this.firstnum);
		} else if (this.userAnswer.equals("Draw")) {
			return (this.secondnum == this.firstnum);
		} else {
			return false;
		}
	}

	// 小文字スタートの定めた文字列を大文字スタートに変更するメソッド
	public String BigStart(String str){
		switch(str){
			case "high":
				return "High";
			case "low":
				return "Low";
			case "draw":
				return "Draw";
			default :
				return "";
		}
	}
	
	// 選択肢をランダムで選択するメソッド
	public String RandomAns(){
		Random rnd = new Random();
		int num = rnd.nextInt(3);
		if(num == 0){
			Common.println("ランダム選択によってHighが選ばれました．");
			return "High";
		}else if(num == 1){
			Common.println("ランダム選択によってLowが選ばれました．");
			return "Low";
		}else{
			Common.println("ランダム選択によってDrawが選ばれました．");
			return "Draw";
		}
	}
}
