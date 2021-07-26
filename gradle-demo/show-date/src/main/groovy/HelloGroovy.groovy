class HelloGroovy {
    void doSomething(Closure closure) {
        closure.call()
    }

    static void main(String... args) {
        def myObject = new HelloGroovy()
        myObject.doSomething {
            println new Date()
        }
    }
}
