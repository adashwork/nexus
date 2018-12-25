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
 * コメントを削除するサーブレット
 */
@WebServlet("/web/comment-delete")
public class CommentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// パラメータの取得
		Comment comment = null;
		Integer id = null;
		if(request.getParameter("commentid") != null && !request.getParameter("commentid").equals("")) {
			id = Integer.parseInt(request.getParameter("commentid"));
		}


		// コメントの削除
		CommentService service = new CommentService();
		boolean result = service.commentDelete(id);

		// 削除に成功した場合
		if(result) {
			// requsetにパラメータを格納
			request.setAttribute("Staff", staff);
			request.setAttribute("comment", comment);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/commentregist.jsp")
					.forward(request, response);

		}else {
		// 失敗した場合、削除対象だった行をオブジェクトで返す
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

			comment = new Comment(
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

			// requsetにパラメータを格納
			request.setAttribute("Staff", staff);
			request.setAttribute("comment", comment);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/commentregist.jsp")
					.forward(request, response);
		}

	}

}
