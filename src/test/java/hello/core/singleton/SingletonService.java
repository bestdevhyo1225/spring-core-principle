package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 딱 1개만 생성해둔다.
    private static final SingletonService instance = new SingletonService();

    // 생성자를 private로 선언한다. -> 외부에서 new 키워드를 사용한 객체 생성을 막기 위함
    private SingletonService() {
    }


    // 객체 인스턴스가 필요하면, 해당 static 메소드를 통해서 조회하도록 허용한다.
    public static SingletonService getInstance() {
        return instance;
    }

    public void print() {
        System.out.println("싱글톤 객체 호출");
    }
}
