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
        
        if(newPw.value.trim().length == 0){
            alert("비밀번호을 입력해주세요.");
            e.preventDefault();
            return;
        }

        const regExp = /^[a-zA-Z0-9!@#\-_]{6,20}$/;
        if(!regExp.test(newPw.value)){
            alert("비밀번호가 유효하지 않습니다.");
            e.preventDefault();
            return;
        }

        // 중복 검사 해야함 ********************
        // 중복 검사 해야함 ********************
        // 중복 검사 해야함 ********************

        if(newPw.value !== newPwConfirm.value){
            alert("비밀번호가 일치하지 않습니다.");
            e.preventDefault();
            return;
        }        
    
    
    
    });

}












