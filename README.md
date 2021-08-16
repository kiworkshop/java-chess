# java-chess 게임

### 1 단계

- 콘솔 UI에서 체스 게임을 할 수 있는 기능을 구현한다.
- 1단계는 체스 게임을 할 수 있는 체스판을 초기화한다.
- 체스판에서 말의 위치 값은 가로 위치는 왼쪽부터 a ~ h이고, 세로는 아래부터 위로 1 ~ 8로 구현한다.
- 체스판에서 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

***

### 2 단계

- 체스 말의 이동 규칙을 찾아보고 체스 말이 이동할 수 있도록 구현한다.
- **`move source위치 target위치`**을 실행해 이동한다.

***

### 3 단계

- 체스 게임은 상대편 King이 잡히는 경우 게임에서 진다. **King이 잡혔을 때 게임을 종료해야 한다.**
- **체스 게임은 현재 남아 있는 말에 대한 점수를 구할 수 있어야 한다.**
- "status" 명령을 입력하면 각 진영의 점수를 출력하고 어느 진영이 이겼는지 결과를 볼 수 있어야 한다.

***

### 기능 요구사항

- [ ] 입력
  - [ ] **`start`** 인 경우, 체스 게임을 시작한다.
  - [ ] **`end`** 인 경우, 체스 게임을 종료한다.
  - [ ] **`move source위치 target위치`** 인 경우, 기물이 이동한다.
    - [ ] target 위치가 될 수 없는 곳인 경우, 예외가 발생한다.
  - [ ] **`status`** 인 경우, 체스 게임 결과를 표출한다.
  - [ ] 예외) null 인 경우, 예외가 발생한다.

- [ ] 출력
  - [ ] 체스 게임 규칙을 출력한다.
  - [ ] 체스판을 초기화 한다.˚
  - [ ] 기물이 이동한 결과를 출력한다.
    - [ ] 각 진영의 점수를 출력한다.
    - [ ] 어느 진영이 이겼는지 출력한다.

- [ ] 체스 게임 - ChessGame
  - [ ] 체스판을 초기화 한다.
    - [ ] 체스판에서 말의 위치 - (가로: 왼쪽부터 a ~ h / 세로: 아래부터 위로 1 ~ 8)
  - [ ] 기물을 이동시킨다.
  - [ ] 상대편 King이 잡히는 경우 게임이 종료된다.
  - [ ] 현재 남아 있는 말에 대한 점수를 계산한다.

- [ ] 체스판 - ChessBoard
  - [ ] 초기 체스판을 세팅한다.
    - [x] 64개의 체스 위치를 생성한다.
    - [ ] 기물을 초기 위치에 놓는다.

- [ ] 위치 - Position
  - [x] 8개의 세로 줄인 file(열)
  - [x] 8개의 가로 줄인 rank(행)

- [ ] 진영 - Player
  - [ ] 각 진영은 검은색(대문자)과 흰색(소문자) 편으로 구분한다.

- [ ] 기물 - Piece
    - [ ] 킹
    - [ ] 퀸
    - [ ] 룩
    - [ ] 비숍
    - [ ] 나이트
    - [ ] 폰

- [ ] 이동 규칙 - Movement
    - [ ] 이동 규칙을 통해, 이동 가능한 위치를 반환한다.
    - [ ] 입력 받은 위치로 이동 가능한지 판단한다.
        - [ ] 입력 받은 위치로 이동 가능하면, 이동한다.