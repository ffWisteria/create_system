
//　自分が格納されているフォルダ名
package dao;

//  自分が格納されているフォルダの外にある必要なクラス
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import beans.Student;

public class StudentDAO{

	// 属性

	// ホスト名
	private final static String HOST_NAME = "db";

	// データベース名
	private final static String DATABASE_NAME = "create_system";

	//  データベースの接続先アドレスを静的変数として記述
	private final static String DRIVER_URL =
		"jdbc:mysql://" + HOST_NAME + ":3306/" + DATABASE_NAME + "?useUnicode=true&characterEncoding=utf-8";

	//  データベース接続ドライバの名前を静的変数として記述
	private final static String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";

	//  データベースのユーザー名
	private final static String USER_NAME = "root";

	//  データベースのユーザーのパスワード
	private final static String PASSWORD = "root";

	//  データベースとの接続を行う
	//  データベースの接続情報を持ったConnectionオブジェクトを返す
	public Connection createConnection(){
		try{
			Class.forName(DRIVER_NAME);
			Connection con = DriverManager.getConnection(DRIVER_URL, USER_NAME, PASSWORD);
			return con;
		} catch(ClassNotFoundException e){
			System.out.println("Can't Find JDBC Driver.\n");

		} catch(SQLException e){
			System.out.println("Connect Error.\n");
		}
		return null;
	}

	//  Connectionオブジェクトを使って、データベースとの接続を切断する
	//  引数Connectionオブジェクト
	public void closeConnection(Connection con){

		try{
			con.close();
		}catch(Exception ex){}
	}

	//  情報をデータベースに登録する
	//  引数はStudentオブジェクトと、Connectionオブジェクト
	public void registStudent(Student student, Connection connection){

		try{

			//  SQLコマンド
			String sql = "insert into student_info values(?, ?, ?)";

			//  SQLコマンドの実行
			PreparedStatement stmt = connection.prepareStatement(sql);

			//  SQLコマンドのクエッションマークに値を、1番目から代入する
			stmt.setString(1, student.getStudentID());
			stmt.setString(2, student.getStudentName());
			stmt.setString(3, student.getStudentBirthplace());

			stmt.executeUpdate();

		}catch(SQLException e){

//			エラーが発生した場合、エラーの原因を出力する
			e.printStackTrace();

		} finally {
		}
	}

	//  検索する
	//  引数はStudentオブジェクトと、Connectionオブジェクト
	public Student searchStudent(Student student, Connection connection){

		try{

			//  SQLコマンド
			String sql = "select * from student_info where student_id = '" + student.getStudentID() + "'";

			//  SQLのコマンドを実行する
			//  実行結果はrsに格納される
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			rs.first();

			//  rsからそれぞれの情報を取り出し、Studentオブジェクトに設定する
			student.setStudentName(rs.getString("student_name"));
			student.setStudentBirthplace(rs.getString("student_birthplace"));

			//  終了処理
			stmt.close();
			rs.close();

			//  Studentオブジェクトを返す
			return student;

			}catch(SQLException e){

				//	エラーが発生した場合、エラーの原因を出力し、nullオブジェクトを返す
				e.printStackTrace();
				return null;

			}finally{
			}
	}

}
