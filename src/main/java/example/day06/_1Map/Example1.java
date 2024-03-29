package example.day06._1Map;

import java.util.*;

public class Example1 {
    public static void main(String[] args) {

        /*  Map 컬렉션
            - key와 value 구성된 엔트리(객체) 를 저장
            - key는 중복 불가능
            - MAP 인터페이스
                - 구현클래스 :
                        HashMap   : 동기화X    ,
                        Hashtable : 동기화O    , 멀티스레드 쓸 때 동기화하는 놈들 권장(List 인터페이스 의 VECTOR같은)
                        TreeMap
                - 선언방법
                    Map.txt<K,V> map = new HashMap<>();
                - 사용방법/메소드
                    .put(key, value)        : 엔트리 추가
                    .get(key)               : 주어진 key 이용한 value 호출
                    .remove(key)            : 주어진 key의 해당하는 엔트리 삭제
                    .keySet()               : 전체 키를 set 컬렉션 반화
                    .entrySet()             : 전체 엔트리 반환
                    .values()               : 전체 값을 컬렉션 반환
                    .clear()                : 전체 엔트리 삭제
                    .size()                 : 전체 엔트리 개수 반환

         */
//        1. MAP 컬렉션 생성
        Map<String, Integer> map = new HashMap<>();
//      2. 컬렉션 객체에 객체(엔트리= key, value) 넣기 / 객체안에 객체 넣는것임 결국엔.
        map.put("신용권", 85);
        map.put("홍길동", 90);
        map.put("동장군", 80);
        map.put("홍길동", 95); // key 중복일 경우 : 새로운 값을 대치
        System.out.println("map = " + map);

        // 3. 키로 값 얻기
        System.out.println(map.get("홍길동"));

        // - 순회 : 인덱스 없으므로 일반 포문 안됨
            // keySet() : 모든 키를 set 컬렉션을 반환
            // keySet() : 모든 키를 set 컬렉션을 반환
                // 1.
                Set<String> keySet = map.keySet();
                for(String key :keySet){
                    System.out.println("key = " + key);
                    System.out.println("values = " + map.get(key));
                }
                // 2.
                Set<Map.Entry<String,Integer>> entrySet = map.entrySet();
                System.out.println("entrySet = " + entrySet);
                Iterator<Map.Entry<String,Integer>> entryIterator = entrySet.iterator();
                while(entryIterator.hasNext()){
                    Map.Entry<String,Integer> entry = entryIterator.next();
                    System.out.println(entry);
                    System.out.println(entry.getKey()); //엔트리명.getKey or 맵.key(키명)
                    System.out.println(entry.getValue()); // 엔트리명.getValue
                }
                //3.
                for(Map.Entry<String,Integer> entry : map.entrySet()){
                    System.out.println(entry);
                }
                //4.
                for (Integer value : map.values()){
                    System.out.println("value = " + value);
                }
                System.out.println("총 엔트리 개수" + map.size());
                //5.
                map.keySet().forEach(key-> System.out.println(key +"="+ map.get(key)));
                map.entrySet().forEach(entry-> System.out.println(entry));

               


        // 4. 키로 엔트리 삭제하기
        map.remove("홍길동");
        System.out.println("map = " + map);


        //===============================================

        //1.Properties 객체
            // - String으로 정해진 Map
        Properties properties = new Properties();
        //2.
        properties.setProperty("driver","com.mysql.jdbc.cj.Driver");
        properties.setProperty("URL", "jdbc.mysql://localhost:3306/springweb");
        properties.setProperty("admin", "root");
        properties.setProperty("password", "1234");
        //3.
        System.out.println(properties.getProperty("driver")); // 하면 com.mysql.jdbc.cj.Driver가 나옴.
        System.out.println(properties.getProperty("URL"));
        System.out.println(properties.getProperty("admin"));
        System.out.println(properties.getProperty("password"));
    }
}
