package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.MatchingCase;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.MatchingService;

/**
 * Servlet implementation class MaServlet
 */
@WebServlet("/web/matching-regist")
public class MatchingRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	* @see HttpServlet#HttpServlet()
	*/
	public MatchingRegistServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		MatchingCase matching = null;
		Comment comment = null;								// 追加 2018/12/14 T.Ikead
		MatchingService service = new MatchingService();

		//idが入力されていた場合、そのidのマッチング事例を表示する。
		if(request.getParameter("id") != null && request.getParameter("id") != "") {
			Integer id = Integer.parseInt(request.getParameter("id"));

			matching = service.getMatching(id);
			//処理結果メッセージをリクエストに格納する
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			//1.8 JSPにフォワード
			request.getRequestDispatcher("/matchingregist.jsp")
			.forward(request, response);
		}
		String companyNo = request.getParameter("companyNo");		// 追加・修正 2018/12/11.12 T.Ikeda
		String kyujinno = request.getParameter("kyujinno");
		String jobseekerid = request.getParameter("jobseekerid");
		String staffid = request.getParameter("staffid");
		Date interviewdt = null;
		try {
			interviewdt = sdf.parse(request.getParameter("interviewdt"));
		} catch (ParseException e) {
			interviewdt = null;
		}
		//		request.getParameter("interviewdt");
		Date enterdt = null;
		try {
			enterdt = sdf.parse(request.getParameter("enterdt"));
		} catch (ParseException e) {
			enterdt = null;
		}
		//		request.getParameter("enterdt");
		String assessment = request.getParameter("assessment");
		String note = request.getParameter("note");
		Date createdt = null;
		Date upDatedt = null;

		String createuserid = staff.getId();
		String upDateuserid = staff.getId();

		//1.2 マッチング結果オブジェクトを作成
		matching = new MatchingCase(0, companyNo, kyujinno, jobseekerid, staffid, interviewdt, enterdt, assessment, note,
				createdt,
				createuserid, upDatedt, upDateuserid);    		// 追加・修正 2018/12/11.12 T.Ikeda


		if (!service.check(matching)) {
			//入力チェックでエラーがあった場合、エラーメッセージをセット
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			//JSPにフォワード
			request.getRequestDispatcher("/matchingregist.jsp")
					.forward(request, response);

			return;
		}

		service.insertMatchingCase(matching, comment);

		//処理結果メッセージをリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("matching", matching);
		request.setAttribute("messages", service.getMessages());

		//1.8 JSPにフォワード
		request.getRequestDispatcher("/matchingregist.jsp")
		.forward(request, response);
	}

}
