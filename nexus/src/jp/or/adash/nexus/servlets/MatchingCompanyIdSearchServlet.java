package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.CompanySearch;
import jp.or.adash.nexus.entity.CompanySearchResult;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.AccountListService;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara
 * @author pgjavaAT
 */
@WebServlet("/web/matching-companyid-search")
public class MatchingCompanyIdSearchServlet extends HttpServlet {

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingCompanyIdSearchServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションからログインスタッフ情報を取得
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");


		String companyName = request.getParameter("companyname");
		String staffId = request.getParameter("staffid");
		String jobCategory = request.getParameter("jobcategory");
		String companyPlace = request.getParameter("companyplace");

		// プルダウンで表示する「業種（産業大分類）」を呼び出す
		// optionのvalueに入れるのはコード、表示するのは業種名
		List<JobCategory> jobCategoryList = new ArrayList<JobCategory>();
		JobCategoryService jcs = new JobCategoryService();
		jobCategoryList = jcs.getLargeJobCategoryList();
		request.setAttribute("jobcategorylist", jobCategoryList);

		// プルダウンで表示する「担当者」を呼び出す
		// optionのvalueに入れるのはコード、表示するのは担当者名
		List<Staff> staffList = new ArrayList<Staff>();
		AccountListService als = new AccountListService();
		staffList = als.getAccountList();
		request.setAttribute("stafflist", staffList);


		// 入力された値をオブジェクトに詰める
		CompanySearch cse = new CompanySearch(companyName, staffId, jobCategory, companyPlace);

		// CompanyService呼び出し、返されたList<CompanySearchResult>をリクエストに格納してJSPへ
		CompanyService companyService = new CompanyService();
		List<CompanySearchResult> companyList = companyService.getCompanyList(cse);
		request.setAttribute("companylist", companyList);
		request.setAttribute("messages", companyService.getMessages());
		request.setAttribute("cse",cse);
		request.setAttribute("Staff", staff);

		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_companyid_search.jsp").forward(request, response);
	}

}
