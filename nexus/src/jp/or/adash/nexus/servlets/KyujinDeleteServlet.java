package jp.or.adash.nexus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.KyujinService;

/**
 * Servlet implementation class KyujinUpdateServlet
 */
@WebServlet("/web/kyujin-delete")
public class KyujinDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyujinDeleteServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.1 リクエストから値を取得する
		//		int deleteflag =
		//				Integer.parseInt(request.getParameter("deleteflag"));
		String no = request.getParameter("no"); // 求人No（Primary Key）
		//		String hiddenDeleteflag = request.getParameter("hiddenDeleteflag");

		KyujinService service = new KyujinService();

		// 1.6 求人票を更新する
		service.deleteKyujin(no, staff.getId());

		// 処理結果メッセージをリクエストに格納する

		request.setAttribute("Staff", staff);
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/jobregist.jsp")
				.forward(request, response);
	}
}
