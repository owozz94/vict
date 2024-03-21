package com.jindev.vict.member;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginForm {

    @NotBlank(message = "이메일을 입력해주세요.")
    @NotNull
    String email; /** 이메일 */

    @NotBlank(message = "비밀번호를 입력해주세요.")
    @NotNull
    String pwd; /** 비밀번호 */

    String useYn; /** 사용 여부 */
    String salt;
}
