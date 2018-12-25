package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.CommentSearchParameter;
import jp.or.adash.nexus.services.CommentService;

/**
 * コメントの検索動作確認用の暫定サーブレット
 * 本来使用する際は、求職者・求人・企業のページからServiceのcommentSearchを呼び出してもらう
 */
@WebServlet("/web/comment-search")
public class CommentSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentSearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		CommentSearchParameter csp = new CommentSearchParameter(1,"","","0001","",-1);
		CommentService cs = new CommentService();
		List<Comment> commentList = new ArrayList<>();
		commentList = cs.commentSearch(csp);
		request.setAttribute("commentlist",commentList);
		request.getRequestDispatcher("/commentsearch.jsp").forward(request, response);
	}

}
