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
 * Servlet implementation class AccountEditDisplayServlet
 */
@WebServlet("/web/account-editcomp")
public class AccountEditCompletionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountEditCompletionServlet() {
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
		//0		アカウント更新情報を取得
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		String authority = request.getParameter("authority");
		String password = request.getParameter("password");



		//0		アカウント情報のオブジェクトを作成
		Staff acStaff = new Staff(id, name, kana, authority, password,
				null, null, null, null, null);



		//0		エラーチェック
		AccountEditService service = new AccountEditService();
		if (!service.errorsCheck(acStaff)) {
			acStaff = service.getStaffAccount(id);
			//0	アカウント情報をセット
			request.setAttribute("Staff", acStaff);
			request.setAttribute("messages", service.getMessages());

			//0	JSPにフォワード
			 request.getRequestDispatcher("/accountedit.jsp").forward(request, response);
			 //ここにjspを入力
			return;
		}




		//0		アカウント情報を更新する。
		service.updateStaff(staff);
		//	0.アカウント情報をセット
		request.setAttribute("Staff", staff);
		request.setAttribute("messages", service.getMessages());
		//0	JSPにフォワードする
		request.getRequestDispatcher("/accounteditcompletion.jsp").forward(request, response);

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
