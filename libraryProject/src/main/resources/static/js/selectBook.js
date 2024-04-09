const selectBookBtn = document.querySelector("#selectBookBtn");
const tbody = document.querySelector("#tbody");

selectBookBtn.addEventListener("click", e=>{

    tbody.innerText = "";
    

    fetch("/library/select")
    .then(resp=>resp.json())
    .then(bookList => {

        for(let book of bookList){

            const tr = document.createElement("tr");

            const arr = ['bookNo', 'bookTitle', 'bookWriter', 'bookPrice', 'regDate']

            for(let key of arr){

                const td = document.createElement("td");

                td.innerText = book[key];

                tr.append(td);
                console.log(typeof(book[key]));
            }

            tbody.append(tr);

        }

    })


})










