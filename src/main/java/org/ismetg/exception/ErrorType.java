package org.ismetg.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorType {
	EMAIL_NOT_VALIDATE(5001,
			"Email olması gereken formatta değil.",
			HttpStatus.NOT_FOUND),
	USER_NOT_FOUND(5002,
			"Girilen Id ile kullanıcı bulunamadı.",
			HttpStatus.NOT_FOUND),
	CATEGORY_NOT_FOUND(5003,
			"Girilen Id ile kategori bulunamadı.",
			HttpStatus.NOT_FOUND),
	CATEGORY_NOT_FOUND2(5004,
			"Girilen ad ile kategori bulunamadı.",
			HttpStatus.NOT_FOUND),
	POST_NOT_FOUND(5005,
			"Girilen Id ile post bulunamadı.",
			HttpStatus.NOT_FOUND),
	LIKE_NOT_FOUND(5006,
						   "Girilen Id ile beğeni bulunamadı.",
				   HttpStatus.NOT_FOUND),
	COMMENT_NOT_FOUND(5007,
			"Girilen Id ile yorum bulunamadı.",
			HttpStatus.NOT_FOUND),
	LIKED_BEFORE(4040,
						   "Girilen user girilen postu daha önce beğenmiş",
				   HttpStatus.I_AM_A_TEAPOT);

	private Integer code;
	private String message;
	private HttpStatus httpStatus;
	
}