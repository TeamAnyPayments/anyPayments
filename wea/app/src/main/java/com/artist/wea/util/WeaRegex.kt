package com.artist.wea.util

import androidx.compose.runtime.MutableState
import java.util.regex.Pattern

class WeaRegex {
    companion object{
        // 아이디 패턴 Regex
        val allPattern = Pattern.compile(".*")
        val allGuideText = "잘못된 입력입니다."
        val idPattern = Pattern.compile("^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#\$%^&*])(?=.*[0-9!@#\$%^&*]).{6,15}\$")
        val idGuideText = "아이디는 영문, 숫자를 포함하여 6-15자 이내로 입력해 주세요";
        // 비밀번호 패턴 Regex
        val pwdPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{8,20}$");
        val pwdGuideText = "비밀번호는 문자 숫자 특수 문자의 조합으로 8글자 이상 20자 이하로 입력해주세요"

        // 이름 패턴 Regex
        val namePattern = Pattern.compile("^[ㄱ-ㅣ가-힣]+$");
        val nameGuideText = "이름은 한글만 입력 가능합니다.";

        // 이메일 패턴 Regex
        val emailPattern = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+");
        val emailGuideText = "이메일 형식이 맞지 않습니다.";
        // 휴대폰 번호 Regex
        val phonePattern = Pattern.compile("^[0-9]{11}")
        val phoneGuideText = "전화번호를 올바르게 입력해주세요"
        // 비밀번호 문자열 일치 여부
        val pwdDiffText = "비밀번호가 같지 않습니다"
        // val pwdSameText = "비밀번호가 동일합니다"
        val joinSuccessGuideText = "회원 가입이 완료되었습니다!"
        val joinRejectGuideText = "회원가입 양식을 다시 확인해주세요!"
        val joinFailGuideText = "회원 가입에 실패하였습니다.\n관리자에게 문의해주세요."

        // 타이머 파싱 함수
        fun parseToTimeString(
            timerSecond:MutableState<Int>
        ):String = if(timerSecond.value/60 > 0) "${timerSecond.value/60}분 ${timerSecond.value%60}초" else "${timerSecond.value % 60}초"

    }

}