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
import jp.or.adash.nexus.services.JobSeekerService;
import jp.or.adash.nexus.services.StaffService;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara tanaka
 * 追記　入力した検索条件が検索後も残るように修正
 * @author pgjavaAT
 */
@WebServlet("/web/jobseeker-list")
public class JobSeekerSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobSeekerSearchServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");


		// 1.検索する求職者ID、求職者かな名、担当者IDを取得する
		String js_id = request.getParameter("js_id");
		String js_kana = request.getParameter("js_kana");
		String stId = request.getParameter("stId");



		// 2.求職者情報一覧を取得する
		JobSeekerService service = new JobSeekerService();
		List<Jobseeker_simple_entity> list = service.getJobSeeker(js_id, js_kana, stId);

		// 3.担当紹介者リストを取得する
		StaffService staffService = new StaffService();
		List<Staff> staffList = staffService.getStaffList();

		// 4.求職者情報を初期化
		request.removeAttribute("list");


		// 5.求職者情報、担当紹介者氏名をリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("list", list);
		request.setAttribute("staffList", staffList);

//		request.setAttribute("js_id", js_id);
//		request.setAttribute("js_kana", js_kana);



		// 6.JSPにフォワードする
		request.getRequestDispatcher("/applicant_list.jsp").forward(request, response);





	}

}
