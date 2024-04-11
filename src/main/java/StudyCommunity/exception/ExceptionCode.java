package StudyCommunity.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    POST_EXISTS(409, "Board exists"),
    POST_NOT_FOUND(404, "Board not found"),
    STUDY_COMMENT_NOT_FOUND(404, "Study Comment not found"),
    STUDY_NOT_FOUND(404, "Study not found"),
    POST_COMMENT_NOT_FOUND(404, "Post Comment not found"),
    NOTE_NOT_FOUND(404, "Note not found");


    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
