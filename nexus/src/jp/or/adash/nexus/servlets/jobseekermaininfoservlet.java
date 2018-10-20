package jp.or.adash.nexus.servlets;
/* 求職者情報の詳細情報を求職者IDを元に表示するservlet
 *
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Job;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.JobSeekerService;
import jp.or.adash.nexus.services.JobService;
import jp.or.adash.nexus.services.TodouhukenService;

/**
 * 求職者の詳細を表示、編集するサーブレット
 * Servlet implementation class jobseekermaininfoservlet
 * @author Y.Okamura & T.Uchi
 */
@WebServlet("/web/jobseeker-info")
public class jobseekermaininfoservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String js_id;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public jobseekermaininfoservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		this.js_id = request.getParameter("js_id");
		// 1.求職者情報一覧を取得する
		JobSeekerService service = new JobSeekerService();
		JobSeekerMain js_info = service.getJobseekermaininfo(js_id);

		// 1.都道府県リストを取得する
		TodouhukenService Tservice = new TodouhukenService();
		List<Todouhuken> Tlist = Tservice.getTodouhukenList();
		// 2.都道府県リストをリクエストに格納する
		request.setAttribute("Todouhukenlist",Tlist);

		// 1.職種大分類リストを取得する
		JobService Lservice = new JobService();
		List<Job> Llist = Lservice.getLargeJobList();
		// 2.職種大分類リストをリクエストに格納する
		request.setAttribute("Largelist", Llist);

		// 1.業種大分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		// 2.業種大分類リストをリクエストに格納する
		request.setAttribute("JCLargelist", JCLlist);

		// 2.求職者情報をリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("info", js_info);
		// 3.JSPにフォワードする
		request.getRequestDispatcher("/applicant_maininfo.jsp").forward(request, response);
	}

}
