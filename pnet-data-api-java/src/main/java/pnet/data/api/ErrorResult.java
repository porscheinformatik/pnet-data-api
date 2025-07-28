package pnet.data.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class ErrorResult {

    private final String status;
    private final String errorId;
    private final String error;
    private final String message;
    private final String request;
    private final ZonedDateTime timestamp;

    public ErrorResult(
        @JsonProperty("status") String status,
        @JsonProperty("errorId") String errorId,
        @JsonProperty("error") String error,
        @JsonProperty("message") String message,
        @JsonProperty("request") String request,
        @JsonProperty("timestamp") ZonedDateTime timestamp
    ) {
        super();
        this.status = status;
        this.errorId = errorId;
        this.error = error;
        this.message = message;
        this.request = request;
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public String getErrorId() {
        return errorId;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getRequest() {
        return request;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    @SuppressWarnings("java:S3776") // It's spaghetti code. Accept it.
    public String toDescription() {
        StringBuilder bob = new StringBuilder();

        if (status != null && !status.isEmpty()) {
            bob.append(status);
        }

        if (errorId != null && !errorId.isEmpty()) {
            if (!bob.isEmpty()) {
                bob.append(" [").append(errorId).append("]");
            } else {
                bob.append(errorId);
            }
        }

        if (error != null && !error.isEmpty()) {
            if (!bob.isEmpty()) {
                bob.append(": ");
            }

            bob.append(error);
        }

        if (message != null && !message.isEmpty()) {
            if (!bob.isEmpty()) {
                bob.append(" (").append(message).append(")");
            } else {
                bob.append(message);
            }
        }

        if (request != null && !request.isEmpty()) {
            if (!bob.isEmpty()) {
                bob.append(" ");
            }

            bob.append("[").append(request).append("]");
        }

        if (timestamp != null) {
            if (!bob.isEmpty()) {
                bob.append(" at ");
            }

            bob.append(timestamp.format(DateTimeFormatter.ISO_DATE_TIME));
        }

        return bob.toString();
    }

    @Override
    public String toString() {
        return String.format(
            "ErrorResult [status=%s, errorId=%s, error=%s, message=%s, request=%s, timestamp=%s]",
            this.status,
            this.errorId,
            this.error,
            this.message,
            this.request,
            this.timestamp
        );
    }
}
