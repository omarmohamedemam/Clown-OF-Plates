package eg.edu.alexu.csd.oop.game;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

/**
 * https://github.com/simply-coded/java-tutorials/blob/master/code/tutorial_48.java
 * LogExample
 */
public class LoggingManger {

	public static Logger setupLogger() {

		Logger logr = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

		LogManager.getLogManager().reset();
		logr.setLevel(Level.ALL);

		ConsoleHandler ch = new ConsoleHandler();
		ch.setLevel(Level.SEVERE);
		logr.addHandler(ch);

		try {
			FileHandler fh = new FileHandler("COP.log", true);
			fh.setLevel(Level.FINE);
			fh.setFormatter(new MyHtmlFormatter());
			logr.addHandler(fh);
		} catch (java.io.IOException e) {
			// don't stop my program but log out to console.
			logr.log(Level.SEVERE, "File logger not working.", e);
		}
		/*
		 * Different Levels in order. OFF SEVERE WARNING INFO CONFIG FINE FINER FINEST
		 * ALL
		 */

		return logr;
	}

}

/**
 * https://www.vogella.com/tutorials/Logging/article.html Custom formatter
 */
class MyHtmlFormatter extends Formatter {
	// this method is called for every log records
	public String format(LogRecord rec) {
		StringBuffer buf = new StringBuffer(1000);
		buf.append("<tr>\n");

		// colorize any levels >= WARNING in red
		if (rec.getLevel().intValue() >= Level.WARNING.intValue()) {
			buf.append("\t<td style=\"color:red\">");
			buf.append("<b>");
			buf.append(rec.getLevel());
			buf.append("</b>");
		} else {
			buf.append("\t<td>");
			buf.append(rec.getLevel());
		}

		buf.append("</td>\n");
		buf.append("\t<td>");
		buf.append(calcDate(rec.getMillis()));
		buf.append("</td>\n");
		buf.append("\t<td>");
		buf.append(formatMessage(rec));
		buf.append("</td>\n");
		buf.append("</tr>\n");

		return buf.toString();
	}

	private String calcDate(long millisecs) {
		SimpleDateFormat date_format = new SimpleDateFormat("MMM dd,yyyy HH:mm");
		Date resultdate = new Date(millisecs);
		return date_format.format(resultdate);
	}

	// this method is called just after the handler using this
	// formatter is created
	public String getHead(Handler h) {
		return "<!DOCTYPE html>\n<head>\n<style>\n" + "table { width: 100% }\n" + "th { font:bold 10pt Tahoma; }\n"
				+ "td { font:normal 10pt Tahoma; }\n" + "h1 {font:normal 11pt Tahoma;}\n" + "</style>\n" + "</head>\n"
				+ "<body>\n" + "<h1>" + (new Date()) + "</h1>\n"
				+ "<table border=\"0\" cellpadding=\"5\" cellspacing=\"3\">\n" + "<tr align=\"left\">\n"
				+ "\t<th style=\"width:10%\">Loglevel</th>\n" + "\t<th style=\"width:15%\">Time</th>\n"
				+ "\t<th style=\"width:75%\">Log Message</th>\n" + "</tr>\n";
	}

	// this method is called just after the handler using this
	// formatter is closed
	public String getTail(Handler h) {
		return "</table>\n</body>\n</html>";
	}
}