package org.ismetg.dto.request;

import org.ismetg.entity.User;

import java.util.List;

public record PostSaveRequestDto(

        String title,
        String content,
        Long userid,
        List<Long> categoryids
) {
}
