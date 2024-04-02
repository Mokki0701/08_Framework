const findEmail = document.querySelector("#findEmail");
const sendAuthKeyBtn = document.querySelector("#sendAuthKeyBtn");
const authKey = document.querySelector("#authKey");
const checkAuthKeyBtn = document.querySelector("#checkAuthKeyBtn");

const checkAuthKey = false;

let authTimer; // 타이머 역할을 할 setInterval을 저장할 변수

const initMin = 4;
const initSec = 59;
const initTime = "05:00";

let min = initMin;
let sec = initSec;

sendAuthKeyBtn.addEventListener("click", ()=>{
    
    min = initMin;
    sec = initSec;

    clearInterval(authTimer);

    checkAuthKey = false;

    // ----------------------------
    fetch("/member/findPw",{
        method : "POST",
        headers : {"Content-Type":"application/json"},
        body : findEmail.innerText()
    })
    .then(resp=>{
        return resp.text();
    })
    .then(result =>{

        if(result == 1){
            console.log("인증 완료!!!");
        }
        else{
            console.log("인증 실패...");
        }
    })




})

























