package com.jindev.vict.member;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Setter
@Getter
public class MemberDto {
    int userUniqueNum; /** 회원 고유 번호 */
    String email; /** 이메일 */
    String pwd; /** 비밀번호 */
    String name; /** 이름 */
    String year; /**생년월일 */
    int gender; /**성별 1:남 2:여 */
    String phoneNum; /** 핸드폰번호 */
    String blogUrl; /** 블로그 주소 */
    Date regDate; /** 회원가입 날짜 */
    Date leaveDate; /** 탈퇴 날짜 */
    String useYn; /** 사용 여부 */
    int loginType; /** 로그인 타입 1:일반가입 2:네이버가입 */
    String socialKey; /** 소셜 로그인 키 */
    String salt; /** ID SALT 값*/
}
