/**
 * アカウント登録用
 * @author A.Ishida
 */

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
import jp.or.adash.nexus.services.AccountService;



/**
 * Servlet implementation class AccountRegistServlets
 */
@WebServlet("/web/account-regist")
public class AccountRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	//SimpleDateFormat sdf = new SimpleDateFormat();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountRegistServlet() {
        super();
    }


    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

		/**
		 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
		 */
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			//0セッション　Objectはいろんな型を入れておける　ユーザーIdとユーザー名
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
			//入力された情報を登録する
			String name = request.getParameter("name");
			String kana = request.getParameter("kana");
			String authority = request.getParameter("kengen");
			String password = request.getParameter("password");



			//staffのオブジェクトにデータをセット ログインした人と、今から登録する人２人分のStaffオブジェクトが必要
			Staff acStaff = new Staff(null, name, kana, authority, password,
				null, staff.getId(), null, staff.getId(), "0");

			//serviceのregistAccountにstaffを渡す
			AccountService accountservice = new AccountService();
			//0		エラーチェック
					AccountEditService service = new AccountEditService();
					if (!service.errorsCheck(acStaff)) {

						//0	アカウント情報をセット
						request.setAttribute("Staff",acStaff);
						request.setAttribute("messages", service.getMessages());


						//0	JSPにフォワード
						 request.getRequestDispatcher("/accountregist.jsp").forward(request, response);
						 //ここにjspを入力
						return;
					}
			boolean result = accountservice.registAccount(acStaff);



			//0登録が完了したら、accountregistcompletion.jspへ飛ぶ
			if(result == true) {
				request.setAttribute("Staff", acStaff);
				request.getRequestDispatcher("/accountregistcompletion.jsp").forward(request, response);

			//0データベースアクセスに失敗した場合
			}else {
				request.setAttribute("Staff", acStaff);
				request.setAttribute("messages", accountservice.getMessages());
				request.getRequestDispatcher("/accountregist.jsp").forward(request, response);
			}
		}
	}


