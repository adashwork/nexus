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

import jp.or.adash.nexus.entity.JobSeekerMain;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.JobSeekerService;

/**
 * 求職者情報を編集するサーブレット
 * Servlet implementation class JobSeekerEditServlet
 * @author Y.Okamura & T.Uchi
 */
@WebServlet("/web/jobseeker-edit")
public class JobSeekerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JobSeekerEditServlet() {
		super();
	}

	/**
	 * @throws IOException
	 * @throws ServletException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		//入力された情報を登録する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		//Date  birthdt= request.getParameter("birthdt");
		Date birthdt = null;
		try {
			birthdt = sdf.parse(request.getParameter("birthdt"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		String sex = request.getParameter("sex");
		/*int age = -1;
		if (!"".equals(request.getParameter("age"))
				&& request.getParameter("age") != null) {
			age = Integer.parseInt(request.getParameter("age"));
		}*/
		String zip21 = request.getParameter("zip21");
		String addr21 = request.getParameter("addr21");
		String seekermail = request.getParameter("seekermail");
		String nearstation = request.getParameter("nearstation");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		String partner = request.getParameter("partner");
		int huyou = -1;
		if (!"".equals(request.getParameter("huyou"))
				&& request.getParameter("huyou") != null) {
			huyou = Integer.parseInt(request.getParameter("huyou"));
		}
		String education = request.getParameter("education");
		String career = request.getParameter("career");
		String HOPEJOB1 = request.getParameter("HOPEJOB1");
		String HOPEJOB2 = request.getParameter("HOPEJOB2");
		String HOPEJOB3 = request.getParameter("HOPEJOB3");
		String HOPEJOBCATEGORY = request.getParameter("HOPEJOBCATEGORY");
		String HOPEJOBCATEGORY2 = request.getParameter("HOPEJOBCATEGORY2");
		String HOPEJOBCATEGORY3 = request.getParameter("HOPEJOBCATEGORY3");
		String hopeworkplace = request.getParameter("hopeworkplace");
		String hopekoyoukeitai = request.getParameter("hopekoyoukeitai");
		String hopeweekday = request.getParameter("hopeweekday");
		int hopeworkingdate = -1;
		if (!"".equals(request.getParameter("hopeworkingdate"))
				&& request.getParameter("hopeworkingdate") != null) {
			hopeworkingdate = Integer.parseInt(request.getParameter("hopeworkingdate"));
		}
		int hopebegintime = -1;
		if (!"".equals(request.getParameter("hopebegintime"))
				&& request.getParameter("hopebegintime") != null) {
			hopebegintime = Integer.parseInt(request.getParameter("hopebegintime"));
		}
		int hopeendtime = -1;
		if (!"".equals(request.getParameter("hopeendtime"))
				&& request.getParameter("hopeendtime") != null) {
			hopeendtime = Integer.parseInt(request.getParameter("hopeendtime"));
		}
		int hopesalary = -1;
		if (!"".equals(request.getParameter("hopesalary"))
				&& request.getParameter("hopesalary") != null) {
			hopesalary = Integer.parseInt(request.getParameter("hopesalary"));
		}
		int hopejikyu = -1;
		if (!"".equals(request.getParameter("hopejikyu"))
				&& request.getParameter("hopejikyu") != null) {
			hopejikyu = Integer.parseInt(request.getParameter("hopejikyu"));
		}
		String hopeetc = request.getParameter("hopeetc");
		String driverlicense = request.getParameter("driverlicense");
		String licenseetc = request.getParameter("licensetc");
		String pasokonskill = request.getParameter("pasokonskill");
		String caution = request.getParameter("caution");
		//String tantoustaffname = request.getParameter("tantoustaffname");
		String tantoustaffid = request.getParameter("tantoustaffid");
		//String password = request.getParameter("password");
		String password = null;
		String note = request.getParameter("note");
		Date createdt = null;
		String createuserid = request.getParameter("createuserid");
		Date updatedt = null;
		String updateuserid = request.getParameter("updateuerid");
		//String deleteflag = request.getParameter("deleteflag");
		String deleteflag = null;

		//求人情報のオブジェクトを作成
		JobSeekerMain seeker = new JobSeekerMain(id, name, kana, sex, birthdt, zip21,
				addr21, seekermail, nearstation, phone, mobile, partner, huyou,
				education, career, HOPEJOB1, HOPEJOB2, HOPEJOB3, HOPEJOBCATEGORY, HOPEJOBCATEGORY2, HOPEJOBCATEGORY3,
				hopeworkplace, hopekoyoukeitai, hopeweekday, hopeworkingdate, hopebegintime, hopeendtime,
				hopesalary, hopejikyu, hopeetc, driverlicense, licenseetc, pasokonskill,
				caution, tantoustaffid, password, note, createdt, createuserid,
				updatedt, updateuserid, deleteflag);

		//入力チェック
		JobSeekerService service = new JobSeekerService();
		if (!service.check(seeker)) {
			//入力チェックでエラーがあった場合、エラーメッセージをセット
			request.setAttribute("info", seeker);
			request.setAttribute("messages", service.getMessages());

			//JSPにフォワード
			request.getRequestDispatcher("/applicant_maininfo.jsp") //ここにjspを入力
					.forward(request, response);

			return;
		}

		//求人者idが使用されてない場合情報を登録する
		service.updateJobSeeker(seeker);

		//処理結果メッセージをリクエストに格納する
		request.setAttribute("Staff", staff);
		request.setAttribute("info", seeker);
		request.setAttribute("messages", service.getMessages());

		// 1.8 JSPにフォワード
		request.getRequestDispatcher("/applicant_maininfo.jsp") //ここにjspを入力
				.forward(request, response);
	}

}
