package jp.or.adash.nexus.servlets;

import java.io.IOException;
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
 * Servlet implementation class CommentDisplayServlet
 */
@WebServlet("/web/comment-disp")
public class CommentDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentDisplayServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// IDがあったら検索実行、なければ空のcommentオブジェクトを返す
		// TODO 内容分類選択への対応

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");


		Comment comment = null;


		if(request.getParameter("commentid") != null && !(request.getParameter("commentid").equals(""))) {
			int id = Integer.parseInt(request.getParameter("commentid"));
			CommentService commentService = new CommentService();
			comment = commentService.commentSearch2(id);
		}

		request.setAttribute("Staff", staff);
		request.setAttribute("comment", comment);
		request.getRequestDispatcher("/commentregist.jsp").forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
