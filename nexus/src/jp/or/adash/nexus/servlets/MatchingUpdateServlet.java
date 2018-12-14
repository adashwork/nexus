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
		String testid = request.getParameter("id");
		if (request.getParameter("no") != null) {
			id = Integer.parseInt(request.getParameter("no"));
		}
		String companyNo = request.getParameter("companyNo");		// 追加・修正 2018/12/11.12 T.Ikeda
		String kyujinno = request.getParameter("kyujinno");
		String jobseekerid = request.getParameter("jobseekerid");
		String staffid = request.getParameter("staffid");
		Date interviewdt = null;
		try {
			interviewdt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("interviewdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date enterdt = null;
		try {
			enterdt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("enterdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String assessment = request.getParameter("assessment");
		String note = request.getParameter("note");
		Date createdt = null;
		String createuserid = request.getParameter("createuserid");
		Date updatedt = null;
		String updateuserid = staff.getId();


		MatchingCase matching = new MatchingCase(id, companyNo, kyujinno, jobseekerid, staffid, interviewdt, enterdt,
				assessment, note, createdt, createuserid, updatedt, updateuserid);		// companyNo追加・修正 2018/12/11.12 T.Ikeda

		MatchingService service = new MatchingService();

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

		service.updateMatchingCase(matching);

		//処理結果メッセージをリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("matching", matching);
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
