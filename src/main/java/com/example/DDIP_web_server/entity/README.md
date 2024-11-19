
# MySQL(DB 정보)
## 사용자&테이블 생성
```
CREATE DATABASE ddip_db;
CREATE USER 'DDIP'@'localhost' IDENTIFIED BY 'DDIP';
GRANT ALL PRIVILEGES ON ddip_db.* TO 'DDIP'@'localhost';
FLUSH PRIVILEGES;
```

## 사용자&테이블 삭제
```
DROP DATABASE ddip_db;
DROP USER 'DDIP'@'localhost';
```

## 테이블 형식
### Member
```
CREATE TABLE Member (
    id VARCHAR(50) NOT NULL PRIMARY KEY,  -- 회원 ID (Primary Key)
    name VARCHAR(50) NOT NULL,           -- 회원 이름
    password VARCHAR(100) NOT NULL,      -- 비밀번호
    email VARCHAR(100) NOT NULL UNIQUE,  -- 이메일 (중복 불가)
    contact_number VARCHAR(20),          -- 연락처
    user_type VARCHAR(100) NOT NULL UNIQUE  -- 회원 유형 (사장/알바생 구분)
);
```
### CrewRoom
```
CREATE TABLE CrewRoom (
    crew_room_id INT AUTO_INCREMENT PRIMARY KEY,  -- 크루룸 ID (Primary Key)
    crew_room_name VARCHAR(100) NOT NULL,          -- 크루룸명
    shop_name VARCHAR(100) NOT NULL,               -- 업장명
    owner_id VARCHAR(50) NOT NULL,                 -- 사장님 ID (Member 테이블의 Foreign Key)
    crew_room_invitation VARCHAR(50),              -- 크루룸 초대코드
    createdAt datetime,							   -- 크루룸 시간
    FOREIGN KEY (owner_id) REFERENCES Member(id)   -- Member 테이블과 연관 관계
);
```
### CrewRoomMember
```
CREATE TABLE CrewRoomMember (
    crew_room_member_id INT AUTO_INCREMENT PRIMARY KEY,  -- 소속 번호 (Primary Key)
    crew_room_id INT NOT NULL,                            -- 크루룸 ID (CrewRoom 테이블의 Foreign Key)
    member_id VARCHAR(50) NOT NULL ,                      -- 회원 ID (Member 테이블의 Foreign Key)
    color VARCHAR(20),                                    -- 알바생 고유 색상
    start_date DATE,                                      -- 알바 시작일
    contact_number VARCHAR(20),                           -- 연락처 
    member_type VARCHAR(50) NOT NULL ,           -- 회원 유형
    FOREIGN KEY (crew_room_id) REFERENCES CrewRoom(crew_room_id),
    FOREIGN KEY (member_id) REFERENCES Member(id)         -- Member 테이블과 연관 관계
);
```

### CrewRoomSchedule
```
CREATE TABLE CrewRoomSchedule (
    schedule_id INT AUTO_INCREMENT PRIMARY KEY,  -- 근무 일정 ID
    crew_room_id INT NOT NULL,                   -- 크루룸 ID
    member_id INT NOT NULL,                      -- 알바생 ID (Member 테이블과 연관)
    start_time TIME NOT NULL,                    -- 근무 시작 시간
    end_time TIME NOT NULL,                      -- 근무 종료 시간
    date DATE NOT NULL,                          -- 근무 날짜
    total_hours TIME,                            -- 총 근무 시간
    pay INT NOT NULL,                            -- 시급
    status VARCHAR(50) NOT NULL,  -- 일정 상태
    FOREIGN KEY (crew_room_id) REFERENCES CrewRoom(crew_room_id),
    FOREIGN KEY (member_id) REFERENCES Member(id)
);
```

### CrewRoomLog
```
CREATE TABLE CrewRoomLog (
    log_id INT AUTO_INCREMENT PRIMARY KEY,          -- 로그 ID
    crew_room_id INT,                               -- 크루룸 ID
    action VARCHAR(20) NOT NULL,  -- 변경 유형
    schedule_id INT,                                -- 변경된 일정 ID
    exchange_id INT,                                -- 교환 ID
    previous_member_id INT,                         -- 이전 알바생 ID
    new_member_id INT,                              -- 새로운 알바생 ID
    change_time DATETIME DEFAULT NOW(),             -- 변경 시간
	change_date DATE NOT NULL,                     -- 교환 날짜
    change_start_time TIME NOT NULL,               -- 교환 시작 시간
    change_end_time TIME NOT NULL,                 -- 교환 종료 시간
    description VARCHAR(255),                       -- 변경 내용 설명
    FOREIGN KEY (crew_room_id) REFERENCES CrewRoom(crew_room_id),
    FOREIGN KEY (schedule_id) REFERENCES CrewRoomSchedule(schedule_id),
    FOREIGN KEY (exchange_id) REFERENCES CrewRoomExchange(exchange_id),
    FOREIGN KEY (previous_member_id) REFERENCES Member(id),
    FOREIGN KEY (new_member_id) REFERENCES Member(id)
);
```
