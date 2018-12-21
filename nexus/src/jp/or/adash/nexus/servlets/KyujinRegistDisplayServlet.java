package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Company;
import jp.or.adash.nexus.entity.Job;
import jp.or.adash.nexus.entity.JobCategory;
import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.entity.Todouhuken;
import jp.or.adash.nexus.services.CompanyService;
import jp.or.adash.nexus.services.JobCategoryService;
import jp.or.adash.nexus.services.JobService;
import jp.or.adash.nexus.services.KyujinService;
import jp.or.adash.nexus.services.TodouhukenService;



/**
 * 求人登録画面初期表示サーブレット
 */
@WebServlet("/web/kyujin-disp")
public class KyujinRegistDisplayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public KyujinRegistDisplayServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		// 1.1 リクエストから求人コードを取得
		String no = null;

		if (!"".equals(request.getParameter("no"))
				&& request.getParameter("no") != null) {
			no = (request.getParameter("no"));
		}

		// 1.2 求人コードがある場合、商品情報を取得
		Kyujin kyujin = null;
		Company company = null;
		if (no != null) {
			KyujinService service = new KyujinService();
			kyujin = service.getKyujin(no);
			//求人に事業所番号が登録されている場合、企業情報を取得する
			if(!"".equals(kyujin.getCompanyno()) && kyujin.getCompanyno() != null){
				CompanyService companyService = new CompanyService();
				company = companyService.getCompanyInfo(kyujin.getCompanyno());
			}
		}

		// 1.3 リクエストに求人情報をセットする
		request.setAttribute("kyujin", kyujin);
		// 企業情報をセットする
		request.setAttribute("company", company);

		// 1.都道府県リストを取得する
		TodouhukenService service = new TodouhukenService();
		List<Todouhuken> list = service.getTodouhukenList();
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


		// 1.4 JSPにフォワードする
		request.getRequestDispatcher("/jobregist.jsp")
			.forward(request, response);


	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
