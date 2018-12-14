package jp.or.adash.nexus.servlets;

import java.io.IOException;

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
 * Servlet implementation class matchingTest
 */
@WebServlet("/web/matching-test")
public class MatchingTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MatchingTestServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		String id = null;

		if (!"".equals(request.getParameter("id"))
				&& request.getParameter("id") != null) {
			id = (request.getParameter("id"));
		}

		MatchingCase matching = null;
		if (id != null) {
			MatchingService service = new MatchingService();
			matching = service.getMatching(Integer.parseInt(id));
		}

		request.setAttribute("Staff", staff);
		request.setAttribute("matching", matching);

		// JSPにフォワードする
		request.getRequestDispatcher("/matching.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
	IOException {
		doGet(request, response);
	}

}
