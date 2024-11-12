package com.andersonsinaluisa.financial_api.core.infrastructure.inbound.dto.dashboard;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BalanceDto {
    public double currentBalace;
}
