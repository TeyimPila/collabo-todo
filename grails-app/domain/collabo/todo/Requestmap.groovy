package collabo.todo

class Requestmap {

    String url
    String configAttribute

    static def constraints = {
        url(blank: false, unique: true)
        configAttribute(blank: false)
    }
}
