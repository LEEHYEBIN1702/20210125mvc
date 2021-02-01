package co.micol.mvc.board.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.micol.mvc.board.dao.BoardsDAO;
import co.micol.mvc.board.service.BoardVO;

@WebServlet("/BorderRowSelect.do")
public class BorderRowSelect extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public BorderRowSelect() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		BoardsDAO dao =new BoardsDAO();
		BoardVO vo = new BoardVO();
		int row = Integer.parseInt(request.getParameter("row"));
		vo.setBoardNo(row);
		
		vo = dao.select(vo); //db 호출
		request.setAttribute("vo", vo);
		String viewPage="views/board/boardSelect.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
