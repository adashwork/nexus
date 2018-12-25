package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.JobCategoryService;

/**
 * 企業の新規登録画面を表示するサーブレット
 * @author mmiyamoto
 * Servlet implementation class CompanyRegistDisplayServlet
 */
@WebServlet("/web/company-registdisp")
public class CompanyRegistDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyRegistDisplayServlet() {
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


		// 1.業種分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();

		//業種分類リストをリクエストに格納する
		request.setAttribute("JCLargeList", JCLlist);


		//新規登録中か編集中か
		request.setAttribute("status", "regist");



		request.setAttribute("Staff", staff);
		request.getRequestDispatcher("/companyregist.jsp")
				.forward(request, response);

	}

}
