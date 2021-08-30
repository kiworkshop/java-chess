const nameSymbolMap = new Map(
    [
        ["k", "♔"],
        ["q", "♕"],
        ["r", "♖"],
        ["b", "♗"],
        ["n", "♘"],
        ["p", "♙"],

        ["K", "♚"],
        ["Q", "♛"],
        ["R", "♜"],
        ["B", "♝"],
        ["N", "♞"],
        ["P", "♟"],

        [".", ""]
    ]
);

window.onload = function () {
        document.querySelectorAll(".cell")
            .forEach(element => {
                    element.innerText = nameSymbolMap.get(element.innerText);
            });
}