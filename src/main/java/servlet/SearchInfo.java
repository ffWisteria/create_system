//--------------------------------
//	SearchInfo.java
//--------------------------------
//　自分が格納されているフォルダ名
package servlet;

//自分が格納されているフォルダの外にある必要なクラス

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Student;
import control.StudentManager;

@WebServlet("/SearchInfo")
//HttpServletを継承することで、このクラスはServletとして、働くことができる
public class SearchInfo extends HttpServlet {
    /**
     *
     */
    private static final long serialVersionUID = 1L;


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/searchStudent.jsp");
        dispatcher.forward(request, response);
    }

    //  requestオブジェクトには、フォームで入力された文字列などが格納されている。
    //  responseオブジェクトを使って、次のページを表示する
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // requestオブジェクトの文字エンコーディングの設定
        request.setCharacterEncoding("UTF-8");

        // requestオブジェクトから登録情報の取り出し
        String stu_id = request.getParameter("stu_id");

        String stu_name = null;
        String stu_birthplace = null;

        // studentのオブジェクトに情報を格納
        Student student = new Student(stu_id, stu_name, stu_birthplace);

        //  StudentManagerオブジェクトの生成
        StudentManager manager = new StudentManager();

        //  学生の検索
        student = manager.searchStudent(student);
        //  requestオブジェクトにオブジェクトを登録
        request.setAttribute("Student", student);
        //  情報表示画面を表示する
        //  forwardはrequestオブジェクトを引数として、次のページに渡すことができる
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/showStudent.jsp");
        dispatcher.forward(request, response);
    }
}
