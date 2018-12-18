package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.Date;
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
import jp.or.adash.nexus.utils.common.DataCommons;

/**
 * Servlet implementation class CompanyEditServlet
 */
@WebServlet("/web/company-edit")
public class CompanyEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompanyEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.業種大分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		// 2.業種大分類リストをリクエストに格納する
		request.setAttribute("JCLargelist", JCLlist);



		//事業所番号の記入がなければ、独自の事業所番号を生成する
		String companyNo = request.getParameter("companyno");

		String corporateNumber = request.getParameter("corporatenumber");
		String companyName = request.getParameter("companyname");
		String companyKana = request.getParameter("companykana");
		String companyPostal = request.getParameter("companypostal");
		String companyPlace = request.getParameter("companyplace");
		String nearStation = request.getParameter("nearstation");
		String companyUrl = request.getParameter("companyurl");
		String jobCategorySmallCd = request.getParameter("jobcategorysmallcd");
		String jobCategoryLargeCd = request.getParameter("jobcategorylargecd");
		Integer capital = DataCommons.parseInteger(request.getParameter("capital"));
		String employees = request.getParameter("employees");
		Integer establishDt = DataCommons.parseInteger(request.getParameter("establishdt"));
		String tantouYakushoku = request.getParameter("tantouyakushoku");
		String tantou = request.getParameter("tantou");
		String tantouKana = request.getParameter("tantoukana");
		String tantouTel = request.getParameter("tantoutel");
		String tantouFax = request.getParameter("tantoufax");
		String tantouEmail = request.getParameter("tantouemail");
		String tantouNote = request.getParameter("tantounote");
		String tantouStaffId = request.getParameter("tantoustaffid");
		String salesRank = request.getParameter("salesrank");
		String salesNote = request.getParameter("salesnote");
		Date createDt = null;
		//String createuserId = staff.getId();
		Date updateDt = null;
		String updateUserId = staff.getId();
		String deletefFag = "0";

		Company company = new Company(companyNo, corporateNumber, companyName, companyKana, companyPostal, companyPlace,
				nearStation, companyUrl, jobCategorySmallCd, jobCategoryLargeCd, capital, employees, establishDt,
				tantouYakushoku, tantou, tantouKana, tantouTel, tantouFax, tantouEmail, tantouNote, tantouStaffId,
				salesRank, salesNote, createDt, null, updateDt, updateUserId, deletefFag);

		CompanyService companyService = new CompanyService();

		//エラーが発生しなかった場合のみ登録処理を行う
		if (companyService.check(company, false)) {

			//もし事業所番号が未記入なら、独自の事業所番号を発行する
			if ("".equals(company.getCompanyNo())) {
				company.setCompanyNo(companyService.createUniqueCompanyNo());
			}

			//企業情報を更新する
			boolean registResult = companyService.updateCompany(company);
		}


		List<Comment> commentList = companyService.getCompanyCommentList(companyNo);


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
