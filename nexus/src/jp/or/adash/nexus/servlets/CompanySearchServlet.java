package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.CompanySearch;
import jp.or.adash.nexus.entity.CompanySearchResult;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.AccountListService;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;

/**
 * 企業検索画面のサーブレット
 * Servlet implementation class CompanySearch
 * @author mosco
 */
@WebServlet("/web/companysearch")
public class CompanySearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanySearchServlet() {
        super();
    }


	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttSpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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

		// フォワード
		request.getRequestDispatcher("/companysearch.jsp").forward(request, response);


	}
}
