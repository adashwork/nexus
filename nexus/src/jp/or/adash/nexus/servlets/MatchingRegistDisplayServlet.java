package jp.or.adash.nexus.servlets;



import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.MatchingSearchResult;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.MatchingService;


/**
 * Servlet implementation class MatchingDisServlet
 */
@WebServlet("/web/matching-registdisp")
public class MatchingRegistDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchingRegistDisplayServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");
		MatchingService service = new MatchingService();
		MatchingSearchResult matching;
		Comment comment;

		//idが入力されていた場合、そのidのマッチング事例を表示する。
		if(request.getParameter("matchinginfo") != null &&  !"".equals(request.getParameter("matchinginfo"))) {
			int id = Integer.parseInt(request.getParameter("matchinginfo"));

			matching = service.getMatching(id);
			comment = new Comment(0, "", "","","",0,"", matching.getImportant(),
											matching.getTitle(), matching.getNote(), null, "",null, "");

			//処理結果メッセージをリクエストに格納する
			request.setAttribute("matching", matching);
			request.setAttribute("comment", comment);
		}

		// JSPにフォワードする
		request.getRequestDispatcher("/matchingregist.jsp").forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		doGet(request, response);
	}

}
