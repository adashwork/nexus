package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.StaffName;
import jp.or.adash.nexus.services.JobSeekerService;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara
 * @author pgjavaAT
 */
@WebServlet("/web/matching-jobseekerid-search")
public class MatchingJobseekerIdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String js_id;
	private String js_kana;
	private String st_name;
	private String js_name;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingJobseekerIdSearchServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.検索する求職者名（漢字）、求職者名（かな）、担当者氏名を取得する
		this.js_name = request.getParameter("js_name");
		this.js_kana = request.getParameter("js_kana");
		this.st_name = request.getParameter("st_name");

		// 2.求職者情報一覧を取得する（求職者ID、求職者名前（漢字・かな））
		JobSeekerService service = new JobSeekerService();
		List<Jobseeker_simple_entity> list = service.getJobSeeker(js_id, js_kana, st_name);

		// 3.担当紹介者氏名を取得する
		List<StaffName> staffs = service.getTantoStaff();

		// 5.求職者情報、担当紹介者氏名をリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("list", list);
		request.setAttribute("staffs", staffs);

		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_jobseekerid_search.jsp").forward(request, response);
	}

}
