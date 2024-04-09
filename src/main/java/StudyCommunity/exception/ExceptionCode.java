package StudyCommunity.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    POST_EXISTS(409, "Board exists"),
    POST_NOT_FOUND(404, "Board not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    STUDY_COMMENT_NOT_FOUND(404, "Study Comment not found"),
    STUDY_NOT_FOUND(404, "Study not found");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
