package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;

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

/**
 * Servlet implementation class CompanyDeleteServlet
 */
@WebServlet("/web/company-delete")
public class CompanyDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyDeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		String companyNo = request.getParameter("companyno");

		CompanyService companyService = new CompanyService();

		// 1.業種分類リストを取得してリクエストに格納する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		request.setAttribute("JCLargeList", JCLlist);

		//削除に成功したら新規登録画面を表示
		if (companyService.delteCompany(companyNo)) {
			request.setAttribute("messages", companyService.getMessages());
			// JSPにフォワード
			request.getRequestDispatcher("/companyregist.jsp")
					.forward(request, response);
			return;
		}

		//ここから下は失敗した場合
		Company company = companyService.getCompanyInfo(companyNo);
		List<Comment> commentList = companyService.getCompanyCommentList(companyNo);

		//中分類と小分類のリストを取得する
		List<JobCategory> JCMlist = JCLservice.getMiddleJobCategoryList(company.getJobCategoryLargeCd());
		List<JobCategory> JCSlist = JCLservice.getSmallJobCategoryList(company.getJobCategoryMiddleCd());

		//業種分類リストをリクエストに格納する

		request.setAttribute("JCMiddleList", JCMlist);
		request.setAttribute("JCSmallList", JCSlist);

		//処理結果メッセージをリクエストに格納する

		request.setAttribute("company", company);
		request.setAttribute("commentlist", commentList);
		request.setAttribute("staff", staff);
		request.setAttribute("messages", companyService.getMessages());

		// JSPにフォワード
		request.getRequestDispatcher("/companyregist.jsp")
				.forward(request, response);
	}

}
