package vn.hcmute.controllers;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import vn.hcmute.models.UserModel;

@WebServlet(urlPatterns = {"/waiting"})
public class WaitingController extends HttpServlet {
	private static final long serialVersionUID = 2212347138139872886L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		UserModel account = (UserModel) session.getAttribute("account");
		int roleid = account.getRoleid();
		if(roleid == 1) {
			resp.sendRedirect(req.getContextPath() + "/user/home");
		}
		else if(roleid == 2) {
			resp.sendRedirect(req.getContextPath() + "/admin/home");
		}
		else if(roleid == 3) {
			resp.sendRedirect(req.getContextPath() + "/manager/home");
		}
	}
}
