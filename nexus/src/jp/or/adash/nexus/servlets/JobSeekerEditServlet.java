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

import jp.or.adash.nexus.entity.JobSeeker;
import jp.or.adash.nexus.services.JobSeekerService;

/**
 * Servlet implementation class JobSeekerEditServlet
 */
@WebServlet("/jobseeker/edit")
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//入力された情報を登録する
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String kana = request.getParameter("kana");
				Date birthdt = null;
				try {
					birthdt = sdf.parse(request.getParameter("birthdt"));
				} catch (ParseException e) {
					e.printStackTrace();
				}
				String sex = request.getParameter("sex");
				int age = -1;
				if (!"".equals(request.getParameter("age"))
						&& request.getParameter("age") != null) {
					age = Integer.parseInt(request.getParameter("age"));
				}
				String postal = request.getParameter("postal");
				String address = request.getParameter("address");
				String nearstation = request.getParameter("nearstation");
				String phone = request.getParameter("phone");
				String mobile = request.getParameter("mobile");
				String partner = request.getParameter("partner");
				int huyou = -1;
				if (!"".equals(request.getParameter("huyou"))
						&& request.getParameter("huyou") != null) {
					age = Integer.parseInt(request.getParameter("huyou"));
				}
				String education = request.getParameter("education");
				String hopejob1 = request.getParameter("hopejob1");
				String hopejob2 = request.getParameter("hopejob2");
				String hopejob3 = request.getParameter("hopejob3");
				String hopejobcategory = request.getParameter("hopejobcategory");
				String hopeworkplace = request.getParameter("hopeworkplace");
				String hopekoyoukeitai = request.getParameter("hopekoyoukeitai");
				int hopeworkingDate = -1;
				if (!"".equals(request.getParameter("hopeworkingDate"))
						&& request.getParameter("hopeworkingDate") != null) {
					age = Integer.parseInt(request.getParameter("hopeworkingDate"));
				}
				int hopebegintime = -1;
				if (!"".equals(request.getParameter("hopebegintime"))
						&& request.getParameter("hopebegintime") != null) {
					age = Integer.parseInt(request.getParameter("hopebegintime"));
				}
				int hopeendtime = -1;
				if (!"".equals(request.getParameter("hopeendtime"))
						&& request.getParameter("hopeendtime") != null) {
					age = Integer.parseInt(request.getParameter("hopeendtime"));
				}
				int hopesalary = -1;
				if (!"".equals(request.getParameter("hopesalary"))
						&& request.getParameter("hopesalary") != null) {
					age = Integer.parseInt(request.getParameter("hopesalary"));
				}
				int hopejikyu = -1;
				if (!"".equals(request.getParameter("hopejikyu"))
						&& request.getParameter("hopejikyu") != null) {
					age = Integer.parseInt(request.getParameter("hopejikyu"));
				}
				String hopeetc = request.getParameter("hopeetc");
				String driverlicense = request.getParameter("driverlicense");
				String licenseetc = request.getParameter("licensetc");
				String pasokonskill = request.getParameter("pasokonskill");
				String caution = request.getParameter("caution");
				String tantoustaffid = request.getParameter("tantoustaffid");
				String password = request.getParameter("password");
				Date createdt = null;
				String createuserid = request.getParameter("createuserid");

				Date upDatedt = null;
				String upDateuserid = request.getParameter("upDateuerid");
				String deleteflag = request.getParameter("deleteflag");


			//求人情報のオブジェクトを作成
			JobSeeker seeker = new JobSeeker(id, name, kana, birthdt, sex, age, postal,
					address,  nearstation, phone, mobile, partner, huyou,
					education, hopejob1, hopejob2, hopejob3, hopejobcategory,
					hopeworkplace, hopekoyoukeitai, hopeworkingDate, hopebegintime, hopeendtime,
					hopesalary, hopejikyu, hopeetc, driverlicense, licenseetc, pasokonskill,
					caution,  tantoustaffid,  password, createdt, createuserid,
					upDatedt,  upDateuserid,  deleteflag);


			//入力チェック
			JobSeekerService service = new JobSeekerService();
				if(!service.check(seeker)) {
					//入力チェックでエラーがあった場合、エラーメッセージをセット
					request.setAttribute("seeker", seeker);
					request.setAttribute("messages", service.getMessages());

					//JSPにフォワード
					request.getRequestDispatcher("/applicantedit.jsp") //ここにjspを入力
						.forward(request, response);

					return;
				}


					//求人者idが使用されてない場合情報を登録する
					service.updateJobSeeker(seeker);


				//処理結果メッセージをリクエストに格納する
				request.setAttribute("seeker", seeker);
				request.setAttribute("messages", service.getMessages());

				// 1.8 JSPにフォワード
				request.getRequestDispatcher("/applicantedit.jsp") //ここにjspを入力
					.forward(request, response);
		}

}
