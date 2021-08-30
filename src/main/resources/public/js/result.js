const returnToMain = document.getElementById("return-to-main");
const returnButton = document.getElementById("return-button");

returnButton.onclick = () => {
    alert("초기 화면으로 돌아갑니다");
    returnToMain.submit();
};