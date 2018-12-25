package jp.or.adash.nexus.servlets;

import java.io.IOException;
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
 * コメントを新規登録するためのサーブレット
 * TODO jspの作成が完了していなかったためパラメータ名は未設定
 */
@WebServlet("/web/comment-regist")
public class CommentRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 入力されたパラメータ等の取得
		Integer id = 0;													// 備考ID
		String companyNo = request.getParameter("companyno");			// 事業所番号
		String kyujinNo = request.getParameter("kyujinno");			// 求人NO
		String jobSeekerId = request.getParameter("jobseekerid");		// 求職者ID
		String staffId = staff.getId();									// 職業紹介者ID
		Integer matchId = -1;											// マッチング事例ID
		String genre = request.getParameter("genre");					// 内容分類
		String important = request.getParameter("important");			// 重要アラート
		String title = request.getParameter("title");					// 件名
		String note = request.getParameter("note");						// 備考
		Date createDt = null;											// 新規登録日
		String createUserId = staff.getId();	// 新規登録ユーザー
		Date updateDt = null;											// 最終更新日
		String updateUserId = staff.getId();	// 最終更新ユーザー

		// マッチングIDは初期値-1。入力されたマッチングIDがあればIntegerに変換して代入
		if(request.getParameter("matchid") != null && !request.getParameter("matchid").equals("")) {
			matchId = Integer.parseInt(request.getParameter("matchid"));
		}

		// コメントオブジェクト作成
		Comment comment = new Comment(id,
										companyNo,
										kyujinNo,
										jobSeekerId,
										staffId,
										matchId,
										genre,
										important,
										title,
										note,
										createDt,
										createUserId,
										updateDt,
										updateUserId);

		CommentService commentService = new CommentService();
		// 入力チェック
		if(commentService.checkComment(comment)) {
			comment = commentService.insertComment(comment);
		}
		// trueならIDも返す、falseならIDは返さない
		request.setAttribute("comment", comment);
		request.setAttribute("messages", commentService.getMessages());
		request.setAttribute("Staff", staff);

		// JSPにフォワード
		request.getRequestDispatcher("/commentregist.jsp")
				.forward(request, response);

	}



}
