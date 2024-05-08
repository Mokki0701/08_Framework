const studentAdd = document.querySelector("#studentAdd").addEventListener("click", e=>{

    const studentName = document.querySelector("#studentName").value;
    const studentMajor = document.querySelector("#studentMajor").value;
    const studentGenders = document.querySelectorAll(".studentGender");

    let studentGender = "";

    for(let i of studentGenders){

        if(i.checked) studentGender = i.value;
    }

    console.log(studentName);
    console.log(studentMajor);
    console.log(studentGender);

    const arr = {
        "studentName" : studentName,
        "studentMajor" : studentMajor,
        "studentGender" : studentGender
    }

    fetch("/student/add",{
        method : "POST",
        headers : {"Content-Type" : "application/json"},
        body : JSON.stringify(arr)
    })
    .then(resp => resp.text())
    .then(result => {

        if(result > 0){
            alert("추가 성공!!!");
        }
        else{
            alert("추가 실패...");
        }
        
    })



});











