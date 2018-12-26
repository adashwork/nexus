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
import jp.or.adash.nexus.entity.CommentSearchParameter;
import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CommentService;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;

/**
 * 企業の詳細情報を表示するサーブレット
 * (検索結果などから企業ページを開く場合は、このサーブレットが呼ばれる)
 * @author mmiyamoto
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
		//存在しない企業番号なら新規登録ページリダイレクト
		if(company == null) {
			response.sendRedirect("/nexus/web/company-registdisp");
			return;
		}


		// 1.業種分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		List<JobCategory> JCMlist = JCLservice.getMiddleJobCategoryList(company.getJobCategoryLargeCd());
		List<JobCategory> JCSlist = JCLservice.getSmallJobCategoryList(company.getJobCategoryMiddleCd());

		//業種分類リストをリクエストに格納する
		request.setAttribute("JCLargeList", JCLlist);
		request.setAttribute("JCMiddleList", JCMlist);
		request.setAttribute("JCSmallList", JCSlist);

		//企業情報をリクエストに格納
		request.setAttribute("company", company);

		//コメントの取得
		CommentService commentService = new CommentService();
		CommentSearchParameter commentSearchParameter = new CommentSearchParameter(null, companyNo, null, null, null, null);
		List<Comment> commentList =  commentService.commentSearch(commentSearchParameter);
		//コメントリストをリクエストに格納する
		request.setAttribute("commentlist", commentList);



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
