const startGame = document.getElementById("start-game");
const startButton = document.getElementById("start-button");

startButton.onclick = () => {
    alert("게임 시작");
    startGame.submit();
};