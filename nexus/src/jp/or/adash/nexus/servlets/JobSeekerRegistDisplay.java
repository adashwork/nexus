package jp.or.adash.nexus.servlets;

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
import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.JobService;
import jp.or.adash.nexus.services.TodouhukenService;

/**
 * 求職者の登録画面を表示するサーブレット
 * Servlet implementation class JobSeekerRegistDisplay
 * @author Y.Okamura & T.Uchi
 */
@WebServlet("/web/jobseeker-disp")
public class JobSeekerRegistDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobSeekerRegistDisplay() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.1 リクエストから求職者情報を取得
		JobSeeker seeker = null;
		// 1.都道府県リストを取得する
		TodouhukenService Tservice = new TodouhukenService();
		List<Todouhuken> Tlist = Tservice.getTodouhukenList();
		// 2.都道府県リストをリクエストに格納する
		request.setAttribute("Todouhukenlist", Tlist);

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

		// 1.3 リクエストに求職者情報をセットする
		request.setAttribute("Staff", staff);
		request.setAttribute("seeker", seeker);

		// 1.4 JSPにフォワードする
		request.getRequestDispatcher("/applicantregist.jsp")
				.forward(request, response);
	}

}
