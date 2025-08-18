package com.example.spring04.exception;

public class BookException extends RuntimeException {
	
	public enum Reason{
		NOT_FOUND,UPDATE_FAILED,DELETE_FAILED
	}
	//필드
		public final Reason reason;
	
	public BookException(Reason reason, String message) {
		//부모 생성자에 예외 메세지를 전달한다.
		super(message);
		//필드에 예외의 원인을 저장한다
		this.reason=reason;
	}
	
	public static BookException notFound(int num) {
		return new BookException(Reason.NOT_FOUND, num+" 번 책이 없습니다");
	}
	public static BookException updateFailed(int num) {
		return new BookException(Reason.UPDATE_FAILED, num+" 번 책 수정 실패");
	}
	
	public static BookException deleteFailed(int num) {
		return new BookException(Reason.DELETE_FAILED, num+" 번 책이 없습니다");
	}
}
