package vn.hcmute.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import vn.hcmute.services.impl.UserServiceImpl;
import vn.hcmute.models.UserModel;
import vn.hcmute.services.IUserService;

@WebServlet(urlPatterns = { "/register" })
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 6783683535067213701L;
	IUserService userService = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("views/register.jsp").forward(req, resp);
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
		String fullname = req.getParameter("fullname");
		if(!password1.equals(password2)) {
			String alertMsg = "Vui lòng nhập lại mật khẩu";
			req.setAttribute("alert", alertMsg);
			req.removeAttribute("password1");
			req.removeAttribute("password2");
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		UserModel userModel = new UserModel();
		userModel.setUsername(username);
		userModel.setPassword(password);
		userModel.setFullname(fullname);
		userModel.setRoleid(1);
		
		if(username.isEmpty() || password.isEmpty()) {
			String alertMsg = "Tài khoản và mật khẩu không được rỗng";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
			return;
		}
		if(userService.addUser(userModel)) {
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		else {
			String alertMsg = "Tài khoản đã tồn tại";
			req.setAttribute("alert", alertMsg);
			req.getRequestDispatcher("/views/register.jsp").forward(req, resp);
		}
		
	}
}
