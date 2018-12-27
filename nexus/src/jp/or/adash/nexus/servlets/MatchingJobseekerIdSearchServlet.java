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
 * マッチング登録画面：【求職者ID検索】に関するサーブレット
 * Servlet implementation class MatchingJobseekerIdSearchServlet
 * @author komukai 2018.12月作成
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

// 2018.12月 komukai　-ここから：求職者検索引用-

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.検索する求職者ID、求職者かな名、担当者氏名を取得する
		this.js_id = request.getParameter("js_id");
		this.js_kana = request.getParameter("js_kana");
		this.st_name = request.getParameter("st_name");

		// 2.求職者情報一覧を取得する
		JobSeekerService service = new JobSeekerService();
		List<Jobseeker_simple_entity> list = service.getJobSeeker(js_id, js_kana, st_name);

		// 3.担当紹介者氏名を取得する
		List<StaffName> st_name = service.getTantoStaff();

		// 4.求職者情報を初期化
		request.removeAttribute("list");

		// 5.求職者情報、担当紹介者氏名をリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("list", list);
		request.setAttribute("st_name", st_name);

// 2018.12月 komukai　-ここまで：求職者検索引用-

		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_jobseekerid_search.jsp").forward(request, response);
	}
}
