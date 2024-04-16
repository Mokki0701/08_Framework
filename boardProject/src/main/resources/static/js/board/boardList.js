/* 글쓰기 버튼 클릭 시 */

const insertBtn = document.querySelector("#insertBtn");

if(insertBtn != null){

    insertBtn.addEventListener("click", e=>{

        /* boardCode 얻어오는 방법 */
        // 1) @PathVariable("boardCode") 얻어와 전역 변수 저정
        // 2) location.pathname.split("/")[2]

        // get 방식 요청
        location.href = `/editBoard/${boardCode}/insert`;
    })

}






















