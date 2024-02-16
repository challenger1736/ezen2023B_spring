package example.day07._1트리컬렉션;

public class Person implements Comparable<Person>{ // Example3과 연계
    public String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public int compareTo(Person o) {
//         1. 나이 순으로 정렬하기
//        if(this.age>o.age){return -1;}
//        else if (this.age == o.age) {
//            return 0;
//        }else {return 1;}

        // 2. name으로 하면 String이라 compareTo 자바에서 미리해놓은 compareTo로 써서 비교 가능.
        // 자바가 미리 compareTo 안만들어 놓은것들은 우리가 만들어야함.
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString() {
        // * 같으면 0, 적으면 -1, 크면 1
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
