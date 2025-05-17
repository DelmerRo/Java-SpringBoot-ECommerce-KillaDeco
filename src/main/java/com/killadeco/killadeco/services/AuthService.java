package com.killadeco.killadeco.services;

import com.killadeco.killadeco.dtos.ExtendedBaseResponse;
import com.killadeco.killadeco.dtos.user.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface AuthService {
    ExtendedBaseResponse<AuthResponseDto> login(LoginRequestDto request);

    ExtendedBaseResponse<AuthResponseDto> register(RegisterRequestDto request);

    ExtendedBaseResponse<String> generatePasswordResetToken(EmailDto email);

    void resetPassword(ResetPasswordRequest request);
}
