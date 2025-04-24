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
    })
    const resultId = await resp.text();

    if(resultId === "") {
      alert("해당 아이디가 없습니다.");
      return;
    }

    memberId.value = resultId;
  });
}

// 기존 아이디 확인 form 태그
const checkId = document.querySelector("#checkId");

if(checkId) {


}