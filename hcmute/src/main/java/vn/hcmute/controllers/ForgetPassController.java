package vn.hcmute.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;
import vn.hcmute.services.impl.UserServiceImpl;

@WebServlet(urlPatterns = { "/forgetpass" })
public class ForgetPassController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2593330160741034805L;
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/forgetpass.jsp").forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");

		String username = req.getParameter("username");
		String password1 = req.getParameter("password1");
		String password2 = req.getParameter("password2");
		String password = password1;
		if (!password1.equals(password2)) {
			String alertMsg = "Vui lòng nhập lại mật khẩu";
			req.setAttribute("alert", alertMsg);
			req.removeAttribute("password1");
			req.removeAttribute("password2");
			req.getRequestDispatcher("/views/forgetpass.jsp").forward(req, resp);
			return;
		}

		if (username.isEmpty() || password.isEmpty()) {
			String alertMsg = "Tài khoản và mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgetpass.jsp").forward(req, resp);
			return;
		}
		if (userService.changePassword(username, password)) {
			resp.sendRedirect("/hcmute/login");
		} else {
			String alertMsg = "Tài khoản chưa tồn tại";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/forgetpass.jsp").forward(req, resp);
		}
	}
}
