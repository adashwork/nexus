package jp.or.adash.nexus.kyujin.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.kyujin.services.KyujinService;


/**
 * Servlet implementation class KyujinInsertServlet
 */
@WebServlet("/KyujinInsertServlet")
public class KyujinInsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public KyujinInsertServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1.1 リクエストから値を取得する
		String no = request.getParameter("no");
		//		String hiddenno = request.getParameter("hiddenno");
		Date receptiondt = null;
		try {
			receptiondt = (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("receptiondt"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Date perioddt = null;
		try {
			perioddt = (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("perioddt"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
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
			koyoukikankaishi = (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("koyoukikankaishi"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		Date koyoukikanowari = null;
		try {
			koyoukikanowari = (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("koyoukikanowari"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String education = request.getParameter("education");
		String experience = request.getParameter("experience");
		String license = request.getParameter("license");
		int agemin = Integer.parseInt(request.getParameter("agemin"));
		int agemax = Integer.parseInt(request.getParameter("agemax"));
		int salarymin = Integer.parseInt(request.getParameter("salarymin"));
		int salarymax = Integer.parseInt(request.getParameter("salarymax"));
		String salaryformcd = request.getParameter("salaryformcd");
		int begintime = Integer.parseInt(request.getParameter("begintime"));
		int endtime = Integer.parseInt(request.getParameter("endtime"));
		int establishdt = Integer.parseInt(request.getParameter("establishdt"));
		long capital = Long.parseLong(request.getParameter("capital"));
		String companyfeature = request.getParameter("companyfeature");
		String tantouyakushoku = request.getParameter("tantouyakushoku");
		String tantoukana = request.getParameter("tantoukana");
		String tantou = request.getParameter("tantou");
		String tantoustaff_id = request.getParameter("tantoustaff_id");
		String applicationform = request.getParameter("applicationform");
		String background = request.getParameter("background");
		String hiddensex = request.getParameter("hiddensex");
		int hiddenagemin = Integer.parseInt(request.getParameter("hiddenagemin"));
		int hiddenagemax = Integer.parseInt(request.getParameter("hiddenagemax"));
		String hiddenetc = request.getParameter("hiddenetc");
		Date createdt= null;
		try {
			createdt= (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("koyoukikanowari"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String createuserid = request.getParameter("createuserid");
		Date upDatedt = null;
		try {
			upDatedt = (Date) (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")).parse(request.getParameter("upDatedt"));
		} catch (ParseException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		String upDateuserid = request.getParameter("upDateuserid");
		String deleteflag = request.getParameter("deleteflag");

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
				createuserid, upDatedt, upDateuserid, deleteflag);

		// 1.3 入力チェック
		KyujinService service = new KyujinService();
		if (!service.check(kyujin)) {
			// 1.4 入力チェックでエラーがあった場合、エラーメッセージをセット
			request.setAttribute("kyujin", kyujin);
			request.setAttribute("messages", service.getMessages());

			// 1.5 JSPにフォワード
			request.getRequestDispatcher("/jobregist.jsp")
					.forward(request, response);

			return;
		}

		// 1.6　求人票を更新する
		service.insertKyujin(kyujin);

		// 処理結果メッセージをリクエストに格納する
		request.setAttribute("kyujin", kyujin);
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/jobregist.jsp")
				.forward(request, response);
	}

}
