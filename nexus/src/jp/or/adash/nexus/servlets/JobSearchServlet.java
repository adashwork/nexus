package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.SimpleKyujin;
import jp.or.adash.nexus.services.JobSearchService;

/**
 * Servlet implementation class KyujinServlet
 */
@WebServlet("/jobsearch")
public class JobSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobSearchServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.1 リクエストから職種名を取得
		String job = null;
		job = request.getParameter("job");
		// 1.2 リクエストから業種名を取得
		String jobcategory = null;
		jobcategory = request.getParameter("jobcategory");
		// 1.3　就業場所コードを取得
		String addresscd = null;
			addresscd = request.getParameter("addresscd");
		// 1.4基本給下限を取得
		int salarymin = -1;
		if (!"".equals(request.getParameter("salarymin"))
				&& request.getParameter("salarymin") != null) {
			salarymin = Integer.parseInt(request.getParameter("salarymin"));
		}
		// 1.5 基本給上限を取得
		int salarymax = -1;
		if (!"".equals(request.getParameter("salarymax"))
				&& request.getParameter("salarymax") != null) {
			salarymax = Integer.parseInt(request.getParameter("salarymax"));
		}
		// 1.11 雇用形態コードを取得
		String koyoukeitaicd = null;
		koyoukeitaicd = request.getParameter("koyoukeitaicd");

		// 2.求人票の取得
		List<SimpleKyujin> kyujinlist = new ArrayList<SimpleKyujin>();
			JobSearchService service = new JobSearchService();
			kyujinlist = service.getKyujin(job, jobcategory,addresscd,
					salarymin, salarymax ,koyoukeitaicd);

		// 1.3 リクエストに求人票情報をセットする
		request.setAttribute("kyujin", kyujinlist);

		// 1.4 JSPにフォワードする
		request.getRequestDispatcher("/jobsearch.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
