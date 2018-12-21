package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.utils.common.DataCommons;

/**
 * 企業登録サーブレット
 * (※最初に登録ページを表示するサーブレッドではありません)
 * @author mmiyamoto
 * Servlet implementation class CompanyRegistServlet
 */
@WebServlet("/web/company-regist")
public class CompanyRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompanyRegistServlet() {
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

		//エラーが発生すればfalseにする
		boolean parameterGetError = true;
		//後でエラーメッセージにaddAllする
		List<String> parameterErrorMessage = new ArrayList<>();

		//フォームパラメーターの取得

		String companyNo = request.getParameter("companyno");

		String corporateNumber = request.getParameter("corporatenumber");
		String companyName = request.getParameter("companyname");
		String companyKana = request.getParameter("companykana");
		String companyPostal = request.getParameter("companypostal");
		String companyPlace = request.getParameter("companyplace");
		String nearStation = request.getParameter("nearstation");
		String companyUrl = request.getParameter("companyurl");
		String jobCategorySmallCd = request.getParameter("jobcategorysmallcd");
		String jobCategoryMiddleCd = request.getParameter("jobcategorymiddlecd");
		String jobCategoryLargeCd = request.getParameter("jobcategorylargecd");
		Integer capital = null;
		try {
			capital = DataCommons.parseInteger(request.getParameter("capital"));
		} catch (NumberFormatException e) {
			parameterGetError = false;
			parameterErrorMessage.add("資本金は半角数字で入力してください");
		}
		String employees = request.getParameter("employees");
		Integer establishDt = null;
		try {
			establishDt = DataCommons.parseInteger(request.getParameter("establishdt"));
		} catch (NumberFormatException e) {
			parameterGetError = false;
			parameterErrorMessage.add("創設年は半角数字4文字で入力してください");
		}
		String tantouYakushoku = request.getParameter("tantouyakushoku");
		String tantou = request.getParameter("tantou");
		String tantouKana = request.getParameter("tantoukana");
		String tantouTel = request.getParameter("tantoutel");
		String tantouFax = request.getParameter("tantoufax");
		String tantouEmail = request.getParameter("tantouemail");
		String tantouNote = request.getParameter("tantounote");
		String tantouStaffId = request.getParameter("tantoustaff_id");
		String salesRank = request.getParameter("salesrank");
		String salesNote = request.getParameter("salesnote");
		Date createDt = null;
		String createuserId = staff.getId();
		Date updateDt = null;
		String updateUserId = staff.getId();
		String deletefFag = "0";

		//企業オブジェクト生成
		Company company = new Company(companyNo, corporateNumber, companyName, companyKana, companyPostal, companyPlace,
				nearStation, companyUrl, jobCategorySmallCd, jobCategoryMiddleCd, jobCategoryLargeCd, capital,
				employees, establishDt,
				tantouYakushoku, tantou, tantouKana, tantouTel, tantouFax, tantouEmail, tantouNote, tantouStaffId,
				salesRank, salesNote, createDt, createuserId, updateDt, updateUserId, deletefFag);

		CompanyService companyService = new CompanyService();
		//エラーが発生しなかった場合のみ登録処理を行う
		if (companyService.check(company, true) && parameterGetError) {
			//企業情報を登録する
			boolean registResult = companyService.insertCompany(company);
		} else {
			//エラーが発生したら

			//新規登録中のパラメーターをリクエストに渡す
			request.setAttribute("status", "regist");
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

		//処理結果メッセージをリクエストに格納する

		request.setAttribute("company", company);
		request.setAttribute("staff", staff);
		parameterErrorMessage.addAll(companyService.getMessages());
		request.setAttribute("messages", parameterErrorMessage);

		// JSPにフォワード
		request.getRequestDispatcher("/companyregist.jsp")
				.forward(request, response);

	}

}
