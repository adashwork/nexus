package jp.or.adash.nexus.kyujin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.kyujin.services.KyujinService;


/**
 * Servlet implementation class KyujinUpdateServlet
 */
@WebServlet("/KyujinDeleteServlet")
public class KyujinDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyujinDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.1 リクエストから値を取得する
		//		int deleteflag =
		//				Integer.parseInt(request.getParameter("deleteflag"));
		String no = request.getParameter("no");
		String hiddenDeleteflag = request.getParameter("hiddenDeleteflag");

		KyujinService service = new KyujinService();

		// 1.6　求人票を更新する
		service.deleteKyujin(no, hiddenDeleteflag);

		// 処理結果メッセージをリクエストに格納する
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/jobregist.jsp")
				.forward(request, response);
	}

}
