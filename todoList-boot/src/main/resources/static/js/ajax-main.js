/* 요소 얻어와서 변수에 저장 */

const totalCount = document.querySelector("#totalCount");
const completeCount = document.querySelector("#completeCount");
const reloadBtn = document.querySelector("#reloadBtn");

const todoTitle = document.querySelector("#todoTitle");
const todoContent = document.querySelector("#todoContent");
const addBtn = document.querySelector("#addBtn");

// 할 일 목록 조회 관련
const tobdy = document.querySelector("#tbody");

// 할 일 상세 조회 관련 요소
const popupLayer = document.querySelector("#popupLayer");
const popupTodoNo = document.querySelector("#popupTodoNo");
const popupTodoTitle = document.querySelector("#popupTodoTitle");
const popupComplete = document.querySelector("#popupComplete");
const popupRegDate = document.querySelector("#popupRegDate");
const popupTodoContent = document.querySelector("#popupTodoContent");
const popupClose = document.querySelector("#popupClose");

// 삭제 버튼
const deleteBtn = document.querySelector("#deleteBtn");

// 완료 여부 수정 버튼
const completeConvertBtn = document.querySelector("#completeConvertBtn");

// 수정 버튼
const updateBtn = document.querySelector("#updateBtn");

// 업데이트 내용 요소
const updateLayer = document.querySelector("#updateLayer");
const updateTodoTitle = document.querySelector("#updateTodoTitle");
const updateTodoContent = document.querySelector("#updateTodoContent");
const updateComplete = document.querySelector("#updateComplete");
const updateCancel = document.querySelector("#updateCancel");


// -------------------------------------------------------------------

// 전체 Todo 개수 조회 및 출력하는 함수
function getTotalCount(){ // 함수 정의

    // 비동기로 서버(DB)에서 전체 Todo 개수 조회하는
    // fetch() API 코드 작성
    // (fetch : 가지고 오다)
   
    fetch("/ajax/totalCount") // 비동기 요청 수행 -> Promise객체 반환
    .then(response =>{

        // response : 비동기 요청에 대한 응답이 담긴 객체

        // response.text() : 응답 데이터를 문자열/숫자 형태로 변환한
        //                  결과를 가지는 Promise 객체 반환

        console.log(response); 
        // console.log(response.text());
        return response.text();
    })

    // 두 번째 then의 매개 변수(result)
    // == 첫 번째 then에서 반환된 Promise 객체의 PromiseResult 값
    .then(result => {

        // result 매개 변수 == Controller 메서드에서 qksghksehls rkqt

        console.log("result",result);

        totalCount.innerText = result;

    })


};

function getCompleteCount(){

    // 첫 번째 then의 response : 
    // - 응답 결과, 요청 주소, 응답 데이터 등이 담겨있음
    // response.text() : 응답 데이터를 text 형태로 변환

    // 두 번째 then의 result
    // - 첫 번째 then에서 text로 변환된 응답 데이터(completeCount 값)
    fetch("/ajax/completeCount")
    .then(response=>response.text())
    .then(result=>{
     
        // #completeCount 요소에 내용으로 result값 출력
        completeCount.innerText = result;

    })

};

// 새로고침 버튼이 클릭 되었을 때
reloadBtn.addEventListener("click", e=>{
    getCompleteCount(); // 비동기로 완료된 할 일 개수 조회
    getTotalCount(); // 비동기로 전체 할 일 개수 조회
});

// 할 일 추가 버튼 클릭 시 동작
addBtn.addEventListener("click", e=>{

    // 비동기로 할 일 추가하는 fetch() API 코드 작성
    // - 요청 주소 : "/todo/add"
    // - 데이터 전달 방식(method) : "POST" 방식
    
    // 파라미터를 저장한 JS 객체 // param을 양 사이드에 "" 를 붙이면 JSON 이라 할 수 있다.
    const param = {
        "todoTitle"   : todoTitle.value,
        "todoContent" : todoContent.value
    }

    fetch("/ajax/add", {
        // key  : value
        method  : "POST", // POST 방식 요청
        headers : {"Content-Type" : "application/json"}, // 요청 데이터의 형식을 JSON으로 지정
        body    : JSON.stringify(param) // param 객체를 JSON(String)으로 변환
    })
    .then(resp=>resp.text()) // 반환된 값을 text로 변환
    
    // 첫 번째 then에서 반환된 값 중 변환된 text를 temp에 저장
    .then(temp =>{
        if(temp > 0){
            alert("추가 성공!!!");

            // 추가 성공한 제목, 내용 지우기
            todoTitle.value = "";
            todoContent.value = "";

            // 할 일이 추가되었기 때문에 전체 Todo 개수 다시 조회
            getTotalCount();

            // 할 일 목록 다시 조회
            selectTodoList();
        }else{
            alert("추가 실패...");
        }

    })
});

// -----------------------------------------------------------
// 비동기(ajax) 상세 조회 기능
const selectTodo = (url) => {

    // 매개 변수 url == "/ajax/detail?todoNo=10" 형태의 문자열

    // resp.json() : 
    // - 응답 데이터가 JSON인 경우
    //   이를 자동으로 Object 형태로 변환하는 메서드
    //   == JSON.parse(JSON 데이터)
    fetch(url)
    .then(resp => resp.json())
    .then(todo => {
        // 매개 변수 todo : 
        // 서버 응답(JSON)이 Object로 변환된 값(첫 번째 then 반환 결과)

        console.log(todo);
        // 프론트엔드는 너무 하기가 싫네

        /* popup layer에 조회된 값을 출력 */
        popupTodoNo.innerText      = todo.todoNo;
        popupTodoTitle.innerText   = todo.todoTitle;
        popupComplete.innerText    = todo.complete;
        popupRegDate.innerText     = todo.regDate;
        popupTodoContent.innerText = todo.todoContent;

        // popup layer 보이게 하기
        popupLayer.classList.remove("popup-hidden");

        // update layer가 혹시라도 열려있으면 숨기기
        updateLayer.classList.add("popup-hidden");

        /* 요소.classList.toggle("클래스명")
          - 요소에 해당 클래스가 있으면 제거
          - 요소에 해당 클래스가 없으면 추가

           요소.classList.add("클래스명")
          - 요소에 해당 클래스가 없으면 추가

           요소.classList.remove("클래스명")
          - 요소에 해당 클래스가 있으면 제거
        */


    });


};

// resp.text() : 이건 단순한 String이나 int값이 반환 받을때 사용 첫 번째 then에서만 사용 가능
// resp.json() : 이건 Todo 객체를 Object 객체로 JS에서 받을 수 있게 반환받았고 
// JSON.parse : 이건 List 반환 받아서 객체 배열할때 만들었고 -> 여러 객체들을 반환 받을 때 사용? 문자열 형태











// 비동기(ajax)로 할 일 목록을 조회하는 함수
const selectTodoList = () => {



    fetch("/ajax/selectList")
    .then(response=> response.text()) // 응답 결과를 text로 변환
    .then(result => {   // result == 첫 번쨰 then에서 반환된 결과값
        console.log(result)
        console.log(typeof result); // 타입 검사 -> String
        // 아! JSON은 객체가 아닌 문자열이다!

        // 문자열은.... 가공은 할 수 있는데 힘들다....
        // -> JSON.parse(JSON데이터) 이용

        // JSON.parse(JSON데이터) : String -> Object
        // - String 형태의 JSON데이터를 JS Object 타입으로 변환

        // JSON.stringify(JS Object) : Object -> String
        // - JS Object 타입을 String 형태의 JSON데이터로 변환

        const todoList = JSON.parse(result);

        console.log(todoList); // 객체 배열 형태 확인!

        // ------------------------------------------------

        /* 기존에 출력되어 있던 할 일 목록 모두 삭제 */
        tobdy.innerHTML = "";


        // #tbody에 tr/td 요소를 생성해서 내용 추가
        for(let todo of todoList){

            // tr태그 생성
            const tr = document.createElement("tr");

            const arr = ['todoNo', 'todoTitle', 'complete', 'regDate'];

            for(let key of arr){
                const td = document.createElement("td");

                // 제목인 경우
                if(key === 'todoTitle'){
                    const a = document.createElement("a");
                    a.innerText= todo[key];
                    a.href="/ajax/detail?todoNo=" + todo.todoNo;
                    td.append(a);
                    tr.append(td);

                    // a태그 클릭 시 기본 이벤트(페이지 이동) 막기
                    a.addEventListener("click", e=> {
                        e.preventDefault();

                        // 할 일 상세 조회 비동기 요청
                        // e.target.href : 클릭된 a태그의 href 속성 값
                        selectTodo(e.target.href);

                    });

                    continue;
                }

                td.innerText = todo[key];
                tr.append(td);

            }

            // tbody의 자식으로 tr 추가
            tobdy.append(tr);

        }
        


    })




};

// popup layer의 X버튼 클릭 시 닫기
popupClose.addEventListener("click", () =>{
    popupLayer.classList.add("popup-hidden");
})

/* 삭제 버튼 킬릭 시 */
deleteBtn.addEventListener("click", ()=>{

    // 취소 클릭 시 아무것도 안함
    if(!confirm("정말 삭제 하시겠습니까?")) return;
    // 삭제할 할 일 번호(PK) 얻어오기
    else{
        const todoNo = popupTodoNo.innerText;// #popupTodoNo 내용 얻어오기

        // 비동기 DELETE 방식 요청
        fetch("/ajax/delete", {
            method : "DELETE", // DELETE 방식 요청 -> @DeleteMapping 처리

            // 데이터 하나를 전달해도 application/json 작성
            headers : {"Content-Type" : "application/json"},
            body : todoNo // todoNo값을 body에 담아서 전달
                          // -> @RequestBody로 꺼냄
        })
        .then(resp => resp.text()) // 요청 결과를 text형태로 변환
        .then(result => {

            if(result > 0){ // 삭제 성공
                alert("삭제 성공!!!");
                
                // 상세 조회 창 닫기
                popupLayer.classList.add("popup-hidden");

                // 전체, 완료된 할 일 개수 다시 조회
                // + 할 일 목록 다시 조회
                getCompleteCount();
                getTotalCount();
                selectTodoList();
            }else{
                alert("삭제 실패...");
            }

        })
    }

});

completeConvertBtn.addEventListener("click", ()=>{
    const todoNo = popupTodoNo.innerText;
    const complete = popupComplete.innerText === 'Y' ? 'N' : 'Y';

    console.log(complete);

    const param = {"todoNo" : todoNo , "complete" : complete};

    fetch("/ajax/completeChange",{
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp => resp.text())
    .then(result =>{

        if(result > 0){
            popupComplete.innerText = complete;
            
            // getCompleteCount();
            // 서버 부하를 줄이기 위해
            const count = Number(completeCount.innerText);
            if(complete === 'Y') completeCount.innerText = count + 1;
            else                 completeCount.innerText = count - 1;

            // selectTodoList();
            const trs = document.querySelectorAll("#tbody tr");

            // 서버 과부하 줄이기 성공!!!
            trs.forEach(tr => {
                const firstId = tr.children[0].innerText;
                if(firstId === todoNo){
                    tr.children[2].innerText = tr.children[2].innerText === 'Y' ? 'N' : 'Y';
                    return;
                }
            })

            // 서버 부하 줄이기 가능!! -> 코드가 조금
            // todoNo 값 알고있음
            // 그럼 tbody의 자식 tr의 자식들중 첫자식 td의 innerText가 todoNo값인것들 체크해서 맞으면 그 tr의 3번째자식의 complete값을 바꾸면되나? 이게 되나?
            // 1. 일단 tr은 나중에 생긴건데 어떻게 접근하지?
            // 2. tbody의 자식 tr 
        }else{
            alert("변경 실패...");
        }

    })
})








updateBtn.addEventListener("click", ()=>{

    popupLayer.classList.add("popup-hidden");

    updateLayer.classList.remove("popup-hidden");

    updateTodoTitle.value = popupTodoTitle.innerText;
    updateTodoContent.value = popupTodoContent.innerHTML.replaceAll("<br>", "\n");
    // HTML 화면에서 줄 바꿈이 <br>로 인식되고 있는데
    // textarea에서는 \n으로 바꿔야 줄 바꿈으로 인식된다.

    // 수정 레이어 -> 수정 버튼에 data-todo-no 속성 추가
    updateComplete.setAttribute("data-todo-no", popupTodoNo.innerText)
})

// -----------------------------------------------------------------

/* 수정 레이어 -> 수정 버튼(#updateBtn) 클릭 시 */

updateComplete.addEventListener("click", e=>{

    // 서버로 전달해야되는 값을 객체로 묶어둠
    const param = {
        "todoNo" : e.target.dataset.todoNo,
        "todoTitle" : updateTodoTitle.value,
        "todoContent" : updateTodoContent.value
    };

    // console.log(param);
    fetch("/ajax/update", {
        method : "PUT", // 수정은 put
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp=> resp.text())
    .then(result => {
        if(result > 0){
            alert("수정 완료!!!");
            updateLayer.classList.add("popup-hidden");
            
            // selectTodo();
            // -> 성능 개선
            popupTodoTitle.innerText = updateTodoTitle.value;
            popupTodoContent.innerHTML = updateTodoContent.value.replaceAll("\n","<br>"); 

            popupLayer.classList.remove("popup-hidden");

            selectTodoList();

            updateTodoTitle.value = "";    // 남은 흔적 제거
            updateTodoContent.value = "";  // 남은 흔적 제거
            updateComplete.removeAttribute("data-todo-no"); // 속성 제거
        }else{
            alert("수정 실패...");
        }
    })


});


/* updateComplete.addEventListener("click", ()=>{
    const todoContent = updateTodoContent.value;
    const todoTitle = updateTodoTitle.value;
    const todoNo = popupTodoNo.innerText;

    const param = {"todoContent" : todoContent , "todoTitle" : todoTitle , "todoNo" : todoNo};

    fetch("/ajax/update",{
        method : "PUT",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(param)
    })
    .then(resp=> resp.text())
    .then(result => {
        if(result > 0){
            alert("수정 완료!!!");
            updateLayer.classList.add("popup-hidden");
            selectTodoList();
        }
    })
}) */

updateCancel.addEventListener("click", ()=>{
    updateLayer.classList.add("popup-hidden");

    popupLayer.classList.remove("popup-hidden");
})






















// -----------------------------------------------------------






// js 파일에 함수 호출 코드 작성 -> 페이지 로딩 시 바로 실행
getCompleteCount();
getTotalCount();
selectTodoList();














