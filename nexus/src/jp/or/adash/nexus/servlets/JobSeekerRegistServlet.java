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

import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.CommonsService;
import jp.or.adash.nexus.services.JobSeekerService;



/**
 * 求職者情報を登録するサーブレット
 * Servlet implementation class JobSeekerRegistServlet
 * @author Y.Okamura & T.Uchi
 */
@WebServlet("/web/jobseeker-regist")
public class JobSeekerRegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	 SimpleDateFormat sdf = new SimpleDateFormat();


    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobSeekerRegistServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		CommonsService cservice = new CommonsService();
		//入力された情報を登録する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String kana = request.getParameter("kana");
		Date birthdt = null;
		try {
			birthdt = sdf.parse(request.getParameter("birthdt"));
		} catch (ParseException e) {
			birthdt = null;
		}
		String sex = request.getParameter("sex");
		int age = -1;
		if (!"".equals(request.getParameter("age"))
				&& request.getParameter("age") != null) {
			age = Integer.parseInt(request.getParameter("age"));
		}
//		int age = Integer.parseInt(request.getParameter("age"));
		String postal = request.getParameter("postal");
		String address = request.getParameter("address");
		String nearstation = request.getParameter("nearstation");
		String phone = request.getParameter("phone");
		String mobile = request.getParameter("mobile");
		String partner = request.getParameter("partner");
		int huyou = -1;
		if (!"".equals(request.getParameter("huyou"))
				&& request.getParameter("huyou") != null) {
			huyou = Integer.parseInt(request.getParameter("huyou"));
		}
//		int huyou = Integer.parseInt(request.getParameter("huyou"));
		String education = request.getParameter("education");
		String hopejob1 = request.getParameter("hopejob1");
		String hopejob2 = request.getParameter("hopejob2");
		String hopejob3 = request.getParameter("hopejob3");
		String hopejobcategory = request.getParameter("hopejobcategory");
		String hopeworkplace = request.getParameter("hopeworkplace");
		String hopekoyoukeitai = request.getParameter("hopekoyoukeitai");
		Integer hopeworkingDate = null;
		if (!"".equals(request.getParameter("hopeworkingDate"))
				&& request.getParameter("hopeworkingDate") != null) {
			hopeworkingDate = Integer.parseInt(request.getParameter("hopeworkingDate"));
		}
//		int hopeworkingDate = Integer.parseInt(request.getParameter("hopeworkingdate"));
		Integer hopebegintime = null;
		if (!"".equals(request.getParameter("hopebegintime"))
				&& request.getParameter("hopebegintime") != null) {
			hopebegintime = Integer.parseInt(request.getParameter("hopebegintime"));
		}
//		int hopebegintime = Integer.parseInt(request.getParameter("hopebegintime"));
		Integer hopeendtime = null;
		if (!"".equals(request.getParameter("hopeendtime"))
				&& request.getParameter("hopeendtime") != null) {
			hopeendtime = Integer.parseInt(request.getParameter("hopeendtime"));
		}
//		int hopeendtime = Integer.parseInt(request.getParameter("hopeendtime"));
		Integer hopesalary = null;
		if (!"".equals(request.getParameter("hopesalary"))
				&& request.getParameter("hopesalary") != null) {
			hopesalary = Integer.parseInt(request.getParameter("hopesalary"));
		}
//		int hopesalary = Integer.parseInt(request.getParameter("hopesalary"));
		Integer hopejikyu = null;
		if (!"".equals(request.getParameter("hopejikyu"))
				&& request.getParameter("hopejikyu") != null) {
			hopejikyu = Integer.parseInt(request.getParameter("hopejikyu"));
		}
//		int hopejikyu = Integer.parseInt(request.getParameter("hopejikyu"));
		String hopeetc = request.getParameter("hopeetc");
		String driverlicense = request.getParameter("driverlicense");
		String licenseetc = request.getParameter("licensetc");
		String pasokonskill = request.getParameter("pasokonskill");
		String caution = request.getParameter("caution");
		String tantoustaffid = request.getParameter("tantoustaffid");
		String password = request.getParameter("password");
		Date createdt = null;
		String createuserid = staff.getId();
		Date upDatedt = null;
		String upDateuserid = staff.getId();
		String deleteflag = request.getParameter("deleteflag");

		// 担当職業紹介者IDから担当職業紹介者の名前を取得する
		String tantoustaffname = cservice.checkStaffName(tantoustaffid);

		//求人情報のオブジェクトを作成
		JobSeeker seeker = new JobSeeker(id, name, kana, birthdt, sex, age, postal,
				address,  nearstation, phone, mobile, partner, huyou,
				education, hopejob1, hopejob2, hopejob3, hopejobcategory,
				hopeworkplace, hopekoyoukeitai, hopeworkingDate, hopebegintime, hopeendtime,
				hopesalary, hopejikyu, hopeetc, driverlicense, licenseetc, pasokonskill,
				caution,  tantoustaffname, tantoustaffid,  password, createdt, createuserid,
				upDatedt,  upDateuserid,  deleteflag);


		//入力チェック
		JobSeekerService service = new JobSeekerService();
			if(!service.check(seeker)) {
				//入力チェックでエラーがあった場合、エラーメッセージをセット
				request.setAttribute("seeker", seeker);
				request.setAttribute("messages", service.getMessages());

				//JSPにフォワード
				request.getRequestDispatcher("/applicantregist.jsp")
					.forward(request, response);

				return;
			}

				//求人者idが使用されてない場合情報を登録する
				service.insertJobSeeker(seeker);

			//処理結果メッセージをリクエストに格納する

			request.setAttribute("Staff", staff);
			request.setAttribute("info", seeker);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/applicant_maininfo.jsp")
				.forward(request, response);
	}

}
