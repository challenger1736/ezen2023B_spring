package example.day05._1SET컬렉션;

import lombok.ToString;

@ToString
public class Member {
    public String name;
    public int age;

    public Member(String name, int age){
        this.name = name;
        this.age = age;
    }
// hashCode 는 오브젝트에서 인트로 주소를 반환해주는 놈,,
    @Override // 재정의
    public int hashCode() {
        return name.hashCode() + age;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Member target){ // 매개변수의 객체가 Member 타입이면
//            Member target = (Member) obj; // 원시적 사용 왜?? 멤버필드 쓰려고
            return target.name.equals(name) && target.age == age;
        }else {
        return false;}
    }
}
