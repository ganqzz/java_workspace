package demo.annotation;

@Doc(desc = "Hoge class", params = {})
public class Hoge {

    @Doc(desc = "Return hoge", params = {}, returnVal = "hoge")
    public String hoge(String hoge) {
        return hoge;
    }

    @Doc(desc = "fuga")
    public void fuga() {}
}
