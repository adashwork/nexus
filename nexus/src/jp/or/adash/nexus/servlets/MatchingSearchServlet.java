package jp.or.adash.nexus.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jp.or.adash.nexus.entity.Comment;
import jp.or.adash.nexus.entity.MatchingSearchParameter;
import jp.or.adash.nexus.entity.MatchingSearchResult;
import jp.or.adash.nexus.entity.Staff;
import jp.or.adash.nexus.services.MatchingService;
import jp.or.adash.nexus.utils.common.StringCommons;

/**
 * マッチング事例を検索するサーブレット
 */
@WebServlet("/web/matching-search")
public class MatchingSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MatchingSearchServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(true);
		Staff staff = (Staff) session.getAttribute("UserData");
		List<MatchingSearchResult> matching = new ArrayList<MatchingSearchResult>();
		MatchingService service = new MatchingService();
		int id = -1;

		//idが入力されていた場合、そのidのマッチング事例を表示する。
		// マッチングIDはnullでないか確認してintに変換する
		if(!"".equals(request.getParameter("matchingid"))
			&& request.getParameter("matchingid") != null) {
			id = Integer.parseInt(request.getParameter("matchingid"));
		}
		MatchingSearchParameter msp
			= new MatchingSearchParameter(id
										 ,request.getParameter("companyno")
										 ,request.getParameter("jobseekerid")
										 ,request.getParameter("staffid")
										 ,StringCommons.splitWords(request.getParameter("note"))
										 );


		matching = service.getMatchingV2(msp);

		// 2018/12/25 kitayama 検索結果の数に応じた分岐を追加

		// 検索結果が1行なら詳細ページに飛ぶ
		if(matching.size() == 1) {
			// 編集画面jsp側がコメントオブジェクトを受け取るようになっているので
			// コメントオブジェクトを生成する
			// TODO JSP側の受け取り方を変更して検索結果オブジェクトだけを渡せばいいようにする
			Comment comment = new Comment(null
										, null
										, null
										, null
										, null
										, null
										, null
										, matching.get(0).getImportant()
										, matching.get(0).getTitle()
										, matching.get(0).getNote()
										, null
										, null
										, null
										, null
											);

			request.setAttribute("Staff", staff);
			request.setAttribute("comment", comment);
			request.setAttribute("matching", matching.get(0));
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/matchingregist.jsp")
					.forward(request, response);
		}else {
		// 検索結果が1行以外なら一覧ページに移動する

		// 処理結果メッセージをリクエストに格納する
			request.setAttribute("Staff", staff);
			request.setAttribute("matching", matching);
			request.setAttribute("messages", service.getMessages());

			// JSPにフォワード
			request.getRequestDispatcher("/matchingsearch.jsp")
					.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		doGet(request, response);
	}

}
