package jp.or.adash.nexus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara
 * @author pgjavaAT
 */
@WebServlet("/web/matching-id-search")
public class MatchingIdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String js_id;
	private String js_kana;
	private String st_name;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingIdSearchServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.検索する求職者ID、求職者かな名、担当者氏名を取得する
//		this.js_id = request.getParameter("js_id");
//		this.js_kana = request.getParameter("js_kana");
//		this.st_name = request.getParameter("st_name");

		// 2.求職者情報一覧を取得する
//		JobSeekerService service = new JobSeekerService();
//		List<Jobseeker_simple_entity> list = service.getJobSeeker(js_id, js_kana, st_name);

		// 3.担当紹介者氏名を取得する
//		List<StaffName> st_name = service.getTantoStaff();

		// 4.求職者情報を初期化
//		request.removeAttribute("list");

		// 5.求職者情報、担当紹介者氏名をリクエストに格納する
//		request.setAttribute("Staff", staff);
//		request.setAttribute("list", list);
//		request.setAttribute("st_name", st_name);

		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_id_search.jsp").forward(request, response);
	}

}
