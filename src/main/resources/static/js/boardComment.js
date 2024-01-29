console.log("boardComment js In~");
console.log(bnoVal);

document.getElementById("cmtPostBtn").addEventListener('click', (e)=>{
    console.log(e.target);
    let cmtText = document.getElementById('cmtText');
    console.log(cmtText);
    if(cmtText.value == null || cmtText.value == ''){
        cmtText.focus();
        alert("댓글을 입력해주세요")
    }else{
        const cmtData = {
            bno : bnoVal,
            writer: document.getElementById('cmtWriter').innerText,
            content:cmtText.value
        };
        console.log(cmtData);
        cmtPostToServer(cmtData).then(result=>{
            if(result === "1"){
                alert("댓글 등록 성공")
                cmtText.value = '';
                spreadCommentList(bnoVal);
            }
        })
    }

})

async function cmtPostToServer(cmtData){
    try {
        const url = "/comment/post";
        const config = {
            method : "post",
            headers : {
                "content-type" : "application/json; charset=utf-8"
            },
            body : JSON.stringify(cmtData)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result
    } catch (error) {
        console.log(error)
    }
}

async function getCommentListFromServer(bno, page){
    try{
        const resp = await fetch("/comment/"+bno+"/"+page);
        const result = await resp.json();
        console.log("list", result);
        return result;
    }catch(error){
        console.log(error);
    }

}

function spreadCommentList(bno, page=1){
    getCommentListFromServer(bno, page).then(result=>{
        console.log(result.cmtList);
        const ul = document.getElementById('cmtListArea');
        if(result.cmtList.length > 0){ //ph.cmtList
            if(page == 1){ //1page에서만 댓글 내용 지우기
                ul.innerHTML = ''; //기존 html 지우기
            }
            for(let cvo of result.cmtList){
                let li = `<li data-cno=${cvo.cno} class="list-group-item">`;
                li += `<div class="ms-2 me-auto">`;
                li += `<div class="fw-bold">${cvo.writer}</div>`
                li += `${cvo.content}`;
                li += `</div>`
                li += `<span class="badge rounded-pill text-bg-warning">${cvo.modAt}</span>`;
                li += `<button type="button" class="btn btn-outline-warning mod" data-bs-toggle="modal" data-bs-target="#myModal">>수정</button>`
                li += `<button type="button" class="btn btn-outline-danger del">삭제</button>`
                li += `</li>`;
                ul.innerHTML += li;
            }
            //page 처리
            let moreBtn = document.getElementById('moreBtn');
            //현재 페이지 번호가 전체 페이지 번호보다 작다면
            //아직 나와야 할 페이지가 더 있다면
            if(result.pgvo.pageNo < result.endPage){
                //숨김 속성 해지
                moreBtn.style.visibility = 'visible';  
                //페이지 +1
                moreBtn.dataset.page = page + 1;
            }
        }else{
            li = `<li class="list-group-item">댓글이 없습니다..</li>`
            ul.innerHTML = li;
        }

    })
}

document.addEventListener('click',(e)=>{
    if(e.target.classList.contains('mod')){ //댓글 수정 버튼
        let li = e.target.closest('li');
    
        let cmtText = li.querySelector('.fw-bold').nextSibling; //그 다음 꺼
        console.log(cmtText);

        document.getElementById('cmtTextMod').value = cmtText.nodeValue;
        document.getElementById('cmtModBtn').setAttribute('data-cno', li.dataset.cno);

    }else if(e.target.id == 'cmtModBtn'){   //모달창 안 수정 버튼
		let cmtDataMod = {
			cno: e.target.dataset.cno,
			content:document.getElementById('cmtTextMod').value
		}
        editCommentToServer(cmtDataMod).then(result=>{
            if(result === "1"){
                alert("댓글 수정 완료.")
                //모달창 닫기
                document.querySelector(".btn-close").click(); 
            }
            spreadCommentList(bnoVal);
        })
    }else if(e.target.classList.contains('del')){//댓글 삭제 버튼
    	let li = e.target.closest('li');
        let cno = li.dataset.cno;
        console.log(cno);
        if(confirm("삭제 하실 ?")){
            deleteCommentToServer(cno).then(result=>{
                if(result === "1"){
                    alert("삭제 성공");
                    spreadCommentList(bnoVal);   
                }
            })
        }
    }else if(e.target.id == 'moreBtn'){
        spreadCommentList(bnoVal, parseInt(e.target.dataset.page))
    }
})

async function editCommentToServer(cmtDataMod){
    try {
        const url = "/comment/edit";
        const config = {
            method : "put",
            headers : {
                'content-type':'application/json; charset=utf-8'
            },
            body:JSON.stringify(cmtDataMod)
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

async function deleteCommentToServer(cno){
	try {
        const url = "/comment/"+cno;
        const config = {
            method:"delete"
        }
        const resp = await fetch(url, config);
        const result = await resp.text();
        return result;
    } catch (error) {
        console.log(error);
    }
}

