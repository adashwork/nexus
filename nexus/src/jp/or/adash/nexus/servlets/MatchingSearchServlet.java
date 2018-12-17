package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.entity.MatchingSearchParameter;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.MatchingService;
import jp.or.adash.nexus.utils.common.StringCommons;

/**
 * Servlet implementation class MatchingSearchServlet
 */
@WebServlet("/web/matching-search")
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
		List<MatchingCase> matching = new ArrayList<MatchingCase>();
		MatchingService service = new MatchingService();
		int id = -1;

		//idが入力されていた場合、そのidのマッチング事例を表示する。
		// マッチングIDはnullでないか確認してintに変換する
			if(!"".equals(request.getParameter("matchingid"))
				&& request.getParameter("matchingid") != null) {
				id = Integer.parseInt(request.getParameter("matchingid"));
			}
			MatchingSearchParameter msp
				= new MatchingSearchParameter(id
											 ,request.getParameter("companyid")
											 ,request.getParameter("jobseekerid")
											 ,request.getParameter("staffid")
											 ,StringCommons.splitWords(request.getParameter("note"))
											 );


			matching = service.getMatchingV2(msp);

			//処理結果メッセージをリクエストに格納する
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/matchingsearch.jsp")
					.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);
	}

}
