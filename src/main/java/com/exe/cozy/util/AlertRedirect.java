package com.exe.cozy.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class AlertRedirect {

	public static void warningMessage(HttpServletResponse response, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(
				"<script>" +
						"	alert('" + message + "');" +
						"	history.back();" +
						"</script>"
				);
		out.flush();
	}

	public static void warningMessage(HttpServletResponse response, int num, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(
				"<script>" +
						"	alert('" + message + "');" +
						"	history.go(" + num + ");" +
						"</script>"
				);
		out.flush();
	}
	public static void warningMessage(HttpServletResponse response, String url, String message) throws IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println(
				"<script>" +
						"	alert('" + message + "');" +
						"	location.href='" + url + "';" +
						"</script>"
				);
		out.flush();
	}
}
