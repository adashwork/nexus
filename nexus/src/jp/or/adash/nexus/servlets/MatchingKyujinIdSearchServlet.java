package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.SimpleKyujin;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.JobSearchService;
import jp.or.adash.nexus.services.TodouhukenService;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara
 * @author pgjavaAT
 */
@WebServlet("/web/matching-kyujinid-search")
public class MatchingKyujinIdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String conpany_name;
	private String conpany_kana;
	private String st_name;



	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.1 リクエストから職種を取得
		String job = null;
		job = request.getParameter("job");

		// 1.2 職業小分類コード1を取得
		String jobsmallcd1 = null;
		jobsmallcd1 = request.getParameter("jobsmallcd1");

		// 1.3  職業小分類コード2を取得
		String jobsmallcd2 = null;
		jobsmallcd2 = request.getParameter("jobsmallcd2");

		// 1.4  職業小分類コード3を取得
		String jobsmallcd3 = null;
		jobsmallcd3 = request.getParameter("jobsmallcd3");

		// 1.5 職業大分類コード1を取得
		String joblargecd1 = null;
		joblargecd1 = request.getParameter("joblargecd1");

		// 1.6 職業大分類コード2を取得
		String joblargecd2 = null;
		joblargecd2 = request.getParameter("joblargecd2");

		// 1.7 職業大分類コード3を取得
		String joblargecd3 = null;
		joblargecd3 = request.getParameter("joblargecd3");
		// 1.7 職業大分類コード3を取得
		String jobcategorysmallcd = null;
		jobcategorysmallcd = request.getParameter("jobcategorysmallcd");
		// 1.7 職業大分類コード3を取得
		String jobcategorylargecd = null;
		jobcategorylargecd = request.getParameter("jobcategorylargecd");
		// 1.7 職業大分類コード3を取得
		String jobcategory = null;
		jobcategory = request.getParameter("jobcategory");


		// 1.11 雇用形態コードを取得
		String koyoukeitaicd = null;
		koyoukeitaicd = request.getParameter("koyoukeitaicd");
		// 1.8基本給下限を取得
		int salarymin = 0;
		if (!"".equals(request.getParameter("salarymin"))
				&& request.getParameter("salarymin") != null) {
			salarymin = Integer.parseInt(request.getParameter("salarymin"));
		}
		// 1.9 基本給上限を取得
		int salarymax = 0;
		if (!"".equals(request.getParameter("salarymax"))
				&& request.getParameter("salarymax") != null) {
			salarymax = Integer.parseInt(request.getParameter("salarymax"));
		}

		// 1.10　就業場所コードを取得
		String addresscd = null;
		addresscd = request.getParameter("addresscd");


		// 2.求人票の取得

		List<SimpleKyujin> kyujinlist = new ArrayList<SimpleKyujin>();
		JobSearchService service = new JobSearchService();
		kyujinlist = service.getKyujin(job, addresscd, jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2,
				joblargecd3,
				jobcategorysmallcd,jobcategorylargecd,
				koyoukeitaicd,salarymin, salarymax);

		//企業情報リストを取得する
		Map<String, Company> companyMap = service.getCompanyMap(kyujinlist);


		// 1.都道府県リストを取得する
		TodouhukenService todouhukenservice = new TodouhukenService();
		List<Todouhuken> todouhukenlist = todouhukenservice.getTodouhukenList();




		// 1.3 リクエストに求人票情報をセットする

		request.setAttribute("Staff", staff);
		request.setAttribute("kyujinlist", kyujinlist);
		request.setAttribute("companyMap", companyMap);
		request.setAttribute("todouhukenlist",todouhukenlist);

		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_kyujinid_search.jsp").forward(request, response);
	}

}
