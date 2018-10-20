package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.KyujinService;

/**
 * Servlet implementation class KyujinUpdateServlet
 */
@WebServlet("/web/kyujin-update")
public class KyujinUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyujinUpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.1 リクエストから値を取得する

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		String no = request.getParameter("no");
		//		String hiddenno = request.getParameter("hiddenno");
		Date receptiondt = null;
		try {
			receptiondt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("receptiondt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		Date perioddt = null;
		try {
			perioddt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("perioddt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String companyno = request.getParameter("companyno");
		String addresscd = request.getParameter("addresscd");
		String jobsmallcd1 = request.getParameter("jobsmallcd１");
		String jobsmallcd2 = request.getParameter("jobsmallcd2");
		String jobsmallcd3 = request.getParameter("jobsmallcd3");
		String joblargecd1 = request.getParameter("joblargecd１");
		String joblargecd2 = request.getParameter("joblargecd2");
		String joblargecd3 = request.getParameter("joblargecd3");
		String jobcategorysmallcd = request.getParameter("jobcategorysmallcd");
		String jobcategorylargecd = request.getParameter("jobcategorylargecd");
		String companykana = request.getParameter("companykana");
		String companyname = request.getParameter("companyname");
		String companypostal = request.getParameter("companypostal");
		String companyplace = request.getParameter("companyplace");
		String companyurl = request.getParameter("companyurl");
		String postal = request.getParameter("postal");
		String address = request.getParameter("address");
		String nearstation = request.getParameter("nearstation");
		String job = request.getParameter("job");
		String hakencd = request.getParameter("hakencd");
		String detail = request.getParameter("detail");
		String koyoukeitaicd = request.getParameter("koyoukeitaicd");
		String koyoukikan = request.getParameter("koyoukikan");

		Date koyoukikankaishi = null;
		try {
			koyoukikankaishi = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("koyoukikankaishi"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date koyoukikanowari = null;
		try {
			koyoukikanowari = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("koyoukikanowari"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String education = request.getParameter("education");
		String experience = request.getParameter("experience");
		String license = request.getParameter("license");
		// 空データでparseIntをするとエラーになるので、空データ以外はチェック。空データは０をセット
		int agemin = 0;
		if (!request.getParameter("agemin").equals("")) {
			agemin = Integer.parseInt(request.getParameter("agemin"));
		}
		int agemax = 0;
		if (!request.getParameter("agemax").equals("")) {
			agemax = Integer.parseInt(request.getParameter("agemax"));
		}
		int salarymin = 0;
		if (!request.getParameter("salarymin").equals("")) {
			salarymin = Integer.parseInt(request.getParameter("salarymin"));
		}
		int salarymax = 0;
		if (!request.getParameter("salarymax").equals("")) {
			salarymax = Integer.parseInt(request.getParameter("salarymax"));
		}
		String salaryformcd = request.getParameter("salaryformcd");
		int begintime = 0;
		if (!request.getParameter("begintime").equals("")) {
			begintime = Integer.parseInt(request.getParameter("begintime"));
		}
		int endtime = 0;
		if (!request.getParameter("endtime").equals("")) {
			endtime = Integer.parseInt(request.getParameter("endtime"));
		}
		int establishdt = 0;
		if (!request.getParameter("establishdt").equals("")) {
			establishdt = Integer.parseInt(request.getParameter("establishdt"));
		}
		long capital = 0;
		if (!request.getParameter("capital").equals("")) {
			capital = Long.parseLong(request.getParameter("capital"));
		}
		String companyfeature = request.getParameter("companyfeature");
		String tantouyakushoku = request.getParameter("tantouyakushoku");
		String tantoukana = request.getParameter("tantoukana");
		String tantou = request.getParameter("tantou");
		String tantoustaff_id = request.getParameter("tantoustaff_id");
		String applicationform = request.getParameter("applicationform");
		String background = request.getParameter("background");
		String hiddensex = request.getParameter("hiddensex");
		int hiddenagemin = 0;
		if (!request.getParameter("hiddenagemin").equals("")) {
			hiddenagemin = Integer.parseInt(request.getParameter("hiddenagemin"));
		}
		int hiddenagemax = 0;
		if (!request.getParameter("hiddenagemax").equals("")) {
			hiddenagemax = Integer.parseInt(request.getParameter("hiddenagemax"));
		}
		String hiddenetc = request.getParameter("hiddenetc");

		Date createdt = null;
		try {
			createdt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("createdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String createuserid = request.getParameter("createuserid");

		Date updatedt = null;
		try {
			updatedt = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("updatedt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		String updateuserid = staff.getId();

		String deleteflag = "0";

		// 1.2求人票オブジェクトを作成

		Kyujin kyujin = new Kyujin(
				no, receptiondt, perioddt, companyno, addresscd, jobsmallcd1,
				jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,
				jobcategorysmallcd, jobcategorylargecd, companykana,
				companyname, companypostal, companyplace, companyurl, postal,
				address, nearstation, job, hakencd, detail, koyoukeitaicd,
				koyoukikan, koyoukikankaishi, koyoukikanowari, education, experience,
				license, agemin, agemax, salarymin, salarymax, salaryformcd, begintime,
				endtime, establishdt, capital, companyfeature, tantouyakushoku,
				tantoukana, tantou, tantoustaff_id, applicationform, background,
				hiddensex, hiddenagemin, hiddenagemax, hiddenetc, createdt,
				createuserid, updatedt, updateuserid, deleteflag);
		// 1.3 入力チェック
		KyujinService service = new KyujinService();
		if (!service.check(kyujin)) {
			// 1.4 入力チェックでエラーがあった場合、エラーメッセージをセット
			//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//			kyujin.setReceptiondt(sdf.format(kyujin.receptiondt()));
			request.setAttribute("kyujin", kyujin);
			request.setAttribute("messages", service.getMessages());

			// 1.5 JSPにフォワード
			request.getRequestDispatcher("/jobregist.jsp")
					.forward(request, response);

			return;
		}

		// 1.6　求人票を更新する

		service.updateKyujin(kyujin);

		// 処理結果メッセージをリクエストに格納する

		request.setAttribute("Staff", staff);
		// TODO 18/09/30 コメントアウトを解除
		request.setAttribute("kyujin", kyujin);
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/jobregist.jsp")
				.forward(request, response);
	}

	public java.util.Date convertToUtilDate(java.sql.Date sqlDate) {
		return sqlDate;
	}
}
