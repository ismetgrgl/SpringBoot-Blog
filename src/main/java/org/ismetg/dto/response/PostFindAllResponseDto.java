package org.ismetg.dto.response;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import org.hibernate.annotations.CreationTimestamp;
import org.ismetg.entity.Category;

import java.time.LocalDate;
import java.util.List;

public record PostFindAllResponseDto(

        String title,
        String content,
        LocalDate publishedat,
        String username,
        List<String> categoriesname
) {
}
