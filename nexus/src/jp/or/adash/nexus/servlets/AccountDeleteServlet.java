package jp.or.adash.nexus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.AccountEditService;

/**
 * Servlet implementation class AccountDeleteServlet
 */
@WebServlet("/web/account-delete")
public class AccountDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");
		// 管理者以外の場合、staff-topに遷移
		if(!staff.getAuthority().equals("1")) {
			request.setAttribute("Staff", staff);
			//0	フォワードする
			request.getRequestDispatcher("/web/staff-top")
			.forward(request, response);

			return;
		}
		//0	リクエストからアカウントIDを取得する
		String id = request.getParameter("id");
		//0 アカウントを削除する。
		AccountEditService service = new AccountEditService();
		service.deleteStaff(id);
		//0 処理結果メッセージをリクエストに格納する
		request.setAttribute("id", id);
		request.setAttribute("messages", service.getMessages());
		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/accountdelete.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
