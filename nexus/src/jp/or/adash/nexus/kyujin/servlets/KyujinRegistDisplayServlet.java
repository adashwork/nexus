package jp.or.adash.nexus.kyujin.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.or.adash.nexus.entity.Kyujin;
import jp.or.adash.nexus.kyujin.services.KyujinService;



/**
 * 求人登録画面初期表示サーブレット
 */
@WebServlet("/detail")
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
		// 1.1 リクエストから求人コードを取得
		String no = "12457-44446666"; //仮の値
		if (!"".equals(request.getParameter("no"))
				&& request.getParameter("no") != null) {
			no = (request.getParameter("no"));
		}

		// 1.2 求人コードがある場合、商品情報を取得
		Kyujin kyujin = null;
		if (no != null) {
			KyujinService service = new KyujinService();
			kyujin = service.getKyujin(no);
		}

		// 1.3 リクエストに求人情報をセットする
		request.setAttribute("kyujin", kyujin);

		// 1.4 JSPにフォワードする
		request.getRequestDispatcher("/jobregist.jsp")
			.forward(request, response);
	}

}
