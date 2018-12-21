package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.services.CommentService;

/**
 * Servlet implementation class CommentRegistServlet
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

		// 入力されたパラメータ等の取得
		Integer id = 0;											// 備考ID
		String companyNo = request.getParameter("");			// 事業所番号
		String kyujinNo = request.getParameter("");			// 求人NO
		String jobSeekerId = request.getParameter("");			// 求職者ID
		String staffId = request.getParameter("");				// 職業紹介者ID
		Integer matchId = -1;								// マッチング事例ID
		String genre = request.getParameter("");				// 内容分類
		String important = request.getParameter("");			// 重要アラート
		String title = request.getParameter("");				// 件名
		String note = request.getParameter("");					// 備考
		Date createDt = null;									// 新規登録日
		String createUserId = request.getParameter("");		// 新規登録ユーザー
		Date updateDt = null;									// 最終更新日
		String updateUserId = request.getParameter("");		// 最終更新ユーザー

		if(request.getParameter("マッチングID") != null && !request.getParameter("マッチングID").equals("")) {
			matchId = Integer.parseInt(request.getParameter("マッチングID"));
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

		// JSPにフォワード
		request.getRequestDispatcher("/companyregist.jsp")
				.forward(request, response);

	}

}
