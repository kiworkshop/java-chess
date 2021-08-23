# java-chess 게임

### 1 단계

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

```
RNBQKBNR  8 (rank 8)
PPPPPPPP  7
........  6
........  5
........  4
........  3
pppppppp  2
rnbqkbnr  1 (rank 1)

abcdefgh
```

***

### 2 단계

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- **`move source위치 target위치`**을 실행해 이동한다.

```
> 체스 게임을 시작합니다.
> 게임 시작 : start
> 게임 종료 : end
> 게임 이동 : move source위치 target위치 - 예. move b2 b3
start
RNBQKBNR
PPPPPPPP
........
........
........
........
pppppppp
rnbqkbnr

move b2 b3
RNBQKBNR
PPPPPPPP
........
........
........
.p......
p.pppppp
rnbqkbnr
```

***

### 3 단계

- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. **King이 잡혔을 때 게임을 종료해야 한다.**
- **체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.**
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

***

### 기능 요구사항

- [ ] 입력
  - [x] **`start`** 인 경우, 체스 게임을 시작한다.
  - [ ] **`end`** 인 경우, 체스 게임을 종료한다.
  - [ ] **`move source위치 target위치`** 인 경우, 체스 말이 이동한다.
      - [ ] target 위치가 될 수 없는 곳인 경우, 예외가 발생한다.
  - [ ] **`status`** 인 경우, 체스 게임 결과를 표출한다.
  - [x] 예외) null 인 경우, 예외가 발생한다.
  - [ ] 예외) 입력 값이 올바르지 않은 경우, 예외가 발생한다.

- [ ] 출력
  - [x] 체스 게임 규칙을 출력한다.
  - [x] 체스판을 초기화 한다.
  - [x] 체스 말이 이동한 결과를 출력한다.
  - [ ] **`status`** 를 입력받은 경우, 각 진영의 점수를 출력한다.
  - [ ] 게임이 종료된 경우, 어느 진영이 이겼는지 출력한다.

- [ ] 체스 게임 - ChessGame
  - [x] 게임을 준비한다.
    - [x] 체스판을 초기화 한다.
      - [x] 체스판에서 말의 초기 위치 (가로: 왼쪽부터 a ~ h / 세로: 아래부터 위로 1 ~ 8)
  - [x] 체스 게임을 시작한다.
    - [x] 흑/백을 번갈아가며 체스 말을 이동시킨다.
  - [ ] 체스 게임을 종료한다.
      - [ ] 현재 남아 있는 말에 대한 점수를 계산한다.
      - [ ] 상대편 King이 잡히는 경우, 게임이 종료된다.

- [x] 체스판 - Board
  - [x] 초기 체스판을 세팅한다.
    - [x] 64개의 체스 위치를 생성한다.
    - [x] 체스 말을 초기 위치에 놓는다. Piece -> position, team

- [x] 위치 - Position
  - [x] 8개의 가로 줄인 file
  - [x] 8개의 세로 줄인 rank
  - [x] 예외) 체스 판에 존재하지 않는 위치인 경우 예외가 발생한다.

- [x] 진영 - Team
  - [x] 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

- [x] 턴 - Turn
  - [x] 체스 말을 이동시키면 턴이 전환된다.

- [ ] 체스 말 - Piece
  - [x] 킹
    - [x] 초기 위치 : 5번째 file, (1,8)번째 rank
    - [x] 이동 규칙
      - [x] 종・횡의 방향 및 사선(대각선) 방향으로 1칸 이동할 수 있다.
      - [x] 체크메이트 되는 위치로는 이동할 수 없다.
  - [x] 퀸
    - [x] 초기 위치 : 4번째 file, (1,8)번째 rank
    - [x] 이동 규칙 : 종・횡의 방향 및 사선(대각선) 방향으로 몇 칸이든 이동할 수 있다. 
  - [x] 룩
    - [x] 초기 위치 : (1,8)번째 file, (1,8)번째 rank
    - [x] 이동 규칙 : 종・횡의 방향으로 몇 칸이든 이동할 수 있다.
  - [x] 비숍
    - [x] 초기 위치 : (3,4)번째 file, (1,8)번째 rank
    - [x] 이동 규칙 : 사선(대각선) 방향으로 몇 칸이든 이동할 수 있다.
  - [x] 나이트
    - [x] 초기 위치 : (2,7)번째 file, (1,8)번째 rank
    - [x] 이동 규칙 : Y 방향(L모양)으로 다른 체스말을 뛰어넘어 이동할 수 있다.
  - [x] 폰
    - [x] 초기 위치 : (1~8)번째 file, (2, 7)번째 rank
    - [x] 이동 규칙  
      - [x] 1칸씩 이동할 수 있다. 
      - [x] 첫 수인 경우 2칸을 이동할 수 있다.
      - [x] 대각선 1칸 앞에 적의 기물이 있는 경우 기물을 뺏고 그 자리로 이동할 수 있다.

- [ ] 이동 규칙 - Movement
  - [x] 체스판에 체스 말이 없는 경우 움직일 수 없다.
  - [x] 아군이 있는 칸에는 이동할 수 없다.
  - [ ] 나이트가 아니면 다른 체스 말을 뛰어넘을 수 없다.
  - [x] 이동 규칙을 통해, 이동 가능한 위치를 반환한다.
  - [ ] 입력 받은 위치로 이동 가능한지 판단한다.
    - [x] 입력 받은 위치로 이동 가능하면, 이동한다.