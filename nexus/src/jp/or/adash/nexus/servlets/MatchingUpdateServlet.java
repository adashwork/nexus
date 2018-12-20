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
 * マッチング情報更新のためのサーブレット
 * @author pgjavaAT
 */
@WebServlet("/web/matching-update")
public class MatchingUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingUpdateServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		int id = 0;
//		String testid = request.getParameter("nohidden");           2018/12/20 T.Ikeda
		if (request.getParameter("nohidden") != null) {
			id = Integer.parseInt(request.getParameter("nohidden"));
		}
		String companyNo = request.getParameter("companyNo");		// 追加・修正 2018/12/11.12 T.Ikeda
		String kyujinNo = request.getParameter("kyujinno");
		String jobseekerId = request.getParameter("jobseekerid");
		String staffId = request.getParameter("staffid");
		Date interviewDt = null;

		Comment comment = null;									// 追加 2018/12/11 T.Ikeda

		try {
			interviewDt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("interviewdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date enterDt = null;
		try {
			enterDt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enterdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String assessment = request.getParameter("assessment");
		String note = request.getParameter("note");
		Date createDt = null;
		String createUserId = request.getParameter("createuserid");
		Date updateDt = null;
		String updateUserId = staff.getId();
		Integer matchId = id;									// 追加 2018/12/20 T.Ikeda
		String genre = "4";	 // 1:求職者情報　2:企業情報　3:求人情報　4:マッチング情報    追加 2018/12/20 T.Ikeda
		String important = request.getParameter("important");	// 追加,修正 2018/12/20 T.Ikeda
		String title = request.getParameter("title");			// 追加,修正 2018/12/20 T.Ikeda


		//1.2 マッチング結果オブジェクトを作成
		MatchingCase matching = new MatchingCase(id, companyNo, kyujinNo, jobseekerId, staffId, interviewDt, enterDt,
				assessment, note, createDt, createUserId, updateDt, updateUserId);		// companyNo追加・修正 2018/12/11.12 T.Ikeda

		// マッチングコメントオブジェクトを作成                 // 追加 2018/12/20 T.Ikeda
		comment = new Comment(0, companyNo, kyujinNo, jobseekerId, staffId, matchId,
				genre, important, title, note, createDt, createUserId,
				updateDt, updateUserId);

		MatchingService service = new MatchingService();

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

		service.updateMatchingCase(matching, comment);

		//処理結果メッセージをリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("matching", matching);
		request.setAttribute("comment", comment);
		request.setAttribute("messages", service.getMessages());

		//1.8 JSPにフォワード
		request.getRequestDispatcher("/matchingregist.jsp")
		.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
