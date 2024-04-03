const findEmail = document.querySelector("#findEmail");
const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");
const authKey = document.querySelector("#authKey");
const checkAuthKeyBtn = document.querySelector("#checkAuthKeyBtn");

const checkAuthKey = false;


sendAuthKeyBtn.addEventListener("click", ()=>{

    checkAuthKey = false;

    fetch("/member/findPw",{
        method : "POST",
        headers : {"Content-Type":"application/json"},
        body : findEmail.value
    })
    .then(resp=>{
        return resp.text();
    })
    .then(result =>{

        if(result == 1){
            alert("발송 성공!!!");
        }
        else{
            alert("발송 실패...");
        }
    })


})

// ------------------------------------------------------------------------------------------------------

checkAuthKeyBtn.addEventListener("click", e=>{

    checkAuthKey = false;

    // 이거 fetch 비동기로 보낼때 param값으로 authKey말고도 이메일도 보내야 되겠네

    fetch("/member/matchAuthKey",{
        method : "POST",
        headers : {"Content-Type":"application/json"},
        body : authKey.value
    })


});

// ------------------------------------------------------------------------------------------------------

// 인증 성공하면 checkAuthKey 버튼 true인지 체크하고 true면 팝업 창 닫고, 비밀번호 재설정 칸으로 가기





















