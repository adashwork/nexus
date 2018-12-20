package jp.or.adash.nexus.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;

/**
 * Servlet implementation class JobSeekerSearchServlet
 * @author aihara
 * @author pgjavaAT
 */
@WebServlet("/web/matching-kyujinid-search")
public class MatchingKyujinIdSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String conpany_name;
	private String conpany_kana;
	private String st_name;


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingKyujinIdSearchServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.検索する企業名漢字、企業名かな、担当者氏名を取得する
		this.conpany_name = request.getParameter("conpany_name");
		this.conpany_kana = request.getParameter("conpany_kana");
		this.st_name = request.getParameter("st_name");


		// 6.JSPにフォワードする
		request.getRequestDispatcher("/matching_jobseekerid_search.jsp").forward(request, response);
	}

}
