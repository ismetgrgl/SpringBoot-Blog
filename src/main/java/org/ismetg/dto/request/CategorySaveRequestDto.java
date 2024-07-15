package org.ismetg.dto.request;

import jakarta.persistence.Column;

public record CategorySaveRequestDto(
        @Column(length = 250)
        String name,
        @Column(length = 5000)
        String description

) {
}
