package StudyCommunity.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_EXISTS(409, "Member exists"),
    MEMBER_NOT_FOUND(404, "Member not found"),
    BOARD_EXISTS(409, "Board exists"),
    BOARD_NOT_FOUND(404, "Board not found"),
    COMMENT_NOT_FOUND(404, "Comment not found"),
    ONLINE_STUDY_NOT_FOUND(404, "Online study not found");

    @Getter
    private final int status;

    @Getter
    private final String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }

}
