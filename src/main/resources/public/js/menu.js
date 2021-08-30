const endGame = document.getElementById("end-game");
const endButton = document.getElementById("end-button");
const status = document.getElementById("status");
const statusButton = document.getElementById("status-button");

endButton.onclick = () => {
    alert("게임 종료");
    endGame.submit();
};

statusButton.onclick = () => {
    alert("현재 스코어");
    status.submit();
};