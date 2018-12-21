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

		String companyNo = request.getParameter("companyNo");		// 追加・修正 2018/12/11.12 T.Ikeda
		String kyujinNo = request.getParameter("kyujinno");
		String jobSeekerId = request.getParameter("jobseekerid");
		String staffId = request.getParameter("staffid");
		Date interviewDt = null;
		try {
			interviewDt = sdf.parse(request.getParameter("interviewdt"));
		} catch (ParseException e) {
			interviewDt = null;
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
		String noteM = "";
		Date createDt = null;
		Date updateDt = null;

		String createUserId = staff.getId();
		String updateUserId = staff.getId();
		Integer matchId = 0;									// 追加 2018/12/18 T.Ikeda
		String genre = "4";	 // 1:求職者情報　2:企業情報　3:求人情報　4:マッチング情報    追加,修正 2018/12/17,18 T.Ikeda
		String important = request.getParameter("important");	// 追加,修正 2018/12/17,18 T.Ikeda
		if (important == null) {
			important = "0";
		}
		String title = request.getParameter("title");			// 追加,修正 2018/12/17,18 T.Ikeda

		//1.2 マッチング結果オブジェクトを作成
		matching = new MatchingCase(0, companyNo, kyujinNo, jobSeekerId, staffId, interviewDt, enterdt, assessment,
				noteM, createDt, createUserId, updateDt, updateUserId);    		// 追加・修正 2018/12/11.12 T.Ikeda
		// マッチングコメントオブジェクトを作成                 // 追加 2018/12/14 T.Ikeda
		comment = new Comment(0, companyNo, kyujinNo, jobSeekerId, staffId, matchId,
				genre, important, title, note, createDt, createUserId,
				updateDt, updateUserId);

		if (!service.check(matching)) {
			//入力チェックでエラーがあった場合、エラーメッセージをセット
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("comment", comment);
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
		request.setAttribute("comment", comment);
		request.setAttribute("messages", service.getMessages());

		//1.8 JSPにフォワード
		request.getRequestDispatcher("/matchingregist.jsp")
		.forward(request, response);
	}

}
