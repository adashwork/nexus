package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.StaffService;

/**
 * Servlet implementation class CompanyMainInfoServlet
 */
@WebServlet("/web/company-info")
public class CompanyMainInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyMainInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		String companyNo = request.getParameter("companyno");
		CompanyService companyService = new CompanyService();
		Company company = companyService.getCompanyInfo(companyNo);

		// 1.業種分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		List<JobCategory> JCMlist = JCLservice.getMiddleJobCategoryList(company.getJobCategoryLargeCd());
		List<JobCategory> JCSlist = JCLservice.getSmallJobCategoryList(company.getJobCategoryMiddleCd());

		//業種分類リストをリクエストに格納する
		request.setAttribute("JCLargeList", JCLlist);
		request.setAttribute("JCMiddleList", JCMlist);
		request.setAttribute("JCSmallList", JCSlist);

		//コメントリストに存在するスタッフidを列挙したMapを生成してリクエストに格納する
		List<Comment> commentList = companyService.getCompanyCommentList(companyNo);
		StaffService staffService = new StaffService();
		Map<String, String> staffMap = staffService.getCommentStaffIdMap(commentList);
		request.setAttribute("commentlist", commentList);


		//スタッフIDと名前がセットになったMapをJSPに埋め込む
		Map<String, String> staffNameMap = staffService.getStaffNameMap(staffMap);
		request.setAttribute("staffNameMap", staffNameMap);

		request.setAttribute("company", company);


		request.setAttribute("Staff", staff);
		request.getRequestDispatcher("/companyregist.jsp")
				.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
