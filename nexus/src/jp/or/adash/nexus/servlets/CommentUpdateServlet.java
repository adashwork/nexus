package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Staff;

/**
 * Servlet implementation class CommentUpdateServlet
 */
@WebServlet("/comment-update")
public class CommentUpdateServlet extends HttpServlet {
	 static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public CommentUpdateServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");

		 Integer id = -1;
		 String companyNo = request.getParameter("companyno");
		 String kyujinNo = request.getParameter("");
		 String jobSeekerId = request.getParameter("");
		 String staffId = request.getParameter("");
		 Integer matchId = request.getParameter("");
		 String genre = request.getParameter("");
		 String important = request.getParameter("");
		 String title = request.getParameter("");
		 String note = request.getParameter("");
		 Date updateDt;
		 String updateUserId;




	}

}
