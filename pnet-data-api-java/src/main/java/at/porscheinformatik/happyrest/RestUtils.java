package at.porscheinformatik.happyrest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Utilities for the framework.
 *
 * @author ham
 */
public final class RestUtils
{

    private static final Map<Integer, String> HTTP_STATUS_MESSAGES;

    static
    {
        Map<Integer, String> httpStatusMessages = new HashMap<>();

        httpStatusMessages.put(100, "Continue");
        httpStatusMessages.put(101, "Switching Protocols");
        httpStatusMessages.put(102, "Processing");
        httpStatusMessages.put(200, "OK");
        httpStatusMessages.put(201, "Created");
        httpStatusMessages.put(202, "Accepted");
        httpStatusMessages.put(203, "Non-Authoritative Information");
        httpStatusMessages.put(204, "No Content");
        httpStatusMessages.put(205, "Reset Content");
        httpStatusMessages.put(206, "Partial Content");
        httpStatusMessages.put(207, "Multi-Status");
        httpStatusMessages.put(208, "Already Reported");
        httpStatusMessages.put(226, "IM Used");
        httpStatusMessages.put(300, "Multiple Choices");
        httpStatusMessages.put(301, "Moved Permanently");
        httpStatusMessages.put(302, "Found (Moved Temporarily)");
        httpStatusMessages.put(303, "See Other");
        httpStatusMessages.put(304, "Not Modified");
        httpStatusMessages.put(305, "Use Proxy");
        httpStatusMessages.put(307, "Temporary Redirect");
        httpStatusMessages.put(308, "Permanent Redirect");
        httpStatusMessages.put(400, "Bad Request");
        httpStatusMessages.put(401, "Unauthorized");
        httpStatusMessages.put(402, "Payment Required");
        httpStatusMessages.put(403, "Forbidden");
        httpStatusMessages.put(404, "Not Found");
        httpStatusMessages.put(405, "Method Not Allowed");
        httpStatusMessages.put(406, "Not Acceptable");
        httpStatusMessages.put(407, "Proxy Authentication Required");
        httpStatusMessages.put(408, "Request Timeout");
        httpStatusMessages.put(409, "Conflict");
        httpStatusMessages.put(410, "Gone");
        httpStatusMessages.put(411, "Length Required");
        httpStatusMessages.put(412, "Precondition Failed");
        httpStatusMessages.put(413, "Request Entity Too Large");
        httpStatusMessages.put(414, "URI Too Long");
        httpStatusMessages.put(415, "Unsupported Media Type");
        httpStatusMessages.put(416, "Requested range not satisfiable");
        httpStatusMessages.put(417, "Expectation Failed");
        httpStatusMessages.put(420, "Policy Not Fulfilled");
        httpStatusMessages.put(421, "Misdirected Request");
        httpStatusMessages.put(422, "Unprocessable Entity");
        httpStatusMessages.put(423, "Locked");
        httpStatusMessages.put(424, "Failed Dependency");
        httpStatusMessages.put(426, "Upgrade Required");
        httpStatusMessages.put(428, "Precondition Required");
        httpStatusMessages.put(429, "Too Many Requests");
        httpStatusMessages.put(431, "Request Header Fields Too Large");
        httpStatusMessages.put(451, "Unavailable For Legal Reasons");
        httpStatusMessages.put(418, "I’m a teapot");
        httpStatusMessages.put(425, "Unordered Collection");
        httpStatusMessages.put(444, "No Response");
        httpStatusMessages.put(449, "The request should be retried after doing the appropriate action");
        httpStatusMessages.put(499, "Client Closed Request");
        httpStatusMessages.put(500, "Internal Server Error");
        httpStatusMessages.put(501, "Not Implemented");
        httpStatusMessages.put(502, "Bad Gateway");
        httpStatusMessages.put(503, "Service Unavailable");
        httpStatusMessages.put(504, "Gateway Timeout");
        httpStatusMessages.put(505, "HTTP Version not supported");
        httpStatusMessages.put(506, "Variant Also Negotiates");
        httpStatusMessages.put(507, "Insufficient Storage");
        httpStatusMessages.put(508, "Loop Detected");
        httpStatusMessages.put(509, "Bandwidth Limit Exceeded");
        httpStatusMessages.put(510, "Not Extended");
        httpStatusMessages.put(511, "Network Authentication Required");

        HTTP_STATUS_MESSAGES = Collections.unmodifiableMap(httpStatusMessages);
    }

    private RestUtils()
    {
        super();
    }

    public static String getHttpStatusMessage(int code)
    {
        String result = HTTP_STATUS_MESSAGES.get(code);

        return result != null ? result : "undefined";
    }

    public static String getHttpStatusMessage(int statusCode, String statusMessage)
    {
        if (statusMessage != null && statusMessage.length() > 0)
        {
            String statusCodeAsString = String.valueOf(statusCode);

            if (statusMessage.startsWith(statusCodeAsString))
            {
                statusMessage = statusMessage.substring(statusCodeAsString.length(), statusMessage.length()).trim();
            }
        }

        if (statusMessage == null || statusMessage.length() == 0)
        {
            statusMessage = RestUtils.getHttpStatusMessage(statusCode);
        }

        return statusMessage;
    }

    public static String getHttpStatus(int statusCode, String statusMessage)
    {
        return statusCode + " " + getHttpStatusMessage(statusCode, statusMessage);
    }

    public static String abbreviate(String s, int length)
    {
        if (s == null)
        {
            return null;
        }

        if (s.length() < length)
        {
            return s;
        }

        return s.substring(0, Math.max(length - 3, 0)) + "...";
    }
}
