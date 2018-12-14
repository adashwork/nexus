package jp.or.adash.nexus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CompanyService;

/**
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




		//ここでデータを受け取る

		//事業所番号の記入がなければ、独自の事業所番号を生成する
		String companyNo = request.getParameter("companyNo");





		String corporateNumber = request.getParameter("corporateNumber");
		String companyName = request.getParameter("companyName");
		String companyKana = request.getParameter("companyKana");
		String companyPostal = request.getParameter("companyPostal");
		String companyPlace = request.getParameter("companyPlace");
		String nearStation = request.getParameter("nearStation");
		String companyUrl = request.getParameter("companyUrl");
		String jobCategorySmallCd = request.getParameter("jobCategorySmallCd");
		String jobCategoryLargeCd = request.getParameter("jobCategoryLargeCd");
		Integer capital = Integer.parseInt(request.getParameter("capital"));
		String employees = request.getParameter("employees");
		Integer establishDt = Integer.parseInt(request.getParameter("establishDt"));
		String tantouYakushoku = request.getParameter("tantouYakushoku");
		String tantou = request.getParameter("tantou");
		String tantouKana = request.getParameter("tantouKana");
		String tantouTel = request.getParameter("tantouTel");
		String tantouFax = request.getParameter("tantouFax");
		String tantouEmail = request.getParameter("tantouEmail");
		String tantouNote = request.getParameter("tantouNote");
		String tantouStaffId = request.getParameter("tantouStaffId");
		String salesRank = request.getParameter("salesRank");
		String salesNote = request.getParameter("salesNote");
		/*	    Date createDt  =                      	request.getParameter("createDt");
				String createuserId  =                	request.getParameter("createuserId");
				Date updateDt  =                      	request.getParameter("updateDt");
				String updateUserId  =                	request.getParameter("updateUserId");
				String deletefFag  =                  	request.getParameter("deletefFag");*/

/*		Company company = new Company(companyNo, corporateNumber, companyName, companyKana, companyPostal, companyPlace,
				nearStation, companyUrl, jobCategorySmallCd, jobCategoryLargeCd, capital, employees, establishDt,
				tantouYakushoku, tantou, tantouKana, tantouTel, tantouFax, tantouEmail, tantouNote, tantouStaffId,
				salesRank, salesNote, createDt, createuserId, updateDt, updateUserId, deletefFag);
*/
		Company company = null;

		CompanyService companyService = new CompanyService();
		if (!companyService.check(company)) {
			//入力チェックでエラーがあった場合、エラーメッセージをセット
			request.setAttribute("company", company);
			request.setAttribute("messages", companyService.getMessages());

			//JSPにフォワード
			request.getRequestDispatcher("/companyregist.jsp")
					.forward(request, response);
			return;
		}

		//もし事業所番号が未記入なら、独自の事業所番号を発行する
		if("".equals(company.getCompanyNo())) {
			company.setCompanyNo(companyService.createUniqueCompanyNo());
		}

		//企業情報を登録する
		boolean registResult =  companyService.insertCompany(company);

		//処理結果メッセージをリクエストに格納する

		request.setAttribute("Company", company);
		request.setAttribute("messages", companyService.getMessages());

		// JSPにフォワード
		request.getRequestDispatcher("/companymaininfo.jsp")
				.forward(request, response);

	}
}
