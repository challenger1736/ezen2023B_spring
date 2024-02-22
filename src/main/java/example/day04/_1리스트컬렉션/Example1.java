package example.day04._1리스트컬렉션;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Example1 {
    public static void main(String[] args) {
        // 컬렉션 프레임워크
        // 1. 컬렉션( 수집 ) , 프레임워크( 미리 만들어진 클래스/인터페이스/함수 )
        // 2. 널리 사용되는 자료구조( 데이터 저장/관리하는 방법론 ) 기반으로 제공한다.
        // 3. 대표 List , Set , Map.txt 인터페이스
        // 1. List
        // - 객체를 저장하면 인덱스가 부여되고 , 중복 저장가능
        // - ArrayList , Vector , LinkedList 클래스
            // ArrayList : [배열구조] 동기화 지원 X [동기화란? 여러 스레드가 하나의 함수를 동시에 호출했을때 대기상태 (synchronized)]
            // 그래서 주로 단일 스레드에서 사용. , 별도의 추가 라이브러리 이용하면 동기화 가능.
            // Vector : [배열구조] 동기화 지원 O , 주로 멀티 스레드에서 사용.
            // LinkedList : [연결구조] 동기화 지원 X, 별도의 추가 라이브러리 이용하면 동기화 가능.
                // 빈번한 중간/특정위치 삽입과 삭제는 링크드리스트가 좋다.
                // 순차적인 삽입 ( add ) 는 ArrayList가 빠르다.

        // List 선언하는 방법
        // E : 리스트 저장하고 싶은 객체 타입
        // 1. List<E> list = new ArrayList<>(); // 나중에 다른 구현체로 변경할 때 유연하게 대처할 수 있음. 일반적으로는 인터페이스를 사용하여 프로그래밍하는 것이 더 좋은 설계 방식으로 여겨집니다.
        // 2. List<E> list = new ArrayList<E>();
        // 3. List list = new ArrayList<>();
        // 4. ArrayList<E> list = new ArrayList<>();

        // 리스트에 관련/제공된 함수
        // 1. 리스트객체명.add( 객체 )        : 주어진 객체를 객체를 리스트내 맨 끝에 추가
        // 2. 리스트객체명.add( 인덱스 , 객체 ) : 주어진 객체를 리스트내 주어진 인덱스에 추가 [ 기존 인덱스 객체 밀려남 ]
        // 3. 리스트객체명.set( 인덱스 , 객체 ) : 주어진 객체를 리스트내 주어진 인덱스에 덮어쓰기 [ 기존 인덱스 객체 사라짐 ]
        // 4. 리스트객체명.size()             : 리스트내 저장된 객체 수를 반환
        // 5. 리스트객체명.get( 인덱스 )       : 주어진 인덱스에 저장된 객체를 반환
        // 6. 리스트객체명.contains( 객체 )     : 주어진 객체가 리스트내 저장되어 있는지 여부[T/F] 반환
        // 7. 리스트객체명.remove( 인덱스 )      : 주어진 인덱스에 저장된 객체를 삭제
        // 8. 리스트객체명.remove( 객체 )       : 주어진 객체를 삭제
        // 9. 리스트객체명.clear( )             : 리스트내 모든 객체를 삭제
        // 10. 리스트객체명.isEmpty( )          : 리스트내 비어 있는지 확인[비어있으면 T]
    // 순회
        // 일반for문
        // 향상된for문. for(타입 반복변수명 : 리스트명){실행문;}
        // 리스트.forEach(반복변수명(자동으로 리스트의 객체로 들어감) -> 실행문or{실행문; 실행문;})

//   1. 리스트 컬렉션 생성//
        List<Board> list = new ArrayList<>();
        // new Vector<>()로 바꿔도 문제 없다.!!
//   2. 리스트에 객체 추가
        list.add(new Board("제목1", "내용1", "글쓴이1"));
        list.add(new Board("제목2", "내용2", "글쓴이2"));
        Board board = new Board("제목3", "내용3", "글쓴이3");
        list.add(board);
        list.add(new Board("제목4", "내용4", "글쓴이4"));
            // add(인덱스, 객체) : 특정 인덱스에 객체 추가 가능.
        list.add(0, new Board("제목5", "내용5", "글쓴이5")); // 앞으로 들어가버리고 뒤로 하나씩 밀림.
        System.out.println(list);
        list.set(0,board);  // 이건 바꿔치기를 해버림.
        System.out.println(list);

//   3. 리스트에 저장된 총 객체 수 호출하기
        int size = list.size();
        System.out.println("size = " + size);

//        4. 리스트 내 저장된 특정 인덱스의 객체 호출하기
        Board board1 = list.get(2);
        System.out.println("2번 인덱스(3번째)의 객체 : board1" + board1);
        
//        5. 리스트 내 저장된 특정 인덱스의 객체 호출 하기
        boolean result1 = list.contains(board1);
        System.out.println("result1 = " + result1);

//        6. 리스트 내 객체 삭제
        list.remove(2);
        System.out.println(list);

        // ============= 리스트 순회 =============== //
        // 1. 일반 for문
        System.out.println("일반 For문 리스트 순회");
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
        System.out.println();

        // 2. 향상된 for문 // for ( 타입 반복변수명 : 리스트/배열 ){} : 리스트내 순차적으로 하나씩 객체를 반복변수에 대입
        System.out.println("향상된 For문 리스트 순회");
        for(Board b : list ){
            System.out.println(b);
        }
        System.out.println();

        // 3. forEach( 반복변수명(객체) -> 실행문or {실행문; 실행문;} ) //
        System.out.println("forEach 문 리스트 순회");
        list.forEach(b-> System.out.println(b));

        // ============= 리스트 순회 =============== //

//        7. 리스트 내 값 전체 삭제
        list.clear();

//        8. 리스트가 비어있는지 확인 (boolean)
        boolean empty1 = list.isEmpty();
        System.out.println("empty1 = " + empty1);

//        9. ArrayList 와 LinkedList의 차이점.
        List<String> list1 = new ArrayList<>();
        List<String> list2 = new LinkedList<>();

        long startTime;
        long endTime;

        startTime = System.nanoTime();
        for(int i =0; i<10000;i++){
            list1.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n", "ArrayList 걸린시간 : ", endTime-startTime);


        startTime = System.nanoTime();
        for(int i =0; i<10000;i++){
            list2.add(0, String.valueOf(i));
        }
        endTime = System.nanoTime();
        System.out.printf("%-17s %8d ns \n", "LinkedList 걸린시간 : ", endTime-startTime);

    }
 }
