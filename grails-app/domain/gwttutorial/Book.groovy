package gwttutorial

class Book {

    String title

    static belongsTo = [author: Author];

    static constraints = {
    }
}
