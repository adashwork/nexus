
package jp.or.adash.nexus.servlets;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.Jobseeker_simple_entity;
import jp.or.adash.nexus.services.JobSeekerService;

/**
 * Servlet implementation class JobSeekerServlet
 */
@WebServlet("/jobseekerservlet")
public class JobSeekerListShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobSeekerListShowServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1.求職者情報一覧を取得する
		JobSeekerService service = new JobSeekerService();
		List<Jobseeker_simple_entity> list = service.getJobSeekerList();

		// 2.求職者情報をリクエストに格納する
		request.setAttribute("list", list);
		// 3.JSPにフォワードする
		request.getRequestDispatcher("/applicant_list.jsp").forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
