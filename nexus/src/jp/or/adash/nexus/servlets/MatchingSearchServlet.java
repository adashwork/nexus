package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.MatchingService;

/**
 * Servlet implementation class MatchingSearchServlet
 */
@WebServlet("/web/match-search")
public class MatchingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MatchingCase matching = null;
		MatchingService service = new MatchingService();

		//idが入力されていた場合、そのidのマッチング事例を表示する。
		if (request.getParameter("no") != null) {
			int id = Integer.parseInt(request.getParameter("no"));

			matching = service.getMatching(id);
			//処理結果メッセージをリクエストに格納する
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/matchingregist.jsp")
					.forward(request, response);
		} else {
			//idが入力されていなかったら初期表示
			//処理結果メッセージをリクエストに格納する
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/matchingregist.jsp")
					.forward(request, response);
		}
	}

}
