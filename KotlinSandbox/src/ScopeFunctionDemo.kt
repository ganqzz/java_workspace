fun main(args: Array<String>) {

    data class Hoge(var x: Any? = null)

    val hoge = Hoge()

    //if (hoge.x is String) {
    //    println(hoge.x.length)
    //}
    (hoge.x as? String)?.apply {
        // this
        println("apply: $length")
    }

    val run = (hoge.x as? String)?.run {
        // this
        println("run: $length")
        1
    }

    (hoge.x as? String)?.also {
        println("also: ${it.length}")
    }

    val let = (hoge.x as? String)?.let {
        println("let: ${it.length}")
        9
    }

    val retUnit = with(hoge) {
    }

    val retInt = with(hoge) {
        this.x as Int?
    }

    val retString = with(hoge) {
        this.x as String
    }
}
