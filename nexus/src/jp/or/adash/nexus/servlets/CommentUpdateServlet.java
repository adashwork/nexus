package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CommentService;

/**
 * コメントテーブルを更新するサーブレット
 * @author kitayama
 */
@WebServlet("/web/comment-update")
public class CommentUpdateServlet extends HttpServlet {
	 static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");


		// 各パラメータの取得
		Integer id = Integer.parseInt(request.getParameter("commentid"));	// コメントID
		String companyNo = request.getParameter("companyno");				// 事業所番号
		String kyujinNo = request.getParameter("kyujinno");				// 求人番号
		String jobSeekerId = request.getParameter("jobseekeeid");			// 求職者ID
		String staffId = request.getParameter("staffid");					// 職業紹介者ID
		Integer matchId = -1;												// マッチングID
		String genre = request.getParameter("genre");						// 内容分類
		String important = request.getParameter("important");				// 重要度
		String title = request.getParameter("title");						// 件名
		String note = request.getParameter("note");						// 備考
		Date createDt = null;												// 新規登録日
		String createUserId = request.getParameter("createuserid");		// 新規登録ユーザー
		Date updateDt = null;												// 最終更新日
		String updateUserId = staff.getId();								// 最終更新ユーザー

		// matchidをint型に変換
		if(request.getParameter("matchid") != null && !request.getParameter("matchid").equals("")) {
			 matchId = Integer.parseInt(request.getParameter("matchid"));
		}

		// createdt, updatedtをDate型に変換
		try {
			createDt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createdt"));
		}catch(Exception e) {
			e.printStackTrace();
		}

		try {
			updateDt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("updatedt"));
		}catch(Exception e) {
			e.printStackTrace();
		}

		// オブジェクトに格納
		Comment comment = new Comment(
				 						id
				 						,companyNo
				 						,kyujinNo
				 						,jobSeekerId
				 						,staffId
				 						,matchId
				 						,genre
				 						,important
				 						,title
				 						,note
				 						,createDt
				 						,createUserId
				 						,updateDt
				 						,updateUserId
				 						);

		// コメントをDBに登録
		CommentService service = new CommentService();
		if(service.checkComment(comment)) {
			service.updateComment(comment);
		}

		// requsetにパラメータを格納
		request.setAttribute("Staff", staff);
		request.setAttribute("comment", comment);
		request.setAttribute("messages", service.getMessages());

		// JSPにフォワード
		request.getRequestDispatcher("/commentregist.jsp")
				.forward(request, response);



	}

}
