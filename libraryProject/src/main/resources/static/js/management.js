const searchContextBtn = document.querySelector("#searchContextBtn");
const tbody = document.querySelector("#tbody");
const inputContext = document.querySelector("#inputContext");
const updateBtn = document.querySelectorAll(".updateBtn");
const deleteBtn = document.querySelectorAll(".deleteBtn");

searchContextBtn.addEventListener("click", e=>{

    tbody.innerText = "";

    if(inputContext.value.trim().length === 0){
        e.preventDefault();
        return;
    }
    
    fetch("/library/checkContext",{
        method : "POST",
        headers : {"Content-Type":"application/json"},
        body : inputContext.value
    })
    .then(resp => resp.json())
    .then(bookList => {

        for(let book of bookList){

            const tr = document.createElement("tr");

            const arr = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate', 'update', 'delete'];

            for(let key of arr){

                const td = document.createElement("td");

                if(key === 'update'){
                    const btn  = document.createElement("button");
                    btn.innerText = "수정";
                    btn.classList.add("updateBtn")

                    btn.addEventListener("click", e=>{
                        const updatePrice = prompt("수정할 가격 입력");

                        const uncle = btn.closest("tr").children[3];
                        const uncle2 = btn.closest("tr").children[0];

                        const param = {
                            "bookNo" : uncle2.innerText,
                            "bookPrice" : updatePrice
                        }

                        fetch("/library/priceUpdate",{
                            method : "POST",
                            headers : {"Content-Type":"application/json"},
                            body : JSON.stringify(param)
                        })

                        uncle.innerText = updatePrice;
                    })

                    td.append(btn);
                    tr.append(td);
                    continue;
                }

                if(key === 'delete'){
                    const btn  = document.createElement("button");
                    btn.classList.add("deleteBtn")
                    btn.innerText = "삭제";

                    btn.addEventListener("click", e=>{

                        const uncle = btn.closest("tr").children[0];
                        fetch("/library/remove",{
                            method : "POST",
                            headers : {"Content-Type":"application/json"},
                            body : uncle.innerText
                        });
                        console.log(uncle.innerText);

                        btn.closest("tr").remove();

                    });

                    td.append(btn);
                    tr.append(td);
                    break;
                }

                td.innerText = book[key];
                tr.append(td);
            }

            console.log(book);
            tbody.append(tr);
        }

    })

});































































