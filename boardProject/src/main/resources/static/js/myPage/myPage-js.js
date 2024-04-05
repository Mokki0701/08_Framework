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


// ------------------------------------------------------------------------------------

/* 프로필 이미지 추가/변경/삭제 변경 */

// 프로필 이미지 페이지 form 태그
const profile = document.querySelector("#profile");

// 프로필 이미지가 새로 업로드 되거나 삭제 되었음을 기록하는
// 상태 변수
let statusCheck = -1;
// -1 : 초기상태(변화 없음)
//  0 : 프로필 이미지 삭제
//  1 : 새 이미지 선택

// input type="file" 태그의 값이 변경 되었을 때
// 변경된 상태를 백업해서 저장할 변수
// -> 파일이 선택/취소된 input을 복제해서 저장

// 요소.cloneNode(true|false) : 요소 복제(true 작성 시 하위 요소도 복제)
let backupInput;


if(profile != null){

    // img 태그 (프로필 이미지가 보여지는 요소)
    const profileImg = document.querySelector("#profileImg");
    
    // input type="file" 태그 (실제 업로드할 프로필 이미지를 선택하는 요소)
    let imageInput = document.querySelector("#imageInput");
    
    // x버튼 (프로필 이미지를 제거하고 기본 이미지로 변경하는 요소)
    const deleteImage = document.querySelector("#deleteImage");








    /* input type="file"의 값이 변했을 때 동작할 함수(이벤트 핸들러) */
    const changeImageFn = e=>{

        // 업로드 가능한 파일 최대 크기 지정하여 필터링
        
        const maxSize = 1024 * 1024 * 5; 
        // 5MB == 1024KB * 5 == 1024B * 1024 * 5

        console.log("e.target", e.target); // input
        console.log("e.target.value", e.target.value); // 변경된 값(파일명)

        console.log("e.target.files", e.target.files); 
        // 선택된 파일에 대한 정보가 담긴 배열 반환
        // -> 왜 배열?? multiple 옵션에 대한 대비

        // 업로드된 파일이 1개 있으면 files[0]에 저장

        // 업로드된 파일이 없으면 files[0] == undefined
        console.log("e.target.files[0]", e.target.files[0]); 


        const file = e.target.files[0];

        // ---------------- 업로드된 파일이 없다면 (취소한 경우) ----------------
        if(file == undefined){
            console.log("파일 선택 후 취소됨");
            
            // 파일 선택 후 취소 -> value = ''
            // -> 선택한 파일 없음으로 기록됨
            // -> backupInput으로 교체 시켜서
            //    이전 이미지가 남아 있는 것 처럼 보이게 함

            // 백업의 백업본
            const temp = backupInput.cloneNode(true);
            
            // input 요소 다음에 백업 요소 추가
            imageInput.after(backupInput); // 이게 왜 있지?

            // 화면에 존재하는 기존 input 제거
            imageInput.remove();

            // imageInput 변수에 백업을 대입해서 대신하도록 함
            imageInput = backupInput;

            // 화면에 추가된 백업본에는
            // 이벤트 리스너가 존재하지 않기 때문에 추가
            imageInput.addEventListener("change", changeImageFn); // 잉???????

            // 한 번 화면에 추가된 요소는 재사용 불가능
            // backupInput의 백업본인 temp를 backupInput 으로 변경
            backupInput = temp;


            return;
        }

        // ---------------- 선택된 파일이 최대 크기를 초과한 경우 ----------------
        if(file.size > maxSize){
            alert("5MB 이하의 이미지 파일을 선택해 주세요.");
            
            // 선택한 이미지가 없는데 5MB 초과하는 이미지를 선택한 경우
            if(statusCheck == -1){
                imageInput.value = '';
            }else{ // 기존 선택한 이미지가 있는데
                   // 다음 선택한 이미지가 최대 크기를 초과한 경우

                // 백업의 백업본
                const temp = backupInput.cloneNode(true);
                
                // input 요소 다음에 백업 요소 추가
                imageInput.after(backupInput); // 이게 왜 있지?

                // 화면에 존재하는 기존 input 제거
                imageInput.remove();

                // imageInput 변수에 백업을 대입해서 대신하도록 함
                imageInput = backupInput;

                // 화면에 추가된 백업본에는
                // 이벤트 리스너가 존재하지 않기 때문에 추가
                imageInput.addEventListener("change", changeImageFn); // 잉???????

                // 한 번 화면에 추가된 요소는 재사용 불가능
                // backupInput의 백업본인 temp를 backupInput 으로 변경
                backupInput = temp;
            }

            return;
        }

        // ---------------- 선택된 이미지 미리보기 ----------------
        
        // JS에서 파일을 읽을 때 사용하는 객체
        // - 파일을 ㄹ읽고 클라이언트 컴퓨터에 저장할 수 있음
        const reader = new FileReader();
    
        // 선택한 파일(file) 을 읽어와
        // BASE64 인코딩 형태로 읽어와 result 변수에 저장
        reader.readAsDataURL(file); // -> 읽어오기 이벤트(load)

        // 읽어오기 끝났을 때
        reader.addEventListener("load", e=>{

            // e.target == reader

            // 읽어온 이미지 파일이 BASE64 형태로 반환됨
            const url = e.target.result; // reader.result

            // 프로필 이미지(img)
            profileImg.setAttribute("src",url);

            // 새 이미지 선택 상태를 기록
            statusCheck = 1;

            // 파일이 선택된 input을 복제해서 백업
            backupInput = imageInput.cloneNode(true);

        });



    }

    // change 이벤트 : 새로운 값이 기존 값과 다를 경우 발생
    imageInput.addEventListener("change",changeImageFn);


    // ---------------------------------- x버튼 클릭 시 기본 이미지로 변경 ----------------------------------
    deleteImage.addEventListener("click", ()=>{

        // 프로필 이미지(img) 를 기본 이미지로 변경
        profileImg.src = "/images/user.png";

        // input에 저장된 값(value)를 ''(빈칸)으로 변경
        // -> input에 저장된 파일 정보가 모두 사라짐
        imageInput.value = '';

        backupInput.value = undefined; // 백업본도 삭제

        // 삭제 상태임을 기록
        statusCheck = 0;

        
    });

    // -------------------- #profile (form) 제출 시 ------------------------
    profile.addEventListener("submit", e=>{

        let flag = true;

        // 기존 프로필 이미지가 없다가 새 이미지가 선택된 경우
        if(loginMemberProfileImg == null && statusCheck == 1) flag = false;

        // 기존 프로필 이미지가 있다가 삭제한 경우
        if(loginMemberProfileImg != null && statusCheck == 0) flag = false;

        // 기존 프로필 이미지가 있다가 새 이미지가 선택된 경우
        if(loginMemberProfileImg != null && statusCheck == 1) flag = false;


        if(flag){ // flag값이 true인 경우
            
            e.preventDefault();
            alert("이미지 변경 후 클릭하세요.");
        }
    });




}



/* [input type="file" 사용 시 유의 사항]]

    1. 파일 선택 후 취소를 누르면 
      선택한 파일이 사라진다. (value == '')

    2. value로 대입할 수 있는 값은 '' (빈칸)만 가능하다

    3. 선택된 파일 정보를 저장하는 속성은 
      value가 아니라 files이다
*/






































