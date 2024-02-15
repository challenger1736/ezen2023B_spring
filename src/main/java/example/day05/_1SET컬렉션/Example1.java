package example.day05._1SET컬렉션;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Example1 {
    public static void main(String[] args) {

        /*
        set 컬렉션
             - 저장 순서/ 인덱스 유지되지 않는다. (꺼내는 순서가 바뀔 수도 있다.)
             - 중복 저장할 수 없다. null 하나만 가능하다.
             *
             기본 중복검사 방식 : hashCode() --t--> equals() --t--> 중복
                          주소값을 인트로 나오게 --> 같으면 equals를 실행해서 기준잡는다.
             -  단일 데이터 중복 검사 문제없음.
             -  but 여러개 데이터로 구성된 객체들의(예를들어 DTO) 중복검사 기준 [기준값이 무조건 주소값]
                    hashCode(), equals() 직접 재정의를 해야한다. (자기들만의, 프로젝트만의 기준들을 세운다.)

        set 인터페이스
             1. 구현 클래스
                HashSet
             2. 사용방법/메소드 (get은 없음! 인덱스로 부를수가 없기때문에.)
                .add
                .size()
                .iterator()
                     Iterator<E> 객체명 = set.iterator()
                        객체명.hasNext(); : 다음에 가져올 객체 boolean / 존재하면 true 아니면 false
                        객체명.next();    : 다음에 가져올 객체 불러오기
                        객체명.remove()    : 가져온 객체를 삭제한다
                .isEmpty()      : set 컬렉션이 비어 있는지 확인[T/F]
                .clear()        : set 컬렉션내 저장된 모든 객체를 삭제
                .remove(객체)    : 주어진 객체를 set 컬렉션내 삭제
                .contains(객체)  : 주어진 객체가 set 컬렉션내 있나 없나 확인[T/F]

        선언
            Set<E> 컬렉션명 = new 구현클래스<>();
            E : 컬렉션에 저장할 객체의 타입

        순회
            1. iterator() 이용한 방법
             Iterator<String> rs = set.iterator(); // Iterator뜻은 반복자, 반복요소 쓰려고 부르는 인터페이스
                while(rs.hasNext()){
                    System.out.println("rs.next() : " + rs.next());

                }
            2. 향상된 for문
                (for String s : set ) {실행문;}
            3. forEach() 함수
                set.forEach(s -> 실행문);
        */
        Set<String> set = new HashSet<String>();

        set.add("Java");
        set.add("JDBC");
        set.add("JSP");
        set.add("Java"); // 중복 발생. : 중복 객체이므로 저장하지 않음. (리터럴이라서 new String("Java")면 서로 다르다.)
        set.add("Spring");

        System.out.println("set = " + set);

        int size = set.size();

        System.out.println("총 객체 수 : " +size);

        // 4. 순회
            // 인덱스가 없으므로 일반 for문을 사용못 함.
            // 1. 향상된 for문
        for(String s : set){
            System.out.println("s = " + s);
        }
            // 2.
        Iterator<String> rs = set.iterator(); // Iterator뜻은 반복자, 반복요소 쓰려고 부르는 인터페이스
        while(rs.hasNext()){
            System.out.println("rs.next() : " + rs.next());

        }
            // 3. 컬렉션객체.forEach함수(반복변수명 -> 실행문);
                // - iterator 지원한 객체만 가능.
        set.forEach(s-> System.out.println("s = " +s));

        // ----------------------------------------------- //
        Set<Member> set2 = new HashSet<>();
        set2.add(new Member("홍길동",30));
        set2.add(new Member("홍길동",30));
        System.out.println("set2 = " + set2);
        System.out.println("set2.hashCode() = " + set2.hashCode());
        System.out.println("총 객체 수 : " +set2.size());

    }
}
