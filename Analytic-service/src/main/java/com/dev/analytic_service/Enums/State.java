package com.dev.analytic_service.Enums;

import lombok.Getter;

@Getter
public enum State {
    OPEN,
    WORK_IN_PROGRESS,
    PENDING_WITH_END_USER,
    RESOLVED
}
