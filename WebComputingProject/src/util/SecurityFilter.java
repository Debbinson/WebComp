package util;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Utente;

public class SecurityFilter implements Filter {

	public SecurityFilter() {

	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (request.getRequestURL().toString().contains(".jsp")) {
			RequestDispatcher rd = request.getRequestDispatcher("/");
			rd.forward(request, response);
			return;
		}

		String servletPath = request.getServletPath();
		if (servletPath.equalsIgnoreCase("/login")) {
			chain.doFilter(request, response);
			return;
		}

		Utente loginedUtente = AppUtils.getLoginedUser(request.getSession());
		HttpServletRequest wrapRequest = request;
		if (loginedUtente != null) {
			String userName = loginedUtente.getUsername();
			List<String> roles = loginedUtente.getRoles();
			wrapRequest = new UserRoleRequestWrapper(userName, roles, request);
		}

		if (SecurityUtils.isSecurityPage(request)) {
			if (loginedUtente == null) {
				String requestUri = request.getRequestURI();
				int rendirectId = AppUtils.storeRendirectAfterLoginUrl(request.getSession(), requestUri);
				response.sendRedirect(wrapRequest.getContextPath() + "/login?redirectId=" + rendirectId);
				return;
			}

			boolean hasPermission = SecurityUtils.hasParmission(wrapRequest);
			if (!hasPermission) {
				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/accessDeniedView.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}

		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
	}
}
