package edu.pe.idat.security.jwt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtEntryPoint implements AuthenticationEntryPoint { //verifica si el token es valido -> lanza mensaje

	private final static Logger logger = LoggerFactory.getLogger(JwtEntryPoint.class);

	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException e)
			throws IOException, ServletException {

		logger.error("fail en el método commence");

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		res.setContentType("application/json");
		res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		res.getOutputStream()
				.println("{ \"timestamp\": \"" + dtf.format(LocalDateTime.now()) + "\"," + "\"status\":"
						+ res.getStatus() + "," + "\"error\":\"" + "Unauthorized" + "\" ,\"message\":\""
						+ "Usuario no encontrado" + "\" }");

		// res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "no autorizado");

	}
}