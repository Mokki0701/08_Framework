/* 회원 정보 수정 페이지 */
const updateInfo = document.querySelector("#updateInfo");

// #updateInfo 요소가 존재 할 때만 수행
if(updateInfo != null){

    updateInfo.addEventListener("submit", e=>{

        const memberNickname = document.querySelector("#memberNickname");
        const memberTel      = document.querySelector("#memberTel");
        const memberAddress  = document.querySelectorAll("[name='memberAddress']");

        // 닉네임 유효성 검사
        if(memberNickname.value.trim().length == 0){
            alert("닉네임을 입력해 주세요.");
            e.preventDefault();
            return;
        }

        let regExp = /^[가-힣A-Za-z0-9]{2,10}$/;
        if(!regExp.test(memberNickname.value)){
            alert("닉네임이 유효하지 않습니다.");
            e.preventDefault();
            return;
        }

        // ********************************************************************************
        // 중복 검사는 나중에 추가 예정.........
        // (테스트 시 닉네임 중복이 안되게 조심!!!)

        // 전화번호 유효성 검사
        if(memberTel.value.trim().length == 0){
            alert("전화번호를 입력해 주세요.");
            e.preventDefault();
            return;
        }

        regExp = /^01[0-9]{1}[0-9]{4}[0-9]{4}$/;
        if(!regExp.test(memberTel.value)){
            alert("전화번호가 유효하지 않습니다.");
            e.preventDefault();
            return;
        }


        // 주소 유효성 검사
        // 입력을 안하면 전부 안해야되고
        // 입력을 하면   전부 해야된다
        const addr0 = memberAddress[0].value.trim().length == 0;
        const addr1 = memberAddress[1].value.trim().length == 0;
        const addr2 = memberAddress[2].value.trim().length == 0;

        // 모두 true인 경우만 true 저장
        const result1 = addr0 && addr1 && addr2;

        // 모두 false인 경우만 true 저장
        const result2 = !(addr0 || addr1 || addr2);
        
        // 모두 입력 또는 모두 미입력
        if(!(result1 || result2)){
            alert("주소를 모두 작성 또는 미작성 해주세요.");
            e.preventDefault(); // 제출 막기
        }


    });



}



// ---------------------------------------------------------------------------------------------
const changePw = document.querySelector("#changePw");

const newPwConfirm = document.querySelector("#newPwConfirm");
const newPw = document.querySelector("#newPw");
const myPageSubmit = document.querySelector(".myPage-submit");


if(changePw != null){
    
    changePw.addEventListener("submit", e=>{
        
        let str; // undefined 상태

        if( currentPw.value.trim().length == 0 )  str = "현재 비밀번호를 입력해 주세요";
        else if( newPw.value.trim().length == 0 ) str = "새 비밀번호를 입력 해주세요";
        else if( newPwConfirm.value.trim().length == 0 ) str = "새 비밀번호 확인을 입력 해주세요";

        if(str != undefined){
            alert(str);
            e.preventDefault();
            return;
        }

        const regExp = /^[a-zA-Z0-9!@#\-_]{6,20}$/;
        if(!regExp.test(newPw.value)){
            alert("새 비밀번호가 유효하지 않습니다.");
            e.preventDefault();
            return;
        }

        // 중복 검사 해야함 ********************
        // 중복 검사 해야함 ********************
        // 중복 검사 해야함 ********************

        if(newPw.value != newPwConfirm.value){
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            return;
        }        
    });

}

// ---------------------------------------------------------------------------------------------
/* 회원 탈퇴 */
const secession = document.querySelector("#secession");

if(secession != null){

    secession.addEventListener("submit", e=>{
    
        const memberPw = document.querySelector("#memberPw");
        const agree = document.querySelector("#agree");

        if(memberPw.value.trim().length == 0){
            alert("비밀번호를 입력해주세요.");
            e.preventDefault();
            return;
        }

        // - 약관 동의 체크 확인

        // checkbox 또는 radio      checked 속성
        // - checked -> 체크 시 true, 미체크시 false 반환

        // - checked = true -> 체크하기
        // - checked = false -> 체크 해제하기

        if(!agree.checked){ // 체크 안됐을 때
            alert("약관에 동의해주세요.");
            e.preventDefault();
            return;
        }


        // - 정말 탈퇴? 물어보기
        if(!confirm("정말 탈퇴 하시겠습니까?")){ // 취소 선택 시
            alert("취소 되었습니다.");
            e.preventDefault();
            return;
        }
    
    
    });
}































