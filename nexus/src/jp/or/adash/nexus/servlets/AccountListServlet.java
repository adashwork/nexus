package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.AccountListService;

/**
 * Servlet implementation class AccountListServlet
 */
@WebServlet("/web/account-list")
public class AccountListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountListServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		// 1.アカウント情報一覧を取得する
		AccountListService service = new AccountListService();
		List<Staff> list = service.getAccountList();
		// 2.アカウント情報をリクエストに格納する
		request.setAttribute("accounts", list);
		request.setAttribute("Staff", staff);

		// 3.JSPにフォワードする
		request.getRequestDispatcher("/account.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
