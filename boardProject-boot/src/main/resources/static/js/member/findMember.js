// 아이디 찾기 form 태그
const findId = document.querySelector("#findId");

if(findId) {

  const memberNickname = document.querySelector("#memberNickname");
  const memberTel = document.querySelector("#memberTel");
  const memberId = document.querySelector("#memberId");

  const idBtn = findId.lastElementChild;

  idBtn.addEventListener("click", async () => {

    if(memberNickname.value.trim().length === 0) {
      alert("닉네임을 입력하세요.");
      return;
    }
    if(memberTel.value.trim().length === 0) {
      alert("전화번호를 입력하세요.");
      return;
    }

    const member = {
      "memberNickname" : memberNickname.value,
      "memberTel" : memberTel.value
    };

    const resp = await fetch("/member/findId", {
      method: "post",
      headers: {"Content-Type" : "application/json"},
      body: JSON.stringify(member)
    });
    const resultId = await resp.text();

    if(resultId === "") {
      alert("해당 아이디가 없습니다.");
      return;
    }

    alert("아이디를 찾았습니다!");
    memberId.value = resultId;
  });
}

// 비밀번호 찾기 form 태그
const findPw = document.querySelector("#findPw");
const inputPw = document.querySelector("#inputPw");

if(findPw) {

  const memberNickname = document.querySelector("#memberNickname");
  const memberTel = document.querySelector("#memberTel");
  const memberEmail = document.querySelector("#memberEmail");

  const pwBtn = findPw.lastElementChild;

  pwBtn.addEventListener("click", async () => {

    if(memberNickname.value.trim().length === 0) {
      alert("닉네임을 입력하세요.");
      return;
    }
    if(memberTel.value.trim().length === 0) {
      alert("전화번호를 입력하세요.");
      return;
    }
    if(memberEmail.value.trim().length === 0) {
      alert("이메일을 입력하세요.");
      return;
    }

    const member = {
      "memberNickname" : memberNickname.value,
      "memberTel" : memberTel.value,
      "memberEmail" : memberEmail.value
    };

    const resp = await fetch("/member/findPw", {
      method: "post",
      headers: {"Content-Type" : "application/json"},
      body: JSON.stringify(member)
    });
    const result = await resp.text();

    if(result == 0) {
      alert("존재하지 않는 회원입니다.");
      return;
    }

    alert("변경할 비밀번호를 입력해주세요.");
    inputPw.classList.remove("hidden");
  });

}

if(inputPw) {

  
}