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
import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.CommonsService;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.JobSeekerService;
import jp.or.adash.nexus.services.JobService;
import jp.or.adash.nexus.services.StaffService;
import jp.or.adash.nexus.services.TodouhukenService;



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

/*		//エラーが発生すればfalseにする
		boolean parameterGetError = true;
		//後でエラーメッセージにaddAllする
		List<String> parameterErrorMessage = new ArrayList<>();*/

		CommonsService service = new CommonsService();
		//入力された情報を登録する
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		//フォームパラメーターの取得
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
		/*int age = -1;
		if (!"".equals(request.getParameter("age"))
				&& request.getParameter("age") != null) {
			age = Integer.parseInt(request.getParameter("age"));
		}*/
//		int age = Integer.parseInt(request.getParameter("age"));

		String zip21 = request.getParameter("zip21");
		String zip22 = request.getParameter("zip22");
		String postal = zip21 +"-"+ zip22;
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
//		int huyou = Integer.parseInt(request.getParameter("huyou"));
		String education = request.getParameter("education");
		String career = request.getParameter("career");
		String hopeJob1 = request.getParameter("hopJob1");
		String hopeJob2 = request.getParameter("hopJob2");
		String hopeJob3 = request.getParameter("hopJob3");
		String hopeJobCategory1 = request.getParameter("hopeJobCategory1");
		String hopeJobCategory2 = request.getParameter("hopeJobCategory2");
		String hopeJobCategory3 = request.getParameter("hopeJobCategory3");
		String hopeworkplace = request.getParameter("hopeworkplace");
		String hopekoyoukeitai = request.getParameter("hopekoyoukeitai");
		String hopeweekday = request.getParameter("hopeweekday");
		Integer hopeworkingdate = null;
		/*		if (!"".equals(request.getParameter("hopeworkingdate"))
						&& request.getParameter("hopeworkingdate") != null) {
					hopeworkingdate = Integer.parseInt(request.getParameter("hopeworkingdate"));
				}
		*/
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
		String password = null;
		String note = request.getParameter("note");
		Date createdt = null;
		String createuserid = staff.getId();
		Date updatedt = null;
		String updateuserid = staff.getId();
		String deleteflag = "0";



		// 担当職業紹介者IDから担当職業紹介者の名前を取得する
		//String tantoustaffname = cservice.checkStaffName(tantoustaffid);

		//求人情報のオブジェクトを作成
		JobSeeker seeker = new JobSeeker(id, name, kana, birthdt, sex, zip21,zip22,postal,
				addr21, seekermail,nearstation, phone, mobile, partner, huyou,
				education,career, hopeJob1, hopeJob2, hopeJob3, hopeJobCategory1, hopeJobCategory2, hopeJobCategory3,
				hopeworkplace, hopekoyoukeitai, hopeweekday, hopeworkingdate, hopebegintime, hopeendtime,
				hopesalary, hopejikyu, hopeetc, driverlicense, licenseetc, pasokonskill,
				caution,  tantoustaffid, password, note, createdt, createuserid,
				updatedt, updateuserid, deleteflag);

		//都道府県一覧を取得する。
		TodouhukenService Todfuservice = new TodouhukenService();
		List<Todouhuken> TodfuList= Todfuservice.getTodouhukenList();

		// 職種大分類リストを取得する
		JobService Lservice = new JobService();
		List<Job> JobLlist = Lservice.getLargeJobList();


		// 業種大分類リストを取得する
		JobCategoryService JCLservice = new JobCategoryService();
		List<JobCategory> JCLlist = JCLservice.getLargeJobCategoryList();



		//担当紹介者リストを取得する
		StaffService staffService = new StaffService();
		List<Staff> staffList = staffService.getStaffList();

		//入力チェック
		JobSeekerService jobseekerService = new JobSeekerService();
			if(!jobseekerService.check(seeker)) {
				//入力チェックでエラーがあった場合、エラーメッセージをセット
				request.setAttribute("info", seeker);
				request.setAttribute("messages", jobseekerService.getMessages());
				request.setAttribute("staffList", staffList);
				request.setAttribute("Todouhukenlist", TodfuList);
				request.setAttribute("Largelist", JobLlist);
				request.setAttribute("JCLargelist", JCLlist);
				//JSPにフォワード
				request.getRequestDispatcher("/applicantregist.jsp")
					.forward(request, response);

				return;
			}

				//求人者idが使用されてない場合情報を登録する
				jobseekerService.insertJobSeeker(seeker);




			//処理結果メッセージをリクエストに格納する

			request.setAttribute("Staff", staff);
			request.setAttribute("info", seeker);
			request.setAttribute("staffList", staffList);
			request.setAttribute("messages", jobseekerService.getMessages());
			request.setAttribute("Todouhukenlist", TodfuList);
			request.setAttribute("Largelist", JobLlist);
			request.setAttribute("JCLargelist", JCLlist);

			//parameterErrorMessage.addAll(jobseekerService.getMessages());
			//request.setAttribute("messages", parameterErrorMessage);

			// JSPにフォワード   使用していない：/applicant_maininfo.jsp
			request.getRequestDispatcher("/applicantregist.jsp")
				.forward(request, response);

	}

}
