package org.ismetg.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.Length;

public record UserSaveRequestDto(
        String firstname,

        String lastname,
        @Email(message = "geçersiz email")
        String email,
        String password

) {
}
