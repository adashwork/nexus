package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Job;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.JobService;
import jp.or.adash.nexus.services.KyujinService;
import jp.or.adash.nexus.services.TodouhukenService;

/**
 * @author kmiyamoto 2018-12-14～
 * Servlet implementation class KyujinInsertServlet
 */
@WebServlet("/web/kyujin-insert")
public class KyujinInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyujinInsertServlet() {
		super();
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
		String companyno = request.getParameter("companyno");
		String postal = request.getParameter("postal");
		String address = request.getParameter("address");
		String nearline = request.getParameter("nearline");
		String nearstationkj = request.getParameter("nearstationkj");
		String addresscd = request.getParameter("addresscd");
		String jobsmallcd1 = request.getParameter("jobsmallcd1");
		String jobsmallcd2 = request.getParameter("jobsmallcd2");
		String jobsmallcd3 = request.getParameter("jobsmallcd3");
		String joblargecd1 = request.getParameter("joblargecd1");
		String joblargecd2 = request.getParameter("joblargecd2");
		String joblargecd3 = request.getParameter("joblargecd3");
		//		String jobcategorysmallcd = request.getParameter("jobcategorysmallcd");
		//		String jobcategorylargecd = request.getParameter("jobcategorylargecd");
		//		String companykana = request.getParameter("companykana");
		//		String companyname = request.getParameter("companyname");
		//		String companypostal = request.getParameter("companypostal");
		//		String companyplace = request.getParameter("companyplace");
		//		String companyurl = request.getParameter("companyurl");
		String job = request.getParameter("job");
		String detail = request.getParameter("detail");
		String koyoukeitaicd = request.getParameter("koyoukeitaicd");
		String hakencd = request.getParameter("hakencd");
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

		// 空データでparseIntをするとエラーになるので、空データ以外はチェック。空データーは0をセット
		int agemin = 0;
		if (!request.getParameter("agemin").equals("")) {
			agemin = Integer.parseInt(request.getParameter("agemin"));
		}
		int agemax = 0;
		if (!request.getParameter("agemax").equals("")) {
			agemax = Integer.parseInt(request.getParameter("agemax"));
		}
		String salaryformcd = request.getParameter("salaryformcd");
		int salarymin = 0;
		if (!request.getParameter("salarymin").equals("")) {
			salarymin = Integer.parseInt(request.getParameter("salarymin"));
		}
		int salarymax = 0;
		if (!request.getParameter("salarymax").equals("")) {
			salarymax = Integer.parseInt(request.getParameter("salarymax"));
		}
		String bonus = request.getParameter("bonus");
		String koutuhi = request.getParameter("koutuhi");
		String teate = request.getParameter("teate");
		int begintime = 0;
		if (!request.getParameter("begintime").equals("")) {
			begintime = Integer.parseInt(request.getParameter("begintime"));
		}
		int endtime = 0;
		if (!request.getParameter("endtime").equals("")) {
			endtime = Integer.parseInt(request.getParameter("endtime"));
		}
		String shift = request.getParameter("shift");
		String flex = request.getParameter("flex");
		String jitan = request.getParameter("jitan");
		int jikangai = 0;
		if (!request.getParameter("jikangai").equals("")) {
			jikangai = Integer.parseInt(request.getParameter("jikangai"));
		}
		int siyoukikan = 0;
		if (!request.getParameter("siyoukikan").equals("")) {
			siyoukikan = Integer.parseInt(request.getParameter("siyoukikan"));
		}
		int workdays = 0;
		if (!request.getParameter("workdays").equals("")) {
			workdays = Integer.parseInt(request.getParameter("workdays"));
		}
		String nenkanholiday = request.getParameter("nenkanholiday");

		//		int establishdt = 0;
		//		if (!request.getParameter("establishdt").equals("")) {
		//		    establishdt = Integer.parseInt(request.getParameter("establishdt"));}
		//		long capital = 0;
		//		if (!request.getParameter("capital").equals("")) {
		//		    capital = Long.parseLong(request.getParameter("capital"));}
		//		String employees = request.getParameter("employees");
		//		String companyfeature = request.getParameter("companyfeature");
		//		String tantouyakushoku = request.getParameter("tantouyakushoku");
		//		String tantoukana = request.getParameter("tantoukana");
		//		String tantou = request.getParameter("tantou");
		//		String tantoustaff_id = request.getParameter("tantoustaff_id");
		String applicationform = request.getParameter("applicationform");
		String background = request.getParameter("background");
		String bosyunumbers = request.getParameter("bosyunumbers");
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
				no, companyno, postal, address, nearline, nearstationkj, addresscd,
				jobsmallcd1, jobsmallcd2, jobsmallcd3, joblargecd1, joblargecd2, joblargecd3,
				job, detail, koyoukeitaicd, hakencd, koyoukikan, koyoukikankaishi, koyoukikanowari,
				education, experience, license, agemin, agemax, salaryformcd, salarymin, salarymax,
				bonus, koutuhi, teate, begintime, endtime, shift, flex, jitan, jikangai, siyoukikan,
				workdays, nenkanholiday, applicationform, background, bosyunumbers, hiddensex,
				hiddenagemin, hiddenagemax, hiddenetc, receptiondt, perioddt,
				createdt, createuserid, updatedt, updateuserid, deleteflag);

		// 1.都道府県リストを取得する
		TodouhukenService TDFKservice = new TodouhukenService();
		List<Todouhuken> list = TDFKservice.getTodouhukenList();
		// 2.都道府県リストをリクエストに格納する
		request.setAttribute("list",list);
		request.setAttribute("Staff", staff);

		// 1.業種大分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();
		// 2.業種大分類リストをリクエストに格納する
		request.setAttribute("JCLargelist", JCLlist);


		// 1.業種小分類リストを取得する
		JobCategoryService JCSservice = new JobCategoryService();
		List<JobCategory> JCSlist = JCSservice.getSmallJobCategoryList();
		// 2.業種小分類リストをリクエストに格納する
		request.setAttribute("JCSmalllist", JCSlist);

		// 1.職種大分類リストを取得する
		JobService Lservice = new JobService();
		List<Job> Llist = Lservice.getLargeJobList();
		// 2.職種大分類リストをリクエストに格納する
		request.setAttribute("Largelist", Llist);

		// 1.職種小分類リストを取得する
		JobService Sservice = new JobService();
		List<Job> Slist = Sservice.getSmallJobList();
		// 2.職種小分類リストをリクエストに格納する
		request.setAttribute("Smalllist", Slist);

		// 1.3 入力チェック
		KyujinService service = new KyujinService();
		if (!service.check(kyujin)) {
			// 1.4 入力チェックでエラーがあった場合、エラーメッセージをセット
			kyujin.setNo(null);
			request.setAttribute("kyujin", kyujin);
			request.setAttribute("messages", service.getMessages());

			// 1.5 JSPにフォワード
			request.getRequestDispatcher("/jobregist.jsp")
					.forward(request, response);

			return;
		}

		// 1.6 求人票を更新する
		service.insertKyujin(kyujin);

		// 処理結果メッセージをリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("kyujin", kyujin);
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/jobregist.jsp")
				.forward(request, response);

		// companyテーブルの内容を引き込むのに使うかも
		//		CompanyService companyService = new CompanyService();
		//		Company company = companyService.getCompanyInfo(companyNo);
		//		request.setAttribute("company", company);

	}

}
